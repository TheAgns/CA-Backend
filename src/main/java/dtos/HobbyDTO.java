package dtos;

import entities.Hobby;
import entities.Phone;

import java.util.List;
import java.util.stream.Collectors;

public class HobbyDTO {
    private Integer id;
    private String name;
    private String description;



    public static List<HobbyDTO> getFromList(List<Hobby> hobbies) {
        return hobbies.stream()
                .map(hobby -> new HobbyDTO(hobby))
                .collect(Collectors.toList());
    }

    public HobbyDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public HobbyDTO(Hobby hobby) {
        this.id = hobby.getH_id();
        this.name = hobby.getName();
        this.description = hobby.getDescription();
    }

    public Integer getId() {
        return id;
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
}
