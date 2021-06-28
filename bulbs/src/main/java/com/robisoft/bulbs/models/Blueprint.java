package com.robisoft.bulbs.models;

public class Blueprint {
    private int[][] room;

    public Blueprint(int[][] room) {
        this.room = room;
    }

    public int[][] getRoom() {
        return room;
    }

    public void setRoom(int[][] room) {
        this.room = room;
    }
}
