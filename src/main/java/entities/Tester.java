package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class Tester {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

            List<String> snatchActors = new ArrayList<>();
            snatchActors.add("Brad Pitt");
            snatchActors.add("Jason Statham");
            List<String> inglouriousActors = new ArrayList<>();
            inglouriousActors.add("Quentin Tarantino");
            inglouriousActors.add("B.J. Novak");
            em.getTransaction().begin();
            em.persist(new Movie("Snatch", 2000,snatchActors));
            em.persist(new Movie("Inglourious Basterds", 2009, inglouriousActors));
            em.getTransaction().commit();

            em.close();
            emf.close();
    }
}
