package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.repositiories;

import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.models.SerieDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<SerieDAO, Long> {

}


