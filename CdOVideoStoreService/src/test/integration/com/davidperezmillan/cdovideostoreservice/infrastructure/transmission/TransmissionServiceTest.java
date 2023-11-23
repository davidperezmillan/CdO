package com.davidperezmillan.cdovideostoreservice.infrastructure.transmission;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.davidperezmillan.cdovideostoreservice.infrastructure.transmission.dtos.AddTorrentRequest;
import com.davidperezmillan.cdovideostoreservice.infrastructure.transmission.dtos.AddTorrentResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TransmissionServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TransmissionService transmissionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addTorrent_shouldCallRestTemplateWithCorrectParameters() {
        // Given
        String torrentUrl = "http://example.com/torrent";
        AddTorrentResponse responseEntity = new AddTorrentResponse();

        // When
        when(restTemplate.postForObject(any(String.class), any(AddTorrentRequest.class), eq(AddTorrentResponse.class)))
                .thenReturn(responseEntity);

        // Then
        transmissionService.addTorrent(torrentUrl);
        verify(restTemplate).postForObject(any(String.class), any(AddTorrentRequest.class), eq(AddTorrentResponse.class));
    }

    @Test
    public void addTorrent_shouldHandleException_whenRestTemplateThrowsException() {
        // Given
        String torrentUrl = "http://invalid.com/torrent";

        // When
        when(restTemplate.postForObject(any(String.class), any(AddTorrentRequest.class), eq(AddTorrentResponse.class)))
                .thenThrow(new RuntimeException());

        // Then
        try {
            transmissionService.addTorrent(torrentUrl);
        } catch (Exception e) {
            // Exception is expected
        }
        verify(restTemplate).postForObject(any(String.class), any(AddTorrentRequest.class), eq(AddTorrentResponse.class));
    }
}