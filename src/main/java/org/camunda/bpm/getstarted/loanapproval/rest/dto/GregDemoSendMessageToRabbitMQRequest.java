package org.camunda.bpm.getstarted.loanapproval.rest.dto;

public class GregDemoSendMessageToRabbitMQRequest {
	
	private String orderId;

	private String exchange;

	private String routingKey;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getRoutingKey() {
		return routingKey;
	}

	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}

	@Override
	public String toString() {
		return "GregDemoSendMessageToRabbitMQRequest [orderId=" + orderId + ", exchange=" + exchange + ", routingKey="
				+ routingKey + "]";
	}
	
	
	
	 
}
