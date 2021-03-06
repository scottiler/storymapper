package com.cs6920.view.manage;

import java.sql.SQLException;

import com.cs6920.control.logic_control.ManageNPCCharactersControl;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import com.cs6920.model.NpcCharacter;
import com.cs6920.view.MainDashboardViewControl;
import com.cs6920.view.ViewControl;

/**
 * Provides the View Control Logic for the Manage NPC Characters View
 * @author Matthew Hodge
 * @version 6.13.2020
 */
public class ManageNPCCharactersViewControl extends ViewControl {
	@FXML
	private TableView<NpcCharacter> existingNPCTableView;
	@FXML
	private TableColumn<NpcCharacter, Integer> npcIDTableColumn;
	@FXML
	private TableColumn<NpcCharacter, Integer> npcTypeTableColumn;
	@FXML
	private TableColumn<NpcCharacter, String> npcNameTableColumn;
	@FXML
	private TableColumn<NpcCharacter, String> npcDescriptionTableColumn;
	
	private MainDashboardViewControl theMainDashboardViewControl;
	private ManageNPCCharactersControl theManageNPCCharactersControl;
    
	/**
	 * Constructor for this View Control
	 * @param theMainDashboardViewControl	Reference to the MainDashboard's View Control
	 */
    public ManageNPCCharactersViewControl(MainDashboardViewControl theMainDashboardViewControl) {
    	this.theMainDashboardViewControl = theMainDashboardViewControl;
    	this.theMainDashboardViewControl.setTheManageNPCCharactersViewControl(this);
    	this.theManageNPCCharactersControl = new ManageNPCCharactersControl(theMainDashboardViewControl.getDBConnection());
    }
    
	/**
	 * Binds the ObservableList to the TableView
	 * @throws SQLException 
	 */
    public void updateExistingNPCCharactersList() throws SQLException {
    	this.theManageNPCCharactersControl.updatePlayerArrayList();
    	this.existingNPCTableView.getItems().clear();
    	this.existingNPCTableView.getItems().addAll(this.theManageNPCCharactersControl.getObservablePlayerList());
    }

	@FXML
    private void initialize() throws SQLException {
		this.npcIDTableColumn.setCellValueFactory(new PropertyValueFactory<NpcCharacter, Integer>("npcId"));
		this.npcTypeTableColumn.setCellValueFactory(new PropertyValueFactory<NpcCharacter, Integer>("npcType"));
		this.npcNameTableColumn.setCellValueFactory(new PropertyValueFactory<NpcCharacter, String>("npcName"));
		this.npcDescriptionTableColumn.setCellValueFactory(new PropertyValueFactory<NpcCharacter, String>("npcDescription"));
    }
	
	@FXML
	private void handleNPCCharacterBackButton() throws SQLException {
		this.theMainDashboardViewControl.loadMainDashboardView();
	}
	
	@FXML
	private void handleEditSelectedNPCButton() throws SQLException {
		if (this.existingNPCTableView.getSelectionModel().getSelectedItem() != null) {
			this.theMainDashboardViewControl.setNPCToEdit(this.existingNPCTableView.getSelectionModel().getSelectedItem().getNpcId());
			this.theMainDashboardViewControl.setMainDashboardStage("editNPCCharacters");
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Edit User Issue");
			alert.setHeaderText("Cannot Edit User");
			alert.setContentText("A User to edit was not selected. Please select the user you wish to edit");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void handleCreateNPCButton() throws SQLException {
		this.theMainDashboardViewControl.setMainDashboardStage("createNPCCharacters");
	}
}
