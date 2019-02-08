package main.java;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchPlusMinusTest {
    SearchPlusMinus game = new SearchPlusMinus();
    private PropertyManager propertyManager = new PropertyManager();
    private int nbCase = propertyManager.getNbCase();

    @Test
    public void Given_ResponseCombinaison_When_ResponseOfThePlayer_Then_ReturnResponseCombinaison(){
        if(nbCase==4) {
            String input = "===+";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            String responseCombinaison[] = {"=", "=", "=", "+"};
            assertArrayEquals(responseCombinaison, game.responseOfThePlayer());
        }
    }

    @Test
    public void Given_ComputerCombinaison_When_PropositionOfTheComputer_Then_ReturnComputerCombinaison(){
        if(nbCase==4){
            String responseCombinaison[] = {"=", "=", "=", "+"};
            int computerCombinaison[] = {6, 0, 0, 0};
            assertEquals(nbCase, game.propositionOfTheComputer(computerCombinaison, responseCombinaison).length);
        }
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
}
