/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cashiers.util;

/**
 *
 * @author elbrujo
 */
public abstract class Util 
{
    public static String getCurrentDate()
    {
        final java.util.Calendar c = java.util.Calendar.getInstance();
        
        int MONTH = c.get(java.util.Calendar.MONTH);
        
        String sDate = (c.get(java.util.Calendar.DATE) < 10) ? "0" + c.get(java.util.Calendar.DATE) : 
                String.valueOf(c.get(java.util.Calendar.DATE));
       
        MONTH = MONTH + 1;
        String sMonth = (c.get(java.util.Calendar.MONTH) < 10) ? "0" + String.valueOf(MONTH) : 
                String.valueOf(MONTH);
        
        return sDate + "/" + sMonth + "/" + c.get(java.util.Calendar.YEAR);
        
    }
    
    public static String getCurrentTime()
    {
        final java.util.Calendar c = java.util.Calendar.getInstance();
        
        String sHour = (c.get(java.util.Calendar.HOUR_OF_DAY) < 10) ? "0" + c.get(java.util.Calendar.HOUR_OF_DAY) : 
                String.valueOf(c.get(java.util.Calendar.HOUR_OF_DAY));
        
        String sMinute = (c.get(java.util.Calendar.MINUTE) < 10) ? "0" + c.get(java.util.Calendar.MINUTE) : 
                String.valueOf(c.get(java.util.Calendar.MINUTE));
        
        String sSecond = (c.get(java.util.Calendar.SECOND) < 10) ? "0" + c.get(java.util.Calendar.SECOND) : 
                String.valueOf(c.get(java.util.Calendar.SECOND));
        
        return sHour + ":" + sMinute + ":" + sSecond;
    }
    
}
