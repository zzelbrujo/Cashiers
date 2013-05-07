package com.cashiers.gui.dialog;

import com.cashiers.util.Util;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 *
 * @author Anton Smirnov (dev@antonsmirnov.name)
 */
public class Dialog extends Stage {
    
    protected String stacktrace;
    protected double originalWidth, originalHeight;
   
    protected Scene scene;
    protected BorderPane borderPanel;

    protected HBox messageBox;
    protected Label messageLabel;
    protected ProgressIndicator progressIndicator;
    
    protected boolean stacktraceVisible;
    protected HBox stacktraceButtonsPanel;
    protected ToggleButton viewStacktraceButton;    
    protected Button copyStacktraceButton;
    protected ScrollPane scrollPane;
    protected Label stackTraceLabel;
    
    protected HBox buttonsPanel;
    protected Button okButton;
         
    /**
     * Dialog builder
     */
    public static class Builder {
        protected static final int STACKTRACE_LABEL_MAXHEIGHT = 240;
        protected static final int MESSAGE_MIN_WIDTH = 180;
        protected static final int MESSAGE_MAX_WIDTH = 800;
        protected static final int BUTTON_WIDTH = 100;
        protected static final double MARGIN = 20;

        protected static Dialog stage;
        
        public Builder create() {
            stage = new Dialog();
            stage.setWidth(Util.getScreenBounds().getWidth());
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);                        
            stage.setIconified(false);
//            stage.centerOnScreen();
            
            stage.borderPanel = new BorderPane();
            stage.borderPanel.getStyleClass().add("pane");

            // message
            stage.messageBox = new HBox();
            stage.messageBox.setSpacing(MARGIN);
            stage.messageBox.setAlignment(Pos.CENTER);
            
            stage.messageLabel = new Label();
            stage.messageLabel.setWrapText(true);
//            stage.messageLabel.setMinWidth(MESSAGE_MIN_WIDTH);
//            stage.messageLabel.setMaxWidth(MESSAGE_MAX_WIDTH);
            
            stage.messageBox.getChildren().add(stage.messageLabel);
            stage.borderPanel.setTop(stage.messageBox);
            BorderPane.setAlignment(stage.messageBox, Pos.CENTER);
            BorderPane.setMargin(stage.messageBox, new Insets(MARGIN, MARGIN, MARGIN, 2 * MARGIN));
            
