package com.davidperezmillan.cdovideostoreservice.application.services;

import com.davidperezmillan.cdovideostoreservice.application.scrap.rules.RulesDonTorrent;
import com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.entities.Episode;
import com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.entities.Session;
import com.davidperezmillan.cdovideostoreservice.infrastructure.rest.dtos.PageResponse;
import com.davidperezmillan.cdovideostoreservice.infrastructure.rest.dtos.SessionResponse;
import com.davidperezmillan.cdovideostoreservice.infrastructure.rest.dtos.TvShowResponse;
import com.davidperezmillan.cdovideostoreservice.application.usecases.SearchTvShowUsecase;
import com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.entities.TvShow;
import com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.repositories.TvShowRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Log4j2
@Service
public class SearchTvShowService implements SearchTvShowUsecase {

    private final TvShowRepository tvShowRepository;

    @Autowired
    public SearchTvShowService(TvShowRepository tvShowRepository) {
        this.tvShowRepository = tvShowRepository;
    }

    @Override
    public PageResponse getAll(int page, int size) {
        String proxyTorrent = RulesDonTorrent.getProxyDonTorrent();

        // get find all pageable
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<TvShow> pageResult = tvShowRepository.findAll(pageable);

        List<TvShow> resultBBDD = pageResult.getContent();
        if (resultBBDD.isEmpty()) {
            log.info("No se ha encontrado series");
            return null;
        }
        log.info("Se han encontrado " + resultBBDD.size() + " series");
        List<TvShowResponse> result = new ArrayList<>();
        for (TvShow tvShow : resultBBDD) {
            TvShowResponse tvShowResponse = new TvShowResponse();
            tvShowResponse.setTitle(tvShow.getTitle());
            tvShowResponse.setId(tvShow.getId());

            tvShowResponse.setCreatedAt(convert(tvShow.getCreatedAt()));
            tvShowResponse.setUpdatedAt(convert(tvShow.getUpdatedAt()));

            log.debug("Serie: " + tvShow.getTitle() + " - " + tvShow.getId());
            Map<Integer, SessionResponse> sessions = new HashMap<>();
            for (Map.Entry<Integer, Session> session : tvShow.getSessions().entrySet()) {
                log.debug("Session: " + session.getKey());
                SessionResponse sessionResponse = new SessionResponse();
                sessionResponse.setUrl(proxyTorrent + session.getValue().getUrl());
                Map<Integer, String> episodes = new HashMap<>();
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

        PageResponse pageResponse = new PageResponse();
        pageResponse.setTotalElements(pageResult.getTotalElements());
        pageResponse.setTotalPages(pageResult.getTotalPages());
        pageResponse.setNumber(pageResult.getNumber());
        pageResponse.setSize(pageResult.getSize());
        pageResponse.setContent(Collections.singletonList(result));
        return pageResponse;

    }

    @Override
    public PageResponse getTvShow(String title, int page, int size) {
        String proxyTorrent = RulesDonTorrent.getProxyDonTorrent();
        // get find all pageable
        Pageable pageable = PageRequest.of(page, size);
        Page<TvShow> pageResult = tvShowRepository.findByTitleContainingIgnoreCase(title, pageable);
        List<TvShow> resultBBDD = pageResult.getContent();
        if (resultBBDD.isEmpty()) {
            log.info("No se ha encontrado la serie " + title);
            return null;
        }

        log.info("Se han encontrado " + resultBBDD.size() + " series");
        List<TvShowResponse> result = new ArrayList<>();
        for (TvShow tvShow : resultBBDD) {
            TvShowResponse tvShowResponse = new TvShowResponse();
            tvShowResponse.setTitle(tvShow.getTitle());
            tvShowResponse.setId(tvShow.getId());
            tvShowResponse.setCreatedAt(convert(tvShow.getCreatedAt()));
            tvShowResponse.setUpdatedAt(convert(tvShow.getUpdatedAt()));

            log.debug("Serie: " + tvShow.getTitle() + " - " + tvShow.getId());
            Map<Integer, SessionResponse> sessions = new HashMap<>();
            for (Map.Entry<Integer, Session> session : tvShow.getSessions().entrySet()) {
                log.debug("Session: " + session.getKey());
                SessionResponse sessionResponse = new SessionResponse();
                sessionResponse.setUrl(proxyTorrent + session.getValue().getUrl());
                Map<Integer, String> episodes = new HashMap<>();
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
        PageResponse pageResponse = new PageResponse();
        pageResponse.setTotalElements(pageResult.getTotalElements());
        pageResponse.setTotalPages(pageResult.getTotalPages());
        pageResponse.setNumber(pageResult.getNumber());
        pageResponse.setSize(pageResult.getSize());
        pageResponse.setContent(Collections.singletonList(result));
        return pageResponse;

    }

    private String convert(Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(fecha);
    }
}
