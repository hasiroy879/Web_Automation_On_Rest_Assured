package utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;


public class Utils {
    public static void setEnvVar(String key, String value) throws ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/config.properties");
        config.setProperty(key, value);
        config.save();
    }

    public static int generateRandomNum(int min, int max){
        double random = Math.random()*(max-min)+min;
        return (int)random;
    }
    public static String setGender(){
        String[] genders = {"male", "female", "other"};
        int index = (int) Math.floor(Math.random() * genders.length);
        String randomGender = genders[index];
        return randomGender;
    }

    public static void main(String[] args) {
        System.out.println(setGender());
    }
}
