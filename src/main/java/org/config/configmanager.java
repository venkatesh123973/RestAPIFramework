package org.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configmanager {

    public static String getKey(String keyName){
        System.out.println("Loading Config File");
        String value=null;
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("configinput.properties"));
            value=prop.getProperty(keyName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
}
