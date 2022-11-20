package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;


public class MainController {
	@FXML
	private Label lblStatus;
	
	@FXML
	private TextField txtusername;

	@FXML
	private TextField txtpassword;
	
	@FXML
	private Button btnlogin;
	
	@FXML
	private Button logout;
	
	public void Login(ActionEvent event) throws Exception {
		User user = new User(txtusername.getText(), txtpassword.getText());
		FireBaseAuth auth = new FireBaseAuth();
		System.out.println(user.getUserName());
		System.out.println(user.getPassWord());
		System.out.println(auth.auth(txtusername.getText(), txtpassword.getText()));
		if (auth.auth(txtusername.getText(), txtpassword.getText()).equals("true")) {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Profile.fxml"));
			Stage window = (Stage) btnlogin.getScene().getWindow();
			window.setScene(new Scene(root, 400, 300));
		} else {
			lblStatus.setText("Login Error");
		}
	}
	public void logout(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
		Stage window = (Stage) logout.getScene().getWindow();
		window.setScene(new Scene(root, 400, 300));
	}
}
