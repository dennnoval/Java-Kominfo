package javakominfo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javakominfo.backend.entity.Gaji;
import javakominfo.backend.entity.Pegawai;
import javakominfo.backend.repository.GajiRepo;
import javakominfo.backend.repository.PegawaiRepo;
import javakominfo.backend.utility.ReportUtil;

import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class KepegawaianController implements Initializable {

  @FXML
  private TableColumn<Pegawai, String> addressColumn;

  @FXML
  private TextArea alamatTextArea;

  @FXML
  private TableColumn<Pegawai, Character> golonganColumn;

  @FXML
  private ComboBox<String> golonganComboBox;

  @FXML
  private TableColumn<Pegawai, Character> jenisKelaminColumn;

  @FXML
  private ComboBox<String> jenisKelaminComboBox;

  @FXML
  private TableView<Pegawai> kepegawaianTableView;

  @FXML
  private TableColumn<Pegawai, String> namaPegawaiColumn;

  @FXML
  private TextField namaPegawaiTextField;

  @FXML
  private TableColumn<Pegawai, String> nipPegawaiColumn;

  @FXML
  private TextField nipPegawaiTextField;

  @FXML
  private TableColumn<Pegawai, LocalDate> tanggalLahirColumn;

  @FXML
  private DatePicker tanggalLahirDatePicker;

  private PegawaiRepo pegawaiRepo;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    jenisKelaminComboBox.getItems().addAll(new String[]{"Laki-laki", "Perempuan"});
    golonganComboBox.getItems().addAll(new String[]{"A", "B", "C"});
    pegawaiRepo = new PegawaiRepo();
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
    InputStream fileStream = getClass().getClassLoader().getResourceAsStream("report/pegawai_report.jrxml");
    reportUtil.printReport(fileStream);
  }

  @FXML
  void edit(ActionEvent event) {
    pegawaiRepo.update(getEntityByForm());
    clearFormField();
    initTable();
  }

  @FXML
  void hapus(ActionEvent event) {
    pegawaiRepo.delete(getEntityByForm());
    clearFormField();
    initTable();
  }

  @FXML
  void reset(ActionEvent event) {
    clearFormField();
  }

  @FXML
  void simpan(ActionEvent event) {
    pegawaiRepo.create(getEntityByForm());
    new GajiRepo().create(new Gaji(
      getEntityByForm().getNIP(), getEntityByForm().getNama(),
      getEntityByForm().getGolongan(), 0, 0, 0
    ));
    clearFormField();
    initTable();
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

  protected void initTable(List<Pegawai>... p) {
    try {
      List<Pegawai> pegawais = new ArrayList<>();
      if (p.length == 0) pegawais = pegawaiRepo.read();
      else pegawais = p[0];
      ObservableList<Pegawai> pegawaiList = FXCollections.observableList(pegawais);
      kepegawaianTableView.setItems(pegawaiList);
      nipPegawaiColumn.setCellValueFactory(new PropertyValueFactory<Pegawai, String>("NIP"));
      namaPegawaiColumn.setCellValueFactory(new PropertyValueFactory<Pegawai, String>("nama"));
      tanggalLahirColumn.setCellValueFactory(new PropertyValueFactory<Pegawai, LocalDate>("tanggalLahir"));
      jenisKelaminColumn.setCellValueFactory(new PropertyValueFactory<Pegawai, Character>("jenisKelamin"));
      golonganColumn.setCellValueFactory(new PropertyValueFactory<Pegawai, Character>("golongan"));
      addressColumn.setCellValueFactory(new PropertyValueFactory<Pegawai, String>("alamat"));
    } catch (Exception ex) {
      // ex.printStackTrace();
    }
  }

  protected void tableItemClickListener() {
    kepegawaianTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
      Pegawai pegawai = newValue;
      if (pegawai == null) pegawai = oldValue;
      nipPegawaiTextField.setText(pegawai.getNIP());
      namaPegawaiTextField.setText(pegawai.getNama());
      tanggalLahirDatePicker.setValue(pegawai.getTanggalLahir());
      jenisKelaminComboBox.getSelectionModel().select(String.valueOf(pegawai.getJenisKelamin()));
      golonganComboBox.getSelectionModel().select(String.valueOf(pegawai.getGolongan()));
      alamatTextArea.setText(pegawai.getAlamat());
    });
  }

  protected void clearFormField() {
    kepegawaianTableView.getSelectionModel().clearSelection();
    nipPegawaiTextField.setText("");
    namaPegawaiTextField.setText("");
    tanggalLahirDatePicker.setValue(null);
    golonganComboBox.getSelectionModel().select(0);
    alamatTextArea.setText("");
  }

  protected Pegawai getEntityByForm() {
    String NIP = nipPegawaiTextField.getText();
    String nama = namaPegawaiTextField.getText();
    LocalDate tanggalLahir = tanggalLahirDatePicker.getValue();
    char jenisKelamin = jenisKelaminComboBox.getSelectionModel().getSelectedItem().toCharArray()[0];
    char golongan = golonganComboBox.getSelectionModel().getSelectedItem().toCharArray()[0];
    String alamat = alamatTextArea.getText();
    return new Pegawai(NIP, nama, tanggalLahir, jenisKelamin, golongan, alamat);
  }

}
