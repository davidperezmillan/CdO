package com.davidperezmillan.cdovideostoreservice.application.services;

import com.davidperezmillan.cdovideostoreservice.application.usecases.InsertTvShowUsecase;
import com.davidperezmillan.cdovideostoreservice.application.scrap.dtos.CalidadEnum;
import com.davidperezmillan.cdovideostoreservice.application.scrap.dtos.ScrapBeanResponse;
import com.davidperezmillan.cdovideostoreservice.application.scrap.services.DonTorrentScraperService;
import com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.entities.Episode;
import com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.entities.Session;
import com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.entities.Torrent;
import com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.entities.TvShow;
import com.davidperezmillan.cdovideostoreservice.infrastructure.bbdd.repositories.TvShowRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class InsertTvShowService implements InsertTvShowUsecase {

    private DonTorrentScraperService donTorrentScraperService;

    private TvShowRepository tvShowRepository;

    @Autowired
    public InsertTvShowService(DonTorrentScraperService donTorrentScraperService, TvShowRepository tvShowRepository) {
        this.donTorrentScraperService = donTorrentScraperService;
        this.tvShowRepository = tvShowRepository;
    }

    @Override
    public int addTitleByLetter(String letter) {
        List<ScrapBeanResponse> scrapBeanResponses = donTorrentScraperService.getTvShow(letter);
        save(scrapBeanResponses);
        return scrapBeanResponses.size();
    }

    @Override
    public int addPremieres() {
        List<ScrapBeanResponse> scrapBeanResponses = donTorrentScraperService.getPremieres();
        save(scrapBeanResponses);
        return scrapBeanResponses.size();

    }

    @Override
    public int addCapitulos(String title) {
        TvShow resultBBDD = tvShowRepository.findByTitle(title);
        resultBBDD.getSessions().forEach((key, value) -> {
            List<ScrapBeanResponse> scrapBeanResponses = donTorrentScraperService.getEpisode(value.getUrl());
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

    @Override
    public int addCapitulos(Long id) {
        TvShow resultBBDD = tvShowRepository.findById(id).get();
        resultBBDD.getSessions().forEach((key, value) -> {
            List<ScrapBeanResponse> scrapBeanResponses = donTorrentScraperService.getEpisode(value.getUrl());
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

    private void save(List<ScrapBeanResponse> scrapBeanResponses) {
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
                    tvShowRepository.save(newTvShow);
                }
            }
        }
    }
}