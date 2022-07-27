package javakominfo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javakominfo.backend.entity.Gaji;
import javakominfo.backend.repository.GajiRepo;
import javakominfo.backend.utility.ReportUtil;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import java.util.List;
import java.util.ArrayList;

public class GajiPegawaiController implements Initializable {

  @FXML
  private TextField cariTextField;

  @FXML
  private TextField gajiPokokTextField;

  @FXML
  private ComboBox<String> golonganComboBox;

  @FXML
  private TextField namaPegawaiTextField;

  @FXML
  private TextField nipPegawaiTextField;

  @FXML
  private Label totalGajiLabel;

  @FXML
  private ComboBox<String> tunjanganPulsaComboBox;

  @FXML
  private TextField tunjanganTransportTextField;

  @FXML
  private TableView<Gaji> gajiPegawaiTableView;

  @FXML
  private TableColumn<Gaji, String> nipPegawaiColumn;

  @FXML
  private TableColumn<Gaji, String> namaPegawaiColumn;

  @FXML
  private TableColumn<Gaji, String> golonganiColumn;

  @FXML
  private TableColumn<Gaji, String> gajiPokokColumn;

  @FXML
  private TableColumn<Gaji, String> tunjanganTransportColumn;

  @FXML
  private TableColumn<Gaji, String> tunjanganPulsaColumn;

  @FXML
  private TableColumn<Gaji, String> totalGajiColumn;

  @FXML
  private Group btnSimpanGroup;

  @FXML
  private Button btnEdit;

  @FXML
  private Group btnHapusGroup;

