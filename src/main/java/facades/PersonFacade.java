package facades;

import dtos.PersonDTO;
import dtos.PersonsDTO;
import dtos.RenameMeDTO;
import entities.Person;
import entities.RenameMe;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class PersonFacade implements IPersonFacade {
        private static PersonFacade instance;
        private static EntityManagerFactory emf;

        //Private Constructor to ensure Singleton
        private PersonFacade() {}

        public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
            if (instance == null) {
                emf = _emf;
                instance = new PersonFacade();
            }
            return instance;
        }

        private EntityManager getEntityManager() {
            return emf.createEntityManager();
        }


    @Override
    public PersonDTO addPerson(String fName, String lName, String phone) {
        EntityManager em = getEntityManager();
        Person p = new Person(fName, lName, phone);
        try{
            em.getTransaction().begin();
                em.persist(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(p);
    }

    @Override
    public PersonDTO deletePerson(int id) {
        return null;
    }

    @Override
    public PersonDTO getPerson(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            TypedQuery<Person> query = em.createQuery("SELECT p from Person p where p.id = :id", Person.class);
            query.setParameter("id", id);
            Person p = query.getSingleResult();
            em.getTransaction().commit();
            return new PersonDTO(p);
        } finally {
            em.close();
            emf.close();
        }
    }

    @Override
    public PersonsDTO getAllPersons() {
        return null;
    }

    @Override
    public PersonDTO editPerson(PersonDTO p) {
        return null;
    }
}
