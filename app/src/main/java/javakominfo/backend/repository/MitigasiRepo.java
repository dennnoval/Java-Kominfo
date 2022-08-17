package javakominfo.backend.repository;

import javakominfo.backend.database.DB;
import javakominfo.backend.entity.Mitigasi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MitigasiRepo implements CRUD<Mitigasi> {

	private final Connection conn;
	private PreparedStatement ps;
	private final String TABLE = "Mitigasi";

	public V_ARepo() {
		conn = new DB().connect();
	}

	@Override
	public boolean create(Mitigasi mitigasi) {
		try {
			ps = conn.prepareStatement("INSERT INTO "+TABLE+" (tanggal, nama_mitigasi, prioritas, dokumentasi, file_dir, ID_VA, NIP) VALUES(?, ?, ?, ?, ?, ?, ?);");
			ps.setDate(1, java.sql.Date.valueOf(mitigasi.getTanggal()));
			ps.setString(2, mitigasi.getNamaMitigasi());
			ps.setString(3, mitigasi.getPrioritas());
			ps.setBinaryStream(4, new FileInputStream(mitigasi.getDokumentasi()));
			ps.setString(5, mitigasi.getFileDir());
			ps.setString(6, mitigasi.getID_VA());
			ps.setString(7, mitigasi.getNIP());
			ps.execute();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Mitigasi> read() {
		List<Mitigasi> mits = new ArrayList<>();
		try {
			ps = conn.prepareStatement("SELECT * FROM "+TABLE+";");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Mitigasi mitigasi = new Mitigasi(
					rs.getDate("tanggal").toLocalDate(),
					rs.getString("nama_mitigasi"),
					rs.getString("prioritas"),
					rs.getString("file_dir"),
					rs.getInt("ID_VA"),
					rs.getString("NIP")
				);
				mitigasi.setID_mitigasi(rs.getString("ID_mitigasi"));
				mits.add(mitigasi);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mits;
	}

	@Override
	public Mitigasi readById(String id) {
		Mitigasi mitigasi = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM "+TABLE+" WHERE ID_mitigasi=?;");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				mitigasi = new Mitigasi(
					rs.getDate("tanggal").toLocalDate(),
					rs.getString("nama_mitigasi"),
					rs.getString("prioritas"),
					rs.getString("file_dir"),
					rs.getInt("ID_VA"),
					rs.getString("NIP")
				);
				mitigasi.setID(rs.getString("ID_mitigasi"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mitigasi;
	}

	@Override
	public void update(Mitigasi mitigasi) {
		try {
			ps = conn.prepareStatement("UPDATE "+TABLE+" SET nama_mitigasi=?, prioritas=?, dokumentasi=?, file_dir=? WHERE ID_mitigasi=? AND NIP=?;");
			ps.setString(1, mitigasi.getNamaMitigasi());
			ps.setString(2, mitigasi.getPrioritas());
			ps.setBinaryStream(3, new FileInputStream(mitigasi.getDokumentasi()));
			ps.setString(4, mitigasi.getFileDir());
			ps.setString(5, mitigasi.getID_mitigasi());
			ps.setString(6, mitigasi.getNIP());
			ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Mitigasi mitigasi) {
		try {
			ps = conn.prepareStatement("DELETE FROM "+TABLE+" WHERE ID_mitigasi=? AND NIP=?;");
			ps.setInt(1, Integer.parseInt(mitigasi.getID_mitigasi()));
			ps.setString(2, mitigasi.getNIP());
			ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
