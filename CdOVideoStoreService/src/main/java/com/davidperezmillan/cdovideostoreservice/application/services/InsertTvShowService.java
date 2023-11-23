package com.davidperezmillan.cdovideostoreservice.application.services;

import com.davidperezmillan.cdovideostoreservice.application.scrap.dtos.CalidadEnum;
import com.davidperezmillan.cdovideostoreservice.application.scrap.dtos.ScrapBeanResponse;
import com.davidperezmillan.cdovideostoreservice.application.scrap.services.DonTorrentScraperService;
import com.davidperezmillan.cdovideostoreservice.application.usecases.InsertTvShowUsecase;
import com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.entities.Episode;
import com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.entities.Session;
import com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.entities.Torrent;
import com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.entities.TvShow;
import com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.repositories.TvShowRepository;
import com.davidperezmillan.cdovideostoreservice.infrastructure.rest.dtos.SessionResponse;
import com.davidperezmillan.cdovideostoreservice.infrastructure.rest.dtos.TvShowResponse;
import com.davidperezmillan.cdovideostoreservice.infrastructure.slack.SlackService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class InsertTvShowService implements InsertTvShowUsecase {

    private final DonTorrentScraperService donTorrentScraperService;

    private final TvShowRepository tvShowRepository;

    @Autowired
    public InsertTvShowService(DonTorrentScraperService donTorrentScraperService, TvShowRepository tvShowRepository) {
        this.donTorrentScraperService = donTorrentScraperService;
        this.tvShowRepository = tvShowRepository;
    }

    @Override
    public List<TvShowResponse> addTitleByLetter(String letter) {
        List<ScrapBeanResponse> scrapBeanResponses = donTorrentScraperService.getTvShow(letter);
        List<TvShow> saveds = save(scrapBeanResponses);

        log.info("Se han encontrado " + saveds.size() + " series");
        List<TvShowResponse> result = new ArrayList<>();
        for (TvShow tvShow : saveds) {
            TvShowResponse tvShowResponse = new TvShowResponse();
            tvShowResponse.setTitle(tvShow.getTitle());
            tvShowResponse.setId(tvShow.getId());

            log.debug("Serie: " + tvShow.getTitle() + " - " + tvShow.getId());
            Map<Integer, SessionResponse> sessions = new HashMap<>();
            for (Map.Entry<Integer, Session> session : tvShow.getSessions().entrySet()) {
                log.debug("Session: " + session.getKey());
                SessionResponse sessionResponse = new SessionResponse();
                sessionResponse.setUrl(session.getValue().getUrl());
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

        return result;
    }

    @Override
    public List<TvShowResponse> addPremieres() {
        List<ScrapBeanResponse> scrapBeanResponses = donTorrentScraperService.getPremieres();
        List<TvShow> saveds = save(scrapBeanResponses);

        // if exist saveds send Slack message
        if (saveds.stream().findAny().isPresent()) {
            SlackService.sendMessage("Se han encontrado " + saveds.size() + " series");
        }

        log.info("Se han encontrado " + saveds.size() + " series");
        List<TvShowResponse> result = new ArrayList<>();
        for (TvShow tvShow : saveds) {
            TvShowResponse tvShowResponse = new TvShowResponse();
            tvShowResponse.setTitle(tvShow.getTitle());
            tvShowResponse.setId(tvShow.getId());

            log.debug("Serie: " + tvShow.getTitle() + " - " + tvShow.getId());
            Map<Integer, SessionResponse> sessions = new HashMap<>();
            for (Map.Entry<Integer, Session> session : tvShow.getSessions().entrySet()) {
                log.debug("Session: " + session.getKey());
                SessionResponse sessionResponse = new SessionResponse();
                sessionResponse.setUrl(session.getValue().getUrl());
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

        return result;

    }

    @Override
    public int addCapitulos(String title) {
        TvShow saveds = tvShowRepository.findByTitle(title);
        saveds.getSessions().forEach((key, value) -> {
            List<ScrapBeanResponse> scrapBeanResponses = donTorrentScraperService.getEpisode(value.getUrl());
            saveds.setSinopsis(scrapBeanResponses.get(0).getSinopsis());
            scrapBeanResponses.forEach(scrapBeanResponse -> {
                Torrent torrent = new Torrent();
                torrent.setMagnetLink(scrapBeanResponse.getUrl());
                Episode episode = new Episode();
                episode.setTorrent(torrent);
                episode.setSession(saveds.getSessions().get(key));
                Session session = saveds.getSessions().get(key);
                session.getEpisodes().put(scrapBeanResponse.getEpisode(), episode);
            });
        });
        tvShowRepository.save(saveds);
        return saveds.getSessions().size();

    }

    @Override
    public int addCapitulos(Long id) {
        TvShow resultBBDD = tvShowRepository.findById(id).get();
        resultBBDD.getSessions().forEach((key, value) -> {
            List<ScrapBeanResponse> scrapBeanResponses = donTorrentScraperService.getEpisode(value.getUrl());
            resultBBDD.setSinopsis(scrapBeanResponses.get(0).getSinopsis());
            scrapBeanResponses.forEach(scrapBeanResponse -> {
                Torrent torrent = new Torrent();
                torrent.setMagnetLink(scrapBeanResponse.getUrl());
                Episode episode = new Episode();
                episode.setTorrent(torrent);
                episode.setSession(resultBBDD.getSessions().get(key));
                Session session = resultBBDD.getSessions().get(key);
                session.getEpisodes().put(scrapBeanResponse.getEpisode(), episode);
            });
        });
        tvShowRepository.save(resultBBDD);
        return resultBBDD.getSessions().size();
    }

    private List<TvShow> save(List<ScrapBeanResponse> scrapBeanResponses) {
        List<TvShow> tvShows = new ArrayList<>();
        // mappear
        for (ScrapBeanResponse scrapBeanResponse : scrapBeanResponses) {
            // update or save
            TvShow tvShow = tvShowRepository.findByTitle(scrapBeanResponse.getName());
            log.debug("Calidad de la serie " + scrapBeanResponse.getName() + " es " + scrapBeanResponse.getQuality());
            if (tvShow != null) {
                Session session = new Session();
                session.setUrl(scrapBeanResponse.getUrl());
                session.setTvShow(tvShow);
                if (scrapBeanResponse.getQuality().equals(CalidadEnum.HD)) {
                    // check if session exists not to duplicate
                    if (tvShow.getSessions().containsKey(scrapBeanResponse.getSession())) {
                        log.info("La serie " + tvShow.getTitle() + " ya tiene la temporada "
                                + scrapBeanResponse.getSession());
                    } else {
                        tvShow.getSessions().put(scrapBeanResponse.getSession(), session);
                        log.info("Actualizamos la serie " + tvShow.getTitle() + " con " + tvShow.getSessions().size()
                                + " temporadas");
                        tvShowRepository.save(tvShow);

                    }
                }
            } else { // new tv show
                TvShow newTvShow = new TvShow();
                newTvShow.setTitle(scrapBeanResponse.getName());
                Session session = new Session();
                session.setUrl(scrapBeanResponse.getUrl());
                session.setTvShow(newTvShow);
                newTvShow.getSessions().put(scrapBeanResponse.getSession(), session);
                if (scrapBeanResponse.getQuality().equals(CalidadEnum.HD)) {
                    log.info("Insertamos una nueva serie " + newTvShow.getTitle() + " con la "
                            + newTvShow.getSessions().size() + " temporada");
                    tvShows.add(tvShowRepository.save(newTvShow));
                }
            }
        }
        return tvShows;
    }
}