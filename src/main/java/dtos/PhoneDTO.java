package dtos;

import entities.Phone;

import java.util.List;
import java.util.stream.Collectors;

public class PhoneDTO {
    private Integer id;
    private String phoneNumber;
    private String description;

    public PhoneDTO(Phone phone) {
        this.id = phone.getId();
        this.phoneNumber = phone.getPhoneNumber();
        this.description = phone.getDescription();
    }

    public PhoneDTO(String phoneNumber,String description){
        this.phoneNumber = phoneNumber;
        this.description = description;
    }

    public static List<PhoneDTO> getFromList(List<Phone> phones) {
        return phones.stream()
                .map(phone -> new PhoneDTO(phone))
                .collect(Collectors.toList());
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

}
