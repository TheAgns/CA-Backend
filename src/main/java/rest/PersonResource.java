package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.CityInfoDTO;
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
       // PersonDTO personDTO1 = new PersonDTO(personDTO.getFirstName(),personDTO.getLastName(),personDTO.getEmail(),personDTO.getAddress());
        //PersonDTO personDTO2 = FACADE.addPerson(personDTO1);
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
    @Path("/city/{zipCode}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getByZip(@PathParam("zipCode") String zipCode ) {
        List<PersonDTO> persons = FACADE.getPersonsByZip(zipCode);
        return GSON.toJson(persons);
    }
    @Path("/person/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPersonById(@PathParam("id") Integer id ) {
        PersonDTO personDTO = FACADE.getPersonById(id);
        return GSON.toJson(personDTO);
    }
    @Path("/phone/{number}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPersonByNumber(@PathParam("number") String number ) {
        PersonDTO personDTO = FACADE.getPersonByNumber(number);
        return GSON.toJson(personDTO);
    }
    @Path("city")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllCityInfos() {
        List<CityInfoDTO> cityInfoDTOS = FACADE.getAllCityInfos();
        return GSON.toJson(cityInfoDTOS);
    }
    @Path("{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editPerson(@PathParam("id") int id, String person) {
        try {
            PersonDTO personDTOEditInfo = GSON.fromJson(person, PersonDTO.class); //manual conversion
            personDTOEditInfo.setId(id);
            personDTOEditInfo = FACADE.editPerson(personDTOEditInfo);
            return GSON.toJson(personDTOEditInfo);
        } catch (WebApplicationException ex) {
            String errorString = "{\"code\": " + ex.getResponse().getStatus() + ", \"message\": \"" + ex.getMessage() + "\"}";
            return errorString;
        }
    }


}

