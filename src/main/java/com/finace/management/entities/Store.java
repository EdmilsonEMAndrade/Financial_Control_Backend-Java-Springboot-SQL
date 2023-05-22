package com.finace.management.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import java.io.Serializable;

@Data
@Entity
@Table(name="stores", schema="finance")
public class Store implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @UniqueElements
    @Column(nullable=false, unique = true)
    private String name;

    public Store(String name){
        this.name = name;
    }

}
