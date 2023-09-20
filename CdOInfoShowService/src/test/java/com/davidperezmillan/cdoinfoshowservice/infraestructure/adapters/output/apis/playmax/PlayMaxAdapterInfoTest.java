package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax;

import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.info.InfoPlayMaxResponse;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
public class PlayMaxAdapterInfoTest {

    private static final int id = 178593;
    private final PlayMaxAdapter playMaxAdapter;

    public PlayMaxAdapterInfoTest() {
        playMaxAdapter = new PlayMaxAdapter();
    }

    @Test
    public void info_serie_ok() {
        InfoPlayMaxResponse response = playMaxAdapter.info(id);
        assertNotNull(response);
    }

    @Test
    public void info_film_no_null_id() {
        InfoPlayMaxResponse response = playMaxAdapter.info(id);
        assertEquals(id, response.getResult().getInfo().getId());
    }

    @Test
    public void info_serie_no_found() {
        InfoPlayMaxResponse response = playMaxAdapter.info(0);
        assertNull(response.getResult());
    }

}
