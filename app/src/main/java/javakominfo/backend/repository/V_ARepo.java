package javakominfo.backend.repository;

import javakominfo.backend.database.DB;
import javakominfo.backend.entity.V_A;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class V_ARepo implements CRUD<V_A> {

	private final Connection conn;
	private PreparedStatement ps;
	private final String TABLE = "V_A";

	public V_ARepo() {
		conn = new DB().connect();
	}

	@Override
	public void create(V_A v_a) {
		try {
			ps = conn.prepareStatement("INSERT INTO "+TABLE+" (tanggal, NIP, nama_va, domain, file, file_dir) VALUES(?, ?, ?, ?, ?)");
			ps.setDate(1, java.sql.Date.valueOf(v_a.getTanggal()));
			ps.setString(2, v_a.getNIP());
			ps.setString(3, v_a.getNamaV_A());
			ps.setString(4, v_a.getDomain());
			ps.setBinaryStream(5, new FileInputStream(v_a.getFile()));
			ps.setString(6, v_a.getFileDir());
			ps.execute();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<V_A> read() {
		List<V_A> v_as = new ArrayList<>();
		try {
			ps = conn.prepareStatement("SELECT * FROM "+TABLE+";");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				V_A v_a = new V_A(
					rs.getDate("tanggal").toLocalDate(),
					rs.getString("NIP"),
					rs.getString("nama_va"),
					rs.getString("domain"),
					rs.getString("file_dir")
				);
				v_a.setID(rs.getString("ID"));
				v_as.add(v_a);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return v_as;
	}

	@Override
	public V_A readById(String id) {
		V_A v_a = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM "+TABLE+" WHERE ID=?;");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				v_a = new V_A(
					rs.getDate("tanggal").toLocalDate(),
					rs.getString("NIP"),
					rs.getString("nama_va"),
					rs.getString("domain"),
					rs.getString("file_dir")
				);
				v_a.setID(rs.getString("ID"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return v_a;
	}

	@Override
	public void update(V_A v_a) {
		try {
			ps = conn.prepareStatement("UPDATE "+TABLE+" SET nama_va=?, domain=?, file=?, file_dir=? WHERE ID=? AND NIP=?;");
			ps.setString(1, v_a.getNamaV_A());
			ps.setString(2, v_a.getDomain());
			ps.setBinaryStream(3, new FileInputStream(v_a.getFile()));
			ps.setString(4, v_a.getFileDir());
			ps.setString(5, v_a.getID());
			ps.setString(6, v_a.getNIP());
			ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(V_A v_a) {
		try {
			ps = conn.prepareStatement("DELETE FROM "+TABLE+" WHERE ID=? AND NIP=?;");
			ps.setString(1, v_a.getID());
			ps.setString(2, v_a.getNIP());
			ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
