package facades;

import dtos.MovieDTO;
import dtos.RenameMeDTO;
import entities.Movie;
import entities.RenameMe;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class MovieFacade {
    private static MovieFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    public MovieFacade() {}

    public static MovieFacade getMovieFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public MovieDTO create(MovieDTO m) {
        Movie mv = new Movie(m.getTitle(), m.getYear(), m.getActors());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(m);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new MovieDTO(mv);
    }

    public List<MovieDTO> getMovieById(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<Movie> query = em.createQuery("SELECT m from Movie m where m.id = :id", Movie.class);
            query.setParameter("id", id);
            List<Movie> movies = query.getResultList();
            return(List<MovieDTO>)(List<?>)movies;
        } finally {
            em.close();
            emf.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<MovieDTO> getAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m", Movie.class);
            List<Movie> mvList = query.getResultList();
            em.getTransaction().commit();
            return (List<MovieDTO>) (List<?>) mvList;
        } finally {
            em.close();
            emf.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<MovieDTO> getByTitle(String title) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<Movie> query = em.createQuery("SELECT m from Movie m where m.titel = :title", Movie.class);
            query.setParameter("title", title);
            List<Movie> mvList = query.getResultList();
            em.getTransaction().commit();
            return(List<MovieDTO>)(List<?>)mvList;
        } finally {
            em.close();
            emf.close();
        }
    }
}