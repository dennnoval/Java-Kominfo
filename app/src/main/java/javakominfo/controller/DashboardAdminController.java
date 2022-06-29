package javakominfo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DashboardAdminController {

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
