package com.wildnettechnologies.recepieonmvp.Recepie.Model;

/**
 * Created by vinod on 23/09/17.
 */

public class RecepieRequestModel {
    private String ingredients;
    private String recepie;
    private int page;

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        ingredients = ingredients.replace(' ', ',');
        ingredients = ingredients.replaceAll("\\s", "");
        this.ingredients = ingredients;
    }

    public String getRecepie() {
        return recepie;
    }

    public void setRecepie(String recepie) {
        this.recepie = recepie;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
