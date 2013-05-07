/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cashiers.util;

import java.util.Calendar;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

/**
 *
 * @author elbrujo
 */
public abstract class Util {
    public static String getCurrentDate() {
        final Calendar c = Calendar.getInstance();        
        int MONTH = c.get(Calendar.MONTH);        
        String sDate = (c.get(Calendar.DATE) < 10) ? "0" + c.get(Calendar.DATE) : 
                String.valueOf(c.get(Calendar.DATE));       
        MONTH = MONTH + 1;
        String sMonth = (c.get(Calendar.MONTH) < 10) ? "0" + String.valueOf(MONTH) : 
                String.valueOf(MONTH);        
        return sDate + "/" + sMonth + "/" + c.get(Calendar.YEAR);        
    }
    
    public static String getCurrentTime() {
        final Calendar c = Calendar.getInstance();        
        String sHour = (c.get(Calendar.HOUR_OF_DAY) < 10) ? "0" + c.get(Calendar.HOUR_OF_DAY) : 
                String.valueOf(c.get(Calendar.HOUR_OF_DAY));        
        String sMinute = (c.get(Calendar.MINUTE) < 10) ? "0" + c.get(Calendar.MINUTE) : 
                String.valueOf(c.get(Calendar.MINUTE));        
        String sSecond = (c.get(Calendar.SECOND) < 10) ? "0" + c.get(Calendar.SECOND) : 
                String.valueOf(c.get(Calendar.SECOND));        
        return sHour + ":" + sMinute + ":" + sSecond;
    }
    
    public static Rectangle2D getScreenBounds() {
        return Screen.getPrimary().getVisualBounds();
    }
    
}
