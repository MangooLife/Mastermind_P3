package main.java;

public class SearchPlusMinus extends Game{

    @Override
    public void playerGuessTheSecret() {
        System.out.println("(Combinaison secrète : "+secretCombinaison.get(0)+secretCombinaison.get(1)+secretCombinaison.get(2)+secretCombinaison.get(3)+")");
        int rightNumber;
        do{
            propositionOfThePlayer();
            rightNumber = 0;
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
            nbLife--;
        }while(nbLife > 0 && rightNumber != 4);
        haveYouWin(rightNumber);
    }

    @Override
    public void computerGuessTheSecret() {
    }

    @Override
    public void computerVsPlayer() {

    }
}