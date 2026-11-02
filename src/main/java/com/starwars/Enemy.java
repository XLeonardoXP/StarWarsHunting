package com.starwars;

public class Enemy {
    protected int id;
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int defence;
    protected int mana;
    protected int maxMana;
    protected int speed;
    protected int DoT;  //danno 5 nel tempo
    protected boolean key;

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getSpeed() {
        return speed;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public int getMana() {
        return mana;
    }

    public int getDefence() {
        return defence;
    }

    public int getDoT() {
        return DoT;
    }

    public void setDoT(int doT) {
        DoT = doT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasKey(){
        return this.key;
    }

    public void changeHealth(int attack) {
        double damageMultiplier = 100.0 / (100 + this.defence);
        this.health -= (int) (attack * damageMultiplier);

        if (this.health < 0) {
            this.health = 0;
        }
    }

    public void statsEnemy() {
        System.out.println("--- " + this.name + " ---");
        System.out.println("Salute: " + this.health + "/" + this.maxHealth);
        System.out.println("Difesa: " + this.defence);
        System.out.println("Mana: " + this.mana + "/" + this.maxMana);
    }

    public boolean isAlive() {
        return this.health > 0;
    }


}
