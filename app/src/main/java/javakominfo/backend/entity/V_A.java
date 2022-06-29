package javakominfo.backend.entity;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;

public class V_A implements Serializable {
	private static final long serialVersionUID = 1L;

	private String ID;
	private LocalDate tanggal;
	private String NIP;
	private String namaV_A;
	private String domain;
	private File file;
	private String fileDir;
  
  public V_A() {}

	public V_A(LocalDate t, String nip, String nva, String d, String fd) {
		tanggal = t;
		NIP = nip;
		namaV_A = nva;
		domain = d;
		fileDir = fd;
		file = new File(fd);
	}
	
	public String getID() {
		return ID;
	}

	public LocalDate getTanggal() {
		return tanggal;
	}

	public String getNIP() {
		return NIP;
	}

	public String getNamaV_A() {
		return namaV_A;
	}

	public String getDomain() {return domain;
	}

	public String getFileDir() { return fileDir; }

	public File getFile() {
		return file;
	}

	public void setID(String id) {
		ID = id;
	}

}
