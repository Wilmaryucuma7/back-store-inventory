package com.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String productId;

    @NotBlank
    @Size(max = 30)
    private String productName;

    @NotBlank
    @Size(max = 120)
    private String productDescription;

    @NotNull
    private double productPrice;

    @NotNull
    private int productQuantity;

//    @ManyToOne
//    @JoinColumn
//
//
//

}
