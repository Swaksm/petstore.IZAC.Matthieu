package fr.epsi;

import fr.epsi.petstore.model.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("petstore");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        // creation adress
        Address a1 = new Address(null, "1", "Rue de Nantes", "44000", "Nantes");
        Address a2 = new Address(null, "2", "Rue de Paris", "75000", "Paris");
        Address a3 = new Address(null, "3", "Rue de Lyon", "69000", "Lyon");
        em.persist(a1); em.persist(a2); em.persist(a3);

        // creation des artciles
        Product p1 = new Product(null, "P01", "tasty crousty", 12.5, ProdType.FOOD, null);
        Product p2 = new Product(null, "P02", "shampouing", 8.0, ProdType.CLEANING, null);
        Product p3 = new Product(null, "P03", "collier", 25.0, ProdType.ACCESSORY, null);
        em.persist(p1); em.persist(p2); em.persist(p3);

        // creation des magasins
        PetStore s1 = new PetStore(); s1.setName("Leclerc"); s1.setAddress(a1);
        PetStore s2 = new PetStore(); s2.setName("Carrefour"); s2.setAddress(a2);
        PetStore s3 = new PetStore(); s3.setName("Burger King"); s3.setAddress(a3);

        // lié des produits  aux magasins
        s1.getProducts().add(p1);
        s2.getProducts().add(p2);
        s3.getProducts().add(p3);

        // enregistrer
        em.persist(s1); em.persist(s2); em.persist(s3);

        // création des animaux
        Cat chat1 = new Cat(); chat1.setCouleur("Noir lumineux"); chat1.setChipId("CHAT-01"); chat1.setBirth(LocalDate.now());
        Cat chat2 = new Cat(); chat2.setCouleur("Blanc sombre"); chat2.setChipId("CHAT-02"); chat2.setBirth(LocalDate.now());
        Fish poisson1 = new Fish(); poisson1.setCouleur("Orange pâle"); poisson1.setLivingEnv(FishLivEnv.FRESH_WATER); poisson1.setBirth(LocalDate.now());

        //on ajoute les animaux au magasin burger king
        s3.addAnimal(chat1);
        s3.addAnimal(chat2);
        s3.addAnimal(poisson1);

        // enregisrer
        em.persist(chat1); em.persist(chat2); em.persist(poisson1);
        em.getTransaction().commit();

        // requete pour recuperer tout les animaux de burger king
        TypedQuery<Animal> maRequete = em.createQuery(
                "SELECT a FROM Animal a WHERE a.petStore.name = 'Burger King'", Animal.class);

        List<Animal> animaux = maRequete.getResultList();

        System.out.println("animaux de burger king :");
        animaux.forEach(a -> System.out.println("couleur : " + a.getCouleur()));

        em.close();
        emf.close();
    }
}