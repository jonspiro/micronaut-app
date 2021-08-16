package com.wayaapi.com.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="first_name", nullable = false)
    @Size(max = 60)
    private String firstName;

    @Column(name="last_name", nullable = false)
    @Size(max = 60)
    private String lastName;

    @Column(name="username", nullable = false, unique = true)
    @Size(max = 50)
    private String username;

    @Column(name="waya_acct_id", nullable = false, unique = true)
    private String wayaAccountId;

    @Column(name="acct_number", nullable = false, unique = true)
    private String accountNumber;

    @Column(name="phone_number", nullable = false, unique = true)
    @Size(max = 20)
    private String phoneNumber;

    @Column(name="password")
    @Size(max = 50)
    private String password;

    @Column(name="token", unique = true)
    private String token;

}
