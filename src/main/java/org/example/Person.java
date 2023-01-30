package org.example;

import javax.persistence.*;
import java.io.Serializable;

@Entity
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//lub (wszyscy w klasie Person, a w Player i Coach sa ich ID)
@Inheritance(strategy=InheritanceType.JOINED)
//i wtedy @GeneratedValue(strategy = GenerationType.IDENTITY)

//lub (wszyscy rozdzieleni z imionami i nazwiskami)
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
//i wtedy@GeneratedValue(strategy = GenerationType.AUTO)

@Table(name="Person")
//@DiscriminatorColumn(name = "TYPE") // z typem osoby: Player lub Coach
public class Person implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PER_ID")
    private int id;

    private String firstname;
    private String surname;

    public Person() {}

    public Person(String firstname, String surname) {
        this.firstname = firstname;
        this.surname = surname;
    }
    @Override
    public String toString() {
        return (getFirstname() + " "+getSurname());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
