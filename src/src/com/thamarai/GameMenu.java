package src.com.thamarai;

import java.util.Scanner;

public class GameMenu {

    public void start() {
        int game = choiceAGame();
        int method = choiceGameMode();
        startAGame(game, method);
    }

    public int choiceAGame() {
        System.out.println("Bienvenue sur Mastermind !");
        System.out.println("Choisissez un jeu :");
        System.out.println("1. Recherche +/-");
        System.out.println("2. Mastermind");

        int gameChoice = 0;
        do {
            try {
                Scanner scGameChoice = new Scanner(System.in);
                gameChoice = scGameChoice.nextInt();
            } catch (Exception e) {
                System.out.println("L'entrée n'est pas valide. Veuillez taper une entrée existante. Merci.");
            }
        } while (gameChoice != 1 && gameChoice != 2);

        return gameChoice;
    }

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
            } catch (Exception e) {
                System.out.println("L'entrée n'est pas valide. Veuillez taper une entrée existante. Merci.");
            }
        } while (gameMethodChoice != 1 && gameMethodChoice != 2 && gameMethodChoice != 3);

        return gameMethodChoice;
    }

    public int endingMenu(){
        System.out.println("1. Rejouer à une partie");
        System.out.println("2. Retour au menu des jeux");
        System.out.println("3. Quitter Mastermind");

        int endingMenuChoice = 0;
        do {
            try {
                Scanner scGameChoice = new Scanner(System.in);
                endingMenuChoice = scGameChoice.nextInt();
            } catch (Exception e) {
                System.out.println("L'entrée n'est pas valide. Veuillez taper une entrée existante. Merci.");
            }
        } while (endingMenuChoice != 1 && endingMenuChoice != 2 && endingMenuChoice != 3);

        return endingMenuChoice;
    }

    public void startAGame(int gameChoice, int gameMethodChoice){
        switch (gameChoice){
            case 1:
                System.out.println("Recherche +/-");
                Game searchPlusMinus = new SearchPlusMinus();
                searchPlusMinus.startAGameByIsMethodChoice(gameMethodChoice);
                break;
            case 2:
                System.out.println("Mastermind");
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

    public  void endingAGame(int endingMenuChoice, int gameChoice, int methodChoice){
        switch (endingMenuChoice){
            case 1:
                startAGame(gameChoice, methodChoice);
                break;
            case 2:
                start();
                break;
            case 3:
                System.out.println("Bye");
                break;
            default:
                System.out.println("Entrée invalide ? Retour au menu principal");
                start();
        }
    }

    /*public void playerGuessTheSecret(){
        generateASecret();
        guessTheSecret();
    }

    public List<Integer> generateASecret(){
        Random rand = new Random();
        Integer computerSecret = 0;
        List<Integer> secretCombinaison = new List<Integer>;

        for(int i=0; i<4; i++){
            computerSecret = rand.nextInt(9);
            secretCombinaison.set(i, computerSecret);
        }
        return secretCombinaison;
    }

    public void guessTheSecret(){
        System.out.println("Combinaison :");
    }*/


}
