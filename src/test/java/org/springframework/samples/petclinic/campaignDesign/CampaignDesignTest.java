package org.springframework.samples.petclinic.campaignDesign;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.disease.Disease;
import org.springframework.samples.petclinic.disease.campaign.CampaignDesignAlgorithm;
import org.springframework.samples.petclinic.disease.campaign.ValidCampaingDesignAlgorithm;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.samples.petclinic.visit.Visit;

public class CampaignDesignTest {
    // This is your SUT:
    CampaignDesignAlgorithm algorithm=new ValidCampaingDesignAlgorithm();
    
    // We provide constants for the severity of the diseases:
    public static final Integer MORTAL=5;
    public static final Integer SEVERE=3;
    public static final Integer SLIGHT=1;

    // We provide four example diseases to ease your work:
    Disease ninjaContusion=createDisease(3,"Ninja contusion",SLIGHT);
    Disease shellShock=createDisease(4,"Shell shock!",SLIGHT);
    Disease insertCoin=createDisease(5,"Insert Coin!",SEVERE);
    Disease switchOff=createDisease(6,"Switch off",MORTAL);

    // Please specify as many tests as you need using structures similar to this:
    /* 
    @Test
    public void someTest(){
        // Arrangement / Configuration /Fixture 
         Set<Visit> data=Set.of(createVisit(ninjaContusion),createVisit(shellShock));
        // Act / SUT invocation 
        Set<Disease> actualResult=algorithm.identifyDiseases(data);
        // Asssert 
        assertEquals(TODO: COMPLETE HERE!);
    }        
     */


    // We provide this method to ease the creation of valid Visits
    public Visit createVisit(Disease d){
        Vet april=new Vet();
        april.setFirstName("April");
        april.setLastName("O'Neil");
        Visit v=new Visit();
        v.setDatetime(LocalDateTime.now());
        v.setPet(createValidPet());
        v.setVet(april);
        v.setDiagnose(d);
        return v;
    }

    public Pet createValidPet(){
        PetType pt=new PetType();
        pt.setName("Turtle");
        Owner splinter=new Owner();
        splinter.setFirstName("Splinter");
        splinter.setLastName("Master");
        Pet p=new Pet();
        p.setType(pt);
        p.setBirthDate(LocalDate.now());
        p.setOwner(splinter);
        p.setName(List.of("Leo","Donnie","Raph","Mickie").get((int)(Math.random()*4)));        
        return p;
    }

    public Disease createDisease(Integer id, String name, Integer severity){
        Disease disease=new Disease();
        disease.setId(id);
        disease.setName(name);
        disease.setSeverity(severity);
        return disease;
    }

    public void setAlgorithm(CampaignDesignAlgorithm value){
        this.algorithm=value;
    }
}
