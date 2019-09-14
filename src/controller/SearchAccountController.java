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
import java.util.ArrayList;
import java.util.ResourceBundle;
import model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SearchAccountController implements Initializable{

    @FXML
    private ListView<String> lstAccounts;

    @FXML
    private Button btnReadFile;

    @FXML
    private TextField txtSearch;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnMonthlyUpdate;

    @FXML
    private void btnReadPressed(MouseEvent event) {
    	BankSingleton.getBank().readRecords();
    	populateListView();
    	System.out.println();
    }
    
    @FXML
    private void btnMonthlyUpdatePressed(MouseEvent event) {
    	BankSingleton.getBank().monthlyUpdate();
    	populateListView();
    }
    
    private ObservableList<String> items = FXCollections.observableArrayList();
    @FXML
	public void initialize(URL arg0, ResourceBundle arg1) {
		populateListView();
		
		txtSearch.textProperty().addListener(
				new ChangeListener<String>() {				
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						try {
							if(!txtSearch.getText().equals("")) {
								if(txtSearch.getText().matches("[0-9]+")) {
									txtSearch.setStyle("");
									filterByAccountNumber(newValue);
								}
								else {
									lstAccounts.getItems().clear();
									txtSearch.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
								}
							}else
								populateListView();
						} catch(Exception ex) {
						}		
					}
				}
		);
	}
	private void filterByAccountNumber(String number) {
		lstAccounts.getItems().clear();
		for (int i = 0; i < items.size(); i++) {
			if(items.get(i).contains("AccountNumber: " + number))
				lstAccounts.getItems().add(items.get(i));
		}
	}
	private void populateListView() {
		lstAccounts.getItems().clear();
		items.clear();
		try {
			ArrayList<String> accounts = BankSingleton.getBank().getAllAccountDetails();
			for (int i = 0; i < accounts.size(); i++) {
				items.add(accounts.get(i));
			}
			lstAccounts.getItems().addAll(items);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}