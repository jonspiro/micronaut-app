package com.wayaapi.com.repository;

import com.wayaapi.com.domain.entities.TransactionEntity;
import com.wayaapi.com.domain.entities.UserEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public abstract interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {
    List<TransactionEntity> findAllByFromUser(UserEntity user);
}
