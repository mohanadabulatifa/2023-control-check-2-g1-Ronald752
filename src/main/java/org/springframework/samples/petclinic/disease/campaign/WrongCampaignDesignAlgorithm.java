package org.springframework.samples.petclinic.disease.campaign;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.samples.petclinic.disease.Disease;
import org.springframework.samples.petclinic.visit.Visit;

public class WrongCampaignDesignAlgorithm implements CampaignDesignAlgorithm{

    // This implementation sorts only by severity and in ascending order (it should be descending!)
    @Override
    public Set<Disease> identifyDiseases(Set<Visit> visits) {
        if(visits==null || visits.isEmpty())
            return Set.of();
        // Buscamos cual es la severidad máxima entre las enfermedades detectadas:            
        Integer maxServerity=visits.stream().filter(v->v.getDiagnose()!=null).map((v)->v.getDiagnose().getSeverity()).distinct().min(Integer::compare).get();
        // Filtramos las visitas sin diagnóstico o que no corresponden con enfermedades de máxima gravedad:
        List<Visit> filteredVisits=visits.stream().filter(v->v.getDiagnose()!=null).filter(v->v.getDiagnose().getSeverity()==maxServerity).toList();
        // Calculamos cuantas visitas ha habido para cada enfermedad        
        return new HashSet<>(
            filteredVisits.stream().map(v->v.getDiagnose()).toList()
            );
    }
    
}
