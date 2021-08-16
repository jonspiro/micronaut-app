package com.wayaapi.com.service;

import com.wayaapi.com.domain.dtos.UserDto;
import com.wayaapi.com.domain.entities.UserEntity;
import com.wayaapi.com.mappers.UserMapper;
import com.wayaapi.com.repository.AccountRepository;
import com.wayaapi.com.util.Util;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Slf4j
@Singleton
public class AccountService {

    @Inject
    AccountRepository accountRepository;

    @Inject
    UserMapper userMapper;

    @Inject
    Util util;

    public UserDto createUser(UserDto userDto) {

        userDto.setAccountNumber(util.generateAcctNumber());
        userDto.setWayaAccountId(util.generateUid());

        UserEntity userEntity = accountRepository.save(userMapper.toEntity(userDto));
        return userMapper.toDto(userEntity);
    }

    public Optional<UserDto> findUser(String username) {
        return accountRepository.findByUsername(username).map(userMapper::toDto);
    }

    public Optional<UserDto> findByRefreshToken(String refreshToken) {
        return accountRepository.findByToken(refreshToken).map(userMapper::toDto);
    }

    public void saveRefreshToken(String username, String refreshToken) {
        accountRepository.findByUsername(username).ifPresent(
                user -> {
                    user.setToken(refreshToken);
                    accountRepository.update(user);
                }
        );
    }

}

