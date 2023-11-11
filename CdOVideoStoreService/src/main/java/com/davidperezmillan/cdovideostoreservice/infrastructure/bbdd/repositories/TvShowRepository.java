package com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.repositories;

import com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.entities.TvShow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TvShowRepository extends JpaRepository<TvShow, Long> {
    TvShow findByTitle(String title);

    List<TvShow> findByTitleContainingIgnoreCase(String title);
}
