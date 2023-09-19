package com.davidperezmillan.cdoinfoshowservice.application.converters.playmax;

import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.search.FichaSearch;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.search.FichaSearchItem;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.search.ResultSearch;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.search.SearchPlayMaxResponse;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayMaxSearchMapperTest {

    @Test
    void mapList() {
        SearchPlayMaxResponse searchPlayMaxResponse = new SearchPlayMaxResponse();
        ResultSearch result = new ResultSearch();
        FichaSearch ficha = new FichaSearch();

        List<FichaSearchItem> fiches = new ArrayList<FichaSearchItem>();
        FichaSearchItem item1 = new FichaSearchItem();
        item1.setTitle("Serie 1");
        item1.setId(1);
        item1.setPoster("Poster 1");
        item1.setIsSerie(true);
        item1.setYear(2020);
        item1.setRating(5.0);
        fiches.add(item1);

        FichaSearchItem item2 = new FichaSearchItem();
        item2.setTitle("Serie 2");
        item2.setId(2);
        item2.setPoster("Poster 2");
        item2.setIsSerie(true);
        item2.setYear(2021);
        item2.setRating(4.0);
        fiches.add(item2);

        ficha.setFichas(fiches);
        result.setFicha(ficha);
        searchPlayMaxResponse.setResult(result);

        Serie[] series = PlayMaxSearchMapper.mapList(searchPlayMaxResponse);

        assertEquals(searchPlayMaxResponse.getResult().getFicha().getFichas().size(), series.length);
        assertEquals(searchPlayMaxResponse.getResult().getFicha().getFichas().get(0).getTitle(), series[0].getTitle());
        assertEquals(searchPlayMaxResponse.getResult().getFicha().getFichas().get(0).getId(), series[0].getId());
        assertEquals(searchPlayMaxResponse.getResult().getFicha().getFichas().get(0).getPoster(),
                series[0].getPoster());
        assertEquals(searchPlayMaxResponse.getResult().getFicha().getFichas().get(0).getIsSerie(),
                series[0].getIsSerie());
        assertEquals(searchPlayMaxResponse.getResult().getFicha().getFichas().get(0).getYear(),
                series[0].getInfo().getYear());
        assertEquals(searchPlayMaxResponse.getResult().getFicha().getFichas().get(0).getRating(),
                series[0].getInfo().getRating());

        assertEquals(searchPlayMaxResponse.getResult().getFicha().getFichas().get(1).getTitle(), series[1].getTitle());
        assertEquals(searchPlayMaxResponse.getResult().getFicha().getFichas().get(1).getId(), series[1].getId());
        assertEquals(searchPlayMaxResponse.getResult().getFicha().getFichas().get(1).getPoster(),
                series[1].getPoster());
        assertEquals(searchPlayMaxResponse.getResult().getFicha().getFichas().get(1).getIsSerie(),
                series[1].getIsSerie());
        assertEquals(searchPlayMaxResponse.getResult().getFicha().getFichas().get(1).getYear(),
                series[1].getInfo().getYear());
        assertEquals(searchPlayMaxResponse.getResult().getFicha().getFichas().get(1).getRating(),
                series[1].getInfo().getRating());

    }
}