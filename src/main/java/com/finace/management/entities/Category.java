package com.finace.management.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
@Data
@Entity
@Table(name="categories", schema="finance")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(nullable=false)
    private String name;

    @OneToOne
    @JoinColumn(name="icon_id")
    private Icon icon;

    public Category(String name, Icon icon){
        this.name = name;
        this.icon = icon;
    }

}
