package com.starwars;

import java.util.Map;

public class Player {
    private int health;
    private int maxHealth;
    private int defence;
    private int mana;
    private int maxMana;
    private int speed;
    private Room currentRoom;

    public Player(){
        this.health = 100;
        this.maxHealth = 100;
        this.defence = 30;
        this.mana = 150;
        this.maxMana = 150;
        this.speed = 50;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getDefence() {
        return defence;
    }

    public int getMana() {
        return mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public int getSpeed() {
        return speed;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void useMana(int mana) {
        this.mana = this.mana - mana;
        if(this.mana < 0){
            this.mana = 0;
        }
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void attack(int ability, Enemy en){
        switch(ability){
            //Fendente pesante
            case 1:
                useMana(10);
                en.changeHealth(20);
                break;
            //Force crush
            case 2:
                useMana(100);
                en.changeHealth(60);
                break;
            // Force chocke
            case 3:
                useMana(50);
                //attacco a turni
                en.setDoT(3);
                break;

        }
    }

    public void move(String direction){
        Room nextRoom = this.currentRoom.getExit(direction);

        if(nextRoom == null){
            System.out.println("Non puoi andare in quella direzione");
        }
        else if(nextRoom.isLocked()){
            System.out.println("Porta sbarrata. Devi trovare un altro modo per entrare.");
        }
        else{
            setCurrentRoom(nextRoom);
            System.out.println("Ti sei spostato in: "+this.currentRoom.getName());
            System.out.println(this.currentRoom.getDescription());
        }

        if(this.currentRoom.hasEnemy()){
            System.out.println("Attenzione! Ci sono nemici in questa stanza.");
        }
    }
}
