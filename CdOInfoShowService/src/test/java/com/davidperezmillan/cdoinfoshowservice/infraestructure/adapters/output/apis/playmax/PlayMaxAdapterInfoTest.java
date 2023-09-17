package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax;

import com.davidperezmillan.cdoinfoshowservice.application.converters.PlayMaxInfoMapperImpl;
import com.davidperezmillan.cdoinfoshowservice.application.converters.PlayMaxSearchMapperImpl;
import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
public class PlayMaxAdapterInfoTest {

    private static final int id = 178593;
    private final PlayMaxAdapter playMaxAdapter;

    public PlayMaxAdapterInfoTest() {
        playMaxAdapter = new PlayMaxAdapter(new PlayMaxSearchMapperImpl(), new PlayMaxInfoMapperImpl());
    }

    @Test
    public void info_serie_ok() {
        Serie response = playMaxAdapter.info(id);
        assertNotNull(response);
    }

    @Test
    public void info_film_no_null_id() {
        Serie response = playMaxAdapter.info(id);
        assertEquals(id, response.getId());
    }

    @Test
    public void info_serie_no_found() {
        Serie response = playMaxAdapter.info(0);
        assertNull(response);
    }

}
