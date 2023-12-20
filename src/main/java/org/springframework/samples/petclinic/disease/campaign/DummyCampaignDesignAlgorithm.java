package org.springframework.samples.petclinic.disease.campaign;

import java.util.Set;

import org.springframework.samples.petclinic.disease.Disease;
import org.springframework.samples.petclinic.visit.Visit;

public class DummyCampaignDesignAlgorithm implements CampaignDesignAlgorithm {

    @Override
    public Set<Disease> identifyDiseases(Set<Visit> visits) {
        return Set.of();
    }
    
}
