package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd;

import com.davidperezmillan.cdoinfoshowservice.domain.model.serie.Serie;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.converter.SerieDAOMapper;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.models.SerieDAO;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.repositiories.SerieRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Log4j2
public class SerieService {
    private final SerieRepository serieRepository;

    private final ModelMapper modelMapperToDao = SerieDAOMapper.toSerieDAO();
    private final ModelMapper modelMapper = SerieDAOMapper.toSerie();

    @Autowired
    public SerieService(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    // Puedes agregar más métodos según sea necesario
    public void createSerie(Serie serie) {
        log.info("Saving serie: " + serie);
        SerieDAO serieDAO = modelMapper.map(serie, SerieDAO.class);
        log.info("Serie mapped to SerieDAO: " + serieDAO);
        SerieDAO savedSerie =serieRepository.save(serieDAO);
        log.info("Serie saved with id: " + savedSerie);
    }

    public Serie getSerieById(int id) {
        SerieDAO serieDAO = serieRepository.findById((long) id).orElse(null);
        return modelMapperToDao.map(serieDAO, Serie.class);
    }

}
