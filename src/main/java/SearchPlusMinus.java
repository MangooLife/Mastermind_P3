package main.java;

import org.apache.logging.log4j.Level;

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
        do{
            displayPlayerGuessTheSecret();
        }while(nbLife > 0 && rightNumber != nbCase);
        haveYouWin(rightNumber);
    }

    @Override
    public void computerGuessTheSecret() {
        do{
            displayComputerGuessTheSecret();
        }while(nbLife > 0 && !computerWinGame);
        haveComputerWin(computerWinGame);
    }

    @Override
    public void computerVsPlayer() {
        int nbLifeCvsP = nbLife * 2;
        do{
            if(!computerWinGame){
                System.out.println("Joueur devine la combinaison de l'ordinateur : ");
                displayPlayerGuessTheSecret();
            }
            System.out.println();
            if(rightNumber != nbCase){
                System.out.println("L'ordinateur devine la combinaison du joueur : ");
                displayComputerGuessTheSecret();
            }
            nbLifeCvsP--;
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
        if(response.equals(onlyNbCaseEqual())){
            computerWinGame = true;
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
        String numberS = "";
        do{
            System.out.print("Veuillez taper une suite de "+nbCase+" chiffres entre 0 et 9: ");
            try{
                Scanner scProposition = new Scanner(System.in);
                numberS = scProposition.nextLine();
                if(!numberS.matches(regexNumber) && !numberS.matches(regexLetter) || numberS.length() != nbCase){
                    LOGGER.log(Level.WARN, "propositionOfThePlayer() - Veuillez fournir un code à "+nbCase+" chiffres");
                    System.out.println("Veuillez fournir un code à "+nbCase+" chiffres");
                }
            } catch(Exception e){
                LOGGER.log(Level.WARN, "propositionOfThePlayer() - Veuillez fournir un code à "+nbCase+" chiffres");
                System.out.println("Veuillez fournir un code à "+nbCase+" chiffres");
            }
        }while(!numberS.matches(regexNumber) && !numberS.matches(regexLetter) || numberS.length() != nbCase);

        if(numberS.matches(regexNumber) && numberS.matches(regexLetter) || numberS.length() == nbCase) {
            for(int i=0; i<numberS.length(); i++){
                guessCombinaison[i] = Integer.valueOf(String.valueOf(numberS.charAt(i)));
            }
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
                if(response.length() != nbCase){
                    System.out.println("Veuillez fournir une suite de "+nbCase+" +, - ou =");
                }
            } catch(Exception e){
                LOGGER.log(Level.WARN, "responseOfThePlayer() - Veuillez fournir une suite de "+nbCase+" +, - ou =");
                System.out.println("Veuillez fournir une suite de "+nbCase+" +, - ou =");
            }
        }while(response.length()!= nbCase);
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
        for(int i=0; i<nbCase; i++){
            if(playerResponse[i].equals("+")){
                if(computerFirstCombinaison[i] == 9){ combinaison = 0; }
                else { combinaison = random.nextInt((9 - (computerFirstCombinaison[i]-1)) + 1) + computerFirstCombinaison[i]; }
                if(combinaison > 9){ combinaison = 9;}
                computerCombinaison[i] = combinaison;
            } else if(playerResponse[i].equals("-")){
                if(computerFirstCombinaison[i] == 0){ combinaison = 1; }
                else { combinaison  = random.nextInt(computerFirstCombinaison[i]-1); }
                computerCombinaison[i] = combinaison;
            } else {
                computerCombinaison[i] = computerFirstCombinaison[i];
            }
        }
        return computerCombinaison;
    }

    public String onlyNbCaseEqual(){
        StringBuilder nbCaseEqual = new StringBuilder();
        for(int i = 0; i < nbCase; i++){
            nbCaseEqual.append("=");
        }
        return nbCaseEqual.toString();
    }
}
