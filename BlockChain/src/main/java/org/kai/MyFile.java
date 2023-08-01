package org.kai;

import java.io.File;
import java.io.IOException;

public class MyFile {
    public static boolean ifTxtExists(String fileName){
        File file = new File("./data/"+fileName+".txt");
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }
    public static void createTxt(String fileName){
        File file = new File("./data/"+fileName+".txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void delTxt(String fileName){
        File file = new File("./data/"+fileName+".txt");
        file.delete();
    }
}
