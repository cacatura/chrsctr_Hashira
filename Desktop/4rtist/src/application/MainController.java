package application;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;


public class MainController {
	@FXML
	private Label lblStatus;
	
	@FXML
	private Label userName;
	
	@FXML
	private TextField txtusername;

	@FXML
	private TextField txtpassword;
	
	@FXML
	private Button btnlogin;
	
	@FXML
	private Button logout;
	
	@FXML
	private Button imgUpload;
	
	@FXML
	private Button browseImg;
	
	@FXML
	private ImageView preview;
	
	@FXML
	private Button confirm;
	
	@FXML
	private Button cancel;
	
	public void Login(ActionEvent event) throws Exception {
		//User user = new User(txtusername.getText(), txtpassword.getText());
		FireBaseAuth auth = new FireBaseAuth();
		//System.out.println(user.getUserName());
		//System.out.println(user.getPassWord());
		//System.out.println(auth.auth(txtusername.getText(), txtpassword.getText()));
		if (auth.auth(txtusername.getText(), txtpassword.getText()).equals("true")) {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Profile.fxml"));
			Stage window = (Stage) btnlogin.getScene().getWindow();
			window.setScene(new Scene(root, 400, 300));
			
		} else {
			lblStatus.setText("Login Error");
		}
	}
	
	public void logout(ActionEvent event) throws Exception {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation((getClass().getResource("/application/ConfirmLogout.fxml")));
		stage = (Stage) logout.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load()));
	}
	public void confirmLogout(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
		Stage window = (Stage) confirm.getScene().getWindow();
		window.setScene(new Scene(root, 400, 300));
	}
	public void cancel(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/application/Profile.fxml"));
		Stage window = (Stage) cancel.getScene().getWindow();
		window.setScene(new Scene(root, 400, 300));
	}
	public void imgUpload(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/application/Upload.fxml"));
		Stage window = (Stage) imgUpload.getScene().getWindow();
		window.setScene(new Scene(root, 400, 300));
	}
	
	private String getFileExtension(File file) {
		String name = file.getName();
		int lastIndexOf = name.lastIndexOf(".");
		if (lastIndexOf == -1) {
        return "";
		}
		return name.substring(lastIndexOf);
	}	
	public void browseImg(ActionEvent event) throws Exception {
		JFileChooser file = new JFileChooser();
		file.setCurrentDirectory(new File(System.getProperty("user.home")));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "*png");
		file.addChoosableFileFilter(filter);
		int result = file.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = file.getSelectedFile();
			String path = selectedFile.getAbsolutePath();
			System.out.println(path);
			BufferedImage buffImg = ImageIO.read(new File(path));
			Image img  = SwingFXUtils.toFXImage(buffImg, null);
			System.out.println(getFileExtension(selectedFile));
			if (img != null && (getFileExtension(selectedFile).equals(".png"))) {
				//System.out.println("Image Read");
				preview.setImage(img);
			} else {
				Alert alert	= new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setContentText("Invalid file. Only PNG and less than 10MB is allowed.");
				alert.showAndWait();
			}
		} else if (result == JFileChooser.CANCEL_OPTION) {
			System.out.println("No File Selected.");
		}
	}
	
	
}
