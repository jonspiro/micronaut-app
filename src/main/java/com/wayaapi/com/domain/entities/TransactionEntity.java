package com.wayaapi.com.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(name="HIBERNATE_SEQUENCE", initialValue=4)
@Entity
@Data
@Table(name = "transactions")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @JoinColumn(name = "from_user")
    @ManyToOne(targetEntity = UserEntity.class)
    private UserEntity fromUser;

    @Column(name="currency")
    private String currency;

    @Column(name="trans_type")
    private String transferType;

    @Column(name="amount")
    private BigDecimal amount;

    @Column(name="trans_id")
    private String transactionId;

    @Column(name="trans_date")
    private Instant transactionDate;

}
