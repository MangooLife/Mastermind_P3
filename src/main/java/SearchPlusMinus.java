package main.java;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class SearchPlusMinus extends Game{

    private int computerCombinaison[] = new int[nbCase];
    private String responseCombinaison[] = new String[nbCase];

    @Override
    public void playerGuessTheSecret() {
        isDeveloperMode();
        do{
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
        }while(nbLife > 0 && rightNumber != nbCase);
        haveYouWin(rightNumber);
    }

    @Override
    public void computerGuessTheSecret() {
        int computerFirstCombinaison[] = secretCombinaison;
        String playerResponse[];
        String winResponseCombinaison[] = {"=","=","=","="};
        boolean computerWinGame = false;
        do{
            rightNumber = 0;
            System.out.print("Proposition : ");
            showValueOfTab(computerFirstCombinaison);
            System.out.print(" -> Réponse : ");
            playerResponse = responseOfThePlayer();
            if(Arrays.equals(playerResponse, winResponseCombinaison)){
                rightNumber = nbCase;
                computerWinGame = true;
                System.out.println("win");
            } else {
                computerFirstCombinaison = propositionOfTheComputer(computerFirstCombinaison, playerResponse);
                nbLife--;
            }
            System.out.println();
        }while(nbLife > 0 && !computerWinGame);
        haveYouWin(rightNumber);
    }

    @Override
    public void computerVsPlayer() {

    }

    /**
     * <b>Method responseOfThePlayer :</b> return list of +, - or =; response of the player
     * @return responseCombinison
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
        for(int i=0; i<nbCase; i++){
            if(playerResponse.equals("+")){
                Integer computerCombinaison = random.nextInt((9 + 1 - (computerFirstCombinaison[i]+1))+computerFirstCombinaison[i]+1);
                secretCombinaison[i] = computerCombinaison;
            } else if(playerResponse.equals("-")){
                Integer computerCombinaison = random.nextInt((computerFirstCombinaison[i]-1));
                secretCombinaison[i] = computerCombinaison;
            }
        }
        return computerCombinaison;
    }
}