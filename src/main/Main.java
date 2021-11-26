package main;

import java.util.Random;
import java.util.Date;
import java.util.Scanner;
import games.Game;
import games.Nim;
import games.TicTacToe;
import games.TicTacToeWithHints;
import players.Player;
import players.Human;
import players.RandomPlayer;
//import players.NegamaxPlayer;
import players.NegamaxPlayerWithCache;
import plays.Orchestrator;

public class Main {

    // temps de pause
    private static final long TIME = 1000;

    // effectue une courte pause dans le programme
    private static void pause() {
        try {
            Thread.sleep(TIME);
        } catch (InterruptedException ignored) {}
    }

    // programme principal
    public static void main(String[] args) {
        System.out.println("=============== FIL ROUGE GAMES ===============\n"); pause();
        System.out.println("Bienvenue dans notre plateforme de jeu textuel !"); pause();

        System.out.println("Réalisé par Ben KABONGO BUZANGU"); pause();
        System.out.println(" Mamadou Mouctar BAH"); pause();
        System.out.println(" Junior MISSOUP"); pause();

        Game gameInit, gamePlayed;
        Player player1, player2;
        Orchestrator orchestrator;

        Scanner scanner = new Scanner(System.in);

        String[][] gamesDescriptions = {
                // Description du jeu de NIM
                {"Bienvenue dans le jeu de Nim !",
                        "C'est un jeu de deux joueurs. Au lancement du jeu il y a un certain nombre d'allumettes.",
                        "Chacun son tour, chaque joueur peut retirer entre 0 et un nombre d'allumettes donnés",
                        "Le dernier joueur à retirer la dernière allumette a perdu la partie."
                },
                // Description du jeu de TICTACTOE
                {"Bienvenue dans le jeu de TICTACTOE ! Le jeu de Morpion !",
                        "C'est un jeu de deux joueurs. Au lancement du jeu il y a un tableau de 3 lignes et 3 colonnes.",
                        "Chacun son tour, chaque joueur choisit une case vide dans laquelle jouer.",
                        "Le premier joueur à alligner trois cases sur un même axe gagne la partie."
                }
        };

        int choice;
        String res;
        do {
            System.out.println("=============== MENU PRINCIPAL ===============\n"); pause();

            // choix des joueurs
            System.out.println("Veuillez choisir le mode de jeu :"); pause();
            System.out.println("1 === HUMAN VS COMPUTER"); pause();
            System.out.println("2 === HUMAN VS HUMAN"); pause();
            System.out.println("3 === COMPUTER VS COMPUTER"); pause();

            // valeurs acceptables : 1, 2, 3
            choice = -1; do {
                try {
                    System.out.print("Votre choix :");
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (Exception ignored) {}
            } while (choice != 1 && choice != 2 && choice != 3);

            // HUMAN VS COMPUTER
            if (choice == 1) {
                System.out.println("Vous avez choisi le mode HUMAN VS COMPUTER"); pause();
                System.out.print("Saisissez votre nom :");
                player1 = new Human(scanner.nextLine(), scanner);

                System.out.println("Choix du second du COMPUTER"); pause();
                System.out.println("1 === RANDOM-PLAYER (Facile)"); pause();
                System.out.println("2 === NEGAMAX-PLAYER (Difficile)"); pause();

                choice = -1; do {
                    try {
                        System.out.print("Votre choix :");
                        choice = Integer.parseInt(scanner.nextLine());
                    } catch (Exception ignored) {}
                } while (choice != 1 && choice != 2);

                // RANDOM PLAYER
                if (choice == 1) {
                    System.out.println("Vous avez choisi RANDOM-PLAYER"); pause();
                    player2 = new RandomPlayer(new Random(new Date().getTime()));
                }

                // NEGAMAX PLAYER
                else {
                    System.out.println("Vous avez choisi NEGAMAX-PLAYER"); pause();
                    player2 = new NegamaxPlayerWithCache();
                }

                System.out.println("Souhaitez-vous commencer en premier ? (O/o pour oui) :");
                res = scanner.nextLine();
                if (res.length() > 0 && res.trim().toLowerCase().charAt(0) == 'o') {
                    System.out.println("Vous commencez en premier !");
                } else {
                    Player player = player2;
                    player2 = player1;
                    player1 = player;
                    System.out.println("Vous avez cédé la main !");
                }
            }

            // HUMAN VS HUMAN
            else if (choice == 2){
                System.out.println("Vous avez choisi le mode HUMAN VS HUMAN"); pause();
                System.out.print("Saisissez le nom du premier joueur :");
                player1 = new Human(scanner.nextLine(), scanner);
                System.out.print("Saisissez le nom du second joueur :");
                player2 = new Human(scanner.nextLine(), scanner);
            }

            // COMPUTER VS COMPUTER
            else {
                System.out.println("Vous avez choisi de faire s'affronter deux machines"); pause();
                System.out.println("1 === RANDOM-PLAYER VS RANDOM-PLAYER"); pause();
                System.out.println("2 === NEGAMAX-PLAYER VS NEGAMAX-PLAYER"); pause();
                System.out.println("3 === RANDOM-PLAYER VS NEGAMAX-PLAYER"); pause();
                System.out.println("4 === NEGAMAX-PLAYER VS RANDOM-PLAYER"); pause();

                choice = -1; do {
                    try {
                        System.out.print("Votre choix :");
                        choice = Integer.parseInt(scanner.nextLine());
                    } catch (Exception ignored) {}
                } while (choice != 1 && choice != 2 && choice != 3 && choice != 4);

                // RANDOM VS RANDOM
                if (choice == 1) {
                    player1 = new RandomPlayer(new Random(new Date().getTime())); pause();
                    System.out.println("Vous avez choisi RANDOM-PLAYER VS RANDOM-PLAYER");pause();
                    player2 = new RandomPlayer(new Random(new Date().getTime()));
                }

                // NEGAMAX VS NEGAMAX
                else if (choice == 2) {
                    System.out.println("Vous avez choisi NEGAMAX-PLAYER VS NEGAMAX-PLAYER"); pause();
                    player1 = new NegamaxPlayerWithCache();
                    player2 = new NegamaxPlayerWithCache();
                }

                // RANDOM VS NEGAMAX
                else if (choice == 3) {
                    System.out.println("Vous avez choisi RANDOM-PLAYER VS NEGAMAX-PLAYER"); pause();
                    player1 = new RandomPlayer(new Random(new Date().getTime()));
                    player2 = new NegamaxPlayerWithCache();
                }

                // NEGAMAX VS RANDOM
                else {
                    System.out.println("Vous avez choisi NEGAMAX-PLAYER VS RANDOM-PLAYER"); pause();
                    player1 = new NegamaxPlayerWithCache();
                    player2 = new RandomPlayer(new Random(new Date().getTime()));
                }
            }

            // choix du jeu
            System.out.println("Veuillez choisir le numéro du jeu :"); pause();
            System.out.println("1 === NIM"); pause();
            System.out.println("2 === TICTACTOE"); pause();

            // valeurs acceptables : 1 pour NIM, 2 pour TICTACTOE
            choice = -1; do {
                try {
                    System.out.print("Votre choix :");
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (Exception ignored) {}
            } while (choice != 1 && choice != 2);

            // NIM
            if (choice == 1) {
                System.out.println("Vous avez choisi le jeu de NIM"); pause();

                // affichage de la description du jeu
                for (String descriptions: gamesDescriptions[0]) {
                    System.out.println(descriptions);
                    pause();
                }

                // choix du nombre initial d'allumettes
                System.out.println("Veuillez choisir le nombre de départ d'allumettes");
                int initialMatches = 0; do {
                    try {
                        System.out.print("Votre choix :");
                        initialMatches = Integer.parseInt(scanner.nextLine());
                    } catch (Exception ignored) {}
                } while(initialMatches <= 0);
                System.out.println("Vous avez choisi "+ initialMatches); pause();

                // choix du nombre maximal de retrait
                System.out.println("Veuillez choisir le nombre maximal de retraits d'allumettes");
                int maxMatches = 0; do {
                    try {
                        System.out.print("Votre choix :");
                        maxMatches = Integer.parseInt(scanner.nextLine());
                    } catch (Exception ignored) {}
                } while(!(maxMatches > 0 && maxMatches <= initialMatches));
                System.out.println("Vous avez choisi "+ initialMatches); pause();

                gameInit = new Nim(initialMatches, maxMatches, player1, player2);
            }

            // TICTACTOE
            else {
                System.out.println("Vous avez choisi TICTACTOE"); pause();

                // affichage de la description du jeu
                for (String descriptions: gamesDescriptions[1]) {
                    System.out.println(descriptions);
                    pause();
                }

                System.out.print("\nJouer avec des indications ? (O/o pour oui) :");
                res = scanner.nextLine();
                if (res.length() > 0 && res.trim().toLowerCase().charAt(0) == 'o')
                    gameInit = new TicTacToeWithHints(player1, player2);
                else gameInit = new TicTacToe(player1, player2);
            }

            do {
                gamePlayed = gameInit.copy();
                orchestrator = new Orchestrator(gamePlayed);
                orchestrator.play();
                System.out.print("\nSouhaitez-vous rejouer ? (O/o pour oui) :");
                scanner.nextLine();
                res = scanner.nextLine();
            } while (res.length() > 0 && res.trim().toLowerCase().charAt(0) == 'o');

            System.out.print("\nRepartir au menu principal ?(O/o pour oui) :");
            res = scanner.nextLine();
        } while (res.length() > 0 && res.trim().toLowerCase().charAt(0) == 'o');

        scanner.close();
        System.out.println("BYE BYE !");
    }
}
