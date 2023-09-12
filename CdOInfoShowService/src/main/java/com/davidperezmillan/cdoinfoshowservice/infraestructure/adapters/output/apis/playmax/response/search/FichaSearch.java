package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.search;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FichaSearch {
    private PaginationSearch pagination;
    private List<FichaSearchItem> fichas;
}