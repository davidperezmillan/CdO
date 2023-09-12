package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax;

import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.info.InfoPlayMaxResponse;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.search.SearchPlayMaxResponse;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
public class PlayMaxAdapterTest {

    private static final String searchQuery = "Ahsoka";
    private static final int id = 135591;

    private PlayMaxAdapter playMaxAdapter;

    public PlayMaxAdapterTest() {
        playMaxAdapter = new PlayMaxAdapter();
    }

    @Test
    public void search_serie_ok() {
        SearchPlayMaxResponse response = playMaxAdapter.search(searchQuery);
        assertNotNull(response);
    }

    @Test
    public void info_serie_ok() {
        InfoPlayMaxResponse response = playMaxAdapter.info(id);
        assertNotNull(response);
    }

    @Test
    public void info_serie_no_null_id() {
        InfoPlayMaxResponse response = playMaxAdapter.info(id);
        assertNotNull(response.getResult().getInfo().getId());
    }

    @Test
    public void search_and_info_serie_ok() {
        SearchPlayMaxResponse response = playMaxAdapter.search(searchQuery);
        log.debug(response.getResult().getFicha().getFichas().get(0).getTitle());
        InfoPlayMaxResponse infoResponse = playMaxAdapter
                .info(response.getResult().getFicha().getFichas().get(0).getId());
        log.debug("infoResponse: {}", infoResponse);
        assertEquals(response.getResult().getFicha().getFichas().get(0).getId(),
                infoResponse.getResult().getInfo().getId());

    }
}
