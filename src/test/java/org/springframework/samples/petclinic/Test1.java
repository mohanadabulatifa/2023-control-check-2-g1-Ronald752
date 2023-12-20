package org.springframework.samples.petclinic;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.disease.Disease;
import org.springframework.samples.petclinic.disease.Symptom;
import org.springframework.samples.petclinic.disease.SymptomRepository;
import org.springframework.samples.petclinic.disease.Treatment;
import org.springframework.samples.petclinic.disease.TreatmentRepository;
import org.springframework.stereotype.Service;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class Test1 extends ReflexiveTest{

    @Autowired(required = false)
    SymptomRepository symptomsRepo;
    @Autowired(required = false)
    TreatmentRepository treatmentsRepo;
    
    @Autowired
    EntityManager em;

    @Test
    public void test1RepositoriesExist(){
        assertNotNull(symptomsRepo,"The symptoms repository was not injected into the tests, its autowired value was null");
        assertNotNull(treatmentsRepo,"The treatments repository was not injected into the tests, its autowired value was null");
        test1RepositoriesContainsMethod();
    }

    public void test1RepositoriesContainsMethod(){
        if(symptomsRepo!=null){
            Object v=symptomsRepo.findById(12);
            assertFalse(null!=v && ((Optional)v).isPresent(), "No result (null) should be returned for a symptom that does not exist");
        }else
            fail("The symptoms repository was not injected into the tests, its autowired value was null");
        
        if(treatmentsRepo!=null){
            Object v=treatmentsRepo.findById(12);
            assertFalse(null!=v && ((Optional)v).isPresent(), "No result (null) should be returned for a treatment that does not exist");
        }else
            fail("The treatments repository was not injected into the tests, its autowired value was null");
    }
    
    

    
    @Test
    public void test1CheckTreatmentsConstraints() {
        Map<String,List<Object>> invalidValues=Map.of(
                                            "name",     List.of(
                                                    "      ","a",
                                                    "En un lugar de la Mancha, de cuyo nombre no quiero acordarme, no ha mucho tiempo que vivía un hidalgo de los de lanza en astillero, adarga antigua, rocín flaco y galgo corredor. Una olla de algo más vaca que carnero, salpicón las más noches, duelos y quebrantos los sábados, lentejas los viernes, algún palomino de añadidura los domingos, consumían las tres partes de su hacienda. "),
                                            "dose",     List.of(
                                                        -1,0       
                                                    )                                            
                                            );


        Treatment t=createValidTreatment(em);
        em.persist(t);
        
        checkThatFieldsAreMandatory(t, em, "name","dose");        
        
        checkThatValuesAreNotValid(t, invalidValues,em);   
    }
    @Test
    public void test1CheckSymptomsContraints() {
         Map<String,List<Object>> invalidValues=Map.of(
                                            "name",     List.of(
                                                    "      ","a",
                                                    "En un lugar de la Mancha, de cuyo nombre no quiero acordarme, no ha mucho tiempo que vivía un hidalgo de los de lanza en astillero, adarga antigua, rocín flaco y galgo corredor. Una olla de algo más vaca que carnero, salpicón las más noches, duelos y quebrantos los sábados, lentejas los viernes, algún palomino de añadidura los domingos, consumían las tres partes de su hacienda. ")                                            
                                            );


        Symptom s=createValidSymptom(em);
        em.persist(s);
        
        checkThatFieldsAreMandatory(s, em, "name");        
        
        checkThatValuesAreNotValid(s, invalidValues,em);   
    }

    
    @Test
    public void test1CheckTreatmentAnnotations() {        
        assertTrue(classIsAnnotatedWith(Treatment.class,Entity.class));
    }

    @Test
    public void test1CheckSymptomAnnotations() {
        assertTrue(classIsAnnotatedWith(Symptom.class,Entity.class));
    }

    public static Symptom createValidSymptom(EntityManager em){        
        Symptom o=new Symptom();        
        setValue(o,"name",String.class,"Un síntoma válido");
        return o;
    }

    public static Treatment createValidTreatment(EntityManager em){
        Treatment t=new Treatment();
        setValue(t,"name",String.class,"Un tratamiento válido");
        t.setDose(10);
        t.setRecommendedFor(Set.of(em.find(Disease.class,1)));
        return t;
    }
}
