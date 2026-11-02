package com.starwars;

import java.util.ArrayList;
import java.util.Scanner;

public class BattleManager {

    public static void startCombat(Player player, Scanner scanner) {
        Room room = player.getCurrentRoom();
        ArrayList<Enemy> enemies = room.getEnemy();

        System.out.println("\n-------------------------------------------");
        System.out.println("⚠️  COMBATTIMENTO INIZIATO! ⚠️");
        System.out.println("-------------------------------------------");

        // Loop finché ci sono nemici e il giocatore è vivo
        while (player.isAlive() && !enemies.isEmpty()) {

            // --- TURNO DEL GIOCATORE ---
            System.out.println("\n--- I TUOI STATS ---");
            System.out.println("Salute: " + player.getHealth() + "/" + player.getMaxHealth() + " | Mana: " + player.getMana() + "/" + player.getMaxMana());

            System.out.println("\nNemici presenti:");
            for (int i = 0; i < enemies.size(); i++) {
                Enemy e = enemies.get(i);
                System.out.println((i + 1) + ". " + e.getName() + " [HP: " + e.getHealth() + "/" + e.getMaxHealth() + "]");
            }

            // Selezione nemico da attaccare
            int enemyIndex = 0;
            if (enemies.size() > 1) {
                System.out.print("\nScegli quale nemico attaccare (1-" + enemies.size() + "): ");
                try {
                    enemyIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;
                    if (enemyIndex < 0 || enemyIndex >= enemies.size()) {
                        enemyIndex = 0;
                    }
                } catch (Exception e) {
                    enemyIndex = 0;
                }
            }

            Enemy target = enemies.get(enemyIndex);

            // Scegli abilità
            System.out.println("\nScegli il tuo attacco:");
            System.out.println("1. Fendente pesante (Costo: 0 Mana)");
            System.out.println("2. Force Crush (Costo: 100 Mana)");
            System.out.println("3. Force Choke (Costo: 50 Mana - Applica DoT)");
            System.out.print("Scelta: ");

            int choice = 1;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                choice = 1;
            }

            // Esegue l'attacco
            player.attack(choice, target);

            // Controllo se il nemico attaccato è morto
            if (!target.isAlive()) {
                System.out.println("💀 " + target.getName() + " è stato sconfitto!");
                if(target.hasKey()){
                    System.out.println("Hai trovato una chiave!");
                    player.setKey();
                }
                enemies.remove(enemyIndex);

            }

            if (enemies.isEmpty()) {
                System.out.println("\n✨ Hai sconfitto tutti i nemici nella stanza!");
                break;
            }

            // --- TURNO DEI NEMICI ---
            System.out.println("\n--- TURNO DEI NEMICI ---");
            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemy = enemies.get(i);

                // Gestione DoT sul nemico (Force Choke)
                if (enemy.getDoT() > 0) {
                    System.out.println("🔥 " + enemy.getName() + " sta soffocando per la Forza! (DoT)");
                    enemy.changeHealth(15); // Danno da DoT ad ogni turno
                    enemy.setDoT(enemy.getDoT() - 1);

                    if (!enemy.isAlive()) {
                        System.out.println("💀 " + enemy.getName() + " è soffocato a morte!");
                        if(enemy.hasKey()){
                            System.out.println("Hai trovato una chiave!");
                            player.setKey();
                        }
                        enemies.remove(i);
                        i--; // Aggiusta indice dopo la rimozione
                        continue;
                    }
                }

                // Il nemico attacca Darth Vader
                int enemyDamage = 15; // Danno base
                System.out.print(enemy.getName() + " ti attacca! ");
                player.takeDamage(enemyDamage);

                if (!player.isAlive()) {
                    System.out.println("\n☠️  SEI STATO SCONFITTO... La galassia cade nel caos.");
                    break;
                }
            }
        }
    }
}