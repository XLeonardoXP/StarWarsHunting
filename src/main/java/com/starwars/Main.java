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
        Authentication auth = new Authentication();
        boolean control = false;

        do{
            printTitle();
            printImage();
            System.out.println("Benvenuti nel gioco di avventura testuale The Hunting.");
            System.out.println("In questa avventura galattica vestire i panni di Darth Fener, acerrimo signore dei Sith che ha portato alla distruzione dell'ordine Jedi e alla nascita dell'Impero Galattico.");
            System.out.println("Per procedere clicca: \n0 per uscire \n1 per registrare il tuo account \n2 per accedere ad un profilo già creato");
            userInput = scanner.nextLine();

            if(userInput.equals("1")){
                while(!control){
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    printTitle();
                    printImage();
                    System.out.println("Per completare la registrazione inserisci i seguenti dati:");
                    System.out.print("Nome: ");
                    String name = scanner.nextLine();
                    System.out.println("Cognome: ");
                    String surname = scanner.nextLine();
                    System.out.println("Username: ");
                    String username = scanner.nextLine();
                    System.out.println("Password: ");
                    String password = scanner.nextLine();

                    control = auth.register(name, surname, username, password);
                }
                break;
            }
            else if(userInput.equals("2")){
                control = false;
                while(!control){
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    printTitle();
                    printImage();
                    System.out.println("Per effettuare il login inserisci i seguenti dati:");
                    System.out.println("Username: ");
                    String username = scanner.nextLine();
                    System.out.println("Password: ");
                    String password = scanner.nextLine();
                    control = auth.login(username, password);
                }
                break;
            }
            else{
                System.exit(0);
            }
        } while(!userInput.equals("0")  && !userInput.equals("1") && !userInput.equals("2"));

        System.out.print("\033[H\033[2J");
        System.out.flush();
        printTitle();
        printImage();

        boolean playing = true;
        Player player = new Player();
        Rebel rebel1_1 = new Rebel("Soldato Ribelle", true);
        Jedi jedi1_1 = new Jedi("Obi Wan Kenobi", false);

        //Creating rooms
        Room room1_1 = new Room("Hangar Principale", "L'imponente ponte d'attacco dello Star Destroyer. La tua navetta di classe Lambda è appena atterrata. Allarmi rossi lampeggiano sulle pareti in acciaio profondo.");
        Room room1_2 = new Room("Corridoio Sud", "Un lungo corridoio illuminato da strisce a LED bianche. Il rumore delle sirene rimbomba. Noti macchie di bruciato sui muri, segno di colpi di blaster.");
        Room room1_3 = new Room("Armeria Secondaria", "Raffinati rastrelliere per blaster e scudi termici, ma molte armi mancano. Una guardia imperiale giace a terra.");
        Room room1_4 = new Room("Incrocio dell'Hangar", "Uno snodo centrale con spessi vani ascensore e porte blindate. A terra ci sono rottami di droni Imperiali scoppiati.");
        Room room1_5 = new Room("Stanza del Generatore", "Il ronzio del reattore ausiliario è quasi assordante. Tubi di plasma scintillano nella penombra.");
        Room room1_6 = new Room("Ponte di Controllo Accessi", "Una grande vetrata si affaccia sulla baia spaziale. Un Padawan Jedi impugna una spada laser blu ed è pronto a difendere i dati rubati.");

        //Set exits
        room1_1.setExits("nord", room1_2);
        room1_2.setExits("sud", room1_1);
        room1_2.setExits("nord", room1_4);
        room1_2.setExits("est", room1_3);
        room1_3.setExits("ovest", room1_2);
        room1_4.setExits("sud", room1_2);
        room1_4.setExits("nord", room1_6);
        room1_4.setExits("ovest", room1_5);
        room1_5.setExits("est", room1_4);
        room1_6.setExits("sud", room1_4);


        //Set locked
        room1_6.setLocked(true);

        //Set enemies
        room1_3.setEnemy(rebel1_1);
        room1_6.setEnemy(jedi1_1);

        //Player initial room
        player.setCurrentRoom(room1_1);
        System.out.println("Inizi il tuo viaggio in: " + room1_1.getName());
        System.out.println(room1_1.getDescription());


        while (playing && player.isAlive()) {

            System.out.print("\nCosa vuoi fare? (es. 'nord', 'sud', 'esci'): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("esci")) {
                playing = false;
                System.out.println("Partita terminata.");
            } else {
                // Tenta il movimento nella direzione digitata
                player.move(input);

                // Se si è spostato in una stanza con nemici, avvia il combattimento
                if (player.getCurrentRoom().hasEnemy()) {
                    int count_enemies = player.getCurrentRoom().getEnemy().size();
                    System.out.println("Attenzione! Ci sono " + count_enemies + " nemici presenti!");

                    // Avvia il loop del combattimento
                    BattleManager.startCombat(player, scanner);
                }
            }
        }



    }
}
