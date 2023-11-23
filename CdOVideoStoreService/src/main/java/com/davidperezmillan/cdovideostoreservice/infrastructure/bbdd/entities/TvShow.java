package com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Data
public class TvShow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;

    @Lob
    @Column
    private String sinopsis;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    @OneToMany(mappedBy = "tvShow", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Map<Integer, Session> sessions = new HashMap<>();

}
