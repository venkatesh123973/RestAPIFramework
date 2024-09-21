package org.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class configmanager {

    public static void updatedpropertyfile(String key,String name){
        Properties prop = new Properties();

        try {
            prop.load(new FileInputStream("configinput.properties"));
            prop.setProperty(key,name);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fout = new FileOutputStream("configinput.properties");
            try {
                prop.store(fout,null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

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
