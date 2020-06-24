package com.cs6920.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Connection class for a MySQL database. This is being built from information located at 
 * https://www.vogella.com/tutorials/MySQLJava/article.html
 * 
 * @author Hodge
 * @version 6.10.2020
 */

public class MySQLAccess {
    private Connection theConnection = null;
    private Statement theStatement = null;
    private String theDBName;
    private ResultSet theResultSet = null;
    private String connectionString;
    private String theDBUserName;
    private String theDBPassword;
    
    /**
     * Default constructor builds the initial connection string from hard coded values for a test server
     */
    public MySQLAccess() {
    	BuildConnectionString("localhost", "root", "test1234", "rpg_story_mapper_db");
    }
    
    /**
     * Gets an open connection to a MySQL database
     * @return	the open connection
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection GetDBConnection() throws ClassNotFoundException, SQLException {
    	Connection theNewConnection;
        // This will load the MySQL driver, each DB has its own driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Setup the connection with the DB
        theNewConnection = DriverManager.getConnection(connectionString, theDBUserName, theDBPassword);
        return theNewConnection;
    }
    
    /**
     * Simple test of whether or not the MySQL server can be reached with the supplied credentials
     * @return	bool, whether or not the server accessed
     * @throws Exception
     */
    public Boolean TestDBConnection() throws Exception {
    	Boolean connectionSuccess = false;
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Setup the connection with the DB
            theConnection = GetDBConnection();
        	theResultSet = null;
        	connectionSuccess = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
        return connectionSuccess;
    }
    
    /**
     * Build the connection string for the DB based on user input for credentails
     * @param hostName	location of the host. Ex, localhost
     * @param userName	username to access the DB. Ex, root
     * @param password	password for this DB user. Ex, test1234
     */
    public void BuildConnectionString(String hostName, String userName, String password, String dBName) {
    	this.theDBName = dBName;
    	this.theDBUserName = userName;
    	this.theDBPassword = password;
    	connectionString = "jdbc:mysql://" + hostName + "/?serverTimezone=UTC";
    }
    
    /**
     * Get the name of the DB to look for on the host
     * @return the DB name
     */
    public String GetTheDBName() {
    	return this.theDBName;
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (theResultSet != null) {
                theResultSet.close();
            }

            if (theStatement != null) {
                theStatement.close();
            }

            if (theConnection != null) {
                theConnection.close();
            }
        } catch (Exception e) {

        }
    }

}