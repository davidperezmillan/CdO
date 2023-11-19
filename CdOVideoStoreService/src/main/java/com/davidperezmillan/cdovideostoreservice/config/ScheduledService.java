package com.davidperezmillan.cdovideostoreservice.config;

import com.davidperezmillan.cdovideostoreservice.application.services.InsertTvShowService;
import com.davidperezmillan.cdovideostoreservice.infrastructure.rest.dtos.TvShowResponse;
import com.davidperezmillan.cdovideostoreservice.infrastructure.slack.SlackService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ScheduledService {

    InsertTvShowService insertTvShowService;

    @Autowired
    public ScheduledService(InsertTvShowService insertTvShowService) {
        this.insertTvShowService = insertTvShowService;
    }

    // se ejecuta cada 15 minutos
    @Scheduled(cron = "${scheduled.cron.expression}")
    public void ejecutarTarea() {
        // Lógica de tu tarea programada
        log.info("Tarea programada ejecutada");
        List<TvShowResponse> nuevos = insertTvShowService.addPremieres();
        String texto = nuevos.stream()
                .map(TvShowResponse::getTitle)
                .collect(Collectors.joining("/n"));
        SlackService.sendMessage("Tarea programada ejecutada: "+ texto);
    }
}