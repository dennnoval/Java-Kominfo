package javakominfo.backend.entity;

import java.io.Serializable;

public class Gaji implements Serializable {

	private String NIP;
	private String nama;
	private char golongan;
	private int gapok;
	private int transport;
	private int pulsa;
	private int totalGaji;
  
  public Gaji() {}

	public Gaji(String nip, String nm, char g, int gp, int t, int p) {
		NIP = nip;
		nama = nm;
		golongan = g;
		gapok = gp;
		transport = t;
		pulsa = p;
		totalGaji = gapok + transport + pulsa;
	}

	public String getNIP() {
		return NIP;
	}

	public String getNama() {
		return nama;
	}

	public char getGolongan() {
		return golongan;
	}

	public int getGapok() {
		return gapok;
	}

	public int getTransport() {
		return transport;
	}

	public int getPulsa() {
		return pulsa;
	}

	public int getTotalGaji() {
		return totalGaji;
	}

}
