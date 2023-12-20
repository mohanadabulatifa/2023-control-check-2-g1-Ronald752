package org.springframework.samples.petclinic.disease;

import java.util.Set;

import org.springframework.samples.petclinic.model.NamedEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Treatment extends NamedEntity{

    
    String description;
    
    @Min(1)
    @NotNull
    Integer dose;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @NotNull
    Set<Disease> recommendedFor;
}
