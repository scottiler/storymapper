package com.cs6920.DAL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.cs6920.model.Player;

public class LoginDAL {
	private MySQLAccess theDBConnection;
    private Connection theConnection = null;
    private Statement theStatement = null;
    private ResultSet theResultSet = null;
	
	public LoginDAL(MySQLAccess theDBConnection) {
		this.theDBConnection = theDBConnection;
	}
	
    public Player getPlayer(String playerName, String playerPassword) throws Exception {
    	Player thePlayer = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Setup the connection with the DB
            this.theConnection = this.theDBConnection.getDBConnection();
            this.theStatement = this.theConnection.createStatement();
            String pST = "SELECT * FROM `" + this.theDBConnection.getTheDBName() + "`.`players` "
            		+ "WHERE `" + this.theDBConnection.getTheDBName() + "`.`players`.`player_name` = \"" + playerName + "\""
            				+ "AND `" + this.theDBConnection.getTheDBName() + "`.`players`.`player_password` = \"" + playerPassword + "\";";
            this.theResultSet = this.theStatement.executeQuery(pST);
            if (this.theResultSet.next() != false) {
                thePlayer = new Player();
                thePlayer.setPlayerName(this.theResultSet.getString("player_name"));
                thePlayer.setPlayerId(Integer.parseInt(this.theResultSet.getString("player_id")));
            }
        } catch (Exception e) {
            thePlayer = null;
        }
        finally {
        	this.theConnection.close();
        }
        return thePlayer;
    }
	
    public Boolean isPlayerAdmin(Player thePlayer) throws Exception {
    	Boolean isAdmin = false;
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Setup the connection with the DB
            this.theConnection = this.theDBConnection.getDBConnection();
            // Statements allow to issue SQL queries to the database
            this.theStatement = this.theConnection.createStatement();
            String pST = "SELECT * FROM `" + this.theDBConnection.getTheDBName() + "`.`admins` "
            		+ "WHERE `" + this.theDBConnection.getTheDBName() + "`.admins.`player_id` = \"" + thePlayer.getPlayerId() + "\" AND is_active = 1";
            // Result set get the result of the SQL query
            ResultSet theResultSet = this.theStatement.executeQuery(pST);
            if (theResultSet.next() != false) {
                isAdmin = true;
            }
        } catch (Exception e) {
        }
        finally {
        	this.theConnection.close();
        }
        return isAdmin;
    }
}
