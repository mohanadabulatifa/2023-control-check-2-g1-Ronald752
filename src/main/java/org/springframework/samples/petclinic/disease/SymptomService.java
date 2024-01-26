package org.springframework.samples.petclinic.disease;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public class SymptomService {
    SymptomRepository repo;
    

    public SymptomService(SymptomRepository sr){
        this.repo=sr;
    }
	@Transactional(readOnly = true)
    public List<Symptom> getAll() {
        return repo.findAll();
    }
	@Transactional
    public Symptom save(Symptom s) {
        return repo.save(s);
    }
}
