/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.PersonDTO;
import dtos.RenameMeDTO;
import entities.Person;
import entities.RenameMe;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade fe = PersonFacade.getFacadeExample(emf);
        fe.addPerson(new PersonDTO(new Person("Musti", "Tokmak","42212121")));
        fe.addPerson(new PersonDTO(new Person("Mafhias", "Enemark","42212122")));
        fe.addPerson(new PersonDTO(new Person("Markus", "Agnsgaard","422123212")));
    }
    
    public static void main(String[] args) {
        populate();
    }
}
