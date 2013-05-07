/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cashiers;

import com.cashiers.gui.BottomHBox;
import com.cashiers.gui.MainScene;
import com.cashiers.gui.dialog.Dialog;
import com.cashiers.mysql.MySQL;
import com.cashiers.util.Util;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author elbrujo
 */
public final class Main extends Application {
    private static Stage s_pStage;
    private ConnectTask<Void> task = new ConnectTask<Void>();
    
    public final EventHandler<ActionEvent> exitEvent =
        new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.exitApp();
            }                        
        };
    
    private class ConnectTask<Void> extends Task<Void> {        
        @Override
        protected Void call() throws Exception {
            try {
                MySQL.getInstance().connect();
                if (MySQL.getInstance().isConnected()) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Dialog.closeCurrentDialog();
                        } 
                    });  
                 }
            } catch (final SQLException e) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Dialog.closeCurrentDialog();
                        Dialog.showError("", "Error: " + e.getMessage() + ".", exitEvent,  Main.getStage());
                    }
                });
            }
            return null;
        } 
    }

    @Override
    public void start(Stage primaryStage) {
        s_pStage = primaryStage;
        
        s_pStage.setScene(MainScene.getInstance().getScene());
        s_pStage.setTitle("Sistema");
        s_pStage.initStyle(StageStyle.UNDECORATED);
        maximizeStage();
        
        s_pStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Dialog.showYesNoConfirmation(
                    "", "¿Está seguro de querer salir del Sistema?", s_pStage,
                    new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            exitApp();
                        }
                    }, null);          
                event.consume();
            }  
        });
        
        s_pStage.show();
        BottomHBox.getInstance().getTextFieldID().requestFocus();
      
        connectToMySQLServer();
    }
    
    public static Stage getStage() {
        return s_pStage;
    }
    
    public static void exitApp() {
        MainScene.getInstance().getBottomHBox().stopScheduler();
        Platform.exit();
    }
    
    private void maximizeStage() {   
        s_pStage.setX(Util.getScreenBounds().getMinX());
        s_pStage.setY(Util.getScreenBounds().getMinY());
        s_pStage.setWidth(Util.getScreenBounds().getWidth());
        s_pStage.setHeight(Util.getScreenBounds().getHeight());
    }
   
    private void connectToMySQLServer() {
        Dialog.showProgressDialog(
            "", "Conectando con la base de datos, por favor espere...", 
            Main.getStage(),// exitEvent,  
            new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    new Thread(task).start();
                } 
            });
    }
    
    public static void main(String... $args) {
        Application.launch($args);
    }
}
