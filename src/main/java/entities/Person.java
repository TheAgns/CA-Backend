package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "person")
@Entity
@NamedQueries({
        @NamedQuery(name = "Person.deleteAllRows", query = "DELETE from Person "),
        @NamedQuery(name = "Person.getAll", query = "SELECT p FROM Person p"),
        @NamedQuery(name = "Person.getByName", query = "SELECT p FROM Person p WHERE p.firstName LIKE :firstName")
})

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

    public void setAddress(Address address){
        this.address = address;
      //  if (address != null){
          //  this.address = address;
           // address.getPersons().add(this);
     //   }
    }






    @ManyToMany(mappedBy = "persons",  cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
    List<Hobby> hobbies = new ArrayList<>();

    @ManyToOne
    //@JoinColumn(name="address_id")
    private Address address;

    public String getEmail() {
        return email;
    }

    public Integer getP_id() {
        return p_id;
    }

    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phones = new ArrayList<>();
        this.hobbies = new ArrayList<>();
        this.address = null;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "Person{" +
                "p_id=" + p_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phones=" + phones +
                ", hobbies=" + hobbies +
                ", address=" + address +
                '}';
    }

    public Address getAddress() {
        return address;
    }
}