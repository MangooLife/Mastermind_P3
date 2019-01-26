package main.java;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Mastermind extends Game{
    private int computerCombinaison[] = new int[nbCase];
    private String responseCombinaison[] = new String[nbCase];
    private int playerResponse;
    private int computerFirstCombinaison[] = secretCombinaison;
    private int nbGoodNumber;
    private int nbGoodCase;
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
        nbGoodNumber = 0; nbGoodCase = 0 ;
        List<Integer> noTwice = new ArrayList<Integer>();
        System.out.print(" -> Réponse : ");
        for(int i = 0; i < nbCase; i++) {
            if (guessCombinaison[i] == computerSecret[i]) {
                nbGoodCase++;
                noTwice.add(guessCombinaison[i]);
            } else {
                for (int j = 0; j < nbCase && !noTwice.contains(guessCombinaison[i]); j++) {
                    if (guessCombinaison[i] == computerSecret[j]) {
                        if (i != j) {
                            nbGoodNumber++;
                        }
                    }
                }
                noTwice.add(guessCombinaison[i]);
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
        showValueOfTab(computerFirstCombinaison);
        System.out.print(" -> Réponse : ");
        nbGoodCase = responseOfThePlayerAskGoodCase();
        nbGoodNumber = responseOfThePlayerAskGoodNumber();
        System.out.println(nbGoodNumber+" présent, "+ nbGoodCase +" bien placé");
        if(nbGoodCase == nbCase){
            computerWinGame = true;
            System.out.println("win");
        } else {
            computerFirstCombinaison = propositionOfTheComputer(computerFirstCombinaison, nbGoodCase, nbGoodNumber);
            nbLife--;
        }
    }

    /**
     * <b>Method responseOfThePlayerAskGoodCase :</b>  return number of good number guess by the computer
     * @return nbGoodCase
     */
    public int responseOfThePlayerAskGoodCase() {
        System.out.print("Combien de chiffre bien placés ? ");
        nbGoodCase = 0;
        do {
            try {
                Scanner scProposition = new Scanner(System.in);
                nbGoodCase = scProposition.nextInt();
                if (nbGoodCase < 0 && nbGoodCase > nbCase) {
                    System.out.println("Veuillez fournir le nombre de case correcte");
                }
            } catch (Exception e) {
                System.out.println("Veuillez fournir le nombre de case correcte");
            }
        } while (nbGoodCase < 0 && nbGoodCase > nbCase);
        return nbGoodCase;
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
                nbGoodNumber = scProposition.nextInt();
                if(nbGoodNumber < 0 && nbGoodNumber > nbCase){
                    System.out.println("Veuillez fournir le nombre de chiffre correcte");
                }
            } catch(Exception e){
                System.out.println("Veuillez fournir le nombre de chiffre correcte");
            }
        }while(nbGoodNumber < 0 && nbGoodNumber > nbCase);

        return nbGoodNumber;
    }


    private List<Integer> numbersWhoseNotInTheCombinaison = new ArrayList<Integer>();
    private int lastCombinaison[] = new int[nbCase];
    private int previousNbGoodCase = 0; private int previousNbGoodNumber = 0;

    /**
     * <b>Method propositionOfTheComputer :</b>
     * @param computerFirstCombinaison
     * @param nbGoodCase
     * @param nbGoodNumber
     * @return list computer combinaison
     */
    public int[] propositionOfTheComputer(int computerFirstCombinaison[], int nbGoodCase, int nbGoodNumber){
        Random random = new Random();
        int computerCombinaison[] = new int[nbCase];
        int combinaison = 0;
        System.out.println(numbersWhoseNotInTheCombinaison);
        System.out.println(previousNbGoodCase);
        System.out.println(previousNbGoodNumber);

        if(nbGoodNumber == 0 && nbGoodCase == 0){
            for (int i = 0; i < nbCase; i++){
                numbersWhoseNotInTheCombinaison.add(computerFirstCombinaison[i]);
                do{
                    combinaison = random.nextInt(9 + 1);
                }while(numbersWhoseNotInTheCombinaison.contains(combinaison));
                computerCombinaison[i] = combinaison;
                System.out.println(numbersWhoseNotInTheCombinaison);
            }
            if(numbersWhoseNotInTheCombinaison.size() >= 9){
                numbersWhoseNotInTheCombinaison.clear();
            }
        } else if (nbGoodNumber == nbCase){
            shuffleArray(computerFirstCombinaison);
            for(int i = 0; i < nbCase; i++){
                computerCombinaison[i] = computerFirstCombinaison[i];
            }
        } else if (nbGoodNumber >= 0){
            if(nbGoodNumber > previousNbGoodNumber){
                do{
                    combinaison = computerFirstCombinaison[0]++;
                }while(numbersWhoseNotInTheCombinaison.contains(combinaison));
                computerCombinaison[0] = combinaison;
                lastCombinaison[0] = computerFirstCombinaison[0];
                for(int i = 1; i < nbCase-1; i++){
                    if(numbersWhoseNotInTheCombinaison.contains(computerFirstCombinaison[i])){
                        do{
                            combinaison = random.nextInt(9 + 1);
                        }while(numbersWhoseNotInTheCombinaison.contains(combinaison));
                        computerCombinaison[i] = combinaison;
                    } else {
                        lastCombinaison[i] = computerFirstCombinaison[i];
                        computerCombinaison[i] = computerFirstCombinaison[i];
                    }
                }
            }  else {
                for(int i = 0; i < nbCase; i++){
                    computerCombinaison[i] = computerFirstCombinaison[i];
                 }
            }
        }

        //manage the number of wrong number
        if(numbersWhoseNotInTheCombinaison.size() < 10){
            Set<Integer> set = new HashSet<>(numbersWhoseNotInTheCombinaison);
            numbersWhoseNotInTheCombinaison.clear();
            numbersWhoseNotInTheCombinaison.addAll(set);
        } else {
            numbersWhoseNotInTheCombinaison.clear();
        }

        //save previous number of good case and good number
        previousNbGoodCase = nbGoodCase;
        previousNbGoodNumber = nbGoodNumber;
        return computerCombinaison;
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