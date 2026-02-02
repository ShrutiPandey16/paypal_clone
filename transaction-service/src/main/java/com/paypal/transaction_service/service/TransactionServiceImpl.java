package com.paypal.transaction_service.service;

import java.time.LocalDateTime;
import java.util.List;

import com.paypal.transaction_service.entity.Transaction;
import com.paypal.transaction_service.repository.TransactionRepository;
import tools.jackson.databind.ObjectMapper;

public class TransactionServiceImpl implements TransactionService{

	private final TransactionRepository repository;
	private final ObjectMapper objectMapper;
	
	public TransactionServiceImpl(TransactionRepository repository, ObjectMapper objectMapper) {
		super();
		this.repository = repository;
		this.objectMapper = objectMapper;
	}

	@Override
	public Transaction createTransaction(Transaction transaction) {
		transaction.setTimestamp(LocalDateTime.now());
		transaction.setStatus("SUCCESS");
		
		return repository.save(transaction);
	}

	@Override
	public List<Transaction> getAllTransactions() {
		return repository.findAll();
	}

}
