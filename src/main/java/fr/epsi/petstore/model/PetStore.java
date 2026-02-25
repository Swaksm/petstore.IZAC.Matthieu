package fr.epsi.petstore.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity @Getter @Setter @NoArgsConstructor
public class PetStore {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name, managerName;

    @OneToOne
    private Address address;

    @ManyToMany
    private Set<Product> products = new HashSet<>();

    @OneToMany(mappedBy = "petStore")
    private Set<Animal> animals = new HashSet<>();

    public void addAnimal(Animal animal) {
        animal.setPetStore(this);
        this.animals.add(animal);
    }
}