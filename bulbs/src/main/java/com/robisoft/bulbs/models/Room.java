package com.robisoft.bulbs.models;

public class Room {
    private int[][] blueprint;

    public Room(int[][] blueprint) {
        this.blueprint = blueprint;
    }

    public int[][] getBlueprint() {
        return blueprint;
    }

    public void setBlueprint(int[][] blueprint) {
        this.blueprint = blueprint;
    }
}
