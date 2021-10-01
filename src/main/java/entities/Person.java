package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "person")
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id", nullable = false)
    private Integer p_id;

    private String firstName;
    private String lastName;
    private String email;

    @OneToMany(mappedBy = "person", cascade = CascadeType.PERSIST)
    List<Phone> phones;

    public List<Phone> getPhones() {
        return phones;
    }

    public void addPhone(Phone phone) {
        this.phones.add(phone);
        if(phone != null){
            phone.setPerson(this);
        }
    }



    @ManyToMany(mappedBy = "persons",  cascade = CascadeType.PERSIST)
    List<Hobby> hobbies = new ArrayList<>();

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="address_id")
    private Address address;

    public Person(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.email = email;
        this.lastName = lastName;
        this.phones = new ArrayList<>();
        this.hobbies = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public Person(String firstName, String lastName, List<Phone> phones, List<Hobby> hobbies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phones = new ArrayList<>();
        if (hobbies == null){
            this.hobbies = new ArrayList<>();
        } else {
            this.hobbies = hobbies;
        }
    }

    public Person() {

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

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }



    public void addHobby(Hobby hobby){
        if(hobby != null) {
            this.hobbies.add(hobby);
            hobby.getPersons().add(this);
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + p_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", hobbies=" + hobbies +
                '}';
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}