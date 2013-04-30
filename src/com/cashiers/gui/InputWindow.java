/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cashiers.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 *
 * @author elbrujo
 */
public class InputWindow 
{
    private static String content = null;
    
    public static String show(final String title, final Window parent)
    {
        // Create stage without iconized button.
        final Stage stage = new Stage(StageStyle.UTILITY);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        if (parent != null) 
        {
            // Only set in case of not null.
            stage.initOwner(parent);
        }
        
        final GridPane gridPanel = new GridPane();
        gridPanel.setHgap(10);
        gridPanel.setVgap(5);
        gridPanel.setPadding(new Insets(10, 20, 10, 20));
        
        final Label labelMessage = new Label("Introdusca el ID del trabajador.");
        labelMessage.setId("label-id");
        
        final TextField textField = new TextField();
        
        final Button buttonOk = new Button("Aceptar ");    
        buttonOk.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) 
            {
                InputWindow.content = textField.getText();
                stage.close();
            }
        });
        
        final Button buttonCancel = new Button("Cancelar");
        buttonCancel.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) 
            {
                InputWindow.content = null;
                stage.close();
            }
        });
        
        gridPanel.add(labelMessage, 0, 0);
        gridPanel.add(buttonOk,5, 0);
        gridPanel.add(buttonCancel,5, 1);
        gridPanel.add(textField, 0, 3, 6, 1);
        
        final Scene scene = new Scene(gridPanel);
        scene.getStylesheets().add(MainScene.class.getResource("css/InputWindow.css").toExternalForm()); 

        stage.setScene(scene);
        // Below method is supported JavaFX 2.2 or later.
        stage.showAndWait();
        
        return InputWindow.content;
    }
}
