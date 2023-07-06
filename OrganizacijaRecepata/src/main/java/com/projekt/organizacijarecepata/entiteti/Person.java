package com.projekt.organizacijarecepata.entiteti;

import java.time.LocalDate;

/**
 * This class represents a person, which is a type of user.
 * It extends the User class.
 */
public class Person extends User {

    Long id;
    String name;
    String surname;
    LocalDate DOB;

    /**
     * Constructs a new Person object with the specified parameters.
     *
     * @param username the username of the person
     * @param password the password of the person
     * @param id       the ID of the person
     * @param name     the name of the person
     * @param surname  the surname of the person
     * @param DOB      the date of birth of the person
     */
    public Person(String username, String password, Long id, String name, String surname, LocalDate DOB) {
        super(username, password);
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.DOB = DOB;
    }

    /**
     * Constructs a new Person object with the specified parameters.
     * This constructor is used when creating a person in the database table.
     *
     * @param id      the ID of the person (from the database table)
     * @param name    the name of the person
     * @param surname the surname of the person
     * @param DOB     the date of birth of the person
     */
    public Person(Long id, String name, String surname, LocalDate DOB) {
        super();
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.DOB = DOB;
    }

    /**
     * Gets the ID of the person.
     *
     * @return the ID of the person
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the person.
     *
     * @param id the ID of the person
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the person.
     *
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person.
     *
     * @param name the name of the person
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the surname of the person.
     *
     * @return the surname of the person
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname of the person.
     *
     * @param surname the surname of the person
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets the date of birth of the person.
     *
     * @return the date of birth of the person
     */
    public LocalDate getDOB() {
        return DOB;
    }

    /**
     * Sets the date of birth of the person.
     *
     * @param DOB the date of birth of the person
     */
    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }
}
