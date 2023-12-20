package org.springframework.samples.petclinic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.samples.petclinic.disease.Symptom;
import org.springframework.samples.petclinic.disease.Treatment;

import jakarta.persistence.EntityManager;

@DataJpaTest()
public class Test3 extends ReflexiveTest {    
    @Autowired
    EntityManager em;    
    
    @Test
    public void test3InitialTreatments(){                        
        Treatment t1=em.find(Treatment.class,1);
        assertNotNull(t1,"There should exist a Treatment with id:1");
        assertEquals("aspirin",getFieldValueReflexively(t1, "name"));
        assertEquals("Aspirin, also known by its generic name acetylsalicylic acid, is a widely used medication with analgesic (pain-relieving), antipyretic (fever-reducing), and anti-inflammatory properties.",t1.getDescription());
        assertEquals(12,t1.getDose());        

        Treatment t2=em.find(Treatment.class,2); 
        assertNotNull(t2,"There should exist a Treatment with id:2");       
        assertEquals("paracetamol",getFieldValueReflexively(t2, "name"));
        assertEquals("Paracetamol, known as acetaminophen in the United States and Canada, is a widely used over-the-counter (OTC) medication with analgesic (pain-relieving) and antipyretic (fever-reducing) properties.",t2.getDescription());
        assertEquals(20,t2.getDose());
        

    }
    @Test
    public void test3InitialSymptoms()
    {
        Symptom symptom1 = em.find(Symptom.class, 1);
        assertNotNull(symptom1,"Cannot find sypmtom with id "+1);
        assertEquals("Cough",getFieldValueReflexively(symptom1,"name"));
        assertNull(symptom1.getDescription());
        
        Symptom symptom2 = em.find(Symptom.class, 2);        
        assertNotNull(symptom2,"Cannot find sypmtom with id "+2);
        assertEquals("Hair loss",getFieldValueReflexively(symptom2,"name"));
        assertEquals("Hair loss in animals, also known as alopecia, can be a common and concerning symptom with various potential underlying causes.",symptom2.getDescription());
        
    }        
    
}
