/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cashiers.gui;

import com.cashiers.Main;
import com.cashiers.gui.dialog.Dialog;
import com.cashiers.mysql.Employee;
import com.cashiers.mysql.MySQL;
import com.cashiers.util.Util;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

/**
 *
 * @author elbrujo
 */
public final class BottomHBox extends HBox {
    private static BottomHBox s_Instance = null;
    
    private static final double DEFAULT_SPACING = 10;
    
    private TextField m_textFieldID;
    private Label m_labelDateAndTime;
    
    private ScheduledExecutorService m_scheduler;
    
    private class Handler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Employee employee = null;                
            if (m_textFieldID.getText().trim().length() > 0) {
                try {
                    employee = MySQL.getInstance().
                            executeQuery(m_textFieldID.getText().trim());
                } catch (SQLException e) {
                    Dialog.showError("", "Error: " + e.getMessage() + ".", null, Main.getStage());
                }
                  
                if (employee != null) {
                    MainScene.getInstance().getObservableList().add(employee);
                    final String topName =
                            employee.getMiddleName() + " " + employee.getSurName() + " " + employee.getFirstName();
                    TopHBox.getInstance().setTopName(topName);
                    m_textFieldID.selectAll();
                } else {
                    Dialog.showError("", "Error: El trabajador no ha sido encontrado.", null, Main.getStage());
                }             
            }
        }  
    }
    
    public static BottomHBox getInstance() {
        if (s_Instance == null) {
            s_Instance = new BottomHBox(); 
        }
        return s_Instance;
    }
    
    public BottomHBox() {
        this(BottomHBox.DEFAULT_SPACING);
    }
    
    public BottomHBox(double spacing) {
        super(spacing);
        
        setId("bottom-hbox");
        setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        
        addControls();
        m_scheduler = Executors.newScheduledThreadPool(1);
        m_scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        m_labelDateAndTime.setText("     " + 
                                        Util.getCurrentTime() + "\n" + Util.getCurrentDate());
                    }
                });
            }
         }, 0, 1, TimeUnit.SECONDS); 
    }
    
    public void stopScheduler() {
        m_scheduler.shutdown();
    }
    
    public TextField getTextFieldID() {
        return m_textFieldID;
    }
     
    private void addControls() {
        m_textFieldID = new TextField();
        m_textFieldID.setOnAction(new Handler()); 
        m_textFieldID.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, 
                                    Boolean oldValue, Boolean newValue) {
                if (m_textFieldID.isFocused()) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            m_textFieldID.selectAll();
                        } 
                    });
                } else {
                     m_textFieldID.setPromptText("Click para capturar el ID");
                }
            }   
        });
        
        m_labelDateAndTime = new Label();
        m_labelDateAndTime.setId("label-date-and-time");
        
        final Label label = new Label("ID:");    
        label.setId("label-id");
        
        final StackPane sPane = new StackPane();    
        sPane.getChildren().add(m_labelDateAndTime);
        sPane.setAlignment(Pos.CENTER_RIGHT);
        
        getChildren().addAll(label, m_textFieldID);
        getChildren().add(sPane);
        
        HBox.setHgrow(sPane, Priority.ALWAYS);
    }
    
}
