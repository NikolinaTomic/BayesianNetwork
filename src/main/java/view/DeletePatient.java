package view;

import app.MyApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeletePatient {
    String jmbg = "";

    public DeletePatient(String jmbg) {
        this.jmbg = jmbg;
    }

    public void deletePatient() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inzenjering?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", MyApp.username, MyApp.password);
            String sql = "DELETE FROM Karton WHERE JMBG = '" + jmbg + "';";
            String sql2 = "DELETE FROM IP WHERE JMBG = '" + jmbg + "';";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
            pstmt.executeUpdate();
            pstmt2.executeUpdate();
            pstmt.close();
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    public void deletePatientsHospitalRecord() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inzenjering?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", MyApp.username, MyApp.password);
            String sql = "DELETE FROM Karton WHERE JMBG = '" + jmbg + "';";
            String sql2 = "DELETE FROM IP WHERE JMBG = '" + jmbg + "';";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
            pstmt.executeUpdate();
            pstmt2.executeUpdate();
            pstmt.close();
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }
}
