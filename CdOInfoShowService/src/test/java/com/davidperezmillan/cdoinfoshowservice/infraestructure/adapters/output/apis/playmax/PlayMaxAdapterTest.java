package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax;

import com.davidperezmillan.cdoinfoshowservice.application.converters.PlayMaxSearchMapperImpl;
import com.davidperezmillan.cdoinfoshowservice.domain.model.Serie;
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
        playMaxAdapter = new PlayMaxAdapter(new PlayMaxSearchMapperImpl());
    }

    @Test
    public void search_serie_ok() {
        Serie[] response = playMaxAdapter.search(searchQuery);
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

}
