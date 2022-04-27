package com.example.demo.model;

import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private float ambience;
    private float food;
    private float clean;
    private float drinks;
    private float service;

    public Review() {
    }

    public Review(int id, float ambience, float food, float clean, float drinks, float service) {
        this.id = id;
        this.ambience = ambience;
        this.food = food;
        this.clean = clean;
        this.drinks = drinks;
        this.service = service;
    }



    public Review(float ambience, float food, float clean, float drinks, float service) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmbience() {
        return ambience;
    }

    public void setAmbience(float ambience) {
        this.ambience = ambience;
    }

    public float getFood() {
        return food;
    }

    public void setFood(float food) {
        this.food = food;
    }

    public float getClean() {
        return clean;
    }

    public void setClean(float clean) {
        this.clean = clean;
    }

    public float getDrinks() {
        return drinks;
    }

    public void setDrinks(float drinks) {
        this.drinks = drinks;
    }

    public float getService() {
        return service;
    }

    public void setService(float service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", ambience=" + ambience +
                ", food=" + food +
                ", clean=" + clean +
                ", drinks=" + drinks +
                ", service=" + service +
                '}';
    }
}
