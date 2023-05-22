package com.finace.management.entities;

import com.finace.management.enums.Transaction;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name="financial_movements", schema="finance")
public class FinancialMovement implements Serializable {
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

    @NotBlank
    @Column(nullable=false)
    private String picture;

    @NotBlank
    @Column(nullable=false)
    private Integer transaction;

    @ManyToOne
    @JoinColumn(name="wallet_id")
    private Wallet wallet;

    public FinancialMovement(String date, Double value, String transactionType, Wallet wallet){
        this.wallet = wallet;
        this.date = LocalDate.parse(date);
        this.transaction = Transaction.toEnumByType(transactionType).getCode();
        this.setValueMovement(value);
    }

    public void setValueMovement(Double value){
        this.valueMovement = this.transaction == 0 ? value*-1 : value;
    }

}
