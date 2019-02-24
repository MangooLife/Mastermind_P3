package com.thamarai;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MastermindTest {
    Mastermind game = new Mastermind();
    private PropertyManager propertyManager = new PropertyManager();
    private int nbCase = propertyManager.getNbCase();

    @Test
    public void Given_Combinaison_When_PropositionOfThePlayer_Then_ReturnCombinaison(){
        if(nbCase==4) {
            String input = "1234";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            int responseCombi[] = {1, 2, 3, 4};
            assertArrayEquals(responseCombi, game.propositionOfThePlayer());
        }
    }

    @Test
    public void Given_NbGoodCase_When_ResponseOfThePlayerAskGoodCase_Then_ReturnNbGoodCase(){
        if(nbCase==4) {
            String input = "1";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            int response = 1;
            assertEquals(response, game.responseOfThePlayerAskGoodCase());
        }
    }

    @Test
    public void Given_NbGoodNumber_When_ResponseOfThePlayerAskGoodNumber_Then_ReturnNbGoodNumber(){
        if(nbCase==4) {
            String input = "1";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            int response = 1;
            assertEquals(response, game.responseOfThePlayerAskGoodNumber());
        }
    }

}
