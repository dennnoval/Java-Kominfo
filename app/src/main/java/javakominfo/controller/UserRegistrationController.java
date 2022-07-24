package javakominfo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javakominfo.backend.entity.Users;
import javakominfo.backend.repository.UsersRepo;

import java.net.URL;
import java.util.ResourceBundle;

public class UserRegistrationController implements Initializable {

  @FXML
  private TextField emailTextField;

  @FXML
  private TextField nipPegawaiTextField;

  @FXML
  private TextField passwordTextField;

  @FXML
  private ComboBox<String> roleComboBox;

  private UsersRepo usersRepo;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    roleComboBox.getItems().addAll(new String[]{"ADMIN", "USER"});
    usersRepo = new UsersRepo();
  }

  @FXML
  void cancel(ActionEvent event) {
    cancelBtn(event, "dashboardadmin.fxml");
  }

  @FXML
  void simpan(ActionEvent event) {
    String username = nipPegawaiTextField.getText();
    String password = passwordTextField.getText();
    String email = emailTextField.getText();
    String role = roleComboBox.getSelectionModel().getSelectedItem();
    Users user = new Users(username, password, email, role);
    usersRepo.create(user);
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
