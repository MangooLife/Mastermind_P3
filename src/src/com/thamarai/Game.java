package src.com.thamarai;

import java.util.*;

public abstract class Game {

    List<Integer> secretCombinaison = new ArrayList<Integer>();
    List<Integer> guessCombinaison = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0));

    public void startAGameByIsMethodChoice(int methodChoice){
        List<Integer> computerSecret = generateASecret();

        switch (methodChoice){
            case 1:
                System.out.println("Computer");
                playerGuessTheSecret(computerSecret);
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
                playerGuessTheSecret(computerSecret);
        }
    }

    public List<Integer> generateASecret(){
        Random random = new Random();
        for(int i=0; i<4; i++){
            Integer computerSecret = random.nextInt(9);
            secretCombinaison.add(computerSecret);
        }
        return secretCombinaison;
    }

    public List<Integer> propositionOfThePlayer(){
        System.out.println("Veuillez taper une suite de 4 chiffres entre 0 et 9: ");
        Scanner scProposition = new Scanner(System.in);
        for(int i=0; i<4; i++){
            int number = 0;
            do{
                number = scProposition.nextInt();
            }while(number < 0 || number > 9);
            guessCombinaison.set(i, number);
        }
        System.out.print("Proposition : "+guessCombinaison.get(0)+guessCombinaison.get(1)+guessCombinaison.get(2)+guessCombinaison.get(3));

        return guessCombinaison;
    }

    public void haveYouWin(int rightNumber){
        if(rightNumber == 4){
            System.out.println("Vous avez gagné contre l'ordinateur. Bravo !");
        } else {
            System.out.println("Vous avez perdu contre l'ordinateur.");
        }
    }

    public void playerGuessTheSecret(List<Integer> computerSecret){
    }

    public void computerGuessTheSecret(){

    }

    public void computerVsPlayer(){

    }
}
