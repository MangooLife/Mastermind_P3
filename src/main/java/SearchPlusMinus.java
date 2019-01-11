package main.java;

import java.util.Random;
import java.util.Scanner;

public class SearchPlusMinus extends Game{

    private int computerCombinaison[] = new int[nbCase];
    private String responseCombinaison[] = new String[nbCase];
    private int computerFirstCombinaison[] = secretCombinaison;
    private String playerResponse[] = {} ;
    private boolean computerWinGame = false;

    @Override
    public void playerGuessTheSecret() {
        isDeveloperMode();
        do{
            displayPlayerGuessTheSecret();
        }while(nbLife > 0 && rightNumber != nbCase);
        haveYouWin(rightNumber);
    }

    @Override
    public void computerGuessTheSecret() {
        int computerFirstCombinaison[] = secretCombinaison;
        String playerResponse[];
        boolean computerWinGame = false;
        do{
            displayComputerGuessTheSecret();
        }while(nbLife > 0 && !computerWinGame);
        haveComputerWin(computerWinGame);
    }

    @Override
    public void computerVsPlayer() {
        int nbLifeCvsP = nbLife * 2;
        do{
            if(computerWinGame==false){
                System.out.println("Joueur devine la combinaison de l'ordinateur : ");
                displayPlayerGuessTheSecret();
            }
            System.out.println();
            if(rightNumber != nbCase){
                System.out.println("L'ordinateur devine la combinaison du joueur : ");
                displayComputerGuessTheSecret();
            }
        }while((nbLifeCvsP) > 0 && !computerWinGame && rightNumber != nbCase);
        haveComputerWin(computerWinGame);
        haveYouWin(rightNumber);
    }

    /**
     * <b>Method displayPlayerGuessTheSecret :</b> display the game playerGuessTheSecret
     */
    public void displayPlayerGuessTheSecret(){
        isDeveloperMode();
        propositionOfThePlayer();
        rightNumber = 0;
        System.out.print(" -> Réponse : ");
        for (int i = 0; i < nbCase; i++){
            if (guessCombinaison[i] == computerSecret[i]) {
                System.out.print("=");
                rightNumber++;
            } else if (guessCombinaison[i] < computerSecret[i]){
                System.out.print("+");
            } else {
                System.out.print("-");
            }
        }
        System.out.println();
        nbLife--;
    }

    /**
     * <b>Method displayComputerGuessTheSecret :</b> display the game computerGuessTheSecret
     */
    public void displayComputerGuessTheSecret(){
        System.out.print("Proposition : ");
        showValueOfTab(computerFirstCombinaison);
        System.out.print(" -> Réponse : ");
        playerResponse = responseOfThePlayer();
        String response = String.join("", playerResponse);
        if(response.equals("====")){ //nbCase à revoir
            computerWinGame = true;
            System.out.println("win");
        } else {
            computerFirstCombinaison = propositionOfTheComputer(computerFirstCombinaison, playerResponse);
            nbLife--;
        }
        System.out.println();
    }

    /**
     * <b>Method propositionOfThePlayer :</b> Player guess the secret code
     * @return guessCombinaison : code guessed by the player
     */
    public int[] propositionOfThePlayer(){
        System.out.print("Veuillez taper une suite de "+nbCase+" chiffres entre 0 et 9: ");
        String number = "";
        do{
            try{
                Scanner scProposition = new Scanner(System.in);
                number = scProposition.nextLine();
                if(number.length() != nbCase){
                    System.out.println("Veuillez fournir un code à "+nbCase+" chiffres");
                }
            } catch(Exception e){
                System.out.println("Veuillez fournir un code à "+nbCase+" chiffres");
            }
        }while(number.length()!=nbCase);

        for(int i=0; i<number.length(); i++){
            guessCombinaison[i] = Integer.valueOf(String.valueOf(number.charAt(i)));
        }
        System.out.print("Proposition : ");
        showValueOfTab(guessCombinaison);
        return guessCombinaison;
    }

    /**
     * <b>Method responseOfThePlayer :</b> return list of +, - or =; response of the player
     * @return responseCombinaison
     */
    public String[] responseOfThePlayer(){
        System.out.print("Est-ce la bonne combinaison ? ");
        String response = "";
        do{
            try{
                Scanner scProposition = new Scanner(System.in);
                response = scProposition.nextLine();
                if(response.length() != nbCase && (response != "=" || response != "+" || response != "-")){
                    System.out.println("Veuillez fournir une suite de "+nbCase+" +, - ou =");
                }
            } catch(Exception e){
                System.out.println("Veuillez fournir une suite de "+nbCase+" +, - ou =");
            }
        }while(response.length()!= nbCase && (response != "=" || response != "+" || response != "-"));
        for(int i=0; i<response.length(); i++){
            responseCombinaison[i] = String.valueOf(response.charAt(i));
        }
        return responseCombinaison;
    }

    /**
     * <b>Method propositionOfTheComputer :</b>
     * @param computerFirstCombinaison
     * @param playerResponse
     * @return list computer combinaison
     */
    public int[] propositionOfTheComputer(int[] computerFirstCombinaison, String[] playerResponse){
        Random random = new Random();
        int computerCombinaison[] = new int[nbCase];
        int combinaison = 0;
        for(int i=0; i<nbCase; i++){ // a revoir
            if(playerResponse[i].equals("+")){
                combinaison = random.nextInt(((9 - (computerFirstCombinaison[i]+1))+1)+computerFirstCombinaison[i]);
                computerCombinaison[i] = combinaison;
            } else if(playerResponse[i].equals("-")){
                combinaison = random.nextInt((((computerFirstCombinaison[i]-1)-0) + 1));
                computerCombinaison[i] = combinaison;
            } else {
                computerCombinaison[i] = computerFirstCombinaison[i];
            }
        }
        return computerCombinaison;
    }
}
