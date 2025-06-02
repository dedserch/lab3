package com.serzhputovski.circles;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.serzhputovski.circles.exception.CircleFileException;
import com.serzhputovski.circles.facade.Facade;


public class Main{
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        String circleFilePath = "data/circles/circles.txt";
        Facade facade = new Facade();
        try {
            facade.execute(circleFilePath);
        } catch (CircleFileException e) {
            logger.error("Failed to execute facade");
        }
    }
}