package com.finace.management.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="wallets", schema="finance")
public class Wallet implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(nullable=false)
    private String name;

    @NotBlank
    @Column(nullable=false)
    private Double balance;

    @ManyToOne
    @JoinColumn(name="currency_id")
    private Currency currency;

    @OneToMany(mappedBy = "wallet")
    private Set<FinancialMovement> financialMovements = new HashSet<>();

    @OneToMany(mappedBy = "originWallet")
    private Set<Transfer> transfersSent = new HashSet<>();

    @OneToMany(mappedBy = "destinyWallet")
    private Set<Transfer> incomingTransfers = new HashSet<>();

    public Wallet(String name, Double balance, Currency currency){
        this.name = name;
        this.balance = balance;
        this.currency = currency;
    }
    public Double getBalance() {
        return this.balance
                + this.getTotalFinancialMovements()
                + this.getTotalIncomingTransfers()
                + this.getTotalTransfersSent();
    }

    public Double getBalance(LocalDate startDate, LocalDate endDate) {
        return this.balance
                + this.getTotalFinancialMovements(startDate, endDate)
                + this.getTotalIncomingTransfers(startDate,endDate)
                + this.getTotalTransfersSent(startDate, endDate);
    }

    private Double getTotalFinancialMovements() {
        Double total = 0.00;
        for (FinancialMovement movement : this.financialMovements) {
            total += movement.getValueMovement();
        }
        return total;
    }

    private Double getTotalFinancialMovements(LocalDate startDate, LocalDate endDate) {
        Double total = 0.00;
        for (FinancialMovement movement : this.financialMovements) {
            LocalDate movementDate = movement.getDate();
            if (movementDate.isAfter(startDate.minusDays(1)) && movementDate.isBefore(endDate.plusDays(1))) {
                total += movement.getValueMovement();
            }else if(movementDate.isAfter(endDate)){
                break;
            }
        }
        return total;
    }

    private Double getTotalTransfersSent(){
        Double total = 0.00;
        for (Transfer movement : this.transfersSent) {
            total -= movement.getValueMovement();
        }
        return total;
    }

    private Double getTotalTransfersSent(LocalDate startDate, LocalDate endDate){
        Double total = 0.00;
        for (Transfer movement : this.transfersSent) {
            LocalDate movementDate = movement.getDate();
            if (movementDate.isAfter(startDate.minusDays(1)) && movementDate.isBefore(endDate.plusDays(1))) {
                total -= movement.getValueMovement();
            }else if(movementDate.isAfter(endDate)){
                break;
            }
        }
        return total;
    }

    private Double getTotalIncomingTransfers(){
        Double total = 0.00;
        for (Transfer movement : this.incomingTransfers) {
            total -= movement.getValueMovement();
        }
        return total;
    }

    private Double getTotalIncomingTransfers(LocalDate startDate, LocalDate endDate){
        Double total = 0.00;
        for (Transfer movement : this.incomingTransfers) {
            LocalDate movementDate = movement.getDate();
            if (movementDate.isAfter(startDate.minusDays(1)) && movementDate.isBefore(endDate.plusDays(1))) {
                total -= movement.getValueMovement();
            }else if(movementDate.isAfter(endDate)){
                break;
            }
        }
        return total;
    }
}
