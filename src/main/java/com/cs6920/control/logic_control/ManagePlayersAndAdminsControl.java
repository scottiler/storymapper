package com.cs6920.control.logic_control;

import java.sql.SQLException;
import java.util.ArrayList;

import com.cs6920.DAL.MySQLAccess;
import com.cs6920.DAL.PlayerDAL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.cs6920.model.Player;

/**
 * Logic Control for Managing Players and Admins, Communicates between the View and DAL for this feature
 * @author Matthew Hodge
 * @version 6.13.2020
 */
public class ManagePlayersAndAdminsControl {
	private PlayerDAL thePlayerDAL;
	private ArrayList<Player> existingPlayerAdminArrayList;
	private ObservableList<Player> observablePlayerList = FXCollections.observableArrayList();

	/**
	 * Constructor that sets up the DAL to the current DBConnection class instance
	 * @param theDBConnection
	 */
	public ManagePlayersAndAdminsControl(MySQLAccess theDBConnection) {
		this.thePlayerDAL = new PlayerDAL(theDBConnection);
	}

	/**
	 * Update the observable list of objects for any changes
	 * @throws SQLException
	 */
	public void updatePlayerArrayList() throws SQLException {
		this.existingPlayerAdminArrayList = new ArrayList<Player>();
		this.existingPlayerAdminArrayList = this.thePlayerDAL.getPlayers();
		this.observablePlayerList.clear();
		this.observablePlayerList.addAll(this.existingPlayerAdminArrayList);
	}
	
	/**
	 * Get the Observable Player List
	 * @return	the observable list
	 */
	public ObservableList<Player> getObservablePlayerList() {
		return this.observablePlayerList;
	}
}
