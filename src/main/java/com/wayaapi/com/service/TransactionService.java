package com.wayaapi.com.service;

import com.wayaapi.com.domain.TransactionRequest;
import com.wayaapi.com.domain.dtos.TransactionDto;
import com.wayaapi.com.domain.entities.TransactionEntity;
import com.wayaapi.com.mappers.TransactionMapper;
import com.wayaapi.com.repository.AccountRepository;
import com.wayaapi.com.repository.TransactionRepository;
import com.wayaapi.com.util.Util;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Singleton
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final TransactionMapper transactionMapper;

    @Inject
    TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository, TransactionMapper transactionMapper){
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.transactionMapper = transactionMapper;

    }

    @Inject
    Util util;

    public Optional<TransactionDto> create(TransactionRequest transactionRequest, String username) {

        return accountRepository.findByUsername(username).map(user ->
                        transactionRepository.save(TransactionEntity.builder()
                                .fromUser(user)
                                .currency(transactionRequest.getCurrency().getCurrencyCode())
                                .transferType(transactionRequest.getTransferType().toString())
                                .amount(util.findAmountPerc(transactionRequest.getAmount()))
                                .transactionId(util.generateUid())
                                .transactionDate(Instant.now()).build())).map(transactionMapper::toDto);

    }

    public List<TransactionDto> getUserTransactions(String username) {

        List<TransactionDto> transactionDtoList = new ArrayList<>();

        accountRepository.findByUsername(username).ifPresent(user ->
                transactionRepository.findAllByFromUser(user).forEach(transaction ->
                        transactionDtoList.add(transactionMapper.toDto(transaction)))
        );

        return transactionDtoList;

    }


}