  private GajiRepo gajiRepo;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    btnSimpanGroup.setVisible(false);
    btnHapusGroup.setVisible(false);
    setDisableCrudButton(true);
    nipPegawaiTextField.setDisable(true);
    namaPegawaiTextField.setDisable(true);
    golonganComboBox.setDisable(true);
    tunjanganPulsaComboBox.getItems().addAll(new String[]{"100000", "200000"});
    golonganComboBox.getItems().addAll(new String[]{"A", "B", "C"});
    gajiRepo = new GajiRepo();
    initTable();
    tableItemClickListener();
  }

  @FXML
  void cancel(ActionEvent event) {
    cancelBtn(event, "dashboardadmin.fxml");
  }

  @FXML
  void cariDataPegawai(ActionEvent event) {
    String searchQuery = cariTextField.getText();
    if (!searchQuery.equals("")) {
      List<Gaji> gajis = gajiRepo.readBy(searchQuery);
      initTable(gajis);
    } else {
      initTable();
    }
    cariTextField.setText("");
  }

  @FXML
  void cetak(ActionEvent event) {
    ReportUtil reportUtil = new ReportUtil();
    InputStream fileStream = getClass().getClassLoader().getResourceAsStream("report/gaji_report.jrxml");
    reportUtil.printReport(fileStream);
  }

  @FXML
  void edit(ActionEvent event) {
    gajiRepo.update(getGajiByForm());
    clearFormField();
    initTable();
    setDisableCrudButton(true);
  }

  @FXML
  void gajiPokokBtn_setTotalGaji(KeyEvent event) {
    setTotalGaji();
  }

  @FXML
  void hapus(ActionEvent event) {
    gajiRepo.delete(getGajiByForm());
    clearFormField();
    initTable();
    setDisableCrudButton(true);
  }

  @FXML
  void reset(ActionEvent event) {
    clearFormField();
    setDisableCrudButton(true);
  }

  @FXML
  void tunjanganPulsaComboBox_setTotalGaji(ActionEvent event) {
    setTotalGaji();
  }

  @FXML
  void simpan(ActionEvent event) {
    gajiRepo.create(getGajiByForm());
    clearFormField();
    initTable();
    setDisableCrudButton(true);
  }

  @FXML
  void tunjanganTransportBtn_setTotalGaji(KeyEvent event) {
    setTotalGaji();
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

  protected void setTotalGaji() {
    long gaji = 0, transport = 0, pulsa = 0;
    if (!gajiPokokTextField.getText().equals(""))
      gaji = Long.valueOf(gajiPokokTextField.getText());
    if (!tunjanganTransportTextField.getText().equals(""))
      transport = Long.valueOf(tunjanganTransportTextField.getText());
    if (tunjanganPulsaComboBox.getSelectionModel().getSelectedItem() != null)
      pulsa = Long.valueOf(tunjanganPulsaComboBox.getSelectionModel().getSelectedItem());
    totalGajiLabel.setText(String.valueOf(gaji + transport + pulsa));
  }

  protected void initTable(List<Gaji>... g) {
    try {
      List<Gaji> gajis = new ArrayList<>();
      if (g.length == 0) gajis = gajiRepo.read();
      else gajis = g[0];
      ObservableList<Gaji> gajiList = FXCollections.observableArrayList();
      gajiList.addAll(gajis);
      gajiPegawaiTableView.setItems(gajiList);
      nipPegawaiColumn.setCellValueFactory(new PropertyValueFactory<Gaji, String>("NIP"));
      namaPegawaiColumn.setCellValueFactory(new PropertyValueFactory<Gaji, String>("nama"));
      golonganiColumn.setCellValueFactory(new PropertyValueFactory<Gaji, String>("golongan"));
      gajiPokokColumn.setCellValueFactory(new PropertyValueFactory<Gaji, String>("gapok"));
      tunjanganTransportColumn.setCellValueFactory(new PropertyValueFactory<Gaji, String>("transport"));
      tunjanganPulsaColumn.setCellValueFactory(new PropertyValueFactory<Gaji, String>("pulsa"));
      totalGajiColumn.setCellValueFactory(new PropertyValueFactory<Gaji, String>("totalGaji"));
    } catch (Exception ex) {
      // ex.printStackTrace();
    }
  }

  protected void tableItemClickListener() {
    gajiPegawaiTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
      Gaji gaji = newValue;
      if (gaji == null) gaji = oldValue;
      nipPegawaiTextField.setText(gaji.getNIP());
      namaPegawaiTextField.setText(gaji.getNama());
      golonganComboBox.getSelectionModel().select(String.valueOf(gaji.getGolongan()));
      gajiPokokTextField.setText(String.valueOf(gaji.getGapok()));
      tunjanganTransportTextField.setText(String.valueOf(gaji.getTransport()));
      tunjanganPulsaComboBox.getSelectionModel().select(String.valueOf(gaji.getPulsa()));
      totalGajiLabel.setText(String.valueOf(gaji.getTotalGaji()));
      setDisableCrudButton(false);
    });
  }

  protected void clearFormField() {
    gajiPegawaiTableView.getSelectionModel().clearSelection();
    nipPegawaiTextField.setText("");
    namaPegawaiTextField.setText("");
    golonganComboBox.getSelectionModel().select(0);
    gajiPokokTextField.setText("");
    tunjanganTransportTextField.setText("");
    tunjanganPulsaComboBox.getSelectionModel().select(0);
    totalGajiLabel.setText("0");
  }

  protected Gaji getGajiByForm() {
    String NIP = nipPegawaiTextField.getText();
    String nama = namaPegawaiTextField.getText();
    char golongan = golonganComboBox.getSelectionModel().getSelectedItem().toCharArray()[0];
    int gapok = Integer.valueOf(gajiPokokTextField.getText());
    int transport = Integer.valueOf(tunjanganTransportTextField.getText());
    int pulsa = Integer.valueOf(tunjanganPulsaComboBox.getSelectionModel().getSelectedItem());
    return new Gaji(NIP, nama, golongan, gapok, transport, pulsa);
  }

  protected void setDisableCrudButton(boolean state) {
    // btnSimpanGroup.setDisable(state);
    btnEdit.setDisable(state);
    // btnHapusGroup.setDisable(state);
  }

}
