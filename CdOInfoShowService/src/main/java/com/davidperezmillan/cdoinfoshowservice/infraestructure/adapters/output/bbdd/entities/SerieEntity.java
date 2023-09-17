package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "series")
@Data
public class SerieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "releaseyear")
    private int releaseYear;

    @Column(name = "sinopsis")
    private String sinopsis;

    // Otros campos y relaciones aquí

}
