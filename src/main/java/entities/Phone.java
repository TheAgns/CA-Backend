package entities;

import dtos.PhoneDTO;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Phone.deleteAllRows", query = "DELETE from Phone "),
        @NamedQuery(name = "Phone.getAll", query = "SELECT p FROM Phone p"),
        @NamedQuery(name = "Phone.getByNumber", query = "SELECT p FROM Phone p WHERE p.phoneNumber LIKE :phoneNumber")
})
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
    public Phone(PhoneDTO phoneDTO) {
        this.phoneNumber = phoneDTO.getPhoneNumber();
        this.description = phoneDTO.getDescription();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
