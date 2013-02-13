package com.wyndhamjade.interview.test.model;

import com.wyndhamjade.interview.test.model.Assignee;
import com.wyndhamjade.interview.test.model.Task;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.naming.InitialContext;

public class ConnectionManager {

    private DataSource dataSource;
    private static ConnectionManager connManager;
    private ConnectionManager() {
        try{
        InitialContext ctx = new InitialContext();
        dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/dataSource");
        }catch(Exception e){}
      }
    public static DataSource getDatasource(){
        if(connManager == null)
           connManager = new ConnectionManager();
        return connManager.dataSource;
    }
       
}
