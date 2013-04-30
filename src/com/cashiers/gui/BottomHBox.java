/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cashiers.gui;

import com.cashiers.util.Util;

/**
 *
 * @author elbrujo
 */
public final class BottomHBox extends javafx.scene.layout.HBox
{
    private static BottomHBox s_Instance = null;
    
    private static final double DEFAULT_SPACING = 10;
    
    private javafx.scene.control.TextField m_textFieldID;
    private javafx.scene.control.Label m_labelDateAndTime;
    
    private java.util.concurrent.ScheduledExecutorService m_scheduler;
    
    public static BottomHBox getInstance()
    {
        if (s_Instance == null)
        {
            s_Instance = new BottomHBox();
            
        }
        return s_Instance;
    }
    
    public BottomHBox()
    {
        this(BottomHBox.DEFAULT_SPACING);
    }
    
    public BottomHBox(double spacing)
    {
        super(spacing);
        
        setId("bottom-hbox");
        setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        
        addControls();
        m_scheduler = java.util.concurrent.Executors.newScheduledThreadPool(1);
        m_scheduler.scheduleAtFixedRate(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        javafx.application.Platform.runLater(new Runnable()
                        {                            
                            @Override
                            public void run()
                            {
                                m_labelDateAndTime.setText("     " + 
                                        Util.getCurrentTime() + "\n" + Util.getCurrentDate());
                            }
                        });
                    }
                }, 0, 1, java.util.concurrent.TimeUnit.SECONDS); 
    }
    
    public void stopScheduler()
    {
        m_scheduler.shutdown();
    }
    
    public javafx.scene.control.TextField getTextFieldID()
    {
        return m_textFieldID;
    }
     
    private void addControls()
    {
        m_textFieldID = new javafx.scene.control.TextField();
        m_textFieldID.focusedProperty().addListener(new javafx.beans.value.ChangeListener<Boolean>() 
        {
            @Override
            public void changed(javafx.beans.value.ObservableValue<? extends Boolean> observable, 
                                    Boolean oldValue, Boolean newValue) 
            {
                if (!newValue)
                {
                    //doesnt has focus
                    m_textFieldID.setPromptText("Click para capturar el ID");
                }
            }   
        });
        
        m_labelDateAndTime = new javafx.scene.control.Label();
        m_labelDateAndTime.setId("label-date-and-time");
        
        final javafx.scene.control.Label label = new javafx.scene.control.Label("ID:");    
        label.setId("label-id");
        
        final javafx.scene.layout.StackPane sPane = new javafx.scene.layout.StackPane();    
        sPane.getChildren().add(m_labelDateAndTime);
        sPane.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
        
        getChildren().addAll(label, m_textFieldID);
        getChildren().add(sPane);
        
        javafx.scene.layout.HBox.setHgrow(sPane, javafx.scene.layout.Priority.ALWAYS);
    }
    
}
