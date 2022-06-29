package javakominfo.backend.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Pegawai implements Serializable {

	private String NIP;
	private String nama;
	private LocalDate tanggalLahir;
	private char jenisKelamin;
	private char golongan;
	private String alamat;
  
  public Pegawai() {}

	public Pegawai(String nip, String nm, LocalDate t, char j, char g, String a) {
		NIP = nip;
		nama = nm;
		tanggalLahir = t;
		jenisKelamin = j;
		golongan = g;
		alamat = a;
	}

	public String getNIP() {
		return NIP;
	}

	public String getNama() {
		return nama;
	}

	public LocalDate getTanggalLahir() {
		return tanggalLahir;
	}

	public char getJenisKelamin() {
		return jenisKelamin;
	}

	public char getGolongan() {
		return golongan;
	}

	public String getAlamat() {
		return alamat;
	}

}
