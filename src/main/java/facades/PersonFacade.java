package facades;

import dtos.*;
import entities.*;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;

import utils.EMF_Creator;

public class PersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;


    private PersonFacade() {

    }

    public static PersonFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade fe = getFacadeExample(emf);
        fe.getAllPersons().forEach(dto -> System.out.println(dto));
    }




    public PersonDTO addPerson(PersonDTO personDTO) {
        EntityManager em = emf.createEntityManager();
        Person person = new Person(personDTO.getFirstName(),personDTO.getLastName(),personDTO.getEmail());

        //adding Phone/phones
        if(personDTO.getPhones() != null) {
            for (PhoneDTO phoneDTO : personDTO.getPhones()) {
                Phone phone = new Phone(phoneDTO.getPhoneNumber(), phoneDTO.getDescription());
                person.addPhone(phone);
            }
        }

        //Adding hobby/hobbies
        if(personDTO.getHobbies() != null) {
            for (HobbyDTO hobbyDTO : personDTO.getHobbies()) {
                Hobby hobby = new Hobby(hobbyDTO.getName(), hobbyDTO.getDescription());
                person.addHobby(hobby);
            }
        }


        System.out.println(personDTO);
        //Adding Address
            Address address = new Address(personDTO.getAddress().getStreet(), personDTO.getAddress().getAdditionalInfo());

            //adding CityInfo
            CityInfo cityInfo = new CityInfo(personDTO.getAddress().getCityInfoDTO().getZipCode(), personDTO.getAddress().getCityInfoDTO().getCity());

            address.addPerson(person);
            //address.setCityInfo(cityInfo);
            cityInfo.addAddress(address);



        try {
            em.getTransaction().begin();
            List<CityInfoDTO> allCityInfos = getAllCityInfos();
            //skal ændres
            List<String> allCityInfoZipCodes = new ArrayList<>();

            for (CityInfoDTO cityInfoDTO : allCityInfos) {
                allCityInfoZipCodes.add(cityInfoDTO.getZipCode());
            }

            if (allCityInfoZipCodes.contains(cityInfo.getZipCode())) {
                em.persist(address);
            } else {
                em.persist(cityInfo);
            }


            //  address.setCityInfo(cityInfo);
            // em.persist(address);
            // em.merge(address);
            // em.persist(person);
            // person.setAddress(address);
            // em.merge(person);
            em.getTransaction().commit();

        } finally {
            em.close();
        }

        return new PersonDTO(person);
    }

    public PersonDTO getPerson(int id) {
        EntityManager em = emf.createEntityManager();
        return new PersonDTO(em.find(Person.class, id));
    }

    public List<PersonDTO> getAllPersons() throws WebApplicationException{
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
            List<Person> persons = query.getResultList();
            return PersonDTO.getDtos(persons);
        }catch (NoResultException e){
            throw new WebApplicationException("No persons to be shown", 404);

        }
    }

    public long getPersonCount(){
        EntityManager em = emf.createEntityManager();
        try {
            long personCount = (long)em.createQuery("SELECT COUNT(p) FROM Person p").getSingleResult();
            return personCount;
        } finally {
            em.close();
        }
    }

    //Get all persons with a given hobby
    //fodbold --> List person
    public List<PersonDTO> getAllPersonsByHobby(String hobby) throws WebApplicationException{
        EntityManager em = emf.createEntityManager();
        try {
            List<Person> persons = em
                    .createQuery("SELECT p FROM Person p JOIN p.hobbies h WHERE h.name = :hobby", Person.class)
                    .setParameter("hobby", hobby)
                    .getResultList();
            return PersonDTO.getDtos(persons);
        }catch(NoResultException e){
            throw new WebApplicationException("No person with the given hobby" + hobby, 404);
        }
    }
    public PersonDTO getPersonByNumber(String number) throws WebApplicationException {
        EntityManager em = getEntityManager();
        try {
            Person person = em
                    .createQuery("SELECT p FROM Person p JOIN p.phones phone WHERE phone.phoneNumber = :number", Person.class)
                    .setParameter("number", number)
                    .getSingleResult();
            return new PersonDTO(person);
        } catch (NoResultException e) {
            throw new WebApplicationException("No number" + number, 404);
        }
    }
    public PersonDTO getPersonById(Integer id) throws WebApplicationException {
        EntityManager em = getEntityManager();

        try {
            Person person = em
                    .createQuery("SELECT p FROM Person p WHERE p.p_id = :id", Person.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return new PersonDTO(person);
        } catch (NoResultException e) {
            throw new WebApplicationException("No person on the given ID: " + id, 404);
        }
    }
    public List<PersonDTO> getPersonsByZip(String zipCode) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        try {
            List<Person> persons = em
                    .createQuery("SELECT p FROM Person p JOIN p.address a JOIN a.cityInfo c WHERE c.zipCode = :zipCode", Person.class)
                    .setParameter("zipCode", zipCode)
                    .getResultList();
            if (persons.isEmpty()) {
                throw new WebApplicationException(String.format("City with zip: (%S) not found", zipCode), 404);
            }
            return PersonDTO.getDtos(persons);
        } finally {
            em.close();
        }
    }
    public List <CityInfoDTO> getAllCityInfos() throws WebApplicationException{
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<CityInfo> query = em.createQuery("SELECT c FROM CityInfo c", CityInfo.class);
            List<CityInfo> cityInfos = query.getResultList();
            return CityInfoDTO.getDtos(cityInfos);
        }catch (NoResultException e){
            throw new WebApplicationException("No persons to be shown", 404);

        }
    }


}
