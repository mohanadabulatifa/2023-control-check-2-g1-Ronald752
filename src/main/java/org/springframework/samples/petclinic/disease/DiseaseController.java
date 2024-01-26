package org.springframework.samples.petclinic.disease;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.exceptions.ResourceNotFoundException;
import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/api/v1/diseases")
public class DiseaseController {

    private final DiseaseService ds;

    @Autowired
    public DiseaseController(DiseaseService ds){
        this.ds = ds;
    }

    @GetMapping
    public ResponseEntity<List<Disease>> findAll(){
        List<Disease> result = ds.findDiseases();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "{diseaseId}")
	public ResponseEntity<Disease> findById(@PathVariable("diseaseId") int id) {
        Disease d = ds.findDiseaseById(id);
        if(d==null)
            throw new ResourceNotFoundException("Disease","id",id);
		return new ResponseEntity<>(d, HttpStatus.OK);
	}
}
