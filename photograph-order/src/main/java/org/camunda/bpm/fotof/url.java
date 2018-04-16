package org.camunda.bpm.fotof;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class url {
	 public static String generateURL(OrderEntity orderEntity) {
		 QString qs = new QString("pg", "q");
		 orderEntity.getFullName();
		    qs.add("svaka", "kul");
		    qs.add("flottur", "nettur");
		    String url = "http://www.dropbox.com/" + qs;
		    return url;
	 }
}
class QString {

  private String q = "";

  public QString(String nick, String val) {
    encode(nick, val);
  }

  public void add(String nick, String val) {
    q += "&";
    encode(nick, val);
  }

  private void encode(String nick, String val) {
    try {
      q +=URLEncoder.encode(nick, "UTF-8");
      q += "=";
      q += URLEncoder.encode(val, "UTF-8");
    } catch (UnsupportedEncodingException ex) {
      throw new RuntimeException("Sorry");
    }
  }

  public String getq() {
    return q;
  }

  public String toString() {
    return getq();
  }

}