/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cashiers.mysql;

import com.cashiers.util.Util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author elbrujo
 */
public final class MySQL {    
    private static MySQL s_Instance = null;
        
    private String URL = "jdbc:mysql://localhost:3306/empleados";
    private String USER = "root";
    private String PASSWORD = "poi8uyt5";
        
    private Connection m_Connection;
    private Statement m_Statement;
    private ResultSet m_ResultSet;
    
    private boolean m_isConnected;
    
    public MySQL() {
        m_isConnected = false;
    }
    
    public static MySQL getInstance() {
        if (s_Instance == null) {
            s_Instance = new MySQL();
        }
        return s_Instance;
    }
    
    /**
     *
     * @throws SQLException
     */
    public void connect() throws SQLException {
        try {
            m_Connection = DriverManager.getConnection(URL, USER, PASSWORD);
            m_Statement = m_Connection.createStatement();
            System.out.printf("Connected!\n");
        } catch (SQLException e) {
            System.out.printf("%s\n", e.getMessage());
            throw e;
        }
        m_isConnected = true;
    }
    
    public void disconnect() throws SQLException {
        try {
            m_isConnected = false;
            if (m_ResultSet != null) {
                m_ResultSet.close();
            }
            if (m_Statement != null) {
                m_Statement.close();
            }
            if (m_Connection != null) {
                m_Connection.close();
            }
        } catch (SQLException e) {
            throw e;              
        }
    }
    
    public boolean isConnected() {
        return m_isConnected;
    }
    
    public Employee executeQuery(String ID) throws SQLException {
        Employee employee = null;
        try {
            m_ResultSet = m_Statement.executeQuery(
                    "SELECT ID, PATERNO, MATERNO, NOMBRE, AREA FROM personal WHERE ID = '" + ID + "'"); 
        } catch (SQLException e) {
            System.out.printf("%s\n", e.getMessage());
            throw e;
        }
        
        while (m_ResultSet.next()) {
            employee = new Employee()
                .setId(m_ResultSet.getString(1)) 
                .setMiddleName(m_ResultSet.getString(2))
                .setSurName(m_ResultSet.getString(3))
                .setName(m_ResultSet.getString(4))
                .setJob(m_ResultSet.getString(5))
                .setCurrentDate(Util.getCurrentDate());
//                .setStartJob(m_ResultSet.getString(7))
//                .setEndJob(m_ResultSet.getString(8));
        }   
        
        return employee;
    }
    
}
