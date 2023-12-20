package org.springframework.samples.petclinic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.samples.petclinic.disease.Symptom;
import org.springframework.samples.petclinic.disease.Treatment;
import org.springframework.samples.petclinic.visit.Visit;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@DataJpaTest()
public class Test2 extends ReflexiveTest{
    
    @Autowired(required = false)
    EntityManager em;         

    @Test
    public void test2TreatmentAnnotations() {
        checkThatFieldIsAnnotatedWith(Treatment.class, "recommendedFor", ManyToMany.class);                          
    }

    @Test
    public void test2SymptomAnnotations() {        
        checkThatFieldIsAnnotatedWith(Symptom.class, "includes", ManyToMany.class);        
        checkThatFieldIsAnnotatedWith(Symptom.class, "excludes", ManyToMany.class);                
    }

    @Test
    public void test2VisitAnnotationsAndConstraints(){
        checkThatFieldIsAnnotatedWith(Visit.class, "symptoms", ManyToMany.class);
    }

    @Test
    private void test2TreatmentConstraints() {
        Treatment t=Test1.createValidTreatment(em);
        checkThatFieldsAreMandatory(t, em,"recommendedFor");        
    }

    @Test
    private void test2SymptomsConstraints() {
        Symptom s=Test1.createValidSymptom(em);
        checkThatFieldsAreMandatory(s, em,"includes");
                
    }


}
