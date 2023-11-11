package com.davidperezmillan.cdovideostoreservice.application.scrap.services;

import com.davidperezmillan.cdovideostoreservice.application.scrap.dtos.ScrapBeanResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DonTorrentScraperServiceTest {

    DonTorrentScraperService donTorrentScraperService;

    @Autowired
    public DonTorrentScraperServiceTest() {
        donTorrentScraperService = new DonTorrentScraperService();
    }

    @Test
    void getTvShow() {
        List<ScrapBeanResponse> request = donTorrentScraperService.getTvShow("Ashoka");

        System.out.println(request);

    }

    @Test
    void getPremieres() {
        List<ScrapBeanResponse> request = donTorrentScraperService.getPremieres();
        System.out.println(request);
    }

    @Test
    void getEpisode() {
        List<ScrapBeanResponse> request = donTorrentScraperService
                .getEpisode("/serie/103152/103156/Ahsoka-1-Temporada-720p");
        System.out.println(request);
    }
}