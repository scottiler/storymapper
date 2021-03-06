package com.cs6920.view.edit;

import java.sql.SQLException;

import com.cs6920.control.logic_control.EditItemsControl;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import com.cs6920.model.Item;
import com.cs6920.view.MainDashboardViewControl;
import com.cs6920.view.ViewControl;
/**
 * Communicates between the Create EditItems fxml view and the logic control
 * @author Matthew Hodge
 * @version 6.23.2020
 */
public class EditItemsViewControl extends ViewControl {
	@FXML
	private TextField editItemTypeTextBox;
	@FXML
	private TextField editItemNameTextBox;
	@FXML
	private TextArea editItemDescriptionTextArea;
	@FXML
	private CheckBox editIsItemForQuestCheckBox;
	@FXML
	private CheckBox editIsItemImplicitCheckBox;
	@FXML
	private CheckBox editIsItemTrophyCheckBox;

	private MainDashboardViewControl theMainDashboardViewControl;
	private EditItemsControl theEditItemsControl;
    
	/**
	 * Constructor for this View Control
	 * @param theMainDashboardViewControl	Reference to the MainDashboard's View Control
	 */
    public EditItemsViewControl(MainDashboardViewControl theMainDashboardViewControl) {
    	this.theMainDashboardViewControl = theMainDashboardViewControl;
    	this.theEditItemsControl = new EditItemsControl(theMainDashboardViewControl.getDBConnection());
    }
    
    /**
     * Gets logic control for this Edit Item view control
     * @return the Item edited
     */
    public EditItemsControl getEditItemsControl() {
    	return this.theEditItemsControl;
    }
    
    /**
     * Sets the form values to those of the Item to edit
     * @param theItemToEdit
     */
    public void setFormForSelectedItem(Item theItemToEdit) {
    	this.editItemTypeTextBox.setText(String.valueOf(theItemToEdit.getItemType()));
    	this.editItemNameTextBox.setText(theItemToEdit.getItemName());
    	this.editItemDescriptionTextArea.setText(theItemToEdit.getItemDescription());
    	this.editIsItemForQuestCheckBox.setSelected(theItemToEdit.getIsQuestItem());
    	this.editIsItemImplicitCheckBox.setSelected(theItemToEdit.getIsImplicitItem());
    	this.editIsItemImplicitCheckBox.setSelected(theItemToEdit.getIstrophy());
    }
    
	@FXML
	private void handleItemEditBackButton() throws SQLException {
		this.theMainDashboardViewControl.setMainDashboardStage("manageItems");
	}
    
	@FXML
	private void handleItemDeleteButton() throws SQLException {
		String itemDeleteError = null;
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Item Edit");
		alert.setHeaderText("Item Edit Status");
		alert.setContentText("Are you sure you want to DELETE " + this.theEditItemsControl.getSelectedItem().getItemName() + "? This operation cannot be undone.");
		alert.showAndWait();
		if (alert.getResult() == ButtonType.OK) {
			try {
				itemDeleteError = this.theEditItemsControl.deleteItem(this.theEditItemsControl.getSelectedItem());
			} catch (Exception e) {
				itemDeleteError = e.getMessage();
			}
			if (itemDeleteError != null) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Error Editing the Item");
				alert.setContentText(itemDeleteError);
				alert.showAndWait();
				return;
			}
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Item Edit");
			alert.setHeaderText("Item Edit Status");
			alert.setContentText("The Item was successfully deleted");
			alert.showAndWait();
			this.theMainDashboardViewControl.setMainDashboardStage("manageItems");
		}
	}
    
	@FXML
	private void handleItemEditSaveButton() throws SQLException {
		String itemCreationError = null;
		try {
			itemCreationError = this.theEditItemsControl.updateItem(this.editItemNameTextBox.getText(), this.editItemDescriptionTextArea.getText(), Integer.parseInt(this.editItemTypeTextBox.getText()), this.editIsItemForQuestCheckBox.isSelected(), this.editIsItemImplicitCheckBox.isSelected(), this.editIsItemTrophyCheckBox.isSelected());
		} catch (Exception e) {
			itemCreationError = e.getMessage();
		}
		if (itemCreationError != null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error Editing the Item");
			alert.setContentText(itemCreationError);
			alert.showAndWait();
			return;
		}
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Item Edit");
		alert.setHeaderText("Item Edit Status");
		alert.setContentText("The Item was successfully modified");
		alert.showAndWait();
		this.theMainDashboardViewControl.setMainDashboardStage("manageItems");
	}
}
