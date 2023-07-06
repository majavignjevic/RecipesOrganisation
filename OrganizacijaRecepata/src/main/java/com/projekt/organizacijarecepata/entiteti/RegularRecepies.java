package com.projekt.organizacijarecepata.entiteti;

import java.time.LocalDate;

/**
 * This class represents a regular recipe, which is a type of recipe.
 * It extends the Recept class.
 */
public class RegularRecepies extends Recept {

    /**
     * Constructs a new RegularRecepies object with the specified parameters.
     *
     * @param id              the ID of the recipe
     * @param name            the name of the recipe
     * @param text            the text/description of the recipe
     * @param author          the author of the recipe
     * @param category        the category of the recipe
     * @param mainIngredient  the main ingredient of the recipe
     * @param insert          the insertion date of the recipe
     * @param lastUpdate      the last update date of the recipe
     */
    public RegularRecepies(Long id, String name, String text, User author, Category category, MainIngredient mainIngredient, LocalDate insert, LocalDate lastUpdate) {
        super(id, name, text, author, category, mainIngredient, insert, lastUpdate);
    }

    /**
     * Gets the type of the recipe.
     *
     * @return the type of the recipe
     */
    @Override
    public String getType() {
        return "Regular";
    }
}
