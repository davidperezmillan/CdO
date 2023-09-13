package com.davidperezmillan.cdoinfoshowservice.application.converters;

import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.request.FindSeriesRequest;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.response.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class FindSerieMapperImplTest {

    @Mock
    private FindSeriesRequest mockRequest;

    @Mock
    private Serie mockSerie;

    @InjectMocks
    private FindSerieMapperImpl findSerieMapper;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    void map() {
        String title = "Ahsoka";
        when(mockRequest.getTitle()).thenReturn(title);
        Serie result = findSerieMapper.map(mockRequest, Serie.class);
        assertEquals(title, result.getTitle());
    }

    @Test
    void testMap() {
        String title = "Ahsoka";
        when(mockSerie.getTitle()).thenReturn(title);
        Item result = findSerieMapper.map(mockSerie, Item.class);
        assertEquals(title, result.getTitle());
    }
}