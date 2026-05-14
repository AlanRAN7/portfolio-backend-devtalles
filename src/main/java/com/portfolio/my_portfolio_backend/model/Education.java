package com.portfolio.my_portfolio_backend.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Education {
    private Long id;
    @NotBlank(message = "El nombre del título no puede estar vacío")
    private String degree;

    @NotBlank(message = "El nombre de la institución no puede estar vacío")
    private String institution;

    @NotNull(message = "La fecha del inicio no puede estar vacío")
    @PastOrPresent(message = "La fecha del inicio no puede ser futura")
    private LocalDate startDate;

    @PastOrPresent(message = "La fecha de fin no puede ser futura")
    private LocalDate endDate; // Puede ser null

    @NotBlank(message = "La descripción no puede estar vacío")
    private String description;
    private Long personalInfoId;
}
