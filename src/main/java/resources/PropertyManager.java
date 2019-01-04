package main.java.resources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
    private Properties properties;
    private final String propertyFilePath = "src/main/java/resources/config.properties";

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

    public int getNbLife(){
        Integer nbLife = Integer.parseInt(properties.getProperty("nbLife"));
        if(nbLife!=null){
            return nbLife;
        } else {
            throw new RuntimeException("nbLife not specified in the config.properties");
        }
    }
}
