/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cashiers.gui;

/**
 *
 * @author elbrujo
 */
public final class TopHBox extends javafx.scene.layout.HBox
{
    private static TopHBox s_Instance = null;
    
    private static final double DEFAULT_SPACING = 10;
   
    private javafx.scene.image.Image m_pImage;
    private javafx.scene.image.ImageView m_pImageView;
   
    private javafx.scene.control.Label m_labelMainName;
    
    public static TopHBox getInstance()
    {
        if (s_Instance == null)
        {
            s_Instance = new TopHBox();
            
        }
        return s_Instance;
    }
    
    public TopHBox()
    {
        this(TopHBox.DEFAULT_SPACING);
    }
    
    public TopHBox(double spacing)
    {
        super(spacing);
        
        setId("top-hbox");
        setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        
        addControls();
    }
    
    public void setTopName(String v)
    {
        m_labelMainName.setText(v);
    }
    
    public String getTopName()
    {
        return m_labelMainName.getText();
    }
    
    public void setImage(javafx.scene.image.Image i)
    {
        m_pImageView.setImage(i);
    }
    
    private void addControls()
    {
        m_pImageView = new javafx.scene.image.ImageView();
        m_pImage = new javafx.scene.image.Image(MainScene.class.getResourceAsStream("images/personal.png"));
        m_pImageView.setImage(m_pImage);
        
        m_labelMainName = new javafx.scene.control.Label("Miguel Angel Covarrubias Hernandez");
        m_labelMainName.setGraphic(m_pImageView);
        m_labelMainName.setId("label-main-name");
        
        getChildren().addAll(m_pImageView, m_labelMainName);        
    } 
    
}
