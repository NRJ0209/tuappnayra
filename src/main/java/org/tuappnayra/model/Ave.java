package org.tuappnayra.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "aves")
@Data
public class Ave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String tipo;

    private int edad;
}