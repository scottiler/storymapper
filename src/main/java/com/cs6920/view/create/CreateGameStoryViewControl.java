/**
 * 
 */
package com.cs6920.view.create;

import java.sql.SQLException;

import com.cs6920.control.logic_control.CreateGameStoryControl;
import com.cs6920.view.MainDashboardViewControl;
import com.cs6920.view.ViewControl;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

/**
 * @author Ashley W. Palmer
 * @date 6/28/2020
 *
 */
public class CreateGameStoryViewControl extends ViewControl {
	
	@FXML
	private TextField createGameStoryTextBox;
	@FXML
	private TextField createGameStorySummaryTextArea;
	@FXML

	private MainDashboardViewControl theMainDashboardViewControl;
	private CreateGameStoryControl theCreateGameStoryControl;
    
	/**
	 * Constructor for this View Control
	 * @param theMainDashboardViewControl	Reference to the MainDashboard's View Control
	 */
    public CreateGameStoryViewControl(MainDashboardViewControl theMainDashboardViewControl) {
    	this.theMainDashboardViewControl = theMainDashboardViewControl;
    	this.theCreateGameStoryControl = new CreateGameStoryControl(theMainDashboardViewControl.getDBConnection());
    }
    
    
    private void resetCreateItemsView() {
    	this.createGameStoryTextBox.setText("");
    	this.createGameStorySummaryTextArea.setText("");

    }
    
	@FXML
	private void handleItemCanelButton() throws SQLException {
		this.resetCreateItemsView();
		this.theMainDashboardViewControl.setMainDashboardStage("manageItems");
	}
    
	@FXML
	private void handleItemCreateButton() throws SQLException {
		String itemCreationError = null;
		try {
			itemCreationError = this.theCreateGameStoryControl.createGameStory(this.createGameStoryTextBox.getText(), this.createGameStorySummaryTextArea.getText());
		} catch (Exception e) {
			itemCreationError = e.getMessage();
		}
		if (itemCreationError != null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error Creating the new GameStory");
			alert.setContentText(itemCreationError);
			alert.showAndWait();
			return;
		}
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Game Story Creation");
		alert.setHeaderText("Game Story Creation Status");
		alert.setContentText("The Game Story was successfully created");
		alert.showAndWait();
		this.resetCreateItemsView();
		this.theMainDashboardViewControl.setMainDashboardStage("manageItems");
	}

}
