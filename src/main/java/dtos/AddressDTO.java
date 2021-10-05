package dtos;

import entities.Address;

public class AddressDTO {
    private String street;
    private String additionalInfo;
    CityInfoDTO cityInfo;

    public AddressDTO(Address address) {
        this.street = address.getStreet();
        this.additionalInfo = address.getAdditionalInfo();
        this.cityInfo = new CityInfoDTO(address.getCityInfo());
    }

    public CityInfoDTO getCityInfoDTO() {
        return cityInfo;
    }

    public void setCityInfoDTO(CityInfoDTO cityInfo) {
        this.cityInfo = cityInfo;
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
}
