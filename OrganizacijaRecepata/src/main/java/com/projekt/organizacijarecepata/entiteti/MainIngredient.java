package com.projekt.organizacijarecepata.entiteti;

/**
 * This enumeration represents the main ingredients used in recipes.
 */
public enum MainIngredient {
    ALMOND_MEAL(1, "Almond Meal"),
    ALMONDS(2, "Almonds"),
    AMARANTH(3, "Amaranth"),
    APPLES(4, "Apples"),
    APRICOTS(5, "Apricots"),
    AVOCADOS(6, "Avocados"),
    BANANAS(7, "Bananas"),
    BARLEY(8, "Barley"),
    BEEF(9, "Beef"),
    BEEF_CHUCK(10, "Beef Chuck"),
    BEEF_RIBS(11, "Beef Ribs"),
    BEEF_TENDERLOIN(12, "Beef Tenderloin"),
    BRISKET(13, "Brisket"),
    BROWN_RICE(14, "Brown Rice"),
    BUCKWHEAT(15, "Buckwheat"),
    BULGUR(16, "Bulgur"),
    CHEESE(17, "Cheese"),
    CHERRIES(18, "Cherries"),
    CHIA_SEEDS(19, "Chia Seeds"),
    CHICKEN(20, "Chicken"),
    CHICKEN_BREASTS(21, "Chicken Breasts"),
    CHICKEN_LEGS(22, "Chicken Legs"),
    CHICKEN_THIGHS(23, "Chicken Thighs"),
    CHICKEN_WINGS(24, "Chicken Wings"),
    CHOCOLATE(25, "Chocolate"),
    COCONUT(26, "Coconut"),
    CORN_FLOUR(27, "Corn Flour"),
    CORNISH_HENS(28, "Cornish Hens"),
    CORNMEAL(29, "Cornmeal"),
    DUCK(30, "Duck"),
    FISH(31, "Fish"),
    FLAX_SEEDS(32, "Flax Seeds"),
    GOAT(33, "Goat"),
    GROUND_BEEF(34, "Ground Beef"),
    GROUND_CHICKEN(35, "Ground Chicken"),
    GROUND_PORK(36, "Ground Pork"),
    GROUND_TURKEY(37, "Ground Turkey"),
    LAMB(38, "Lamb"),
    MANGOS(39, "Mangos"),
    MILLET(40, "Millet"),
    MUSHROOM(41, "Mushroom"),
    NECTARINES(42, "Nectarines"),
    OAT_FLOUR(43, "Oat Flour"),
    OATS(44, "Oats"),
    PEACHES(45, "Peaches");

    private int id;
    private String ingredientName;

    /**
     * Constructs a new MainIngredient with the specified ID and ingredient name.
     *
     * @param id             the ID of the main ingredient
     * @param ingredientName the name of the main ingredient
     */
    MainIngredient(int id, String ingredientName) {
        this.id = id;
        this.ingredientName = ingredientName;
    }

    /**
     * Gets the ID of the main ingredient.
     *
     * @return the ID of the main ingredient
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name of the main ingredient.
     *
     * @return the name of the main ingredient
     */
    public String getIngredientName() {
        return ingredientName;
    }

    @Override
    public String toString() {
        return getIngredientName();
    }
}
