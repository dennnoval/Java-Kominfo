package javakominfo.backend.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
  // private final String DB_PATH = getClass().getResource("/javakominfo/backend/h2db").getPath();
  // private final String URL1 = "jdbc:h2:"+DB_PATH+"/javakominfo";
  // private final String URL2 = "jdbc:h2:tcp://localhost:9092/~/Desktop/h2-db/javakominfo";
  private final String URL1 = "jdbc:mysql://localhost:3306/javakominfo";
  private final String USER = "root";
  private final String PASS = "";

  private final String ONLINE_URL = "jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6484711";
  private final String ONLINE_USER = "sql6484711";
  private final String ONLINE_PASS = "NTGwF25Wuz";

  public Connection connect() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      return DriverManager.getConnection(URL1, USER, PASS); // for offline use
      // return DriverManager.getConnection(ONLINE_URL, ONLINE_USER, ONLINE_PASS); // for online use
    } catch(ClassNotFoundException | SQLException ex) {
      ex.printStackTrace();
    }
    return null;
  }
}
