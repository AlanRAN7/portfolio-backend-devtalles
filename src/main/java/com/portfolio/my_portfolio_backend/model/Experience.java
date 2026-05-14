package com.portfolio.my_portfolio_backend.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Experience {
private Long id;

@NotBlank(message = "El titulo del trabajo no debe de estar vacío")
private String jobTitle;

@NotBlank(message = "El nombre de la compañía no debe de estar vacío")
private String companyName;

@NotNull(message = "La fecha de inicio no debe de ser nula")
@PastOrPresent(message = "La fecha del inicio no puede ser futura")
private LocalDate startDate;

@PastOrPresent(message = "La fecha del inicio no puede ser futura")
private LocalDate endDate;

@NotBlank(message = "La descripción no debe de estar vacío")
private String description;

private Long personalInfoId;
}
