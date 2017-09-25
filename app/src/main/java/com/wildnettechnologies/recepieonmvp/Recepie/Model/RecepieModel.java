package com.wildnettechnologies.recepieonmvp.Recepie.Model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinod on 20/09/17.
 */

public class RecepieModel {

    @Expose
    private String title;
    @Expose
    private Double version;
    @Expose
    private String href;
    @Expose
    private List<Result> results = new ArrayList<Result>() {};

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getVersion() {
        return version;
    }

    public void setVersion(Double version) {
        this.version = version;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public static class Result {

        @Expose
        private String title;
        @Expose
        private String href;
        @Expose
        private String ingredients;
        @Expose
        private String thumbnail;

        public Result(String title, String href, String ingredients, String thumbnail){
            this.title = title;
            this.href = href;
            this.ingredients = ingredients;
            this.thumbnail = thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getIngredients() {
            return ingredients;
        }

        public void setIngredients(String ingredients) {
            this.ingredients = ingredients;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }
    }

}
