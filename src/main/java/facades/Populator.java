/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.PersonDTO;
import entities.Hobby;
import entities.Person;

import javax.persistence.EntityManager;
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
        EntityManager em = emf.createEntityManager();

        Person person1 = new Person("Musti", "Tokmak","42212121");
        Person person2 = new Person("Mafhias", "Enemark","42212122");
        Person person3 = new Person("Markus", "Agnsgaard","422123212");
        Hobby hobby1 = new Hobby("Fodbold","Spark til en rund bold");
        Hobby hobby2 = new Hobby("Haandbold","Kast med en rund bold");
        person1.addHobby(hobby1);
        person2.addHobby(hobby2);
        person2.addHobby(hobby1);

        em.getTransaction().begin();
        em.persist(person1);
        em.persist(person2);
        em.getTransaction().commit();

       /* fe.addPerson(new PersonDTO(person1));
        fe.addPerson(new PersonDTO(person2));
        fe.addPerson(new PersonDTO(person3));*/

    }
    
    public static void main(String[] args) {
        populate();
    }
}
