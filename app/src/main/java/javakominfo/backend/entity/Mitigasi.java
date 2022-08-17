package javakominfo.backend.entity;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;

public class Mitigasi implements Serializable {
  private static final long serialVersionUID = 1L;

  private int ID_mitigasi;
  private LocalDate tanggal;
  private String namaMitigasi;
  private String prioritas;
  private File dokumentasi;
  private String fileDir;
  private int ID_VA;
  private String NIP;

  public Mitigasi() {}

  public Mitigasi(LocalDate tanggal, String namaMitigasi, String prioritas, String fileDir, int ID_VA, String NIP) {
    this.tanggal = tanggal;
    this.namaMitigasi = namaMitigasi;
    this.prioritas = prioritas;
    this.dokumentasi = new File(fileDir);
    this.fileDir = fileDir;
    this.ID_VA = ID_VA;
    this.NIP = NIP;
  }

  public int getID_mitigasi() {
    return ID_mitigasi;
  }

  public LocalDate getTanggal() {
    return tanggal;
  }

  public String getNamaMitigasi() {
    return namaMitigasi;
  }

  public String getPrioritas() {
    return prioritas;
  }

  public File getDokumentasi() {
    return dokumentasi;
  }

  public String getFileDir() {
    return fileDir;
  }

  public int getID_VA() {
    return ID_VA;
  }

  public String getNIP() {
    return NIP;
  }

  public void setID_mitigasi(int ID_VA) {
    this.ID_VA = ID_VA;
  }

}
