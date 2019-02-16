package main.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.apache.logging.log4j.Level.WARN;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameMenuTest {
    GameMenu game = new GameMenu();
    Logger LOGGER = LogManager.getLogger(GameMenuTest.class);
    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


    @Test
    public void Given_Number1_When_ChoiceAGame_Then_ReturnNumber1(){
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        int response = 1;
        assertEquals(response, game.choiceAGame());
    }

    @Test
    public void Given_Number2_When_ChoiceAGame_Then_ReturnNumber1(){
        String input = "2";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        int response = 2;
        assertEquals(response, game.choiceAGame());
    }

    @Test
    public void Given_Number3_When_ChoiceAGame_Then_ReturnError(){
        String input = "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        LOGGER.log(WARN, "choiceAGame() - L'entrée n'est pas valide. Veuillez taper une entrée existante. Merci.");
    }

    @Test
    public void Given_Number1_When_ChoiceGameMode_Then_ReturnNumber1(){
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        int response = 1;
        assertEquals(response, game.choiceGameMode());
    }

    @Test
    public void Given_Number2_When_ChoiceGameMode_Then_ReturnNumber2(){
        String input = "2";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        int response = 2;
        assertEquals(response, game.choiceGameMode());
    }

    @Test
    public void Given_Number3_When_ChoiceGameMode_Then_ReturnNumber3(){
        String input = "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        int response = 3;
        assertEquals(response, game.choiceGameMode());
    }

    @Test
    public void Given_Number4_When_ChoiceGameMode_Then_ReturnError(){
        String input = "4";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        LOGGER.log(WARN, "choiceGameMode() - L'entrée n'est pas valide. Veuillez taper une entrée existante. Merci.");
    }

    @Test
    public void Given_Number1_When_EndingMenu_Then_ReturnNumber1(){
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        int response = 1;
        assertEquals(response, game.endingMenu());
    }

    @Test
    public void Given_Number2_When_EndingMenu_Then_ReturnNumber2(){
        String input = "2";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        int response = 2;
        assertEquals(response, game.endingMenu());
    }

    @Test
    public void Given_Number3_When_EndingMenu_Then_ReturnNumber3(){
        String input = "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        int response = 3;
        assertEquals(response, game.endingMenu());
    }

    @Test
    public void Given_Number4_When_EndingMenu_Then_ReturnError(){
        String input = "4";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        LOGGER.log(WARN, "endingMenu() - L'entrée n'est pas valide. Veuillez taper une entrée existante. Merci.");
    }
}
