package javakominfo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javakominfo.backend.entity.Role;
import javakominfo.backend.entity.Users;
import javakominfo.backend.repository.UsersRepo;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class DashboardAdminController implements Initializable {

  @FXML
  private Button gajiBtn;

  @FXML
  private Button logoutBtn;

  @FXML
  private Button pegawaiBtn;

  @FXML
  private Button registrationBtn;

  @FXML
  private Button vaBtn;

  @FXML
  private Label titleLabel;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    Preferences prefs = Preferences.userNodeForPackage(LoginController.class);
    String nip = prefs.get("nip", null);
    Users usr = new UsersRepo().readById(nip);
    Role role = usr.getRole();
    if (role == Role.KARYAWAN) {
      gajiBtn.setDisable(true);
      pegawaiBtn.setDisable(true);
      registrationBtn.setDisable(true);
      String title = titleLabel.getText().replace("Admin", "Karyawan");
      titleLabel.setText(title);
    }
  }

  @FXML
  void logout(ActionEvent event) {
    cancelBtn(event, "login.fxml");
  }

  @FXML
  void viewGajiPegawaiFrame(ActionEvent event) {
    gajiBtn.getScene().getWindow().hide();
    AnchorPane root = null;
    try {
      root = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("gajipegawai.fxml"));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  void viewKominfoPegawaiFrame(ActionEvent event) {
    pegawaiBtn.getScene().getWindow().hide();
    AnchorPane root = null;
    try {
      root = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("kepegawaian.fxml"));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  void viewRegisFrame(ActionEvent event) {
    registrationBtn.getScene().getWindow().hide();
    AnchorPane root = null;
    try {
      root = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("userregistration.fxml"));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  void viewVAFrame(ActionEvent event) {
    vaBtn.getScene().getWindow().hide();
    AnchorPane root = null;
    try {
      root = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("vulnerabiltyassessment.fxml"));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
  }

  protected void cancelBtn(ActionEvent evt, String fxml) {
    Node node = (Node) evt.getSource();
    Stage stage = (Stage) node.getScene().getWindow();
    stage.hide();

    AnchorPane root = null;
    try {
      root = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource(fxml));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

}
