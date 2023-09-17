package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.repositiories;

import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.bbdd.entities.SerieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepository extends JpaRepository<SerieEntity, Long> {

}
