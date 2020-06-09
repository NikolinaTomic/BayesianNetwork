package view;

import app.MyApp;
import model.ExaminationHistory;
import model.Patient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddData {

    public JFrame mainF = new JFrame("Ophthalmology");
    private ExaminationHistory ip = new ExaminationHistory();
    private String notes = "";

    public AddData(String checkedSymptoms, String jmbg, String diagnose, String treatment, Patient o) {

        String finalT = treatment;

        JPanel main = new JPanel();
        JButton cancel = new JButton("Cancel");
        JButton ok = new JButton("Save");
        JPanel pan = new JPanel();
        pan.setLayout(new FlowLayout());
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        JPanel p3 = new JPanel();
        p3.setLayout(new FlowLayout());
        JPanel p4 = new JPanel();
        p4.setLayout(new FlowLayout());
        JPanel p5 = new JPanel();
        p5.setLayout(new FlowLayout());
        JLabel nb = new JLabel("Symptoms:             ");
        JTextArea nbT = new JTextArea();
        nbT.setEditable(false);
        nbT.setText(checkedSymptoms);
        nbT.setPreferredSize(new Dimension(400, 50));
        nbT.setLineWrap(true);


        JLabel tr = new JLabel("Treatment:               ");
        JTextArea trT = new JTextArea();

        trT.setPreferredSize(new Dimension(400, 50));
        trT.setText(finalT);
        trT.setLineWrap(true);

        JLabel dn = new JLabel("Additional notes:      ");

        JTextArea dnT = new JTextArea();
        dnT.setLineWrap(true);
        dnT.setPreferredSize(new Dimension(400, 250));

        JLabel disease = new JLabel("Diagnosis:              ");
        JTextArea diseaseT = new JTextArea(diagnose);
        diseaseT.setPreferredSize(new Dimension(400, 30));
        diseaseT.setLineWrap(true);

        JLabel dok = new JLabel("Doctor:                    ");
        String[] dokNiz = {"Nema", "Srdjan", "Milica", "Ivan", "Nina"};
        JComboBox doctors = new JComboBox(dokNiz);

        p1.add(nb);
        p1.add(nbT);
        p2.add(tr);
        p2.add(trT);
        p3.add(dn);
        p3.add(dnT);
        p5.add(disease);
        p5.add(diseaseT);
        p4.add(dok);
        p4.add(doctors);

        JLabel lab1 = new JLabel("                  ");
        JLabel lab2 = new JLabel("                   ");
        pan.add(lab1);
        pan.add(cancel);
        pan.add(ok);

        main.add(lab1);
        main.add(lab2);

        ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (doctors.getSelectedItem().equals("")) {
                    JOptionPane.showMessageDialog(null, "You must enter doctor's name!");
                    doctors.setBackground(Color.RED);
                } else {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();

                    ip.setDate(dtf.format(now));
                    ip.setSymptoms(nbT.getText());
                    ip.setAdditionalNotes(dnT.getText());
                    ip.setDoctor((String) doctors.getSelectedItem());
                    ip.setTreatment(trT.getText());
                    ip.setDisease(diseaseT.getText());

                    Connection conn;
                    try {
                        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inzenjering?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", MyApp.username, MyApp.password);

                        String sql2 = "insert into IP (JMBG,Simptomi,Tretman,Doktor,Datum,DN,Disease) values (?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement pstmt2 = conn.prepareStatement(sql2);
                        pstmt2.setString(1, jmbg);
                        pstmt2.setString(2, ip.getSymptoms());
                        pstmt2.setString(3, ip.getTreatment());
                        pstmt2.setString(4, ip.getDoctor());
                        pstmt2.setString(5, ip.getDate());
                        pstmt2.setString(6, ip.getAdditionalNotes());
                        pstmt2.setString(7, ip.getDisease());

                        pstmt2.executeUpdate();
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                    mainF.dispose();
                    try {
                        MainFrame mf = new MainFrame();
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        });

        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                mainF.dispose();
                try {
                    MainFrame mf = new MainFrame();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        main.add(p1);
        main.add(p2);
        main.add(p3);
        main.add(p5);
        main.add(p4);
        main.add(pan);
        mainF.setSize(800, 560);
        mainF.add(main);
        mainF.setVisible(true);
    }

}
