package com.starwars;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static com.starwars.Menu.printImage;
import static com.starwars.Menu.printTitle;

public class Main {
    static void main() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        //TimeUnit.SECONDS.sleep(1);

        do{
            printTitle();
            printImage();
            System.out.println("Benvenuti nel gioco di avventura testuale The Hunting.");
            System.out.println("In questa avventura galattica vestire i panni di Darth Fener, acerrimo signore dei Sith che ha portato alla distruzione dell'ordine Jedi e alla nascita dell'Impero Galattico.");
            System.out.println("Per procedere clicca: \n0 per uscire \n1 per registrare il tuo account \n2 per accedere ad un profilo già creato");
            userInput = scanner.nextLine();
        } while(!userInput.equals("0")  && !userInput.equals("1") && !userInput.equals("2"));



        boolean playing = true;
        Player player = new Player();

        //Room room1_1 = new Room();


        /*
        while(playing){
            System.out.print("\nCosa vuoi fare? (es. 'nord', 'sud', 'esci'): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("esci")) {
                playing = false;
                System.out.println("Partita terminata.");
            } else {
                // Tenta il movimento nella direzione digitata
                player.move(input);
            }
        }

        Room armeria = new Room("Armeria", "La stanza dove vengono depositate le armi degli stormtroopers.");
        Room riunioni = new Room("Riunioni", "La stanza dove vengono depositate le armi degli stormtroopers.");
        Jedi luke = new Jedi();
        armeria.setEnemy(luke);
        armeria.setExits("nord", riunioni);
        */

    }
}
