package com.cs6920.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cs6920.model.QuestItems;

/**
 * Data Access Layer for QuestItems
 * @author Matthew Hodge
 * @version 7.3.2020
 */
public class QuestItemsDAL {
	private MySQLAccess sqlAccess;
	private Connection theConnection;
	
	/**
	 * Creates a QuestItemsDAL object to be used by the controllers
	 */
	public QuestItemsDAL(MySQLAccess theDBConnection) {
		this.sqlAccess = theDBConnection;
	}
	
	/**
	 * Get Quest Items in QuestItems table
	 * @return The Array List of QuestItems
	 * @throws SQLException
	 */
	public ArrayList<QuestItems> getQuestItemsByQuestId(int questId) throws SQLException {
		ArrayList<QuestItems> theQuestItemsArrayList = new ArrayList<QuestItems>();
		
		try {
            this.theConnection = this.sqlAccess.getDBConnection();
            Statement statement = this.theConnection.createStatement();
            String query = "SELECT  quest_id, " + 
            		"item_id, " + 
            		"item_quantity, " + 
            		"item_display_name " + 
            	"FROM " + this.sqlAccess.getTheDBName() + ".questitems "
            			+ "WHERE `quest_id` = \"" + questId + "\";";
            ResultSet results = statement.executeQuery(query);
            while (results.next() != false) {
            	QuestItems questItem = new QuestItems();
                questItem.setQuestId(Integer.parseInt(results.getString("quest_id")));
                questItem.setItemId(Integer.parseInt(results.getString("item_id")));
                questItem.setItemQuantity(Integer.parseInt(results.getString("item_quantity")));
                questItem.setItemDisplayName(results.getString("item_display_name"));
                theQuestItemsArrayList.add(questItem);
            }
        } catch (Exception e) {
        	System.err.println(e.getMessage());
        }
        finally {
        	this.theConnection.close();
        }
		return theQuestItemsArrayList; 
	}
	
	/**
	 * Get Quest Items in QuestItems table
	 * @return The Array List of QuestItems
	 * @throws SQLException
	 */
	public QuestItems getQuestItem(int questId, int itemId) throws SQLException {
		QuestItems questItem = null;
		
		try {
            this.theConnection = this.sqlAccess.getDBConnection();
            Statement statement = this.theConnection.createStatement();
            String query = "SELECT  quest_id, " + 
            		"item_id, " + 
            		"item_quantity, " + 
            		"item_display_name " + 
            	"FROM " + this.sqlAccess.getTheDBName() + ".questitems "
            			+ "WHERE `quest_id` = \"" + questId + "\" AND item_id = \"" + itemId + "\"";
            ResultSet results = statement.executeQuery(query);
            if (results.next() != false) {
            	questItem = new QuestItems();
                questItem.setQuestId(Integer.parseInt(results.getString("quest_id")));
                questItem.setItemId(Integer.parseInt(results.getString("item_id")));
                questItem.setItemQuantity(Integer.parseInt(results.getString("item_quantity")));
                questItem.setItemDisplayName(results.getString("item_display_name"));
            }
        } catch (Exception e) {
        	System.err.println(e.getMessage());
        }
        finally {
        	this.theConnection.close();
        }
		return questItem; 
	}
	
	/**
	 * Deletes an existing quest item entry in the database QuestItems table
	 * @param questId
	 * @return True if deleted | False if not deleted
	 * @throws SQLException
	 */
	public Boolean deleteQuestItemsByQuestId(int questId) throws SQLException {
		Boolean success = false;
		try {
			this.theConnection = this.sqlAccess.getDBConnection();
			String query = "DELETE FROM " + this.sqlAccess.getTheDBName() + ".questitems " + 
					"WHERE quest_id = ?";
			PreparedStatement preparedStmt = this.theConnection.prepareStatement(query);
			preparedStmt.setString(1, String.valueOf(questId));
			preparedStmt.execute();
		      success = true;
		} catch (Exception e) {
        	System.err.println(e.getMessage());
        }
        finally {
        	this.theConnection.close();
        }
		return success;
	}
	
	/**
	 * Creates an existing quest item entry in the database QuestItems table
	 * @param questId
	 * @return True if deleted | False if not deleted
	 * @throws SQLException
	 */
	public Boolean createQuestItem(int questId, int itemId, int itemQuantity, String itemDisplayName) throws SQLException {
		Boolean success = false;
		try {
			this.theConnection = this.sqlAccess.getDBConnection();
			String query = "INSERT INTO " + this.sqlAccess.getTheDBName() + ".questitems " + 
					"(quest_id, " + 
            		"item_id, " + 
            		"item_quantity, " + 
            		"item_display_name) " +
					"VALUES (?, ?, ?, ?)";
			 PreparedStatement preparedStmt = this.theConnection.prepareStatement(query);
			  preparedStmt.setString(1, String.valueOf(questId));
			  preparedStmt.setString(2, String.valueOf(itemId));
			  preparedStmt.setString(3, String.valueOf(itemQuantity));
			  preparedStmt.setString(4, itemDisplayName);
		      preparedStmt.execute();
		      success = true;
		} catch (Exception e) {
        	System.err.println(e.getMessage());
        }
        finally {
        	this.theConnection.close();
        }
		return success;
	}
	
	/**
	 * Updates an existing quest item quantity in the database QuestItems table
	 * @param questItem
	 * @param itemQuantity
	 * @return True if updated | False if not updated
	 * @throws SQLException
	 */
	public Boolean updateQuestItemQuantity(QuestItems questItem, int itemQuantity) throws SQLException {
		Boolean success = false;
		try {
			this.theConnection = this.sqlAccess.getDBConnection();
			String query = "UPDATE " + this.sqlAccess.getTheDBName() + ".questitems " + 
					"SET " + 
            		"item_quantity = ? " + 
            		"WHERE quest_id = ? AND item_id = ?";
			 PreparedStatement preparedStmt = this.theConnection.prepareStatement(query);
			  preparedStmt.setString(1, String.valueOf(itemQuantity));
			  preparedStmt.setString(2, String.valueOf(questItem.getQuestId()));
			  preparedStmt.setString(3, String.valueOf(questItem.getItemId()));
		      preparedStmt.execute();
		      success = true;
		} catch (Exception e) {
        	System.err.println(e.getMessage());
        }
        finally {
        	this.theConnection.close();
        }
		return success;
	}
}
