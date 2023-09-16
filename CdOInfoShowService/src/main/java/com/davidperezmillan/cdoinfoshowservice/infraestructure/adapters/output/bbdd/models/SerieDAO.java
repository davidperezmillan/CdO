package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "SERIES")
@Table(name = "SERIES")
@Data
public class SerieDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "releaseyear")
    private int releaseYear;

    // Otros campos y relaciones aquí

}
