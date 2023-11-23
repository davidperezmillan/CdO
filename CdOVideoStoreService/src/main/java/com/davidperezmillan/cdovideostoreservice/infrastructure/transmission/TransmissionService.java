package com.davidperezmillan.cdovideostoreservice.infrastructure.transmission;

import com.davidperezmillan.cdovideostoreservice.infrastructure.transmission.dtos.AddTorrentRequest;
import com.davidperezmillan.cdovideostoreservice.infrastructure.transmission.dtos.AddTorrentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransmissionService {

    @Value("${transmission.host}")
    private String transmissionHost;

    @Value("${transmission.port}")
    private int transmissionPort;

    private final RestTemplate restTemplate;

    public TransmissionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void addTorrent(String torrentLink) {
        restTemplate.postForObject("http://" + transmissionHost + ":" + transmissionPort + "/transmission/rpc",
                new AddTorrentRequest(torrentLink), AddTorrentResponse.class);
    }

}