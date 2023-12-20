package org.springframework.samples.petclinic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.samples.petclinic.disease.Symptom;
import org.springframework.samples.petclinic.disease.Treatment;
import org.springframework.samples.petclinic.visit.Visit;

import jakarta.persistence.EntityManager;

@DataJpaTest
public class Test4 extends ReflexiveTest {
    
    @Autowired
    EntityManager em;
    
    @Test
    public void test4TreatmentLinks() {        
        checkContainsById(Treatment.class,1,"getRecommendedFor",2,em);
        checkContainsById(Treatment.class,2,"getRecommendedFor",1,em);        
    }

    @Test
    public void test4SymptomsLinks() {        
        checkContainsById(Symptom.class,1,"getIncludes",2,em);
        checkContainsById(Symptom.class,1,"getIncludes",3,em);
        checkContainsById(Symptom.class,2,"getIncludes",1,em);
        checkContainsById(Symptom.class,2,"getIncludes",3,em);
        checkContainsById(Symptom.class,1,"getExcludes",1,em);
        checkContainsById(Symptom.class,2,"getExcludes",2,em);
    }

    @Test
    public void test4VisitsLinks() {
                         
        checkContainsById(Visit.class,1,"getSymptoms",1,em);
        checkContainsById(Visit.class,1,"getSymptoms",2,em);
        
    }
    
}
