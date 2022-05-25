package com.example.dogapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DogBreed implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("life_span")
    private String lifeSpan;
    @SerializedName("origin")
    private String origin;
    @SerializedName("url")
    private String url;
    @SerializedName("bred_for")
    private String breed_for;

    public DogBreed.height getHeight() {
        return Height;
    }

    public void setHeight(DogBreed.height height) {
        this.Height = height;
    }

    public height getWeight() {
        return weight;
    }

    public void setWeight(height weight) {
        this.weight = weight;
    }

    @SerializedName("height")
    private   height Height;
    @SerializedName("weight")
    private height weight;
    public String getBreed_for() {
        return breed_for;
    }

    public void setBreed_for(String breed_for) {
        this.breed_for = breed_for;
    }

    public String getBreed_group() {
        return breed_group;
    }

    public void setBreed_group(String breed_group) {
        this.breed_group = breed_group;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }
    @SerializedName("breed_group")
    private String breed_group;
    @SerializedName("temperament")
    private String temperament;

    public DogBreed(int id, String name, String lifeSpan, String origin, String url, String breed_for, String breed_group, String temperament) {
        this.id = id;
        this.name = name;
        this.lifeSpan = lifeSpan;
        this.origin = origin;
        this.url = url;
        this.breed_for = breed_for;
        this.breed_group = breed_group;
        this.temperament = temperament;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    public class height{
        @SerializedName("imperial")
        private String imperial;

        public String getImperial() {
            return imperial;
        }

        public void setImperial(String imperial) {
            this.imperial = imperial;
        }

        public String getMetric() {
            return metric;
        }

        public void setMetric(String metric) {
            this.metric = metric;
        }

        @SerializedName("metric")
        private String metric;

        public height(String imperial, String metric) {
            this.imperial = imperial;
            this.metric = metric;
        }
    }

}
