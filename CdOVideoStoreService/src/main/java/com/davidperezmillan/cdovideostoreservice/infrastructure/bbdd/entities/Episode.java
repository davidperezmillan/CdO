package com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Torrent torrent = new Torrent();

}
