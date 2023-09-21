package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "series")
@DynamicUpdate
@Data
public class SerieEntity {

    @Id
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "releaseyear")
    private int releaseYear;

    @Column(name = "sinopsis", length = 10000)
    @Lob
    private String sinopsis;

    @Column(name = "visto", updatable = false)
    private boolean visto;

}
