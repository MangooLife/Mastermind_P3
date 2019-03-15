package com.thamarai;

public class Main {
    public static void main(String[] args) {
        //check the developerMode
        PropertyManager propertyManager = new PropertyManager();
        if(args.length == 0){
            propertyManager.setIsDeveloperMode("0");
        } else {
            for(int i = 0; i < args.length; i++){
                if (args[i].equals("developerMode")){
                    propertyManager.setIsDeveloperMode("1");
                }
            }
        }
        //Start the game
        GameMenu game = new GameMenu();
        game.start();
    }
}