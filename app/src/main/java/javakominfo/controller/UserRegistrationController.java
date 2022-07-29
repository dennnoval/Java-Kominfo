package javakominfo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javakominfo.backend.entity.Users;
import javakominfo.backend.repository.PegawaiRepo;
import javakominfo.backend.repository.UsersRepo;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserRegistrationController implements Initializable {

  @FXML
  private TextField emailTextField;

  @FXML
  private ComboBox<String> nipComboBox;

  @FXML
  private TextField passwordTextField;

  @FXML
  private ComboBox<String> roleComboBox;

  private UsersRepo usersRepo;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    usersRepo = new UsersRepo();
    new PegawaiRepo().read().stream()
      .filter(pegawai -> usersRepo.read().stream().noneMatch(user -> pegawai.getNIP().equals(user.getUsername())))
      .forEach(pegawai -> nipComboBox.getItems().add(pegawai.getNIP()));
    roleComboBox.getItems().addAll(new String[]{"ADMIN", "KARYAWAN"});
  }

  @FXML
  void cancel(ActionEvent event) {
    cancelBtn(event, "dashboardadmin.fxml");
  }

  @FXML
  void simpan(ActionEvent event) {
    String username = nipComboBox.getSelectionModel().getSelectedItem();
    System.out.println(username);
    String password = passwordTextField.getText();
    String email = emailTextField.getText();
    String role = roleComboBox.getSelectionModel().getSelectedItem();
    Users user = new Users(username, password, email, role);
    boolean isCreated = usersRepo.create(user);
    if (isCreated)
      showAlert("Registrasi user baru berhasil");
    else showAlert("Registrasi gagal!");
  }

  @FXML
  void emailTextFieldOnKeyReleased(KeyEvent keyEvent) {
    if (emailTextField.getText().matches("^[a-z0-9_]+@\\w+.[a-z]+$")) {
      System.out.println(emailTextField.getText());
    }
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

  protected Optional<ButtonType> showAlert(String msg) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText(null);
    alert.setContentText(msg);
    return alert.showAndWait();
  }

}
