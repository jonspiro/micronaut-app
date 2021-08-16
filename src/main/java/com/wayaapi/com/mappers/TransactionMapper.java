package com.wayaapi.com.mappers;

import com.wayaapi.com.domain.dtos.TransactionDto;
import com.wayaapi.com.domain.entities.TransactionEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TransactionMapper {

    final private UserMapper userMapper;

    @Inject TransactionMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    public TransactionEntity toEntity(TransactionDto transactionDto) {

        return TransactionEntity.builder()
                .id(transactionDto.getId())
                .fromUser(userMapper.toEntity(transactionDto.getUser()))
                .transferType(transactionDto.getTransferType())
                .amount(transactionDto.getAmount())
                .transactionDate(transactionDto.getTransactionDate())
                .transactionId(transactionDto.getTransactionId())
                .currency(transactionDto.getCurrency()).build();
    }

    public TransactionDto toDto(TransactionEntity transactionEntity) {

        return TransactionDto.builder()
                .id(transactionEntity.getId())
                .user(userMapper.toDto(transactionEntity.getFromUser()))
                .transferType(transactionEntity.getTransferType())
                .amount(transactionEntity.getAmount())
                .transactionDate(transactionEntity.getTransactionDate())
                .transactionId(transactionEntity.getTransactionId())
                .currency(transactionEntity.getCurrency()).build();
    }

}

