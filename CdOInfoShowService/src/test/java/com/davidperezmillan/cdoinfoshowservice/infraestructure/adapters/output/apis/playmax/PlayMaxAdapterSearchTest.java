package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax;

import com.davidperezmillan.cdoinfoshowservice.application.converters.PlayMaxInfoMapperImpl;
import com.davidperezmillan.cdoinfoshowservice.application.converters.PlayMaxSearchMapperImpl;
import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
public class PlayMaxAdapterSearchTest {

    private static final String searchQuery = "Ahsoka";

    private final PlayMaxAdapter playMaxAdapter;

    public PlayMaxAdapterSearchTest() {
        playMaxAdapter = new PlayMaxAdapter(new PlayMaxSearchMapperImpl(), new PlayMaxInfoMapperImpl());
    }

    @Test
    public void search_serie_ok() {
        Serie[] response = playMaxAdapter.search(searchQuery);
        assertNotNull(response);
    }

    @Test
    public void search_serie_no_found() {
        Serie[] response = playMaxAdapter.search(null);
        assertNull(response);
    }

}