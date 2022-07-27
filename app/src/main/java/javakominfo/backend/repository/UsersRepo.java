package javakominfo.backend.repository;

import javakominfo.backend.database.DB;
import javakominfo.backend.entity.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsersRepo implements CRUD<Users> {

	private final Connection conn;
	private PreparedStatement ps;
	private final String TABLE = "Users";

	public UsersRepo() {
		conn = new DB().connect();
	}

	@Override
	public boolean create(Users u) {
		try {
			ps = conn.prepareStatement("INSERT INTO "+TABLE+" VALUES(?, ?, ?, ?, ?);");
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getRole().toString());
			ps.setDate(5, u.getCreatedAt());
			ps.execute();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Users> read() {
		List<Users> users = new ArrayList<>();
		try {
			ps = conn.prepareStatement("SELECT * FROM "+TABLE+";");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Users usr = new Users(
					rs.getString("username"), 
					rs.getString("password"),
					rs.getString("email"),
					rs.getString("role"),
					rs.getDate("createdAt")
				);
				users.add(usr);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public Users readById(String id) {
		Users user = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM "+TABLE+" WHERE username=?;");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user = new Users(
					rs.getString("username"), 
					rs.getString("password"),
					rs.getString("email"),
					rs.getString("role")
				);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void update(Users u) {
		try {
			ps = conn.prepareStatement("UPDATE "+TABLE+" SET password=?, email=?, role=? WHERE username=?;");
			ps.setString(1, u.getPassword());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getRole().toString());
			ps.setString(4, u.getUsername());
			ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Users u) {
		try {
			ps = conn.prepareStatement("DELETE FROM "+TABLE+" WHERE username=?;");
			ps.setString(1, u.getUsername());
			ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public Users readByUsernameAndPassword(String username, String password) {
		Users user = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM "+TABLE+" WHERE username=? AND password=?;");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user = new Users(
					rs.getString("username"),
					rs.getString("password"),
					rs.getString("email"),
					rs.getString("role")
				);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}

}
