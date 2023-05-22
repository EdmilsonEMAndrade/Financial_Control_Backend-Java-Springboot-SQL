package com.finace.management.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="currencies", schema="finance")
public class Currency implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @UniqueElements
    @Column(nullable=false, unique = true)
    private String name;

    @NotBlank
    @UniqueElements
    @Column(nullable=false, unique = true)
    private String sign;

    @NotBlank
    @UniqueElements
    @Column(nullable=false, unique = true)
    private String country;

    @OneToMany(mappedBy = "currency")
    private Set<Wallet> wallet = new HashSet<>();

    public Currency(String name, String sign, String country){
        this.name = name;
        this.sign = sign;
        this.country = country;
    }

}
