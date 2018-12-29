package src.com.thamarai;

import java.util.List;

public class SearchPlusMinus extends Game{

    @Override
    public void playerGuessTheSecret(List<Integer> computerSecret) {
        System.out.println("Combinaison secrète : "+secretCombinaison.get(0)+secretCombinaison.get(1)+secretCombinaison.get(2)+secretCombinaison.get(3)+")");
        int life = 3;
        int rightNumber = 0;
        do{
            propositionOfThePlayer();
            System.out.print(" -> Réponse : ");
            for (int i = 0; i < guessCombinaison.size(); i++){
                if (guessCombinaison.get(i).equals(computerSecret.get(i))) {
                    System.out.print("=");
                    rightNumber++;
                } else if (guessCombinaison.get(i) < computerSecret.get(i)){
                    System.out.print("+");
                } else {
                    System.out.print("-");
                }
            }
            System.out.println();
            life--;
        }while(life > 0 && rightNumber < 4);
        haveYouWin(rightNumber);
    }

    @Override
    public void computerGuessTheSecret() {
        super.computerGuessTheSecret();
    }

    @Override
    public void computerVsPlayer() {
        super.computerVsPlayer();
    }
}