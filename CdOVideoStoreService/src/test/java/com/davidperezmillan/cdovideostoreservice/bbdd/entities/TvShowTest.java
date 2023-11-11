package com.davidperezmillan.cdovideostoreservice.bbdd.entities;

import com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.entities.Episode;
import com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.entities.Session;
import com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.entities.Torrent;
import com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.entities.TvShow;
import com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.repositories.TvShowRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class TvShowTest {

    private static String magnetLink = "magnet:?xt=urn:btih:4d1c8b0b4e1e4b4b9e4b4b9e4b4b9e4b4b9e4b4b";

    @Autowired
    TvShowRepository tvShowRepository;

    @Test
    void testEquals() {
        TvShow tvShow = createTvShow("Ahsoka");
        tvShowRepository.save(tvShow);

        TvShow tvShowBBDD = tvShowRepository.findById(tvShow.getId()).orElse(null);

        assertThat(tvShow).isNotNull();
        assertThat(Objects.requireNonNull(tvShowBBDD).getTitle()).isEqualTo("Ahsoka");
        assertEquals(tvShow, tvShowBBDD);
        assertEquals(tvShowBBDD.getSessions().size(), 1);

    }

    @Test
    void testUpdate() {
        TvShow tvShow = createTvShow("Ahsoka");
        tvShowRepository.save(tvShow);

        TvShow updateElement = tvShowRepository.findByTitle(tvShow.getTitle());
        this.createSessionAddSerie(updateElement);
        tvShowRepository.save(updateElement);

        List<TvShow> tvShowBBDDList = tvShowRepository.findAll();

        assertThat(tvShowBBDDList).isNotNull();
        assertEquals(tvShowBBDDList.size(), 1);
        assertEquals(tvShowBBDDList.get(0).getSessions().size(), 2);

    }

    @Test
    void testLike() {
        TvShow tvShow = createTvShow("Ahsoka");
        tvShowRepository.save(tvShow);

        List<TvShow> tvShowBBDDList = tvShowRepository.findByTitleContainingIgnoreCase("ahs");

        assertThat(tvShowBBDDList).isNotNull();
        assertEquals(tvShowBBDDList.size(), 1);
        assertEquals(tvShowBBDDList.get(0).getSessions().size(), 1);

    }

    private TvShow createTvShow(String title) {
        TvShow tvShow = new TvShow();
        tvShow.setTitle(title);
        createSessionAddSerie(tvShow);
        return tvShow;
    }

    private void createSessionAddSerie(TvShow tvShow) {

        Session session = new Session();

        for (int i = 0; i < 10; i++) {
            Episode episode = new Episode();
            Torrent torrent = new Torrent();
            torrent.setMagnetLink(magnetLink);

            episode.setTorrent(torrent);
            session.getEpisodes().put(i, episode);

        }

        tvShow.getSessions().put(tvShow.getSessions().size() + 1, session);
    }

}