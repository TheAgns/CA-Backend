package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "hobby")
@Entity
@NamedQueries({
        @NamedQuery(name = "Hobby.deleteAllRows", query = "DELETE from Hobby "),
        @NamedQuery(name = "Hobby.getAll", query = "SELECT h FROM Hobby h"),
        @NamedQuery(name = "Hoby.getByName", query = "SELECT h FROM Hobby h WHERE h.name LIKE :name")
})
public class Hobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "h_id", nullable = false)
    private Integer h_id;
    private String name;
    private String description;

    public Integer getH_id() {
        return h_id;
    }

    @ManyToMany
    private List<Person> persons;

 /*   @ManyToMany(mappedBy = "hobbies",  cascade = CascadeType.PERSIST)
    List<Person> persons = new ArrayList<>();*/


    public Hobby(String name, String description) {
        this.name = name;
        this.description = description;
        this.persons = new ArrayList<>();
    }

    public Hobby() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Hobby{" +
                "id=" + h_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}