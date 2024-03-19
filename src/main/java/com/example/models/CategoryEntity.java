package com.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    private String categoryId;

    @NotBlank
    @Size(max = 30)
    private String categoryName;

    @NotBlank
    @Size(max = 120)
    private String categoryDescription;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<ProductEntity> products;
}
