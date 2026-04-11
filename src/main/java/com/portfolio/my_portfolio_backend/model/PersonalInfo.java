package com.portfolio.my_portfolio_backend.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfo {
    private Long id;

    @NotBlank(message =  "El nombre no puede estar vacío")
    private String firstName;

    @NotBlank(message =  "El apellido no puede estar vacío")
    private String lastName;

    @NotBlank(message = "El titulo no puede estar vacío")
    private String title;

    @NotBlank(message = "La descripción del perfil no puede estar vacío")
    private String profileDescription;

    @NotBlank(message = "La imagen del perfil no puede estar vacío")
    private String profileImageURL;

    @Min(value = 0, message = "Los años de experiencia no pueden ser negativos")
    private Integer yearsOfExperience;

    @NotBlank (message = "El email no puede estar vacío")
    @Email(message = "Debe de ser tipo email")
    private String email;

    @NotBlank(message = "El teléfono no puede estar vacío")
    private String phone;

    @NotBlank(message = "LinkedIn es una red obligatoria")
    private String linkedinURL;

    @NotBlank(message = "Github es una red obligatoria")
    private String githubURL;
}
