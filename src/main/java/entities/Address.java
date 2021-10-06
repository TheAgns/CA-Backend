package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Address.deleteAllRows", query = "DELETE from Address "),
        @NamedQuery(name = "Address.getAll", query = "SELECT a FROM Address a"),
        @NamedQuery(name = "Address.getByName", query = "SELECT a FROM Address a WHERE a.street LIKE :street")
})
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String street;
    private String additionalInfo;
//actions test mat
    @OneToMany(mappedBy = "address", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    List<Person> persons;

    @ManyToOne
    @JoinColumn(name="zip_code")
    private CityInfo cityInfo;

    public void addPerson(Person person) {
        this.persons.add(person);
        if (person != null) {
            person.setAddress(this);
        }
    }
//
    public Address() {
    }

    public Address(String street, String additionalInfo) {
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.persons = new ArrayList<>();
    }

    public void setCityInfo(CityInfo cityInfo){
        if (cityInfo != null){
            this.cityInfo = cityInfo;
            cityInfo.getAddressList().add(this);
        }
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                ", cityInfo=" + cityInfo +
                '}';
    }
}
