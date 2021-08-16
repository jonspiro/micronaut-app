package com.wayaapi.com.domain.dtos;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Introspected
public class UserDto {

    @NotBlank
    @NotNull
    private String firstName;

    @NotBlank
    @NotNull
    private String lastName;

    @NotBlank
    @NotNull
    private String username;

    private String wayaAccountId;
    
    private String accountNumber;

    @NotBlank
    @NotNull
    private String password;

    @NotBlank
    @NotNull
    private String phoneNumber;

}
