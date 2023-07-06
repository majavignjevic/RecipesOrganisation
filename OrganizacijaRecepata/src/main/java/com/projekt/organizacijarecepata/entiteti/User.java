package com.projekt.organizacijarecepata.entiteti;

import java.io.Serializable;

/**
 * This class represents a user in the system.
 * It implements the Serializable interface to support object serialization.
 */
public class User implements Serializable {
    Long id;
    String username;
    String password;
    Long personId;

    /**
     * Constructs a new User object with the specified username and password.
     *
     * @param username the username of the user
     * @param password the password of the user
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Constructs a new User object with the specified parameters.
     *
     * @param id       the ID of the user
     * @param username the username of the user
     * @param password the password of the user
     * @param personId the person ID associated with the user
     */
    public User(Long id, String username, String password, Long personId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.personId = personId;
    }

    /**
     * Constructs a new empty User object.
     */
    public User() {
    }

    /**
     * Gets the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the person ID associated with the user.
     *
     * @return the person ID associated with the user
     */
    public Long getPersonId() {
        return personId;
    }

    /**
     * Sets the person ID associated with the user.
     *
     * @param personId the person ID to set
     */
    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    /**
     * Gets the ID of the user.
     *
     * @return the ID of the user
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     *
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This class represents a builder for constructing User objects.
     */
    public static class UserBuilder {
        private String username;
        private String password;

        /**
         * Sets the username for the UserBuilder.
         *
         * @param username the username to set
         * @return the UserBuilder instance
         */
        public UserBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        /**
         * Sets the password for the UserBuilder.
         *
         * @param password the password to set
         * @return the UserBuilder instance
         */
        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        /**
         * Builds a new User object using the provided username and password.
         *
         * @return the constructed User object
         */
        public User build() {
            User user = new User(username, password);
            this.username = username;
            this.password = password;
            return user;
        }
    }

    /**
     * Returns a string representation of the User object.
     *
     * @return a string representation of the User object
     */
    @Override
    public String toString() {
        return username;
    }
}
