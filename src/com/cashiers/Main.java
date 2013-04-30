/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cashiers;

import com.cashiers.gui.dialog.Dialog;
import com.cashiers.gui.MainScene;
import com.cashiers.gui.BottomHBox;

/**
 *
 * @author elbrujo
 */
public final class Main extends javafx.application.Application 
{   
    private static javafx.stage.Stage s_pStage;
    
    @Override
    public void start(javafx.stage.Stage primaryStage) 
    {
        s_pStage = primaryStage;
        
        s_pStage.setScene(MainScene.getInstance().getScene());
        s_pStage.setResizable(true);
        s_pStage.setTitle("Sistema");

        //s_pStage.setFullScreen(true);
        
        s_pStage.setOnCloseRequest(new javafx.event.EventHandler<javafx.stage.WindowEvent>()
        {
            @Override
            public void handle(javafx.stage.WindowEvent event) 
            {
                Dialog.showYesNoConfirmation("","¿Está seguro de querer salir del Sistema?", s_pStage, 
                        new javafx.event.EventHandler<javafx.event.ActionEvent>()
                        {
                            @Override
                            public void handle(javafx.event.ActionEvent event) 
                            {
                                exitApp();
                            }
                        }, null);
          
                event.consume();
            }  
        });
        
        s_pStage.show();
        BottomHBox.getInstance().getTextFieldID().requestFocus();
    }
    
    public static javafx.stage.Stage getStage()
    {
        return s_pStage;
    }
    
    public static void main(String... $args)
    {
        launch($args);
    }
    
    public static void exitApp()
    {
        MainScene.getInstance().getBottomHBox().stopScheduler();
        javafx.application.Platform.exit();
    }
}
