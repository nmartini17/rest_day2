package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.MovieDTO;
import facades.FacadeExample;
import facades.MovieFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/movie")
public class MovieResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final FacadeExample FACADE =  FacadeExample.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String helloWorld() {
        return "{\"msg\":\"Hello World\"}";
    }

    //URL = api/all
    @Path("/all")
    @GET
    @Produces
    public String getAllMovies(){
        MovieFacade facade = new MovieFacade();
        List<MovieDTO> md = facade.getAll();
        return GSON.toJson(md);
    }

    //URL = api/movie/id/'id'
    @Path("/id/{id}")
    @GET
    @Produces("application/json")
    public String getMovieById(@PathParam("id") Integer id){
        MovieFacade facade = new MovieFacade();
        List<MovieDTO> md = facade.getMovieById(id);
        return GSON.toJson(md);
    }

    //URL = api/movie/title/'TitleOfMovie'
    @Path("/title/{title}")
    @GET
    @Produces("application/json")
    public String getMovieByTitle(@PathParam("title") String title){
        MovieFacade facade = new MovieFacade();
        List<MovieDTO> md = facade.getByTitle(title);
        return GSON.toJson(md);
    }
}
