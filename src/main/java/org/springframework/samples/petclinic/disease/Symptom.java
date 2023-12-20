package org.springframework.samples.petclinic.disease;

import java.util.Set;

import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.visit.Visit;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Symptom extends NamedEntity{

    String description;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @NotNull
    Set<Disease> includes;
    
    @ManyToMany(fetch = FetchType.EAGER)
    Set<Disease> excludes;
}
