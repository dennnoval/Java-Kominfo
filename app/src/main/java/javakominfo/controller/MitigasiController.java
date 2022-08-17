import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javakominfo.backend.entity.Mitigasi;
import javakominfo.backend.repository.MitigasiRepo;
import javakominfo.backend.repository.V_ARepo;
import javakominfo.backend.utility.ReportUtil;
import javakominfo.controller.LoginController;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class MitigasiController implements Initializable {

  @FXML
  private Group cetakBtnGroup;

  @FXML
  private Group editBtnGroup;

  @FXML
  private TableColumn<Mitigasi, String> fileDirColumn;

  @FXML
  private TextField fileDirTextField;

  @FXML
  private Group hapusBtnGroup;

  @FXML
  private TableColumn<Mitigasi, String> idVAColumn;

  @FXML
  private ComboBox<String> idVAComboBox;

  @FXML
  private TableView<Mitigasi> mitigasiTable;

  @FXML
  private TableColumn<Mitigasi, String> namaMitigasiColumn;

  @FXML
  private TextField namaMitigasiTextField;

  @FXML
  private TableColumn<Mitigasi, String> nipColumn;

  @FXML
  private TextField nipTextField;

  @FXML
  private TableColumn<Mitigasi, String> prioritasColumn;

  @FXML
  private ComboBox<String> prioritasComboBox;

  @FXML
  private Group resetBtnGroup;

  @FXML
  private Group simpanBtnGroup;

  @FXML
  private TableColumn<Mitigasi, LocalDate> tanggalColumn;

  @FXML
  private TableColumn<Mitigasi, String> idMitigasiColumn;

  @FXML
  private DatePicker tanggalPicker;

  private Preferences prefs;

  private MitigasiRepo mitRepo;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    prioritasComboBox.getItems().addAll(new String[]{"LOW","MID","HIGH"});
    new V_ARepo().read().forEach(v_a ->
      idVAComboBox.getItems().add(v_a.getID())
    );
    prefs = Preferences.userNodeForPackage(LoginController.class);
    nipTextField.setText(prefs.get("nip", null));
    mitRepo = new MitigasiRepo();
    initTable();
    tableItemClickListener();
  }

  @FXML
  void cancel(ActionEvent event) {
    cancelBtn(event, "dashboardadmin.fxml");
  }

  @FXML
  void cetak(ActionEvent event) {
    ReportUtil reportUtil = new ReportUtil();
    InputStream fileStream = getClass().getClassLoader().getResourceAsStream("report/mitigasi_report.jrxml");
    reportUtil.printReport(fileStream);
  }

  @FXML
  void edit(ActionEvent event) {
    Mitigasi mit = getEntityByForm();
    mit.setID_mitigasi(mitigasiTable.getSelectionModel().getSelectedItem().getID_mitigasi());
    mitRepo.update(mit);
    initTable();
    clearFormField();
  }

  @FXML
  void hapus(ActionEvent event) {
    Mitigasi mit = getEntityByForm();
    mit.setID_mitigasi(mitigasiTable.getSelectionModel().getSelectedItem().getID_mitigasi());
    mitRepo.delete(mit);
    initTable();
    clearFormField();
  }

  @FXML
  void reset(ActionEvent event) {
    clearFormField();
  }

  @FXML
  void simpan(ActionEvent event) {
    mitRepo.create(getEntityByForm());
    initTable();
    clearFormField();
  }

  @FXML
  void browseFile(MouseEvent event) {
    FileChooser fc = new FileChooser();
    fc.setTitle("Pilih berkas PDF");
    fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf"));
    try {
      File file = fc.showOpenDialog(new Stage());
      fileDirTextField.setText(file.getCanonicalPath());
    } catch (Exception ex) {}
  }

  protected void initTable() {
    try {
      List<Mitigasi> mits = mitRepo.read();
      ObservableList<Mitigasi> mitList = FXCollections.observableArrayList(mits);
      mitigasiTable.setItems(mitList);
      idMitigasiColumn.setCellValueFactory(new PropertyValueFactory<Mitigasi, String>("ID_mitigasi"));
      tanggalColumn.setCellValueFactory(new PropertyValueFactory<Mitigasi, LocalDate>("tanggal"));
      namaMitigasiColumn.setCellValueFactory(new PropertyValueFactory<Mitigasi, String>("namaMitigasi"));
      prioritasColumn.setCellValueFactory(new PropertyValueFactory<Mitigasi, String>("prioritas"));
      fileDirColumn.setCellValueFactory(new PropertyValueFactory<Mitigasi, String>("fileDir"));
      idVAColumn.setCellValueFactory(new PropertyValueFactory<Mitigasi, String>("ID_VA"));
      nipColumn.setCellValueFactory(new PropertyValueFactory<Mitigasi, String>("NIP"));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  protected void tableItemClickListener() {
    mitigasiTable.getSelectionModel().selectedItemProperty().addListener((obsVal, oldVal, newVal) -> {
      Mitigasi mit = newVal;
      if (mit == null) mit = oldVal;
      tanggalPicker.setValue(mit.getTanggal());
      namaMitigasiTextField.setText(mit.getNamaMitigasi());
      prioritasComboBox.getSelectionModel().select(mit.getPrioritas());
      fileDirTextField.setText(mit.getFileDir());
      idVAComboBox.getSelectionModel().select(String.valueOf(mit.getID_VA()));
      nipTextField.setText(mit.getNIP());
    });
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

  protected void clearFormField() {
    tanggalPicker.setValue(null);
    nipTextField.setText(prefs.get("nip", null));
    namaMitigasiTextField.setText(null);
    prioritasComboBox.getSelectionModel().clearSelection();
    fileDirTextField.setText(null);
    idVAComboBox.getSelectionModel().clearSelection();
  }

  protected Mitigasi getEntityByForm() {
    LocalDate tanggal = tanggalPicker.getValue();
    String namaMitigasi = namaMitigasiTextField.getText();
    String prioritas = prioritasComboBox.getSelectionModel().getSelectedItem();
    String fileDir = fileDirTextField.getText();
    int idVA = Integer.parseInt(idVAComboBox.getSelectionModel().getSelectedItem());
    String NIP = nipTextField.getText();
    return new Mitigasi(tanggal, namaMitigasi, prioritas, fileDir, idVA, NIP);
  }

}
