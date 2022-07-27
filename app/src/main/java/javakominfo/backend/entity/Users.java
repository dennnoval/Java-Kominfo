package javakominfo.backend.entity;

import java.io.Serializable;
import java.sql.Date;

public class Users implements Serializable {

	private String username;
	private String password;
	private String email;
	private Role role;
	private Date createdAt;
  
  public Users() {}

	public Users(String u, String p, String e, String r) {
		username = u;
		password = p;
		email = e;
		role = r.equals("ADMIN") ? Role.ADMIN : Role.KARYAWAN;
		createdAt = new Date(System.currentTimeMillis());
	}

	public Users(String u, String p, String e, String r, Date ca) {
		username = u;
		password = p;
		email = e;
		role = r.equals("ADMIN") ? Role.ADMIN : Role.KARYAWAN;
		createdAt = ca;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public Role getRole() {
		return role;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

}
