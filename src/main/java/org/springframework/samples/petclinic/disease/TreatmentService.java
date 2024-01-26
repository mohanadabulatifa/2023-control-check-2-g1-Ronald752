package org.springframework.samples.petclinic.disease;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public class TreatmentService {
    private TreatmentRepository repo;

    public TreatmentService(TreatmentRepository tr){
        this.repo=tr;
    }
	@Transactional(readOnly = true)
    public List<Treatment> getAll() {
           return repo.findAll();
        
    }
	@Transactional
    public Treatment save(Treatment t) {
        return repo.save(t);
    }
}
