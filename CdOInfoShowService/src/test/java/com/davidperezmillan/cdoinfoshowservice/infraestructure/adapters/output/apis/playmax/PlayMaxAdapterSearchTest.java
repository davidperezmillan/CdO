package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax;

import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.search.SearchPlayMaxResponse;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@Log4j2
public class PlayMaxAdapterSearchTest {

    private static final String searchQuery = "Obi";

    private final PlayMaxAdapter playMaxAdapter;

    public PlayMaxAdapterSearchTest() {
        playMaxAdapter = new PlayMaxAdapter();
    }

    @Test
    public void search_serie_ok() {
        SearchPlayMaxResponse response = playMaxAdapter.search(searchQuery);
        assertNotNull(response);
    }

    @Test
    public void search_serie_no_found() {
        SearchPlayMaxResponse response = playMaxAdapter.search(null);
        assertNull(response);
    }

}
