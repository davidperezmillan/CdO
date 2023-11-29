package com.davidperezmillan.cdovideostoreservice.config;

import com.davidperezmillan.cdovideostoreservice.application.services.InsertTvShowService;
import com.davidperezmillan.cdovideostoreservice.infrastructure.rest.dtos.TvShowResponse;
import com.davidperezmillan.cdovideostoreservice.infrastructure.slack.SlackService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class ScheduledService {

    InsertTvShowService insertTvShowService;

    @Autowired
    public ScheduledService(InsertTvShowService insertTvShowService) {
        this.insertTvShowService = insertTvShowService;
    }

    @Scheduled(cron = "${scheduled.cron.expression}")
    public void ejecutarTarea() {
        // Lógica de tu tarea programada
        log.info("Tarea programada ejecutada");
        List<TvShowResponse> nuevos = insertTvShowService.addPremieres();
        StringBuilder text = new StringBuilder();
        if (!nuevos.isEmpty()) {
            for (TvShowResponse nuevo : nuevos) {
                log.info("Nueva serie: " + nuevo.getTitle());
                int capitulos = insertTvShowService.addCapitulos(nuevo.getId());
                text.append("Nueva serie añadida: ").append(nuevo.getTitle()).append(" con ").append(capitulos)
                        .append(" capítulos\n");
            }
            SlackService.sendMessage(text.toString());
        }
    }
}