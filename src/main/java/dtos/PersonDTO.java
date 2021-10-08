package dtos;

import entities.Hobby;
import entities.Person;
import entities.Phone;

import java.util.ArrayList;
import java.util.List;

public class PersonDTO{
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private List<PhoneDTO> phones;
    List<HobbyDTO> hobbies;
    private AddressDTO address;

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public PersonDTO(){

    }

    public PersonDTO(String firstName, String lastName, String email, List<PhoneDTO> phones, List<HobbyDTO> hobbies, AddressDTO address) {
        this.id = -1;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phones = phones;
        this.hobbies = hobbies;
        this.address = address;
    }

    public PersonDTO(String firstName, String lastName, String email, AddressDTO address) {
        this.id = -1; // er det blevet persistet?
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phones = new ArrayList<PhoneDTO>();
        this.hobbies = new ArrayList<HobbyDTO>();
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhones(List<PhoneDTO> phones) {
        this.phones = phones;
    }

    public List<PhoneDTO> getPhones() {
        return phones;
    }

    public PersonDTO(Person person) {
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.email = person.getEmail();
        this.phones = PhoneDTO.getFromList(person.getPhones());
        this.hobbies = HobbyDTO.getFromList(person.getHobbies());
        this.address = new AddressDTO(person.getAddress());
    }

    public List<HobbyDTO> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<HobbyDTO> hobbies) {
        this.hobbies = hobbies;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phones=" + phones +
                ", hobbies=" + hobbies +
                ", address=" + address +
                '}';
    }

    public static List<PersonDTO> getDtos(List<Person> personList){
        List<PersonDTO> personDTOS = new ArrayList();
        personList.forEach(rm->personDTOS.add(new PersonDTO(rm)));
        return personDTOS;
    }
}