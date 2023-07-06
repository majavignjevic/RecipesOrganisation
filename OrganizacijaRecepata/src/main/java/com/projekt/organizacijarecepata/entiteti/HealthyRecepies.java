package com.projekt.organizacijarecepata.entiteti;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * This class represents a healthy recipe, which is a type of recipe.
 * It extends the abstract class Recept.
 */
public class HealthyRecepies extends Recept {
    /**
     * The number of calories of the healthy recipe.
     */
    Double kalorije;

    /**
     * Constructs a new HealthyRecepies object with the specified parameters.
     *
     * @param id              the ID of the healthy recipe
     * @param name            the name of the healthy recipe
     * @param text            the text/description of the healthy recipe
     * @param author          the author of the healthy recipe
     * @param category        the category of the healthy recipe
     * @param mainIngredient  the main ingredient of the healthy recipe
     * @param insert          the date of healthy recipe insertion
     * @param lastUpdate      the date of the last update to the healthy recipe
     * @param kalorije        the number of calories of the healthy recipe
     */
    public HealthyRecepies(Long id, String name, String text, User author, Category category, MainIngredient mainIngredient, LocalDate insert, LocalDate lastUpdate, Double kalorije) {
        super(id, name, text, author, category, mainIngredient, insert, lastUpdate);
        this.kalorije = kalorije;
    }

    /**
     * Constructs a new HealthyRecepies object with the specified parameters including the number of changes and calories.
     *
     * @param id              the ID of the healthy recipe
     * @param name            the name of the healthy recipe
     * @param text            the text/description of the healthy recipe
     * @param author          the author of the healthy recipe
     * @param category        the category of the healthy recipe
     * @param mainIngredient  the main ingredient of the healthy recipe
     * @param insert          the date of healthy recipe insertion
     * @param lastUpdate      the date of the last update to the healthy recipe
     * @param change          the number of changes made to the healthy recipe
     * @param kalorije        the number of calories of the healthy recipe
     */
    public HealthyRecepies(Long id, String name, String text, User author, Category category, MainIngredient mainIngredient, LocalDate insert, LocalDate lastUpdate, int change, Double kalorije) {
        super(id, name, text, author, category, mainIngredient, insert, lastUpdate, change);
        this.kalorije = kalorije;
    }

    /**
     * Retrieves the number of calories of the healthy recipe.
     *
     * @return the number of calories of the healthy recipe
     */
    public Double getKalorije() {
        return kalorije;
    }

    /**
     * Sets the number of calories of the healthy recipe.
     *
     * @param kalorije the number of calories of the healthy recipe
     */
    public void setKalorije(Double kalorije) {
        this.kalorije = kalorije;
    }

    /**
     * Returns the type of the recipe as "Healthy".
     *
     * @return the type of the recipe
     */
    @Override
    public String getType() {
        return "Healthy";
    }
}

