package com.wayaapi.com.mappers;

import com.wayaapi.com.domain.entities.UserEntity;
import com.wayaapi.com.domain.dtos.UserDto;

import javax.inject.Singleton;

@Singleton
public class UserMapper {

    public UserEntity toEntity(UserDto userDto) {

        return UserEntity.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .username(userDto.getUsername())
                .accountNumber(userDto.getAccountNumber())
                .wayaAccountId(userDto.getWayaAccountId())
                .phoneNumber(userDto.getPhoneNumber())
                .password(userDto.getPassword()).build();

    }

    public UserDto toDto(UserEntity userEntity) {

        return UserDto.builder()
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .username(userEntity.getUsername())
                .accountNumber(userEntity.getAccountNumber())
                .wayaAccountId(userEntity.getWayaAccountId())
                .phoneNumber(userEntity.getPhoneNumber())
                .password(userEntity.getPassword()).build();

    }

}

