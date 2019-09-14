package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MenuController {
	   @FXML
	    private Button btnManage;

	    @FXML
	    private Button btnAdd;

	    @FXML
	    private Button btnTransfer;

    	@FXML
	    private void btnAddPressed(MouseEvent event) { 
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/CreateAccount.fxml"));
			    Parent root1 = (Parent) fxmlLoader.load();
			    Stage stage = new Stage();
			    stage.setTitle("Create new Account");
			    stage.setScene(new Scene(root1));  
			    stage.show();		  	       
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
    	
    	@FXML
	    private void btnTransferPressed(MouseEvent event) { 
    		try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Transfer.fxml"));
			    Parent root1 = (Parent) fxmlLoader.load();
			    Stage stage = new Stage();
			    stage.setTitle("Transfer");
			    stage.setScene(new Scene(root1));  
			    stage.show();		  	       
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
    	
    	@FXML
	    private void btnManagePressed(MouseEvent event) { 
    		try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/SearchAccount.fxml"));
			    Parent root1 = (Parent) fxmlLoader.load();
			    Stage stage = new Stage();
			    stage.setTitle("Manage Accounts");
			    stage.setScene(new Scene(root1));  
			    stage.show();		  	       
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
}
