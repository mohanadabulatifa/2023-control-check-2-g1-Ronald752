package org.springframework.samples.petclinic.disease;

import java.util.Set;

import org.springframework.samples.petclinic.model.NamedEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Treatment extends NamedEntity{
    
    String description;


    @NotNull
    @Min(1)
    Integer dose;

    @ManyToMany(fetch = FetchType.EAGER)
    @NotNull
    Set<Disease> recommendedFor;
}
