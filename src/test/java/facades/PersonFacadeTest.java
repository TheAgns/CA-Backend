package facades;

import dtos.PersonDTO;
import entities.*;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class PersonFacadeTest {

    private static EntityManagerFactory emf;
    private static PersonFacade facade;
    Person person1;

    public PersonFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = PersonFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Phone.deleteAllRows").executeUpdate();
            em.createNamedQuery("Hobby.deleteAllRows").executeUpdate();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
            em.createNamedQuery("CityInfo.deleteAllRows").executeUpdate();

            //creating persons
            person1 = new Person("Mathias", "Poulsen","mat@gmail.com");
            Person person2 = new Person("Mustafa", "Tokmak","musti@gmail.com");
            Person person3 = new Person("Mathias", "Poulsen","mark@gmail.com");

            //adding phones
            person1.addPhone(new Phone("11223344","privat"));
            person2.addPhone(new Phone("22334455","hjemme"));
            person3.addPhone(new Phone("33445566","privat"));

            //adding hobby/hobbies
            person1.addHobby(new Hobby("fodbold","spark til en bold"));
            person2.addHobby(new Hobby("fodbold","spark til en bold"));
            person3.addHobby(new Hobby("basketball","kast med bold i en kurv"));

            //Creating new addresses
            Address address1 = new Address("Slangevej", "Den er farlig");
            Address address2 = new Address("Slangegade", "Den er ufarlig");

            //Adding CityInfo
            CityInfo cityInfo1 = new CityInfo("2860","Soeborg");
            CityInfo cityInfo2 = new CityInfo("3400","Hilleroed");

            address1.addPerson(person1);
            address2.addPerson(person2);
            address1.addPerson(person3);
            cityInfo1.addAddress(address1);
            cityInfo2.addAddress(address2);

            //persist test persons
            em.persist(cityInfo1);
            em.persist(cityInfo2);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testAFacadeMethod() throws Exception {
        assertEquals(3, facade.getPersonCount(), "Expects two rows in the database");
    }

    @Test
    void getAllPersons() {
        List<PersonDTO> personDTOS = facade.getAllPersons();
        assertEquals(3,personDTOS.size());
    }

    @Test
    void getAllPersonsByHobby() {
        List<PersonDTO> personDTOS = facade.getAllPersonsByHobby("fodbold");
        assertEquals(2,personDTOS.size());

        //TODO: bliver persisted i forskellige rækkefølge.
       PersonDTO personDTO = facade.getPerson(person1.getP_id());
       assertEquals("Mathias",personDTO.getFirstName());
    }


}
