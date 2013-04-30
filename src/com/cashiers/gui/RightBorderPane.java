/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cashiers.gui;

import com.cashiers.Main;
import com.cashiers.gui.dialog.Dialog;

/**
 *
 * @author elbrujo
 */
public class RightBorderPane extends javafx.scene.layout.BorderPane
{
    private static RightBorderPane s_Instance = null;
    
    public RightBorderPane()
    {
        super();
        
        setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        setId("right-tile-pane");
        
        addControls();
    }
    
    public static RightBorderPane getInstance()
    {
        if (s_Instance == null)
        {
            s_Instance = new RightBorderPane();
            
        }
        return s_Instance;
    }
    
    private void addControls()
    {
//        final javafx.scene.control.Button m_buttonFind = new javafx.scene.control.Button();
//        m_buttonFind.setText("Buscar");
//        m_buttonFind.setFocusTraversable(false);
//        m_buttonFind.setContentDisplay(javafx.scene.control.ContentDisplay.TOP);
//        m_buttonFind.setGraphic(new javafx.scene.image.ImageView(
//                new javafx.scene.image.Image(MainScene.class.getResourceAsStream("images/find.png"))));
//        m_buttonFind.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>()
//        {
//            @Override
//            public void handle(javafx.event.ActionEvent event) 
//            {
//                System.out.println(InputWindow.show("Buscar trabajador", Main.getStage()));
//            }   
//        });
        
        final javafx.scene.control.Button m_buttonInformation = new javafx.scene.control.Button();
        m_buttonInformation.setText("Detalles");
        m_buttonInformation.setFocusTraversable(false);
        m_buttonInformation.setContentDisplay(javafx.scene.control.ContentDisplay.TOP);
        m_buttonInformation.setGraphic(new javafx.scene.image.ImageView(
                new javafx.scene.image.Image(MainScene.class.getResourceAsStream("images/details.png"))));
        
        final javafx.scene.control.Button m_buttonExit = new javafx.scene.control.Button();
        m_buttonExit.setText("Salir");
        m_buttonExit.setFocusTraversable(false);
        m_buttonExit.setContentDisplay(javafx.scene.control.ContentDisplay.TOP);
        m_buttonExit.setGraphic(new javafx.scene.image.ImageView(
                new javafx.scene.image.Image(MainScene.class.getResourceAsStream("images/exit.png"))));
        m_buttonExit.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>()
        {
            @Override
            public void handle(javafx.event.ActionEvent event)
            {
                 Dialog.showYesNoConfirmation("","¿Está seguro de querer salir del Sistema?", Main.getStage(), 
                        new javafx.event.EventHandler<javafx.event.ActionEvent>()
                        {
                            @Override
                            public void handle(javafx.event.ActionEvent event) 
                            {
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
