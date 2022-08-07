package javakominfo.backend.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
  private final String URL = "jdbc:mysql://localhost:3306/javakominfo";
  private final String USER = "root";
  private final String PASS = "";

  public Connection connect() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      return DriverManager.getConnection(URL, USER, PASS);
    } catch(ClassNotFoundException | SQLException ex) {
      ex.printStackTrace();
    }
    return null;
  }
}
