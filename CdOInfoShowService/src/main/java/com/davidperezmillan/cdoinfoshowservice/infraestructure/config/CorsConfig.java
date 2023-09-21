package com.davidperezmillan.cdoinfoshowservice.infraestructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost").allowedOrigins("http://192.168.1.195") // Agrega
                                                                                                                  // aquí
                                                                                                                  // los
                                                                                                                  // orígenes
                                                                                                                  // permitidos
                .allowedMethods("GET", "POST", "PUT", "DELETE"); // Agrega aquí los métodos HTTP permitidos
                                                                 // .allowedHeaders("*")

        // Agrega aquí los encabezados permitidos .allowCredentials(true);

    }

}
