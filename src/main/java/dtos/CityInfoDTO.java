package dtos;

import entities.Address;
import entities.CityInfo;
import entities.Person;

import java.util.ArrayList;
import java.util.List;

public class CityInfoDTO {
    private String city;
    private String zipCode;

    public CityInfoDTO(CityInfo cityInfo) {
        this.city = cityInfo.getCity();
        this.zipCode = cityInfo.getZipCode();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipcode(String zipcode) {
        this.zipCode = zipcode;
    }
    public static List<CityInfoDTO> getDtos(List<CityInfo> cityInfos){
        List<CityInfoDTO> cityInfoDTOS = new ArrayList();
        cityInfos.forEach(rm->cityInfoDTOS.add(new CityInfoDTO(rm)));
        return cityInfoDTOS;
    }
}
