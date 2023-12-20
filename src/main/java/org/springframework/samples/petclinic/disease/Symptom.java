package org.springframework.samples.petclinic.disease;

import java.util.Set;

import org.springframework.samples.petclinic.model.NamedEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Symptom extends NamedEntity{
    
    String description;
    
    @Transient
    Set<Disease> includes;
    
    @Transient
    Set<Disease> excludes;
}
