package com.wayaapi.com.repository;

import com.wayaapi.com.domain.entities.UserEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByToken(String token);

}