            // buttons
            stage.buttonsPanel = new HBox();
            stage.buttonsPanel.setSpacing(MARGIN);
            stage.buttonsPanel.setAlignment(Pos.BOTTOM_CENTER);
            BorderPane.setMargin(stage.buttonsPanel, new Insets(0, 0, 1.5 * MARGIN, 0));
            stage.borderPanel.setBottom(stage.buttonsPanel);
            stage.borderPanel.widthProperty().addListener(new ChangeListener<Number> () {
                @Override
                public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                    stage.buttonsPanel.layout();
                }     
            });
            stage.borderPanel.heightProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                    stage.setX(0);
                    stage.setY((Util.getScreenBounds().getHeight() / 2) - (t1.doubleValue() / 2));
                }
            });
            
            stage.scene = new Scene(stage.borderPanel);
            
            stage.setScene(stage.scene);
            stage.getScene().getStylesheets().add(Dialog.class.getResource("css/DialogWindow.css").toExternalForm());
  
            return this;
        }
        
        public Builder setOwner(Window owner) {
            if (owner != null) {
                stage.initOwner(owner);
                stage.borderPanel.setMaxWidth(owner.getWidth());
                stage.borderPanel.setMaxHeight(owner.getHeight());
            }
            return this;
        }
        
        public Builder setTitle(String title) {
            stage.setTitle(title);
            return this;
        }
        
        public Builder setMessage(String message) {
            stage.messageLabel.setText(message);
            return this;
        }
                
        protected Builder addProgressBar() {
            stage.progressIndicator = new ProgressIndicator();
            stage.messageBox.getChildren().add(stage.progressIndicator);
            return this;
        }
        
        protected Builder addConfirmationButton(String buttonCaption, final EventHandler actionHandler) {
            Button confirmationButton = new Button(buttonCaption);
            confirmationButton.setMinWidth(BUTTON_WIDTH);
            confirmationButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    stage.close();
                    if (actionHandler != null) {
                        actionHandler.handle(t);
                    }
                }
            });
            
            stage.buttonsPanel.getChildren().add(confirmationButton);
            return this;
        }
        
        /**
         * Add Yes button to confirmation dialog
         * 
         * @param actionHandler action handler
         * @return 
         */
        public Builder addYesButton(EventHandler actionHandler) {
            return addConfirmationButton("Aceptar", actionHandler);
        }
        
        /**
         * Add No button to confirmation dialog
         * 
         * @param actionHandler action handler
         * @return 
         */
        public Builder addNoButton(EventHandler actionHandler) {
            return addConfirmationButton("Cancelar", actionHandler);
        }
        
        /**
         * Build dialog
         * 
         * @return dialog instance
         */
        public Dialog build() {
//            if (stage.buttonsPanel.getChildren().size() == 0) {
//                throw new RuntimeException("Add one dialog button at least");
//            }
//            
//            stage.buttonsPanel.getChildren().get(0).requestFocus();
            return stage;
        }
        
        public Builder addOnShownListener(EventHandler<WindowEvent> e) {
            if (e != null) {
                stage.setOnShown(e);
            }
            return this;            
        }
        
    } // Builder class
    
    public static void showYesNoConfirmation(String title, String message, Window owner, 
            EventHandler<ActionEvent> yesHandler, EventHandler<ActionEvent> noHandler) {
        new Builder()
            .create()
            .setOwner(owner)
            .setTitle(title)
            .setMessage(message)            
            .addYesButton(yesHandler)
            .addNoButton(noHandler)
                .build()                
                    .show();
    }
    
    public static void showProgressDialog(String title, String message, Window owner,
            /*EventHandler<ActionEvent> cancelHandler, */EventHandler<WindowEvent> onShownHandler) {
        new Builder()
            .create()
            .setOwner(owner)
            .setTitle(title)
            .setMessage(message)  
            .addProgressBar()
            //addNoButton(cancelHandler)
            .addOnShownListener(onShownHandler)
                .build()
                    .show();
    }
    
    /**
     * Show information dialog box as parentWindow child
     * 
     * @param title dialog title
     * @param message dialog message
     * @param owner parent window
     */
    public static void showInfo(String title, String message, Window owner) {
        new Builder()
            .create()
            .setOwner(owner)
            .setTitle(title)
            .setMessage(message)            
            .addYesButton(null)
                .build()
                    .show();
    }
    
    /**
     * Show information dialog box as parentStage child
     * 
     * @param title dialog title
     * @param message dialog message
     */
    public static void showInfo(String title, String message) {
        showInfo(title, message, null);
    }

    /**
     * Show warning dialog box as parentStage child
     * 
     * @param title dialog title
     * @param message dialog message
     * @param owner parent window
     */
    public static void showWarning(String title, String message, Window owner) {
        new Builder()
            .create()
            .setOwner(owner)
            .setTitle(title)
            .setMessage(message)
            .addYesButton(null)
                .build()
                    .show();
    }
    
    /**
     * Show warning dialog box
     * 
     * @param title dialog title
     * @param message dialog message
     */
    public static void showWarning(String title, String message) {
        showWarning(title, message, null);
    }

    /**
     * Show error dialog box
     * 
     * @param title dialog title
     * @param message dialog message
     * @param owner parent window
     */
    public static void showError(String title, String message, EventHandler<ActionEvent> e, Window owner) {
        new Builder()
            .create()
            .setOwner(owner)
            .setTitle(title)
            .setMessage(message)
            .addYesButton(e)
                .build()
                    .show();
    }
    
    /**
     * Show error dialog box
     * 
     * @param title dialog title
     * @param message dialog message
     */
    public static void showError(String title, String message) {
        showError(title, message, null, null);
    }
    
    public static void closeCurrentDialog() {
        Builder.stage.close();
    }
}
