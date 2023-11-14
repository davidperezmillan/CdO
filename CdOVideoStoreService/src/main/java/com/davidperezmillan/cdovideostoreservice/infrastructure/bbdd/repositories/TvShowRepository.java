package com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.repositories;

import com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.entities.TvShow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TvShowRepository extends JpaRepository<TvShow, Long> {
    TvShow findByTitle(String title);

    Page<TvShow> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
