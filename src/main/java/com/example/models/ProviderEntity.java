package com.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
}
