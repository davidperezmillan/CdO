package com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Torrent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String magnetLink;

    @OneToOne(mappedBy = "torrent")
    private Episode episode;

}
