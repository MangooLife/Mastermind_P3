package main.java;

import main.java.resources.PropertyManager;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private PropertyManager propertyManager = new PropertyManager();
    protected int nbCase = propertyManager.getNbCase();
    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


    Game game = new Game() {
        @Override
        public void playerGuessTheSecret() {

        }

        @Override
        public void computerGuessTheSecret() {

        }

        @Override
        public void computerVsPlayer() {

        }
    };

    @Test
    public void Given_SecretCombinaison_When_GenerateASecret_Then_ReturnSecretCombinaison(){
       int[] secret = game.generateASecret();
       assertEquals(nbCase, secret.length);
    }

    @Test
    public void Given_Combinaison_When_PropositionOfThePlayer_Then_ReturnCombinaison(){
        if(nbCase==4) {
            String input = "6000";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            int tableauEntier[] = {6, 0, 0, 0};
            assertArrayEquals(tableauEntier, game.propositionOfThePlayer());
        }
    }

    @Test
    public void Given_RightNumber_When_HaveYouWin_Then_SuccessMessage(){
        System.setOut(new PrintStream(outContent));
        game.haveYouWin(nbCase);
        assertEquals("Vous avez gagné contre l'ordinateur. Bravo !\n", outContent.toString());
    }

    @Test
    public void Given_RightNumber_When_HaveYouWin_Then_ErrorMessage(){
        System.setOut(new PrintStream(outContent));
        game.haveYouWin(nbCase+1);
        assertNotEquals("Vous avez perdu contre l'ordinateur. Le code secret est \n 0000\n", outContent.toString());
    }

    @Test
    public void Given_Tab_When_ShowValueOfTab_Then_ShowTab(){
        System.setOut(new PrintStream(outContent));
        int tab[] = {6, 0, 0, 0};
        game.showValueOfTab(tab);
        assertEquals("6000", outContent.toString());
    }
}
