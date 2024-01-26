package org.springframework.samples.petclinic.disease;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TreatmentRepository extends CrudRepository<Treatment, Integer>{

    List<Treatment> findAll();

    Treatment save(Treatment any);
    
}
