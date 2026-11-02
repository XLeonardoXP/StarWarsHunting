package com.starwars;

public class Jedi extends Enemy{

    public Jedi(String name, boolean key){
        super.maxHealth = 100;
        super.health = 100;
        super.defence = 5;
        super.maxMana = 80;
        super.mana = 80;
        super.speed = 70;
        super.name = name;
        super.key = key;
    }



}