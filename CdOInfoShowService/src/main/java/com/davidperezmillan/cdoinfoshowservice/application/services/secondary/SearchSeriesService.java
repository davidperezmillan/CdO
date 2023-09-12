package com.davidperezmillan.cdoinfoshowservice.application.services.secondary;

import com.davidperezmillan.cdoinfoshowservice.domain.model.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.PlayMaxAdapter;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.search.SearchPlayMaxResponse;

public class SearchSeriesService {

    public Serie[] search(Serie search) {
        // TODO call service to search serie playmax
        PlayMaxAdapter playMaxAdapter = new PlayMaxAdapter();
        SearchPlayMaxResponse searchPlayMaxResponse = playMaxAdapter.search(search.getTitle());
        Serie[] listSearchs = new Serie[searchPlayMaxResponse.getResult().getFicha().getFichas().size()];
        for (int i = 0; i < searchPlayMaxResponse.getResult().getFicha().getFichas().size(); i++) {
            // TODO Converter in infrastructure
            Serie s = new Serie();
            s.setTitle(searchPlayMaxResponse.getResult().getFicha().getFichas().get(i).getTitle());
            s.setId(searchPlayMaxResponse.getResult().getFicha().getFichas().get(i).getId());
            s.setPoster(searchPlayMaxResponse.getResult().getFicha().getFichas().get(i).getPoster());
            s.setIsSerie(searchPlayMaxResponse.getResult().getFicha().getFichas().get(i).getIsSerie());
            s.setYear(searchPlayMaxResponse.getResult().getFicha().getFichas().get(i).getYear());
            s.setRating(searchPlayMaxResponse.getResult().getFicha().getFichas().get(i).getRating());
            listSearchs[i] = s;
        }
        return listSearchs;
    }
}
