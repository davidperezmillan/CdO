package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.search;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PaginationSearch {
    private int results;
    private int limit;
    private int prev;
    private int next;
}