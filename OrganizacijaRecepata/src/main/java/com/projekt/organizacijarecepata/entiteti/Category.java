package com.projekt.organizacijarecepata.entiteti;

/**
 * This is an enumeration representing different categories of recipes.
 */
public enum Category {
    BREAKFAST(1),
    LUNCH(2),
    DINNER(3),
    SNACK(4),
    DRINK(5);

    /**
     * The ID associated with each category.
     */
    int id;

    /**
     * Constructs a new Category with the specified ID.
     *
     * @param id the ID of the category
     */
    Category(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of the category.
     *
     * @return the ID of the category
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the category.
     *
     * @param id the ID to set
     */
    public void setId(int id) {
        this.id = id;
    }
}
