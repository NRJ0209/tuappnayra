package org.tuappnayra.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;      // Validaciones
import jakarta.validation.constraints.Positive;      // Validaciones
import lombok.Data;

@Entity
@Table(name = "aves")
@Data
public class Ave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")    // Validaciones
    private String nombre;

    @NotBlank(message = "El tipo no puede estar vacío")      // Validaciones
    private String tipo;

    @Positive(message = "La edad debe ser un número positivo")    // Validaciones
    private int edad;
}