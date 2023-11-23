package com.davidperezmillan.cdovideostoreservice.infrastructure.transmission.dtos;

public class AddTorrentRequest {

    private String torrentLink;

    public AddTorrentRequest(String torrentLink) {
        this.torrentLink = torrentLink;
    }
}
