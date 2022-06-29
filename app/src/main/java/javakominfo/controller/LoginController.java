package javakominfo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javakominfo.backend.entity.Users;
import javakominfo.backend.repository.UsersRepo;

import java.util.logging.Logger;
import java.util.prefs.Preferences;

public class LoginController {

  Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  @FXML
  private TextField usernameTextField;

  @FXML
  private PasswordField passwordField;

  @FXML
  private Button loginBtn;

  @FXML
  private Button cancelBtn;

  @FXML
  void cancel(ActionEvent event) {
    cancelBtn.getScene().getWindow().hide();
  }

  @FXML
  void login(ActionEvent event) {
    UsersRepo repo = new UsersRepo();
    String username = usernameTextField.getText();
    String password = passwordField.getText();
    Users user = repo.readByUsernameAndPassword(username, password);

    if (user != null) {
      logger.info("Login success!");
      Preferences prefs = Preferences.userNodeForPackage(LoginController.class);
      prefs.put("nip", user.getUsername());
      loginBtn.getScene().getWindow().hide();
      AnchorPane root = null;
      try {
        root = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("dashboardsuperadmin.fxml"));
      } catch (Exception ex) {
        logger.severe(ex.getLocalizedMessage());
      }
      Scene scene = new Scene(root);
      Stage stage = new Stage();
      stage.setScene(scene);
      stage.show();
    } else {
      logger.info("Login gagal!");
    }
  }

}
