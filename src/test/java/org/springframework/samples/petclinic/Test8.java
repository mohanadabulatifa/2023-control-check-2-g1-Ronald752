package org.springframework.samples.petclinic;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.disease.Disease;
import org.springframework.samples.petclinic.disease.DiseaseService;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.samples.petclinic.vet.VetService;
import org.springframework.samples.petclinic.visit.UnfeasibleDiagnoseException;
import org.springframework.samples.petclinic.visit.Visit;
import org.springframework.samples.petclinic.visit.VisitRepository;
import org.springframework.samples.petclinic.visit.VisitService;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class Test8 {

	@MockBean
	VisitRepository vr;

    @Autowired
	VisitService visitService;    

	@Autowired 
	DiseaseService diseaseService;

	@Autowired 
	PetService  petService;

	@Autowired
	VetService vetservice;

	@Autowired
	EntityManager em;    

	@Test
	public void test8UnfeasibleDiagnoseDetected() {
		Visit v=createInvalidDiagnose();		
		assertThrows(UnfeasibleDiagnoseException.class, () -> visitService.save(v),"The treatment of the offer is the base treatment of dicount with id 1, an Unfeasible Offer exception should be thrown.");		
	}

	@Test
	public void test8ValidDiagnoseSaved() {
		Visit v=createValidDiagnose();				
		try {
			visitService.save(v);				
		} catch (UnfeasibleDiagnoseException e) {
			fail("The offer is valid, the exteption shold not be thrown! :"+e.getMessage());
		}
		verify(vr,times(1)).save(v);
	}

	private Visit createValidDiagnose() {
		Vet vet=vetservice.findVetById(1);
        Pet p=petService.findPetById(1);
		Disease d=diseaseService.findDiseaseById(1);
		Visit result=new Visit();
		result.setPet(p);
		result.setDiagnose(d);
		result.setDatetime(LocalDateTime.now());
		result.setVet(vet);
        return result;
	}

	private Visit createInvalidDiagnose() {
		Vet vet=vetservice.findVetById(1);
        Pet p=petService.findPetById(2);
		Disease d=diseaseService.findDiseaseById(1);
		Visit result=new Visit();
		result.setPet(p);
		result.setDiagnose(d);
		result.setDatetime(LocalDateTime.now());
		result.setVet(vet);
        return result;
	}
}
