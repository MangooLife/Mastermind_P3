package main.java;

import java.util.*;

public abstract class Game {

    List<Integer> secretCombinaison = new ArrayList<>();
    List<Integer> guessCombinaison = new ArrayList<>(Arrays.asList(0, 0, 0, 0));
    List<Integer> computerSecret = generateASecret();


    /**
     * <b>Method void startAGameByIsMethodChoice :</b> start a game by the player method choice
     * @param methodChoice
     */
    public void startAGameByIsMethodChoice(int methodChoice){
        switch (methodChoice){
            case 1:
                System.out.println("Computer");
                playerGuessTheSecret();
                break;
            case 2:
                System.out.println("Player");
                computerGuessTheSecret();
                break;
            case 3:
                System.out.println("Player vs Computer");
                computerVsPlayer();
                break;
            default:
                System.out.println("Jeu par défaut: ");
                playerGuessTheSecret();
        }
    }

    /**
     * <b>Method generateASecret :</b> return a secret code
     * @return (list[Integer]) secretCombinaison
     */
    public List<Integer> generateASecret(){
        Random random = new Random();
        for(int i=0; i<4; i++){
            Integer computerSecret = random.nextInt(9);
            secretCombinaison.add(computerSecret);
        }
        return secretCombinaison;
    }

    /**
     * <b>Method propositionOfThePlayer :</b> Player guess the secret code
     * @return guessCombinaison : code guessed by the player
     */
    public List<Integer> propositionOfThePlayer(){
        System.out.println("Veuillez taper une suite de 4 chiffres entre 0 et 9: ");
        int number = 0;
        for(int i=0; i<4; i++){
            do{
                try{
                    Scanner scProposition = new Scanner(System.in);
                    number = scProposition.nextInt();
                } catch(Exception e){
                    System.out.println("L'entrée est invalide");
                }
            }while(number < 0 || number > 9);
            guessCombinaison.set(i, number);
        }
        System.out.print("Proposition : "+guessCombinaison.get(0)+guessCombinaison.get(1)+guessCombinaison.get(2)+guessCombinaison.get(3));

        return guessCombinaison;
    }

    /**
     * <b>Method haveYouWin :</b> return success message if rightNumber is equal
     * to 4 else game over for the player
     * @param rightNumber
     */
    public void haveYouWin(int rightNumber){
        if(rightNumber == 4){
            System.out.println("Vous avez gagné contre l'ordinateur. Bravo !");
        } else {
            System.out.println("Vous avez perdu contre l'ordinateur.");
        }
    }

    /**
     * <b>Method playerGuessTheSecret :</b> Mode challenger
     */
    public abstract void playerGuessTheSecret();

    /**
     * <b>Method computerGuessTheSecret :</b> Mode defenseur
     */
    public abstract void computerGuessTheSecret();

    /**
     * <b>Method computerVsPlayer :</b> Mode Player vs Computer
     */
    public abstract void computerVsPlayer();
}
