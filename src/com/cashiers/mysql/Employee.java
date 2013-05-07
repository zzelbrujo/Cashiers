/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cashiers.mysql;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author elbrujo
 */
public final class Employee {
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty middleName;
    private final SimpleStringProperty surName;
    private final SimpleStringProperty job;
    private final SimpleStringProperty currentDate;
    private final SimpleStringProperty startJob;
    private final SimpleStringProperty endJob;
    private final SimpleStringProperty id;
    
    public Employee() {
        firstName = new SimpleStringProperty();
        middleName = new SimpleStringProperty();
        surName = new SimpleStringProperty();
        job = new SimpleStringProperty();
        currentDate = new SimpleStringProperty();
        startJob = new SimpleStringProperty();
        endJob = new SimpleStringProperty();
        id = new SimpleStringProperty();
    }
    
    public String getFirstName() {
        return firstName.get();
    }
        
    public Employee setName(String v) {
        firstName.set(v);
        return this;
    }
        
    public String getMiddleName() {
        return middleName.get();
    }
       
    public Employee setMiddleName(String v) {
        middleName.set(v);
        return this;
    }
        
    public String getSurName() {
        return surName.get();      
    }       
        
    public Employee setSurName(String v) {
        surName.set(v);
        return this;
    }
        
    public String getJob() {
        return job.get();
    }
        
    public Employee setJob(String v) {
        job.set(v);
        return this;
    }
        
    public String getCurrentDate() {
        return currentDate.get();
    }
        
    public Employee setCurrentDate(String v) {
        currentDate.set(v);
        return this;
    }
        
    public String getStartJob() {
        return startJob.get();
    }
        
    public Employee setStartJob(String v) {
        startJob.set(v);
        return this;
    }
        
    public String getEndJob() {
        return endJob.get();
    }
        
    public Employee setEndJob(String v) {
        endJob.set(v);
        return this;
    }
        
    public String getId() {
        return id.get();
    }
        
    public Employee setId(String v) {
        id.set(v);
        return this;
    }
}
