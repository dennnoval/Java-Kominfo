package javakominfo;

import javakominfo.backend.database.DB;
import javakominfo.backend.entity.Mitigasi;
import javakominfo.backend.repository.MitigasiRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;

public class MitigasiRepoTest {

  /*@Test
  void createTest() {
    MitigasiRepo repo = new MitigasiRepo();
    Mitigasi mitigasi = new Mitigasi(
      LocalDate.parse("2012-04-18"),
      "Mitigasi parameter tampering",
      "high",
      "/Users/dennnoval/Desktop/StressTest2_Kominfo.pdf",
      26,
      "003"
    );
    boolean isInserted = repo.create(mitigasi);
    Assertions.assertTrue(isInserted);
  }*/

  /*@Test
  void readTest() {
    MitigasiRepo repo = new MitigasiRepo();
    List<Mitigasi> mits = repo.read();
    mits.forEach(System.out::println);
    Assertions.assertNotNull(mits);
  }*/

  /*@Test
  void readByIdTest() {
    MitigasiRepo repo = new MitigasiRepo();
    Mitigasi mit = repo.readById("5");
    System.out.println(mit);
    Assertions.assertNotNull(mit);
  }*/

  /*@Test
  void updateTest() {
    MitigasiRepo repo = new MitigasiRepo();
    Mitigasi mit = new Mitigasi(
      LocalDate.parse("2012-04-15"),
      "Mitigasi CSRF",
      "low",
      "/Users/dennnoval/Desktop/StressTest2_Kominfo.pdf",
      26,
      "003"
    );
    mit.setID_mitigasi(5);
    repo.update(mit);
  }*/

  /*@Test
  void deleteTest() {
    MitigasiRepo repo = new MitigasiRepo();
    Mitigasi mit = new Mitigasi(
      LocalDate.parse("2012-04-15"),
      "Mitigasi CSRF",
      "low",
      "/Users/dennnoval/Desktop/StressTest2_Kominfo.pdf",
      26,
      "002"
    );
    mit.setID_mitigasi(4);
    repo.delete(mit);
  }*/

}
