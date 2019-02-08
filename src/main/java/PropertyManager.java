package main.java;

import java.io.BufferedReader;
import java.io.FileReader;

import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
    private Properties properties;
    private final String propertyFilePath = "src/main/resources/config.properties";

    public PropertyManager(){
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            properties.load(reader);
            reader.close();
        }
        catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("config.properties not found at "+propertyFilePath);
        }
    }

    /**
     * <b>Method getNbLife :</b> initialize the number of life
     * @return nbLife
     */
    public int getNbLife(){
        Integer nbLife = Integer.parseInt(properties.getProperty("nbLife"));
        if(nbLife!=null){
            return nbLife;
        } else {
            throw new RuntimeException("nbLife not specified in the config.properties");
        }
    }

    /**
     * <b>Method getNbCase :</b> initialize the number of case of the secret code
     * @return  nbCase
     */
    public int getNbCase(){
        Integer nbCase = Integer.parseInt(properties.getProperty("nbCase"));
        if(nbCase!=null){
            return nbCase;
        } else {
            throw new RuntimeException("nbCase not specified in the config.properties");
        }
    }

    /**
     * <b>Method getIsDeveloperMode :</b> initialize the mode of the app
     * @return  isDeveloperMode
     */
    public int getIsDeveloperMode(){
        Integer isDeveloperMode = Integer.parseInt(properties.getProperty("isDeveloperMode"));
        if(isDeveloperMode!=null){
            return isDeveloperMode;
        } else {
            throw new RuntimeException("isDeveloperMode not specified in the config.properties");
        }
    }
}