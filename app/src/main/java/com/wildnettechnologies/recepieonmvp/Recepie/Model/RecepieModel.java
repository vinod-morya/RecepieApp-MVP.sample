package com.wildnettechnologies.recepieonmvp.Recepie.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinod on 20/09/17.
 */

public class RecepieModel implements Parcelable
    {

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

    public static class Result implements Parcelable
        {

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

            @Override
            public int describeContents() { return 0; }

            @Override
            public void writeToParcel(Parcel dest, int flags)
                {
                    dest.writeString(this.title);
                    dest.writeString(this.href);
                    dest.writeString(this.ingredients);
                    dest.writeString(this.thumbnail);
                }

            protected Result(Parcel in)
                {
                    this.title = in.readString();
                    this.href = in.readString();
                    this.ingredients = in.readString();
                    this.thumbnail = in.readString();
                }

            public static final Parcelable.Creator<Result> CREATOR = new Parcelable.Creator<Result>()
                {
                    @Override
                    public Result createFromParcel(Parcel source) {return new Result(source);}

                    @Override
                    public Result[] newArray(int size) {return new Result[size];}
                };
        }

        @Override
        public int describeContents() { return 0; }

        @Override
        public void writeToParcel(Parcel dest, int flags)
            {
                dest.writeString(this.title);
                dest.writeValue(this.version);
                dest.writeString(this.href);
                dest.writeTypedList(this.results);
            }

        public RecepieModel() {}

        protected RecepieModel(Parcel in)
            {
                this.title = in.readString();
                this.version = (Double) in.readValue(Double.class.getClassLoader());
                this.href = in.readString();
                this.results = in.createTypedArrayList(Result.CREATOR);
            }

        public static final Parcelable.Creator<RecepieModel> CREATOR = new Parcelable.Creator<RecepieModel>()
            {
                @Override
                public RecepieModel createFromParcel(Parcel source) {return new RecepieModel(source);}

                @Override
                public RecepieModel[] newArray(int size) {return new RecepieModel[size];}
            };
    }
