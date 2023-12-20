package org.springframework.samples.petclinic;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.disease.Disease;
import org.springframework.samples.petclinic.disease.Symptom;
import org.springframework.samples.petclinic.disease.SymptomRepository;
import org.springframework.samples.petclinic.disease.SymptomService;
import org.springframework.samples.petclinic.disease.Treatment;
import org.springframework.samples.petclinic.disease.TreatmentRepository;
import org.springframework.samples.petclinic.disease.TreatmentService;
import org.springframework.samples.petclinic.pet.PetType;

@ExtendWith(MockitoExtension.class)
public class Test5 extends ReflexiveTest{
     @Mock
    TreatmentRepository tr;
    @Mock
    SymptomRepository sr;

    
    TreatmentService ts;    
    SymptomService ss;
    
    @BeforeEach
    public void configuation(){
        ts=new TreatmentService(tr);
        ss=new SymptomService(sr);
    }
    
    @Test
    public void test5CheckTransactionalityOfTreatmentService(){
        checkTransactional(TreatmentService.class,"save", Treatment.class);        
        checkTransactional(TreatmentService.class,"getAll");
    }
    
    @Test
    public void test5CheckTransactionalityOfSymptomService(){
        checkTransactional(SymptomService.class,"save", Symptom.class);        
        checkTransactional(SymptomService.class,"getAll");
    }    
    
    @Test
    public void test5TreatmentServiceCanGetTreatments(){
        assertNotNull(ts,"TreatmentService was not injected by spring");
        when(tr.findAll()).thenReturn(List.of());
        List<Treatment> offers=ts.getAll();
        assertNotNull(offers,"The list of Treatments found by the TreatmentService was null");
        // The test fails if the service does not invoke the findAll of the repository:
        verify(tr).findAll();            
    }
    

    @Test
    public void test5SymptomServiceCanGetSymptoms(){
        assertNotNull(ss,"SymptomService was not injected by spring");
        when(sr.findAll()).thenReturn(List.of());
        List<Symptom> discounts=ss.getAll();
        assertNotNull(discounts,"The list of symptoms found by the SymptomService was null");
        // The test fails if the service does not invoke the findAll of the repository:
        verify(sr).findAll();               
    }

    @Test
    public void test5SymptomServiceCanSaveSymptoms(){
        assertNotNull(ss,"SymptomService was not injected by spring");
        when(sr.save(any(Symptom.class))).thenReturn(null);
        Disease d=createValidDisease();
        Symptom s=new Symptom();
        setValue(s,"name",String.class,"A valid symptom");
        s.setIncludes(Set.of(d));                
        ss.save(s);        
        // The test fails if the service does not invoke the save function of the repository:
        verify(sr).save(s);     
    }        

    @Test
    public void test5TreatmentServiceCanSaveTreatments() {        
        assertNotNull(ts,"OfferService was not injected by spring");
        when(tr.save(any(Treatment.class))).thenReturn(null);
        Disease d=createValidDisease();
        Treatment t=new Treatment();
        setValue(t, "name", String.class, "Pucherito de la mama");
        t.setRecommendedFor(Set.of(d));        
            ts.save(t);        
        // The test fails if the service does not invoke the save function of the repository:
        verify(tr).save(t);                
    }

    private Disease createValidDisease() {
        PetType pt=new PetType();
        pt.setId(1);
        pt.setName("cat");
        Disease d=new Disease();
        d.setName("COVID19");
        d.setDescription("Better not!");
        d.setSeverity(3);
        d.setSusceptiblePetTypes(Set.of(pt));
        return d;
    }

}
