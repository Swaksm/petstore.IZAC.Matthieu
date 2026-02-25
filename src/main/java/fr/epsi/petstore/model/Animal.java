package fr.epsi.petstore.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity @Getter @Setter @NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Animal {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate birth;
    private String couleur;
    @ManyToOne @JoinColumn(name = "ID_STORE")
    private PetStore petStore;
}