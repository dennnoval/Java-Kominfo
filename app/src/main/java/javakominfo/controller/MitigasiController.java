import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javakominfo.backend.utility.ReportUtil;

import java.io.InputStream;

public class MitigasiController {

  @FXML
  private Group cetakBtnGroup;

  @FXML
  private Group editBtnGroup;

  @FXML
  private TableColumn<?, ?> fileDirColumn;

  @FXML
  private TextField fileDirTextField;

  @FXML
  private Group hapusBtnGroup;

  @FXML
  private TableColumn<?, ?> idVAColumn;

  @FXML
  private TextField idVATextField;

  @FXML
  private TableView<?> mitigasiTable;

  @FXML
  private TableColumn<?, ?> namaMitigasiColumn;

  @FXML
  private TextField namaMitigasiTextField;

  @FXML
  private TableColumn<?, ?> nipColumn;

  @FXML
  private TextField nipTextField;

  @FXML
  private TableColumn<?, ?> prioritasColumn;

  @FXML
  private ComboBox<?> prioritasComboBox;

  @FXML
  private Group resetBtnGroup;

  @FXML
  private Group simpanBtnGroup;

  @FXML
  private TableColumn<?, ?> tanggalColumn;

  @FXML
  private DatePicker tanggalPicker;

  @FXML
  void cancel(ActionEvent event) {

  }

  @FXML
  void cetak(ActionEvent event) {
    ReportUtil reportUtil = new ReportUtil();
    InputStream fileStream = getClass().getClassLoader().getResourceAsStream("report/mitigasi_report.jrxml");
    reportUtil.printReport(fileStream);
  }

  @FXML
  void edit(ActionEvent event) {

  }

  @FXML
  void hapus(ActionEvent event) {

  }

  @FXML
  void reset(ActionEvent event) {

  }

  @FXML
  void simpan(ActionEvent event) {

  }

}
