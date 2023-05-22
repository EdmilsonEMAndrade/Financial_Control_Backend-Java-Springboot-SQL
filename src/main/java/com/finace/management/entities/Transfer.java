package com.finace.management.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name="transfers", schema="finance")
public class Transfer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(nullable=false)
    private LocalDate date;

    @NotBlank
    @Column(nullable=false)
    private Double valueMovement;

    @ManyToOne
    @JoinColumn(name = "origin_wallet_id", nullable = false)
    private Wallet originWallet;

    @ManyToOne
    @JoinColumn(name = "destiny_wallet_id", nullable = false)
    private Wallet destinyWallet;
}
