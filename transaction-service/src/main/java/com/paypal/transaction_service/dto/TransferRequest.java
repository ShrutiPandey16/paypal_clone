package com.paypal.transaction_service.dto;

public class TransferRequest {

	private String senderName;
	private String ReceiverName;
	private Double amount;
	
	public TransferRequest(String senderName, String receiverName, Double amount) {
		super();
		this.senderName = senderName;
		ReceiverName = receiverName;
		this.amount = amount;
	}
	
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getReceiverName() {
		return ReceiverName;
	}
	public void setReceiverName(String receiverName) {
		ReceiverName = receiverName;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
}
