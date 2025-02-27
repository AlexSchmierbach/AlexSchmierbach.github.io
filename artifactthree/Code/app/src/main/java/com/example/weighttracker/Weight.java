package com.example.weighttracker;

import androidx.annotation.NonNull;

public class Weight {
    private final int id;
    private String date;
    private float weight;

    public Weight(int id, String date, float weight) {
        this.id = id;
        this.date = date;
        this.weight = weight;
    }

    public int getID() { return this.id; }

    public String getDate() {
        return this.date;
    }

    public float getWeight() {
        return this.weight;
    }

    public void setDate(String newDate) {
        this.date = newDate;
    }

    public void setWeight(float newWeight) {
        this.weight = weight;
    }
}
