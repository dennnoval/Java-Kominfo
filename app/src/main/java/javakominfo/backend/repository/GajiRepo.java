package javakominfo.backend.repository;

import javakominfo.backend.database.DB;
import javakominfo.backend.entity.Gaji;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GajiRepo implements CRUD<Gaji> {

	private final Connection conn;
	private PreparedStatement ps;
	private final String TABLE = "Gaji";

	public GajiRepo() {
		conn = new DB().connect();
	}

	@Override
	public void create(Gaji g) {
		try {
			ps = conn.prepareStatement("INSERT INTO "+TABLE+" VALUES(?, ?, ?, ?, ?, ?, ?);");
			ps.setString(1, g.getNIP());
			ps.setString(2, g.getNama());
			ps.setString(3, String.valueOf(g.getGolongan()));
			ps.setInt(4, g.getGapok());
			ps.setInt(5, g.getTransport());
			ps.setInt(6, g.getPulsa());
			ps.setInt(7, g.getTotalGaji());
			ps.execute();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Gaji> read() {
		List<Gaji> gajis = new ArrayList<>();
		try {
			ps = conn.prepareStatement("SELECT * FROM "+TABLE+";");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Gaji gaji = new Gaji(
					rs.getString("NIP"), 
					rs.getString("nama"),
					rs.getString("golongan").charAt(0),
					rs.getInt("gapok"),
					rs.getInt("transport"),
					rs.getInt("pulsa")
				);
				gajis.add(gaji);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return gajis;
	}

	@Override
	public Gaji readById(String id) {
		Gaji gaji = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM "+TABLE+" WHERE NIP=?;");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				gaji = new Gaji(
					rs.getString("NIP"), 
					rs.getString("nama"),
					rs.getString("golongan").charAt(0),
					rs.getInt("gapok"),
					rs.getInt("transport"),
					rs.getInt("pulsa")
				);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return gaji;
	}

	@Override
	public void update(Gaji g) {
		try {
			ps = conn.prepareStatement("UPDATE "+TABLE+" SET nama=?, golongan=?, gapok=?, transport=?, pulsa=?, total_gaji=? WHERE NIP=?;");
			ps.setString(1, g.getNama());
			ps.setString(2, String.valueOf(g.getGolongan()));
			ps.setInt(3, g.getGapok());
			ps.setInt(4, g.getTransport());
			ps.setInt(5, g.getPulsa());
			ps.setInt(6, g.getTotalGaji());
			ps.setString(7, g.getNIP());
			ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Gaji g) {
		try {
			ps = conn.prepareStatement("DELETE FROM "+TABLE+" WHERE NIP=?;");
			ps.setString(1, g.getNIP());
			ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public List<Gaji> readBy(String sq) {
		List<Gaji> gajis = new ArrayList<>();
		try {
			String sql = "SELECT * FROM "+TABLE+" WHERE NIP LIKE '%"+sq+"%' " +
				" OR nama LIKE '%"+sq+"%' OR golongan LIKE '%"+sq+"%' OR gapok LIKE '%"+sq+"%';";
			java.sql.Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				Gaji gaji = new Gaji(
					rs.getString("NIP"),
					rs.getString("nama"),
					rs.getString("golongan").charAt(0),
					rs.getInt("gapok"),
					rs.getInt("transport"),
					rs.getInt("pulsa")
				);
				gajis.add(gaji);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return gajis;
	}

}
