package javakominfo.backend.repository;

import javakominfo.backend.database.DB;
import javakominfo.backend.entity.Pegawai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PegawaiRepo implements CRUD<Pegawai> {

	private final Connection conn;
	private PreparedStatement ps;
	private final String TABLE = "Pegawai";

	public PegawaiRepo() {
		conn = new DB().connect();
	}

	@Override
	public void create(Pegawai p) {
		try {
			ps = conn.prepareStatement("INSERT INTO "+TABLE+" VALUES(?, ?, ?, ?, ?, ?);");
			ps.setString(1, p.getNIP());
			ps.setString(2, p.getNama());
			ps.setDate(3, java.sql.Date.valueOf(p.getTanggalLahir()));
			ps.setString(4, String.valueOf(p.getJenisKelamin()));
			ps.setString(5, String.valueOf(p.getGolongan()));
			ps.setString(6, p.getAlamat());
			ps.execute();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Pegawai> read() {
		List<Pegawai> pegawais = new ArrayList<>();
		try {
			ps = conn.prepareStatement("SELECT * FROM "+TABLE+";");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Pegawai pegawai = new Pegawai(
					rs.getString("NIP"), 
					rs.getString("nama"),
					rs.getDate("tanggal_lahir").toLocalDate(),
					rs.getString("jenis_kelamin").charAt(0),
					rs.getString("golongan").charAt(0),
					rs.getString("alamat")
				);
				pegawais.add(pegawai);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return pegawais;
	}

	@Override
	public Pegawai readById(String id) {
		Pegawai pegawai = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM "+TABLE+" WHERE NIP=?;");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				pegawai = new Pegawai(
					rs.getString("NIP"), 
					rs.getString("nama"),
					rs.getDate("tanggal_lahir").toLocalDate(),
					rs.getString("jenis_kelamin").charAt(0),
					rs.getString("golongan").charAt(0),
					rs.getString("alamat")
				);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return pegawai;
	}

	@Override
	public void update(Pegawai p) {
		try {
			ps = conn.prepareStatement("UPDATE "+TABLE+" SET nama=?, tanggal_lahir=?, jenis_kelamin=?, golongan=?, alamat=? WHERE NIP=?;");
			ps.setString(1, p.getNama());
			ps.setDate(2, java.sql.Date.valueOf(p.getTanggalLahir()));
			ps.setString(3, String.valueOf(p.getJenisKelamin()));
			ps.setString(4, String.valueOf(p.getGolongan()));
			ps.setString(5, p.getAlamat());
			ps.setString(6, p.getNIP());
			ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Pegawai p) {
		try {
			ps = conn.prepareStatement("DELETE FROM "+TABLE+" WHERE NIP=?;");
			ps.setString(1, p.getNIP());
			ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
