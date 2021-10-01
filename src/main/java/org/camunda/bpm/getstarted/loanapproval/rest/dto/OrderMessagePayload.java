package org.camunda.bpm.getstarted.loanapproval.rest.dto;

import java.io.Serializable;

public class OrderMessagePayload implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String orderId;

	private Integer amount;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "OrderMessagePayload [orderId=" + orderId + ", amount=" + amount + "]";
	}

}
