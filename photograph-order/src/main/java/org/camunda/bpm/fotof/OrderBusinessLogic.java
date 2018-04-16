package org.camunda.bpm.fotof;

import org.camunda.bpm.engine.cdi.jsf.TaskForm;
import org.camunda.bpm.engine.delegate.DelegateExecution;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@Named
public class OrderBusinessLogic {

	// Inject the entity manager
	@PersistenceContext
	private EntityManager entityManager;
	// Inject task form available through the Camunda cdi artifact
	@Inject
	private TaskForm taskForm;
	private static Logger LOGGER = Logger.getLogger(OrderBusinessLogic.class.getName());
	public void persistOrder(DelegateExecution delegateExecution) {
		// Create new order instance
		OrderEntity orderEntity = new OrderEntity();

		// Get all process variables
		Map<String, Object> variables = delegateExecution.getVariables();

		// Set order attributes customer data
		orderEntity.setFullName((String) variables.get("fullName"));
		orderEntity.setPhoneNumber((String) variables.get("phoneNumber"));
		orderEntity.setEmail((String) variables.get("email"));
		orderEntity.setShippingAddress((String) variables.get("shippingAddress"));
		
		// Set order attributes booking data
		orderEntity.setSessionStart((String) variables.get("sessionStart"));
		orderEntity.setTimeOfDay((String) variables.get("timeOfDay"));
		orderEntity.setShootingType((String) variables.get("shootingType"));
		orderEntity.setShootingLocation((String) variables.get("shootingLocation"));
		
		/*
		 * Persist order instance and flush. After the flush the id of the order
		 * instance is set.
		 */
		entityManager.persist(orderEntity);
		entityManager.flush();

		// Remove no longer needed process variables
		delegateExecution.removeVariables(variables.keySet());

		// Add newly created order id as process variable
		delegateExecution.setVariable("orderId", orderEntity.getId());
	}
	
	public void updateOrder(DelegateExecution delegateExecution) {
		Map<String, Object> variables = delegateExecution.getVariables();
		OrderEntity orderEntity = getOrder((Long) delegateExecution.getVariable("orderId"));
		orderEntity.setAep1Info((String) variables.get("aep1Info"));
		orderEntity.setAep2Info((String) variables.get("aep2Info"));
		orderEntity.setAep3Info((String) variables.get("aep3Info"));
		
		
		/*
		 * Persist order instance and flush. After the flush the id of the order
		 * instance is set.
		 */
		entityManager.persist(orderEntity);
		entityManager.flush();

		// Remove no longer needed process variables
		delegateExecution.removeVariables(variables.keySet());

		// Add newly created order id as process variable
		delegateExecution.setVariable("orderId", orderEntity.getId());
	}

	public OrderEntity getOrder(Long orderId) {
		// Load order entity from database
		return entityManager.find(OrderEntity.class, orderId);
	}

	/*
	 * Merge updated order entity and complete task form in one transaction. This
	 * ensures that both changes will rollback if an error occurs during
	 * transaction.
	 */
	public void mergeOrderAndCompleteTask(OrderEntity orderEntity) {
		// Merge detached order entity with current persisted state
		if(orderEntity == null) {
			LOGGER.log(Level.INFO, "Orderentity in mergeOrderAndCompleteTask is null");
			return;
		}
		entityManager.merge(orderEntity);
		try {
			// Complete user task from
			taskForm.completeTask();
		} catch (IOException e) {
			// Rollback both transactions on error
			throw new RuntimeException("Cannot complete task", e);
		}
	}	

	public void rejectOrder(DelegateExecution delegateExecution) {
		//OrderEntity order = getOrder((Long) delegateExecution.getVariable("orderId"));
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
	
	public void generateDropboxLink(DelegateExecution delegateExecution) {
		OrderEntity order = getOrder((Long) delegateExecution.getVariable("orderId"));
		Map<String, Object> variables = delegateExecution.getVariables();
		
		
		if (order == null)
			return;
		
		String link = Url.generateURL();
		order.setDropboxLink(link);
		LOGGER.log(Level.INFO, "generate dropbox link = " + link);
		entityManager.persist(order);
		entityManager.flush();

		// Remove no longer needed process variables
		delegateExecution.removeVariables(variables.keySet());

		// Add newly created order id as process variable
		delegateExecution.setVariable("orderId", order.getId());
	}

}
