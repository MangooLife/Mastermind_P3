package main.java;

import org.apache.logging.log4j.Level;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Mastermind extends Game{
    private List<Integer> numbersWhoseNotInTheCombinaison = new ArrayList<Integer>();
    private List<Integer> winCombinaison = new ArrayList<Integer>();
    private String responseCombinaison[] = new String[nbCase];
    private int computerCombinaison[] = generateACombi();
    private int lastCombinaison[] = new int[nbCase];
    private int playerResponse;
    private int nbGoodNumber, nbGoodCase;
    private int nbGoodNumberComputer, nbGoodCaseComputer;
    private int combinaison = 0;
    private int previousNbGoodCase, previousNbGoodNumber = 0;
    private boolean computerWinGame = false;
    private String regexNumber = "[0-9]";
    private String regexLetter = "[^a-zA-Z]";

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
            if(!computerWinGame){
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
                LOGGER.log(Level.WARN, "propositionOfThePlayer() - Veuillez fournir un code à "+nbCase+" chiffres");
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
        nbGoodCaseComputer = 0;
        String testNbGoodCaseComputer = "0";
        do {
            System.out.print("Combien de chiffre bien placés ? ");
            try {
                Scanner scProposition = new Scanner(System.in);
                nbGoodCaseComputer = scProposition.nextInt();
                testNbGoodCaseComputer = Integer.toString(nbGoodCaseComputer);
                System.out.println(nbGoodCaseComputer);
                if (!testNbGoodCaseComputer.matches(regexNumber) && !testNbGoodCaseComputer.matches(regexLetter) && nbGoodCaseComputer > nbCase) {
                    LOGGER.log(Level.WARN, "responseOfThePlayerAskGoodCase() - Veuillez fournir le nombre de chiffre bien placés");
                    System.out.println("Veuillez fournir le nombre de chiffre bien placés");
                }
            } catch (Exception e) {
                LOGGER.log(Level.WARN, "responseOfThePlayerAskGoodCase() - Veuillez fournir le nombre chiffre bien placés");
                System.out.println("Veuillez fournir le nombre de chiffre bien placés");
            }
        } while (!testNbGoodCaseComputer.matches(regexNumber) && !testNbGoodCaseComputer.matches(regexLetter) && nbGoodCaseComputer > nbCase);
        return nbGoodCaseComputer;
    }

    /**
     * <b>Method responseOfThePlayerAskGoodNumber :</b> return number of good case guess by the computer
     * @return nbGoodNumber
     */
    public int responseOfThePlayerAskGoodNumber() {
        nbGoodNumber = 0;
        String testNbGoodNumber = "0";
        do{
            System.out.print("Combien de chiffre correctes ? ");
            try{
                Scanner scProposition = new Scanner(System.in);
                nbGoodNumberComputer = scProposition.nextInt();
                testNbGoodNumber = Integer.toString(nbGoodNumberComputer);
                if(!testNbGoodNumber.matches(regexNumber) && !testNbGoodNumber.matches(regexLetter) && nbGoodNumberComputer > nbCase){
                    LOGGER.log(Level.WARN, "responseOfThePlayerAskGoodNumber() - Veuillez fournir le nombre de chiffre correcte");
                    System.out.println("Veuillez fournir le nombre de chiffre correcte");
                }
            } catch(Exception e){
                LOGGER.log(Level.WARN, "responseOfThePlayerAskGoodNumber() - Veuillez fournir le nombre de chiffre correcte");
                System.out.println("Veuillez fournir le nombre de chiffre correcte");
            }
        }while(!testNbGoodNumber.matches(regexNumber) && !testNbGoodNumber.matches(regexLetter) && nbGoodNumberComputer > nbCase);

        return nbGoodNumberComputer;
    }

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
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
}