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
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CreateAccountController implements Initializable{
    @FXML
    private ComboBox<String> cmbAccountType;

    @FXML
    private TextField txtAccountNumber;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtInitialBalance;

    @FXML
    private TextField txtFee;

    @FXML
    private TextField txtMinimumBalance;

    @FXML
    private Slider sliderInterest;

    @FXML
    private Label lblInterest;

    @FXML
    private Button btnCreate;
    
    @FXML
    private void btnCreatePressed(MouseEvent event) { 
    	try {
	    	if(checkValidInput()) {
		    	long accountNumber = Long.parseLong(txtAccountNumber.getText());
		    	long phone = Long.parseLong(txtPhoneNumber.getText());
		    	String firstName = txtFirstName.getText();
		    	String lastName = txtLastName.getText();
		    	String email = txtEmail.getText();
		    	double initialBalance = Double.parseDouble(txtInitialBalance.getText());
		    	
		    	if(cmbAccountType.getValue().equals("Savings")) {
		    		double interestRate = sliderInterest.getValue();
		    		double minimumBalance = Double.parseDouble(txtMinimumBalance.getText());
		    		
		    		Bank bank = BankSingleton.getBank(); 			
		    		bank.addAccount(new SavingsAccount(accountNumber, new Person(firstName, lastName, phone, email), initialBalance, interestRate,
		    				 minimumBalance));
		    	}
		    	else if(cmbAccountType.getValue().equals("Chequings")) {
		    		double fee = Double.parseDouble(txtFee.getText());
		    		
		    		Bank bank = BankSingleton.getBank(); 			
		    		bank.addAccount(new ChequingAccount(accountNumber, new Person(firstName, lastName, phone, email), initialBalance, fee));
		    	}  	
	    	}
	    	else {
	    		Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText("All the fields are required");
				alert.showAndWait();
	    	}
    	}catch (Exception ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(ex.getMessage());
			alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(ex.toString())));
			alert.showAndWait();
		}
    }
    private boolean checkValidInput() {
    		if(txtAccountNumber.getText().equals("") || txtPhoneNumber.getText().equals("") 
    				||txtFirstName.getText().equals("") ||txtLastName.getText().equals("") 
    				||txtEmail.getText().equals("") ||txtInitialBalance.getText().equals(""))
    			return false;  		      	
    	return true;
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/**
		 * Adding ComboBox options
		 * */
		cmbAccountType.getItems().clear();
		cmbAccountType.getItems().addAll("Chequings","Savings");
		cmbAccountType.getSelectionModel().selectFirst();

		/**
		 * Disabling fields
		 * */
		txtMinimumBalance.setDisable(true);
		sliderInterest.setDisable(true);
		
		/**
		 * Changing enable of fields according to the account type
		 * */
		cmbAccountType.valueProperty().addListener(
			new ChangeListener<String>() {
	            @Override 
	            public void changed(ObservableValue<? extends String> value, String oldValue, String newValue) { 
	                if(newValue.equals("Savings")) {
	                	txtFee.setDisable(true);
	            		txtMinimumBalance.setDisable(false);
	            		sliderInterest.setDisable(false);
	                }
	                else if(newValue.equals("Chequings")) {
	                	txtFee.setDisable(false);
	            		txtMinimumBalance.setDisable(true);
	            		sliderInterest.setDisable(true);
	                }
	            }    
			}
        );
		
		/**
		 * Changing slider lbl value according to the slider
		 * */
		sliderInterest.valueProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
						Double interestPercentage = Double.valueOf(newValue.doubleValue());
						DecimalFormat df = new DecimalFormat("#.##");
						lblInterest.setText(df.format(interestPercentage));
					}
				}
			);	
		
	}
}
