package com.paypal.transaction_service.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name="transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "senderName", nullable = false)
	private String senderName;
	
	@Column(name = "receiverName", nullable = false)
	private String receiverName;
	
	@Column(nullable = false)
	@Positive(message = "Amount must be positive")
	private Double amount;
	
	@Column(nullable = false)
	private LocalDateTime timestamp;
	
	@Column(nullable = false)
	private String status;

	public Transaction(long id, String senderName, String receiverName,
			@Positive(message = "Amount must be positive") Double amount, LocalDateTime timestamp, String status) {
		super();
		this.id = id;
		this.senderName = senderName;
		this.receiverName = receiverName;
		this.amount = amount;
		this.timestamp = timestamp;
		this.status = status;
	}
	
	public void prePersist() {
		if(timestamp == null)
			timestamp = LocalDateTime.now();
		
		if(status == null)
			status = "PENDING";
	}

//	Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", senderName=" + senderName + ", receiverName=" + receiverName + ", amount="
				+ amount + ", timestamp=" + timestamp + ", status=" + status + "]";
	}
	
	
}














