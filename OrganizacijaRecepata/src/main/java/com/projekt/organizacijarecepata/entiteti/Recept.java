package com.projekt.organizacijarecepata.entiteti;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This abstract class represents a recipe.
 */
public abstract class Recept implements Serializable {
    /**
     * The ID of the recipe.
     */
    Long id;
    /**
     * The name of the recipe.
     */
    String name;
    /**
     * The text/description of the recipe.
     */
    String text;
    /**
     * The author of the recipe.
     */
    User author;
    /**
     * The category of the recipe.
     */
    Category category;
    /**
     * The main ingredient of the recipe.
     */
    MainIngredient mainIngredient;
    /**
     * The date of recipe insertion.
     */
    LocalDate insert;
    /**
     * The date of the last update to the recipe.
     */
    LocalDate lastUpdate;

    /**
     * The number of changes made to the recipe.
     */
    int change;

    /**
     * Constructs a new Recept object with the specified parameters.
     *
     * @param id              the ID of the recipe
     * @param name            the name of the recipe
     * @param text            the text/description of the recipe
     * @param author          the author of the recipe
     * @param category        the category of the recipe
     * @param mainIngredient  the main ingredient of the recipe
     * @param insert          the date of recipe insertion
     * @param lastUpdate      the date of the last update to the recipe
     */
    public Recept(Long id, String name, String text, User author, Category category, MainIngredient mainIngredient, LocalDate insert, LocalDate lastUpdate) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.author = author;
        this.category = category;
        this.mainIngredient = mainIngredient;
        this.insert = insert;
        this.lastUpdate = lastUpdate;
    }

    /**
     * Constructs a new Recept object with the specified parameters including the number of changes.
     *
     * @param id              the ID of the recipe
     * @param name            the name of the recipe
     * @param text            the text/description of the recipe
     * @param author          the author of the recipe
     * @param category        the category of the recipe
     * @param mainIngredient  the main ingredient of the recipe
     * @param insert          the date of recipe insertion
     * @param lastUpdate      the date of the last update to the recipe
     * @param change          the number of changes made to the recipe
     */
    public Recept(Long id, String name, String text, User author, Category category, MainIngredient mainIngredient, LocalDate insert, LocalDate lastUpdate, int change) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.author = author;
        this.category = category;
        this.mainIngredient = mainIngredient;
        this.insert = insert;
        this.lastUpdate = lastUpdate;
        this.change = change;
    }

    /**
     * Retrieves the ID of the recipe.
     *
     * @return the ID of the recipe
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the recipe.
     *
     * @param id the ID of the recipe
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the recipe.
     *
     * @return the name of the recipe
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the recipe.
     *
     * @param name the name of the recipe
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the text/description of the recipe.
     *
     * @return the text/description of the recipe
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text/description of the recipe.
     *
     * @param text the text/description of the recipe
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Retrieves the author of the recipe.
     *
     * @return the author of the recipe
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Sets the author of the recipe.
     *
     * @param author the author of the recipe
     */
    public void setAuthor(User author) {
        this.author = author;
    }

    /**
     * Retrieves the category of the recipe.
     *
     * @return the category of the recipe
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets the category of the recipe.
     *
     * @param category the category of the recipe
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Retrieves the main ingredient of the recipe.
     *
     * @return the main ingredient of the recipe
     */
    public MainIngredient getMainIngredient() {
        return mainIngredient;
    }

    /**
     * Sets the main ingredient of the recipe.
     *
     * @param mainIngredient the main ingredient of the recipe
     */
    public void setMainIngredient(MainIngredient mainIngredient) {
        this.mainIngredient = mainIngredient;
    }

    /**
     * Retrieves the date of recipe insertion.
     *
     * @return the date of recipe insertion
     */
    public LocalDate getInsert() {
        return insert;
    }

    /**
     * Sets the date of recipe insertion.
     *
     * @param insert the date of recipe insertion
     */
    public void setInsert(LocalDate insert) {
        this.insert = insert;
    }

    /**
     * Retrieves the date of the last update to the recipe.
     *
     * @return the date of the last update to the recipe
     */
    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the date of the last update to the recipe.
     *
     * @param lastUpdate the date of the last update to the recipe
     */
    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Retrieves the number of changes made to the recipe.
     *
     * @return the number of changes made to the recipe
     */
    public int getChange() {
        return change;
    }

    /**
     * Sets the number of changes made to the recipe.
     *
     * @param change the number of changes made to the recipe
     */
    public void setChange(int change) {
        this.change = change;
    }

    /**
     * Returns the type of the recipe.
     *
     * @return the type of the recipe
     */
    public abstract String getType();

    /**
     * Returns a string representation of the recipe.
     *
     * @return a string representation of the recipe
     */
    @Override
    public String toString() {
        return name;
    }
}
