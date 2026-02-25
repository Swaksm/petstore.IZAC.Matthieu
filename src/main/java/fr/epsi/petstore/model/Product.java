package fr.epsi.petstore.model;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code, label;
    private double price;
    @Enumerated(EnumType.STRING)
    private ProdType type;
    @ManyToMany(mappedBy = "products")
    private Set<PetStore> petStores;
}