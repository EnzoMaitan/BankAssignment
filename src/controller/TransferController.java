package controller;

/*
 * Name:	Enzo Maitan Favaro
 * Student ID:	40941046
 * Couse & Section:	CST8132 300/301
 * Assignment: Lab 9 
 * Professor: Istiaque Shahriar
 * Date: 2019-04-19
 */

import java.net.URL;
import java.util.ResourceBundle;
import model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class TransferController implements Initializable{
    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtAccountNumber;

    @FXML
    private ComboBox<String> cmbOperation;

    @FXML
    private Button btnTransfer;

    @FXML
    private Button btnCancel;
    
    @FXML
    private void btnTransferPressed(MouseEvent event) {   
    	long accountNumber = Long.parseLong(txtAccountNumber.getText());
    	double amount = Double.parseDouble(txtAmount.getText());
    	try {
    		if(cmbOperation.getValue().equals("Deposit")) {
    			BankSingleton.getBank().depositInAccount(accountNumber, amount);
    		}
    		else if(cmbOperation.getValue().equals("Withdraw")) {
    			BankSingleton.getBank().withdrawFromAccount(accountNumber, amount);
    		}	
    		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setHeaderText("Transaction completed successfully");
			alert.showAndWait();
		}catch(AccountDoesNotExistException ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(ex.getMessage());
			alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(ex.toString())));
			alert.showAndWait();
		}
    	catch (TransactionIllegalArgumentException ex) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(ex.getMessage());
			alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(ex.toString())));
			alert.showAndWait();
		}
    	catch(Exception ex) {
    		//TODO
    	}
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnTransfer.setDisable(true);

		/**
		 * Adding ComboBox options
		 * */
		cmbOperation.getItems().clear();
		cmbOperation.getItems().addAll("Deposit","Withdraw");		
		cmbOperation.getSelectionModel().selectFirst();
		/**
		 * Filtering user input
		 * */
		txtAmount.textProperty().addListener(
				new ChangeListener<String>() {				
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						try {
							if(txtAmount.getText().equals("") && txtAccountNumber.getText().equals("")) {
								btnTransfer.setDisable(true);
							}
							
							if(!txtAmount.getText().matches("[0-9]+")) {
								btnTransfer.setDisable(true);
								txtAmount.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
							}
							else if(txtAccountNumber.getText().matches("[0-9]+") && !txtAccountNumber.getText().equals("")){
								btnTransfer.setDisable(false);
								txtAmount.setStyle("");
								txtAccountNumber.setStyle("");
							}
						} catch(Exception ex) {
						}		
					}
				}
		);
		txtAccountNumber.textProperty().addListener(
				new ChangeListener<String>() {				
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						try {
							if(txtAmount.getText().equals("") && txtAccountNumber.getText().equals("")) {
								btnTransfer.setDisable(true);
							}
							
							if(!txtAccountNumber.getText().matches("[0-9]+")) {
								btnTransfer.setDisable(true);
								txtAccountNumber.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
								}
							else if(txtAmount.getText().matches("[0-9]+") && !txtAmount.getText().equals("")){
								btnTransfer.setDisable(false);
								txtAmount.setStyle("");
								txtAccountNumber.setStyle("");
							}
						} catch(Exception ex) {
						}		
					}
				}
		);
	}
}
