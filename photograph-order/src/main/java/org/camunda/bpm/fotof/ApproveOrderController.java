package org.camunda.bpm.fotof;

import org.camunda.bpm.engine.cdi.BusinessProcess;
import org.camunda.bpm.engine.delegate.DelegateExecution;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

@Named
@ConversationScoped
public class ApproveOrderController implements Serializable {

	private static final long serialVersionUID = 1L;

	// Inject the BusinessProcess to access the process variables
	@Inject
	private BusinessProcess businessProcess;

	// Inject the EntityManager to access the persisted order
	@PersistenceContext
	private EntityManager entityManager;

	// Inject the OrderBusinessLogic to update the persisted order
	@Inject
	private OrderBusinessLogic orderBusinessLogic;

	// Caches the OrderEntity during the conversation
	private OrderEntity orderEntity;

	public OrderEntity getOrderEntity() {
		if (orderEntity == null) {
			// Load the order entity from the database if not already cached
			orderEntity = orderBusinessLogic.getOrder((Long) businessProcess.getVariable("orderId"));
		}
		return orderEntity;
	}

	public void submitForm() throws IOException {
		// Persist updated order entity and complete task form
		orderBusinessLogic.mergeOrderAndCompleteTask(orderEntity);
	}

	public OrderEntity getOrder(Long orderId) {
		// Load order entity from database
		return entityManager.find(OrderEntity.class, orderId);
	}

	public void calculateOrder(DelegateExecution delegateExecution) throws IOException {
		OrderEntity order = getOrder((Long) delegateExecution.getVariable("orderId"));
		Map<String, Object> variables = delegateExecution.getVariables();
		double totalCost = 0.0;

		if (order == null)
			return;

		if (order.getShootingLocation().equals("InStudio"))
			totalCost = 100;
		else if (order.getShootingLocation().equals("OnLocation"))
			totalCost = 150;

		order.setTotalCost(totalCost);
		
		entityManager.persist(order);
		entityManager.flush();

		// Remove no longer needed process variables
		delegateExecution.removeVariables(variables.keySet());

		// Add newly created order id as process variable
		delegateExecution.setVariable("orderId", order.getId());
		
	}
}