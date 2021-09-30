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
    @Column(name = "id", nullable = false)
    private Integer id;

    String firstName;
    String lastName;
    String phone; //Phone phone
    String email;
    String address;

    @ManyToMany(mappedBy = "persons", cascade = CascadeType.PERSIST)
    List<Hobby> hobbies;

    public Person(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.hobbies = new ArrayList<>();
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

    public String getPhone() {
        return phone;
    }

    public void addHobby(Hobby hobby){
        if(hobby != null) {
            this.hobbies.add(hobby);
            hobby.getPersons().add(this);
        }
    }
}