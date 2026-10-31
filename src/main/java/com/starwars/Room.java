package com.starwars;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Room {
    private String name;
    private String description;
    private Map<String,Room> exits;
    private ArrayList<Enemy> enemies;
    private boolean isLocked;

    public Room(String name, String description){
        this.name = name;
        this.description = description;
        this.exits = new HashMap<>();
        this.enemies = new ArrayList<>();
        this.isLocked = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Room> getExits() {
        return exits;
    }

    public Room getExit(String direction) {
        return this.exits.get(direction);
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setExits(String direction, Room room) {
        this.exits.put(direction, room);
    }


    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public ArrayList<Enemy> getEnemy() {
        return enemies;
    }

    public void setEnemy(Enemy enemy) {
        this.enemies.add(enemy);
    }

    public boolean hasEnemy(){
        return (!this.enemies.isEmpty());
    }
}
