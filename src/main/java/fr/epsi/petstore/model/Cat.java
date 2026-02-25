package fr.epsi.petstore.model;
import jakarta.persistence.Entity;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor
public class Cat extends Animal {
    private String chipId;
}