package entities;

import javax.persistence.*;

@Entity
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String phoneNumber;
    private String description;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="p_id")
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Phone() {
    }

    public Phone(String phoneNumber, String description) {
        this.phoneNumber = phoneNumber;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
