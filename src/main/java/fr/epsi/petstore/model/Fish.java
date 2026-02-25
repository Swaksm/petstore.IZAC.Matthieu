package fr.epsi.petstore.model;
import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor
public class Fish extends Animal {
    @Enumerated(EnumType.STRING)
    private FishLivEnv livingEnv;
}