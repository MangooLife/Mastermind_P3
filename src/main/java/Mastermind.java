package main.java;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Mastermind extends Game{
    private String responseCombinaison[] = new String[nbCase];
    private int playerResponse;
    private int computerCombinaison[] = generateACombi();
    private int nbGoodNumber, nbGoodCase;
    private int nbGoodNumberComputer, nbGoodCaseComputer;
    private boolean computerWinGame = false;

    @Override
    public void playerGuessTheSecret() {
        do{
            displayPlayerGuessTheSecret();
        }while(nbLife > 0 && nbGoodCase != nbCase);
        haveYouWin(nbGoodCase);
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
            if(computerWinGame==false){
                System.out.println("Joueur devine la combinaison de l'ordinateur : ");
                displayPlayerGuessTheSecret();
            }
            System.out.println(computerWinGame + " " + nbGoodCase);
            if(nbGoodCase != nbCase){
                System.out.println("L'ordinateur devine la combinaison du joueur : ");
                displayComputerGuessTheSecret();
            }
            nbLifeCvsP--;
        }while((nbLifeCvsP) > 0 && !computerWinGame && nbGoodCase != nbCase);
        haveComputerWin(computerWinGame);
        haveYouWin(nbGoodCase);
    }

    /**
     * <b>Method displayPlayerGuessTheSecret :</b> display the game playerGuessTheSecret
     */
    public void displayPlayerGuessTheSecret(){
        isDeveloperMode(); propositionOfThePlayer();
        nbGoodNumber = 0; nbGoodCase = 0 ;
        List<Integer> noDouble = new ArrayList<Integer>();
        System.out.print(" -> Réponse : ");
        for(int i = 0; i < nbCase; i++) {
            if (guessCombinaison[i] == computerSecret[i]) {
                nbGoodCase++;
                noDouble.add(guessCombinaison[i]);
                guessCombinaison[i] = -1;
            }
        }
        for(int i = 0; i < nbCase; i++){
            for (int j = 0; j < nbCase && !noDouble.contains(guessCombinaison[i]) && guessCombinaison[i] != -1; j++) {
                if (guessCombinaison[i] == computerSecret[j]) {
                    nbGoodNumber++;
                    guessCombinaison[i] = -1;
                }
            }
        }
        System.out.println(nbGoodNumber+" présent, "+ nbGoodCase +" bien placé");
        nbLife--;
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
     * <b>Method displayComputerGuessTheSecret :</b> display the game computerGuessTheSecret
     */
    public void displayComputerGuessTheSecret(){
        System.out.print("Proposition : ");
        showValueOfTab(computerCombinaison);
        System.out.print(" -> Réponse : ");
        nbGoodCaseComputer = responseOfThePlayerAskGoodCase();
        nbGoodNumberComputer = responseOfThePlayerAskGoodNumber();
        System.out.println(nbGoodNumberComputer+" présent, "+ nbGoodCaseComputer +" bien placé");
        if(nbGoodCaseComputer == nbCase){
            computerWinGame = true;
            System.out.println("win");
        } else {
            computerCombinaison = propositionOfTheComputer(computerCombinaison, nbGoodCaseComputer, nbGoodNumberComputer);
            nbLife--;
        }
    }

    /**
     * <b>Method responseOfThePlayerAskGoodCase :</b>  return number of good number guess by the computer
     * @return nbGoodCase
     */
    public int responseOfThePlayerAskGoodCase() {
        System.out.print("Combien de chiffre bien placés ? ");
        nbGoodCaseComputer = 0;
        do {
            try {
                Scanner scProposition = new Scanner(System.in);
                nbGoodCaseComputer = scProposition.nextInt();
                if (nbGoodCaseComputer < 0 && nbGoodCaseComputer > nbCase) {
                    System.out.println("Veuillez fournir le nombre de case correcte");
                }
            } catch (Exception e) {
                System.out.println("Veuillez fournir le nombre de case correcte");
            }
        } while (nbGoodCaseComputer < 0 && nbGoodCaseComputer > nbCase);
        return nbGoodCaseComputer;
    }

    /**
     * <b>Method responseOfThePlayerAskGoodNumber :</b> return number of good case guess by the computer
     * @return nbGoodNumber
     */
    public int responseOfThePlayerAskGoodNumber() {
        System.out.print("Combien de chiffre correctes ? ");
        nbGoodNumber = 0;
        do{
            try{
                Scanner scProposition = new Scanner(System.in);
                nbGoodNumberComputer = scProposition.nextInt();
                if(nbGoodNumberComputer < 0 && nbGoodNumberComputer > nbCase){
                    System.out.println("Veuillez fournir le nombre de chiffre correcte");
                }
            } catch(Exception e){
                System.out.println("Veuillez fournir le nombre de chiffre correcte");
            }
        }while(nbGoodNumberComputer < 0 && nbGoodNumberComputer > nbCase);

        return nbGoodNumberComputer;
    }


    private List<Integer> numbersWhoseNotInTheCombinaison = new ArrayList<Integer>();
    private int lastCombinaison[] = new int[nbCase];
    private List<int[]> game = new ArrayList<int[]>();
    private int combinaison = 0;
    private int previousNbGoodCase, previousNbGoodNumber = 0;
    private List<Integer> winCombinaison = new ArrayList<Integer>();


    /**
     * <b>Method propositionOfTheComputer :</b>
     * @param computerCombinaison
     * @param nbGoodCase
     * @param nbGoodNumber
     * @return list computer combinaison
     */
    public int[] propositionOfTheComputer(int computerCombinaison[], int nbGoodCase, int nbGoodNumber) {
        int[] newCombinaison = new int[nbCase];

        if(winCombinaison.size() == nbCase || nbGoodNumber == nbCase){
            for(int i = 0; i < nbCase; i++){
                lastCombinaison[i] = winCombinaison.get(i);
            }
            shuffleArray(lastCombinaison);
            for (int i = 0; i < nbCase; i++) {
                newCombinaison[i] = lastCombinaison[i];
                winCombinaison.add(lastCombinaison[i]);
            }
        } else if (nbGoodCase > 0){
            for(int i = 0; i<nbGoodCase; i++){
                winCombinaison.add(computerCombinaison[i]);
                System.out.println(winCombinaison);
            }
            for (int i = 0; i < nbCase; i++) {
                numbersWhoseNotInTheCombinaison.add(computerCombinaison[i]);
                do {
                    combinaison = computerCombinaison[i] + 1;
                } while (numbersWhoseNotInTheCombinaison.contains(combinaison));
                if(combinaison < 10) {
                    newCombinaison[i] = combinaison;
                } else {
                    newCombinaison[i] = 0;
                }
            }
            if (numbersWhoseNotInTheCombinaison.size() >= 9) {
                numbersWhoseNotInTheCombinaison.clear();
            }
        } else if (nbGoodNumber == 0 && nbGoodCase == 0 || nbGoodNumber == previousNbGoodNumber) {
            for (int i = 0; i < nbCase; i++) {
                numbersWhoseNotInTheCombinaison.add(computerCombinaison[i]);
                do {
                    combinaison = computerCombinaison[i] + 1;
                } while (numbersWhoseNotInTheCombinaison.contains(combinaison));
                if(combinaison < 10) {
                    newCombinaison[i] = combinaison;
                } else {
                    newCombinaison[i] = 0;
                }

            }
            if (numbersWhoseNotInTheCombinaison.size() >= 9) {
                numbersWhoseNotInTheCombinaison.clear();
            }
        }
        return newCombinaison;
    }

    static void shuffleArray(int[] ar)
    {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
}