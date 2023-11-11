package com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.entities;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Entity
@Data
public class TvShow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;

    @OneToMany(mappedBy = "tvShow", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<Integer, Session> sessions = new HashMap<Integer, Session>();

}
