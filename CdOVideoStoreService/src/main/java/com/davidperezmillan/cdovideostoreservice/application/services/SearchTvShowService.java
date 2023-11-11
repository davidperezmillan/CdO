package com.davidperezmillan.cdovideostoreservice.application.services;

import com.davidperezmillan.cdovideostoreservice.application.scrap.rules.RulesDonTorrent;
import com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.entities.Episode;
import com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.entities.Session;
import com.davidperezmillan.cdovideostoreservice.infrastructure.rest.dtos.SessionResponse;
import com.davidperezmillan.cdovideostoreservice.infrastructure.rest.dtos.TvShowResponse;
import com.davidperezmillan.cdovideostoreservice.application.usecases.SearchTvShowUsecase;
import com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.entities.TvShow;
import com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.repositories.TvShowRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class SearchTvShowService implements SearchTvShowUsecase {

    private TvShowRepository tvShowRepository;

    @Autowired
    public SearchTvShowService(TvShowRepository tvShowRepository) {
        this.tvShowRepository = tvShowRepository;
    }

    @Override
    public List<TvShowResponse> getTvShow(String title) {
        List<TvShow> resultBBDD = tvShowRepository.findByTitleContainingIgnoreCase(title);
        List<TvShowResponse> result = new ArrayList<TvShowResponse>();
        if (resultBBDD.isEmpty()) {
            log.info("No se ha encontrado la serie " + title);
            return null;
        }
        log.info("Se han encontrado " + resultBBDD.size() + " series");
        for (TvShow tvShow : resultBBDD) {
            TvShowResponse tvShowResponse = new TvShowResponse();
            tvShowResponse.setTitle(tvShow.getTitle());
            tvShowResponse.setId(tvShow.getId());

            log.debug("Serie: " + tvShow.getTitle() + " - " + tvShow.getId());
            Map<Integer, SessionResponse> sessions = new HashMap<Integer, SessionResponse>();
            for (Map.Entry<Integer, Session> session : tvShow.getSessions().entrySet()) {
                log.debug("Session: " + session.getKey());
                SessionResponse sessionResponse = new SessionResponse();
                Map<Integer, String> episodes = new HashMap<Integer, String>();
                for (Map.Entry<Integer, Episode> episode : session.getValue().getEpisodes().entrySet()) {
                    log.debug("Episode: " + episode.getKey());
                    episodes.put(episode.getKey(), episode.getValue().getTorrent().getMagnetLink());
                }
                sessionResponse.setEpisodes(episodes);
                sessions.put(session.getKey(), sessionResponse);
            }
            tvShowResponse.setSessions(sessions);
            result.add(tvShowResponse);
        }
        return result;

    }
}
