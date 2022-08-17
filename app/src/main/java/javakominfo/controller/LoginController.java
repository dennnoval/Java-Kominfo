package javakominfo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javakominfo.backend.entity.Users;
import javakominfo.backend.repository.PegawaiRepo;
import javakominfo.backend.repository.UsersRepo;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
  void passwordFieldOnEnterKeyPressed(KeyEvent keyEvent) {
    if (keyEvent.getCode() == KeyCode.ENTER) {
      loginAction();
    }
  }

  @FXML
  void login(ActionEvent event) {
    loginAction();
  }

  protected void loginAction() {
    String username = usernameTextField.getText();
    String password = passwordField.getText();
    UsersRepo repo = new UsersRepo();
    Users user = repo.readByUsernameAndPassword(username, password);

    if (user != null) {
      if (showAlert("Login successfully!").get() == ButtonType.OK) {
        Preferences prefs = Preferences.userNodeForPackage(LoginController.class);
        prefs.put("nip", user.getUsername());
        String namaPegawai = new PegawaiRepo().readById(user.getUsername()).getNama();
        prefs.put("nama_pegawai", namaPegawai);
        loginBtn.getScene().getWindow().hide();
        AnchorPane root = null;
        try {
          root = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("dashboardadmin.fxml"));
        } catch (Exception ex) {
          logger.severe(ex.getLocalizedMessage());
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
      }
    } else {
      showAlert("Login failed!");
    }
  }

  protected Optional<ButtonType> showAlert(String msg) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText(null);
    alert.setContentText(msg);
    return alert.showAndWait();
  }

}
