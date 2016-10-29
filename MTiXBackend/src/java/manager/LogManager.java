/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.io.IOException;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Student-ID
 */
public class LogManager {

    Logger logger = Logger.getLogger("MyLog");
    FileHandler fh;

    public LogManager() throws IOException {
        try {
            StringBuffer sb = new StringBuffer();
            char[] chars = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
            Random random = new Random();
            for (int i = 1; i <= 2; i++) {
                sb.append(chars[random.nextInt(chars.length)]);
            }           
            String newFileName = new String(sb);
            newFileName = "C:/Users/Student-ID/Desktop/Project/MTiXFile" + newFileName + ".log";
            fh = new FileHandler(newFileName);
            System.out.println("log file name: " + newFileName);
            
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public void logMessage(String msg) {
        logger.info(msg);
    }
}
