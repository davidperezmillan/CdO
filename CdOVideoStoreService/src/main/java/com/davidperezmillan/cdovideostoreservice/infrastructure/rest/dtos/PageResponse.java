package com.davidperezmillan.cdovideostoreservice.infrastructure.rest.dtos;

import lombok.Data;

import java.util.List;

@Data
public class PageResponse {

    private int totalPages;
    private long totalElements;
    private int number;
    private int size;

    private List<Object> content;
}
