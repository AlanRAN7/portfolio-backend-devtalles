package com.portfolio.my_portfolio_backend.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill {
private Long id;

@NotBlank(message =  "El nombre no puede estar vacío")
private String name;

@NotNull(message = "El porcentaje no debe de ser nulo")
@Min(value = 0, message = "El minimo valor debe de ser 0")
@Max(value=100, message = "El máximo valor debe de ser 100")
private Integer levelPercentage;
@NotBlank(message = "La clase del icono no puede estar vacía")
private String iconClass;
@NotBlank(message = "La skill debe de estar sujeto con una persona")
private Long personalInfoId;
}
