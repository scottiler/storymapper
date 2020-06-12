package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * This control communicates between the FXML View and Logic Control for Login
 * @author Matthew Hodge
 * @version 6.12.2020
 */

public class LoginViewControl {
	@FXML
    private TextField hostTextInput;
	@FXML
    private TextField usernameDBTextInput;
	@FXML
    private TextField passwordDBTextInput;
	@FXML
    private Label dBConnectionMessageLabel;
	
	private FXMLLoader theFxmlLoader;
	private LoginControl theLoginControl;
	
	/**
	 * Constructor for the LoginView Control with 1 argument
	 * @param theLoginControl	the instance of a LoginControl to communicate with
	 */
	public LoginViewControl(LoginControl theLoginControl) {
		this.theLoginControl = theLoginControl;
        theFxmlLoader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));
        theFxmlLoader.setController(this);
	} 
	
	@FXML
	private void handleTestServerButtonAction(ActionEvent event)
	{
		theLoginControl.BuildConnectionString(hostTextInput.getText(), usernameDBTextInput.getText(), passwordDBTextInput.getText());
		if (theLoginControl.TestDBConnection()) {
		    dBConnectionMessageLabel.setText("Connection successful.");
		    dBConnectionMessageLabel.setTextFill(Color.GREEN);
		}
		else {
		    dBConnectionMessageLabel.setText("A connection was not established.");
		    dBConnectionMessageLabel.setTextFill(Color.RED);
		}
	}
	
	/**
	 * Provides access to this Fxml view. Primarily for the Main Application to load and manage it.
	 * @return	this FXMLLoader
	 */
	public FXMLLoader getTheFxmlLoader() {
		return theFxmlLoader;
	}
}
