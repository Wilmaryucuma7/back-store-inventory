package com.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "providers")
public class ProviderEntity {
    @Id
    private String providerId;

    @NotBlank
    @Size(max = 30)
    private String providerName;

    @Email
    @NotBlank
    @Size(max = 80)
    private String providerEmail;

    @NotBlank
    @Size(max = 12)
    private String providerPhone;

    @NotBlank
    @Size(max = 80)
    private  String providerAddress;

    @OneToMany(mappedBy = "provider")
    @JsonIgnore
    private List<ProductEntity> products;
}
