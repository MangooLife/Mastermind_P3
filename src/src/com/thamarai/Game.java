package src.com.thamarai;

public abstract class Game {

    public void startAGameByIsMethodChoice(int methodChoice){
        switch (methodChoice){
            case 1:
                System.out.println("Computer");
                //playerGuessTheSecret();
                break;
            case 2:
                System.out.println("Player");
                //computerGuessTheSecret();
                break;
            case 3:
                System.out.println("Player vs Computer");
                //computerVsPlayer();
                break;
            default:
                System.out.println("Jeu par défaut: ");
                playerGuessTheSecret();
        }
    }

    public void playerGuessTheSecret(){

    }

    public void computerGuessTheSecret(){

    }

    public void computerVsPlayer(){

    }
}
