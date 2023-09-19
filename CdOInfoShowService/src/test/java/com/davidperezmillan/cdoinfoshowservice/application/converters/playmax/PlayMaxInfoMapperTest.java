package com.davidperezmillan.cdoinfoshowservice.application.converters.playmax;

import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.info.InfoPlayMaxResponse;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.info.ResultInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayMaxInfoMapperTest {

    @Test
    void toSerie() {
        InfoPlayMaxResponse infoPlayMaxResponse = new InfoPlayMaxResponse();
        ResultInfo resultInfo = new ResultInfo();
        com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.info.Info info = new com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.info.Info();
        info.setYear(2020);
        info.setRating(5);
        info.setSinopsis("Sinopsis");
        info.setTitle("Title");
        info.setId(1);
        info.setPoster("Poster");
        info.setIsSerie(true);
        resultInfo.setInfo(info);
        infoPlayMaxResponse.setResult(resultInfo);

        Serie serie = PlayMaxInfoMapper.toSerie(infoPlayMaxResponse);

        assertEquals(serie.getTitle(), info.getTitle());
        assertEquals(serie.getId(), info.getId());
        assertEquals(serie.getPoster(), info.getPoster());
        assertEquals(serie.getIsSerie(), info.getIsSerie());
        assertEquals(serie.getInfo().getYear(), info.getYear());
        assertEquals(serie.getInfo().getRating(), info.getRating());
        assertEquals(serie.getInfo().getSynopsis(), info.getSinopsis());

    }
}