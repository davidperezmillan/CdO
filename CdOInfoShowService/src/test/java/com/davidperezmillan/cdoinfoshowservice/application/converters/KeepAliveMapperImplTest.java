package com.davidperezmillan.cdoinfoshowservice.application.converters;

import com.davidperezmillan.cdoinfoshowservice.domain.model.Live;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.keepalive.request.KeepAliveRequest;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.keepalive.response.KeepAliveResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class KeepAliveMapperImplTest {

    @Mock
    private KeepAliveRequest mockRequest;

    @Mock
    private Live mockLive;

    @InjectMocks
    private KeepAliveMapperImpl keepAliveMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMapKeepAliveRequest() {
        // Configurar el comportamiento del mock
        String name = "David";
        when(mockRequest.getName()).thenReturn(name);

        // Llamar al método que queremos probar
        Live result = keepAliveMapper.map(mockRequest, Live.class);

        // Verificar los resultados
        assertEquals(name, result.getName());
    }

    @Test
    public void testMapLive() {
        // Configurar el comportamiento del mock
        String name = "David";
        when(mockLive.getName()).thenReturn(name);

        // Llamar al método que queremos probar
        KeepAliveResponse result = keepAliveMapper.map(mockLive, KeepAliveResponse.class);

        // Verificar los resultados
        assertEquals("Estoy vivo, " + name + "!!!", result.getComment());
    }
}
