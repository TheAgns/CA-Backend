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
                ", adress=" + address +
                '}';
    }

    public static List<PersonDTO> getDtos(List<Person> personList){
        List<PersonDTO> personDTOS = new ArrayList();
        personList.forEach(rm->personDTOS.add(new PersonDTO(rm)));
        return personDTOS;
    }
}
