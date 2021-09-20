package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.MovieDTO;
import dtos.PersonDTO;
import facades.FacadeExample;
import facades.MovieFacade;
import facades.PersonFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/person")
public class PersonResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final PersonFacade FACADE =  PersonFacade.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String helloWorld() {
        return "{\"msg\":\"Hello World\"}";
    }


    //URL = api/person/'id'
    @Path("/{id}")
    @GET
    @Produces("application/json")
    public String getPerson(@PathParam("id") Integer id){
        PersonDTO pd = FACADE.getPerson(id);
        return GSON.toJson(pd);
    }

    //URL = api/add
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public String addPerson(String person){
        PersonDTO personDTO = GSON.fromJson(person, PersonDTO.class);
        PersonDTO newPersonDTO = FACADE.addPerson(personDTO.getFname(), personDTO.getLname(), personDTO.getPhone());
        return GSON.toJson(newPersonDTO);
    }
}
