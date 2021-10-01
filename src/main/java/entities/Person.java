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

    String firstName;
    String lastName;
    String phone; //Phone phone
    String email;
    String address;

    @ManyToMany(mappedBy = "persons",  cascade = CascadeType.PERSIST)
    List<Hobby> hobbies = new ArrayList<>();


  /*  @ManyToMany
    @JoinTable(
            name = "link_person_hobby",
            joinColumns = @JoinColumn(name = "p_id"),
            inverseJoinColumns = @JoinColumn(name = "h_id")
    )
    private List<Hobby> hobbies;*/


    public Person(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.hobbies = new ArrayList<>();
    }

    public Person(String firstName, String lastName, String phone, List<Hobby> hobbies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
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
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", hobbies=" + hobbies +
                '}';
    }
}