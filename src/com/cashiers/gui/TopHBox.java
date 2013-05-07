/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cashiers.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 *
 * @author elbrujo
 */
public final class TopHBox extends HBox {
    private static TopHBox s_Instance = null;
    
    private static final double DEFAULT_SPACING = 10;
   
    private Image m_pImage;
    private ImageView m_pImageView;
   
    private Label m_labelMainName;
    
    public static TopHBox getInstance() {
        if (s_Instance == null) {
            s_Instance = new TopHBox();            
        }
        return s_Instance;
    }
    
    public TopHBox() {
        this(TopHBox.DEFAULT_SPACING);
    }
    
    public TopHBox(double spacing) {
        super(spacing);
        setId("top-hbox");
        setPadding(new Insets(10, 10, 10, 10));
        addControls();
    }
    
    public void setTopName(String v) {
        m_labelMainName.setText(v);
    }
    
    public String getTopName() {
        return m_labelMainName.getText();
    }
    
    public void setImage(Image i) {
        m_pImageView.setImage(i);
    }
    
    private void addControls() {
        m_pImageView = new ImageView();
        m_pImage = new Image(MainScene.class.getResourceAsStream("images/personal.png"));
        m_pImageView.setImage(m_pImage);
        
        m_labelMainName = new Label();
        m_labelMainName.setGraphic(m_pImageView);
        m_labelMainName.setId("label-main-name");
        
        getChildren().addAll(m_pImageView, m_labelMainName);        
    } 
    
}
