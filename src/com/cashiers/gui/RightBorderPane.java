
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cashiers.gui;

import com.cashiers.Main;
import com.cashiers.gui.dialog.Dialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author elbrujo
 */
public class RightBorderPane extends BorderPane {
    private static RightBorderPane s_Instance = null;
    
    public RightBorderPane() {
        super();
        
        setPadding(new Insets(0, 10, 0, 10));
        setId("right-border-pane");
        
        addControls();
    }
    
    public static RightBorderPane getInstance() {
        if (s_Instance == null) {
            s_Instance = new RightBorderPane();      
        }
        return s_Instance;
    }
    
    private void addControls() {
        final Button m_buttonInformation = new Button();
        m_buttonInformation.setText("Detalles");
        m_buttonInformation.setFocusTraversable(false);
        m_buttonInformation.setContentDisplay(ContentDisplay.TOP);
        m_buttonInformation.setGraphic(new ImageView(
                new Image(MainScene.class.getResourceAsStream("images/details.png"))));
        
        final Button m_buttonExit = new Button();
        m_buttonExit.setText("Salir");
        m_buttonExit.setFocusTraversable(false);
        m_buttonExit.setContentDisplay(ContentDisplay.TOP);
        m_buttonExit.setGraphic(new ImageView(
                new Image(MainScene.class.getResourceAsStream("images/exit.png"))));
        m_buttonExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                 Dialog.showYesNoConfirmation("","¿Está seguro de querer salir del Sistema?", Main.getStage(), 
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                Main.exitApp();
                            }
                        }, null);         
                event.consume();
            }
        });
        
        setTop(m_buttonInformation);
        setBottom(m_buttonExit);
    }
    
}
