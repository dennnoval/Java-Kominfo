package javakominfo.backend.utility;

import javakominfo.backend.database.DB;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;

public class ReportUtil {

	private final Connection conn;

	public ReportUtil() {
		conn = new DB().connect();
	}

  public void printReport(InputStream fileStream) {
    try {
      HashMap<String, Object> parameter = new HashMap<>();
      JasperReport jasperReport = JasperCompileManager.compileReport(fileStream);
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, conn);
      JasperViewer.viewReport(jasperPrint, false);
      JasperViewer.setDefaultLookAndFeelDecorated(true);
    } catch(Exception | ExceptionInInitializerError e){
      e.printStackTrace();
    }
  }
	
}