package com.thamarai;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

import static org.apache.logging.log4j.Level.WARN;

public class GameMenu {

    private static final Logger LOGGER = LogManager.getLogger(Game.class);

    /**
     * <b>Method void start :</b> init a game with startGame method with two int parameters : game option (1 or 2)
     * and method option (1, 2 and 3). This two parameters are initialize with choiceAGame and choiceGameMode
     * respectively.
     */
    public void start() {
        LOGGER.info("Start game");
        int game = choiceAGame();
        int method = choiceGameMode();
        startAGame(game, method);
    }

    /**
     * <b>Method choiceAGame :</b>
     * @return (int) game chosen by the Player (1: Recherche +/- or 2: Mastermind)
     */
    public int choiceAGame() {
        System.out.println("Bienvenue !");
        System.out.println("Choisissez un jeu :");
        System.out.println("1. Recherche +/-");
        System.out.println("2. Mastermind");

        int gameChoice = 0;
        do {
            try {
                Scanner scGameChoice = new Scanner(System.in);
                gameChoice = scGameChoice.nextInt();
                if(gameChoice != 1 && gameChoice != 2) {
                    System.out.println("L'entrée n'est pas valide. Veuillez taper une entrée existante. Merci.");
                }
            } catch (Exception e) {
                LOGGER.log(WARN, "choiceAGame() - L'entrée n'est pas valide. Veuillez taper une entrée existante. Merci.");
                System.out.println("L'entrée n'est pas valide. Veuillez taper une entrée existante. Merci.");
            }
        } while (gameChoice != 1 && gameChoice != 2);

        return gameChoice;
    }

    /**
     * <b>Method choiceGameMode :</b>
     * @return (int) method chosen by the Player (1: Challenger, 2: Défenseur and 3: Duel)
     */
    public int choiceGameMode(){
        System.out.println("Choisissez un mode de jeu :");
        System.out.println("1. Mode challenger: devinez la combinaison secrète de l'ordinateur");
        System.out.println("2. Mode défenseur: laissez l'ordinateur deviner votre comnbinaison secrète");
        System.out.println("3. Mode duel: vous vs l'ordinateur. Le premier qui trouve la combinaison gagne");

        int gameMethodChoice = 0;
        do {
            try {
                Scanner scGameChoice = new Scanner(System.in);
                gameMethodChoice = scGameChoice.nextInt();
                if(gameMethodChoice != 1 && gameMethodChoice != 2 && gameMethodChoice != 3){
                    System.out.println("L'entrée n'est pas valide. Veuillez taper une entrée existante. Merci.");
                }
            } catch (Exception e) {
                LOGGER.log(WARN, "choiceGameMode() - L'entrée n'est pas valide. Veuillez taper une entrée existante. Merci.");
                System.out.println("L'entrée n'est pas valide. Veuillez taper une entrée existante. Merci.");
            }
        } while (gameMethodChoice != 1 && gameMethodChoice != 2 && gameMethodChoice != 3);

        return gameMethodChoice;
    }

    /**
     *<b>Method void startGame :</b> Start the game with the type of game and the mode chosen by the player
     * and ending with endingMenuChoice whose return a int. Then call the endingAGame method.
     * @param gameChoice
     * @param gameMethodChoice
     */
    public void startAGame(int gameChoice, int gameMethodChoice){
        switch (gameChoice){
            case 1:
                System.out.println("===== Recherche +/- =====");
                Game searchPlusMinus = new SearchPlusMinus();
                searchPlusMinus.startAGameByIsMethodChoice(gameMethodChoice);
                break;
            case 2:
                System.out.println("===== Mastermind =====");
                Game mastermind = new Mastermind();
                mastermind.startAGameByIsMethodChoice(gameMethodChoice);
                break;
            default:
                System.out.println("Entrée invalide. Retour au menu principal");
                start();
        }
        int endingMenuChoice = endingMenu();
        endingAGame(endingMenuChoice, gameChoice, gameMethodChoice);
    }

    /**
     * <b>Method endingMenu :</b>
     * @return (int) ending option chosen by the Player (1: Rejouer, 2: Retour menu and 3: Quitter)
     */
    public int endingMenu(){
        System.out.println("1. Rejouer à une partie");
        System.out.println("2. Retour au menu des jeux");
        System.out.println("3. Quitter Mastermind");

        int endingMenuChoice = 0;
        do {
            try {
                Scanner scGameChoice = new Scanner(System.in);
                endingMenuChoice = scGameChoice.nextInt();
                if(endingMenuChoice != 1 && endingMenuChoice != 2 && endingMenuChoice != 3){
                    System.out.println("L'entrée n'est pas valide. Veuillez taper une entrée existante. Merci.");
                }
            } catch (Exception e) {
                LOGGER.log(WARN, "endingMenu() - L'entrée n'est pas valide. Veuillez taper une entrée existante. Merci.");
                System.out.println("L'entrée n'est pas valide. Veuillez taper une entrée existante. Merci.");
            }
        } while (endingMenuChoice != 1 && endingMenuChoice != 2 && endingMenuChoice != 3);

        return endingMenuChoice;
    }

    /**
     * <b>Method void endingAGame :</b> End the game with the ending option chosen by the player
     * @param endingMenuChoice ending option chosen by the player
     * @param gameChoice game chosen by the player if the player want to replay the game
     * @param methodChoice method chosen by the player if the player want to replay the game
     */
    public  void endingAGame(int endingMenuChoice, int gameChoice, int methodChoice){
        switch (endingMenuChoice){
            case 1:
                startAGame(gameChoice, methodChoice);
                break;
            case 2:
                start();
                break;
            case 3:
                System.out.println("Au revoir. A bientôt :) !");
                break;
            default:
                System.out.println("Entrée invalide ? Retour au menu principal");
                start();
        }
    }
}