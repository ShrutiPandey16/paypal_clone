package com.paypal.transaction_service.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import com.paypal.transaction_service.entity.Transaction;
import com.paypal.transaction_service.repository.TransactionRepository;
import tools.jackson.databind.ObjectMapper;

@Service
public class TransactionServiceImpl implements TransactionService{

	private final TransactionRepository repository;
	private final ObjectMapper objectMapper;
	
	public TransactionServiceImpl(TransactionRepository repository, ObjectMapper objectMapper) {
		super();
		this.repository = repository;
		this.objectMapper = objectMapper;
	}

	@Override
	public Transaction createTransaction(Transaction request) {
		System.out.println("Entered createTransaction()");
		
		Long senderId = request.getSenderId();
		Long receiverId = request.getReceiverId();
		Double amount = request.getAmount();
		
		Transaction transaction = new Transaction();
		transaction.setSenderId(senderId);
		transaction.setReceiverId(receiverId);
		transaction.setAmount(amount);
		transaction.setTimestamp(LocalDateTime.now());
		transaction.setStatus("SUCCESS");
		
		System.out.println("Incoming Transaction object: " + transaction);
		
		Transaction saved = repository.save(transaction);
		
		System.out.println("Saved transaction from DB: " + transaction);
		
		return repository.save(transaction);
	}

	@Override
	public List<Transaction> getAllTransactions() {
		return repository.findAll();
	}

}
