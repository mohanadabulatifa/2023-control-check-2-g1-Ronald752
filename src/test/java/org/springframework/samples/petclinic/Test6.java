package org.springframework.samples.petclinic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.samples.petclinic.disease.Disease;
import org.springframework.samples.petclinic.disease.DiseaseRepository;
import org.springframework.samples.petclinic.pet.PetType;

import jakarta.persistence.EntityManager;

@DataJpaTest
public class Test6 {
       
    @Autowired
    DiseaseRepository dr;
        
    @Autowired
    EntityManager em;
    @Test
    public void test() {
        validatefindByComplexCriteria();
    }    

    private void 
    validatefindByComplexCriteria() {
        PetType cats=em.find(PetType.class, 1);
        PetType dogs=em.find(PetType.class, 2);
        PetType hamsters=em.find(PetType.class, 6);
        Set<Disease> diseases = dr.findEpidemicDiseases(            
            Set.of(dogs,hamsters),    
            LocalDateTime.of(2023,11,12,0,0,0),
            LocalDateTime.of(2023, 12, 11,0,0,0),
            3
        );
        assertNotNull(diseases);
        assertEquals(1, diseases.size());
        assertEquals("Rabies Shot",diseases.iterator().next().getName());

        diseases = dr.findEpidemicDiseases(            
            Set.of(dogs,hamsters),    
            LocalDateTime.of(2023,11,12,0,0,0),
            LocalDateTime.of(2023, 12, 11,0,0,0),
            1
        );
        assertNotNull(diseases);
        assertEquals(2, diseases.size());
        
        diseases = dr.findEpidemicDiseases(            
            Set.of(dogs,hamsters),    
            LocalDateTime.of(2023,11,12,0,0,0),
            LocalDateTime.of(2023, 12, 11,0,0,0),
            5
        );
        assertNotNull(diseases);
        assertEquals(0, diseases.size());

        diseases = dr.findEpidemicDiseases(            
            Set.of(dogs,hamsters),    
            LocalDateTime.of(2000,11,12,0,0,0),
            LocalDateTime.of(2000, 12, 11,0,0,0),
            1
        );
        assertNotNull(diseases);
        assertEquals(0, diseases.size());

        diseases = dr.findEpidemicDiseases(            
            Set.of(dogs),    
            LocalDateTime.of(2023,11,12,0,0,0),
            LocalDateTime.of(2023, 12, 11,0,0,0),
            2
        );
        assertNotNull(diseases);
        assertEquals(1, diseases.size());
        assertEquals("Rabies Shot",diseases.iterator().next().getName());
        diseases = dr.findEpidemicDiseases(            
            Set.of(cats),    
            LocalDateTime.of(2023,11,12,0,0,0),
            LocalDateTime.of(2023, 12, 11,0,0,0),
            1
        );
        assertNotNull(diseases);
        assertEquals(1, diseases.size());
        assertEquals("Rabies Shot",diseases.iterator().next().getName());        

    }
}
