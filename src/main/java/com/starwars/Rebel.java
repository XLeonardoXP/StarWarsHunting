package com.starwars;

public class Rebel extends Enemy{

    public Rebel(String name, boolean key){
        super.maxHealth = 60;
        super.health = 60;
        super.defence = 0;
        super.maxMana = 20;
        super.mana = 20;
        super.speed = 25;
        super.name = name;
        super.key = key;
    }
}
