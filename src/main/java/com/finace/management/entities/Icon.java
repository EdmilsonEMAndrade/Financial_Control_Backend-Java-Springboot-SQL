package com.finace.management.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


@Data
@Entity
@Table(name="icons", schema="finance")
public class Icon implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(nullable=false)
    private String illustration;

    @NotBlank
    @Column(nullable=false, length = 6, name = "color")
    private String colorHexadecimal;

    @OneToOne(mappedBy = "icon")
    private Category category;

    public Icon(String illustration, String colorHexadecimal){
        this.illustration = illustration;
        this.colorHexadecimal = colorHexadecimal;
    }
}
