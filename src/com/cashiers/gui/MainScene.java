/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cashiers.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

/**
 *
 * @author elbrujo
 */
public final class MainScene
{
    private static MainScene s_Instance;
    
    private Scene m_Scene;
    
    private TopHBox m_topHBox;
    private BottomHBox m_bottomHBox;
    private RightBorderPane m_rightPane;
    
    private BorderPane m_borderPane;
    private StackPane m_stackPane;
    
    private TableView<Employee> m_tableView;
    private ObservableList<Employee> m_observableList;
       
    static
    {
        s_Instance = null;
    }
    
    public MainScene()
    {
        m_topHBox = TopHBox.getInstance();
        m_bottomHBox = BottomHBox.getInstance();
        m_rightPane = RightBorderPane.getInstance();
        
        m_borderPane = new BorderPane();
        
        createTableView();
        
        m_borderPane.setTop(m_topHBox);
        m_borderPane.setRight(m_rightPane);
        m_borderPane.setBottom(m_bottomHBox);
        
        m_Scene = new Scene(m_borderPane);//, 960, 600);
        //m_Scene = new Scene(m_borderPane, 800, 600);
        m_Scene.getStylesheets().add(MainScene.class.getResource("css/MainScene.css").toExternalForm());         
    }
    
    public static MainScene getInstance()
    {
        if (s_Instance == null)
        {
            s_Instance = new MainScene();
        }
        
        return s_Instance;
    }
    
    public javafx.scene.Scene getScene()
    {
        return m_Scene;
    }   
    
    public BottomHBox getBottomHBox()
    {
        return m_bottomHBox;
    }
    
    private void createTableView()
    {
        m_stackPane = new StackPane();
        m_tableView = new TableView<Employee>();
        m_observableList = FXCollections.observableArrayList();
        
        TableColumn tableColumnName = new TableColumn("Nombre(s)");
        tableColumnName.setMinWidth(100D);
        tableColumnName.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
        
        TableColumn tableColumnMiddleName = new TableColumn("Apellido Paterno");
        tableColumnMiddleName.setMinWidth(150D);
        tableColumnMiddleName.setCellValueFactory(new PropertyValueFactory<Employee, String>("middleName"));
        
        TableColumn tableColumnSurName = new TableColumn("Apellido Materno");
        tableColumnSurName.setMinWidth(150D);
        tableColumnSurName.setCellValueFactory(new PropertyValueFactory<Employee, String>("surName"));
        
        TableColumn tableColumnJob = new TableColumn("Area");
        tableColumnJob.setMinWidth(80D);
        tableColumnJob.setCellValueFactory(new PropertyValueFactory<Employee, String>("job"));
        tableColumnJob.setCellFactory(new CustomCellFactory());
        
        TableColumn tableColumnDate = new TableColumn("Fecha");
        tableColumnDate.setMinWidth(80D);
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<Employee, String>("currentDate"));
        tableColumnDate.setCellFactory(new CustomCellFactory());
        
        TableColumn tableColumnSchedule = new TableColumn("Horario");
        
        TableColumn tableColumnStartJob = new TableColumn("Entrada");
        tableColumnStartJob.setMinWidth(50D);
        tableColumnStartJob.setCellValueFactory(new PropertyValueFactory<Employee, String>("startJob"));
        tableColumnStartJob.setCellFactory(new CustomCellFactory());
        
        TableColumn tableColumnEndJob = new TableColumn("Salida");
        tableColumnEndJob.setMinWidth(50D);
        tableColumnEndJob.setCellValueFactory(new PropertyValueFactory<Employee, String>("endJob"));
        tableColumnEndJob.setCellFactory(new CustomCellFactory());
        
        tableColumnSchedule.getColumns().addAll(tableColumnStartJob, tableColumnEndJob);
        
        m_tableView.setItems(m_observableList);
        m_tableView.getColumns().addAll(tableColumnMiddleName, tableColumnSurName, tableColumnName, 
                tableColumnJob, tableColumnDate, tableColumnSchedule);
       
//        m_tableView.getSelectionModel().getSelectedItem().getJob();
        
//        Employee e = new Employee();
//        e.setName("Miguel Angel");
//        e.setMiddleName("Covarrubias");
//        e.setSurName("Hernandez");
//        e.setJob("Sistemas");
//        e.setCurrentDate(Util.getCurrentDate());
//        e.setStartJob("19:11");
//        e.setEndJob("11:00");
//        
//        m_observableList.add(e);m_observableList.add(e);m_observableList.add(e);m_observableList.add(e);
//        m_observableList.add(e);m_observableList.add(e);m_observableList.add(e);m_observableList.add(e);
//        m_observableList.add(e);m_observableList.add(e);m_observableList.add(e);m_observableList.add(e);
//        m_observableList.add(e);m_observableList.add(e);m_observableList.add(e);m_observableList.add(e);
//        m_observableList.add(e);m_observableList.add(e);m_observableList.add(e);m_observableList.add(e);
        
        m_stackPane.getChildren().add(m_tableView);
        m_borderPane.setCenter(m_stackPane);
        
    }
    
    public class Employee
    {
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty middleName;
        private final SimpleStringProperty surName;
        private final SimpleStringProperty job;
        private final SimpleStringProperty currentDate;
        private final SimpleStringProperty startJob;
        private final SimpleStringProperty endJob;
        private final SimpleStringProperty id;
       
        public Employee()
        {
            firstName = new SimpleStringProperty();
            middleName = new SimpleStringProperty();
            surName = new SimpleStringProperty();
            job = new SimpleStringProperty();
            currentDate = new SimpleStringProperty();
            startJob = new SimpleStringProperty();
            endJob = new SimpleStringProperty();
            id = new SimpleStringProperty();
        }
    
        public String getFirstName()
        {
            return firstName.get();
        }
        
        public void setName(String v)
        {
            firstName.set(v);
        }
        
        public String getMiddleName()
        {
            return middleName.get();
        }
       
        public void setMiddleName(String v)
        {
            middleName.set(v);
        }
        
        public String getSurName()
        {
            return surName.get();      
        }       
        
        public void setSurName(String v)
        {
            surName.set(v);
        }
        
        public String getJob()
        {
            return job.get();
        }
        
        public void setJob(String v)
        {
            job.set(v);
        }
        
        public String getCurrentDate()
        {
            return currentDate.get();
        }
        
        public void setCurrentDate(String v)
        {
            currentDate.set(v);
        }
        
        public String getStartJob()
        {
            return startJob.get();
        }
        
        public void setStartJob(String v)
        {
            startJob.set(v);
        }
        
        public String getEndJob()
        {
            return endJob.get();
        }
        
        public void setEndJob(String v)
        {
            endJob.set(v);
        }
        
        public String getId()
        {
            return id.get();
        }
        
        public void setId(String v)
        {
            id.set(v);
        }
    }
    
    public class CustomCellFactory implements 
            Callback<TableColumn<Employee, String>, TableCell<Employee, String>>
    {
        @Override
        public TableCell<Employee, String> call(TableColumn<Employee, String> param) 
        {
            TableCell<Employee, String> cell = new TableCell<Employee, String>() 
            {
                @Override
                public void updateItem(String item, boolean empty)
                {
                    super.updateItem(item, empty);
                    setText(empty ? null : getString());
                }
                
                private String getString() 
                {
                    return getItem() == null ? "" : getItem().toString();
                }
            };
            cell.setStyle("-fx-alignment: CENTER;");
            return cell;
        }
    
    }
    
}