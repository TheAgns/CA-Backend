package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PersonDTO;
import entities.Person;
import facades.Populator;
import utils.EMF_Creator;
import facades.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

//Todo Remove or change relevant parts before ACTUAL use
@Path("xxx")
public class PersonResource {

    private final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private final PersonFacade FACADE =  PersonFacade.getFacadeExample(EMF);
    private final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {

        long count = FACADE.getPersonCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    @Path("populate")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String populateDB() {

        Populator.populate();
        return "Musti have been populated";
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll() {
        List<PersonDTO> persons = FACADE.getAllPersons();
        return GSON.toJson(persons);
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addPerson(String person) {

        PersonDTO personDTO = GSON.fromJson(person,PersonDTO.class);
        PersonDTO personDTO1 = FACADE.addPerson(personDTO);

        return GSON.toJson(personDTO1);
    }

    //Get all persons with a given hobby
    @Path("/hobby/{name}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getByHobby(@PathParam("name") String name ) {
        List<PersonDTO> persons = FACADE.getAllPersonsByHobby(name);
        return GSON.toJson(persons);
    }

    //Get information about a person (address, hobbies etc) given a phone number

    //Get all persons living in a given city (i.e. 2800 Lyngby)
    //Get the number of people with a given hobby
    //Get a list of all zip codes in Denmark
    //Create new Persons
    //Edit Persons


}

