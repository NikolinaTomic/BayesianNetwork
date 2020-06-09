package view;

import app.MyApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MedicalRecord {
    public JFrame mainF = new JFrame("Medical record");

    public MedicalRecord(String symptoms, String treatment, String doctor, String date, String diagnosis, String notes, final String id) {
        JPanel main = new JPanel();

        JButton cancel = new JButton("Cancel");
        JButton done = new JButton("Done");

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
        JPanel p6 = new JPanel();
        p6.setLayout(new FlowLayout());
        JLabel nb = new JLabel("Symptoms:             ");
        final JTextArea nbT = new JTextArea();
        nbT.setText(symptoms);
        nbT.setPreferredSize(new Dimension(400, 50));
        nbT.setLineWrap(true);

        JLabel tr = new JLabel("Treatment:                ");
        final JTextArea trT = new JTextArea();
        trT.setLineWrap(true);
        trT.setPreferredSize(new Dimension(400, 50));
        trT.setText(treatment);
        JLabel dn = new JLabel("Additional notes:     ");
        final JTextArea dnT = new JTextArea(notes);
        dnT.setLineWrap(true);
        dnT.setPreferredSize(new Dimension(400, 250));
        JLabel dok = new JLabel("Doctor:                    ");
        final JTextArea doktor = new JTextArea(doctor);
        doktor.setEditable(false);
        JLabel datum = new JLabel("Date:                     ");
        final JTextArea datumT = new JTextArea(date);
        datumT.setPreferredSize(new Dimension(400, 30));
        datumT.setEditable(false);
        JLabel disease = new JLabel("Diagnosis:              ");
        final JTextArea diseaseT = new JTextArea(diagnosis);
        diseaseT.setPreferredSize(new Dimension(400, 30));
        diseaseT.setLineWrap(true);
        p5.add(datum);
        p5.add(datumT);
        p6.add(disease);
        p6.add(diseaseT);
        p1.add(nb);
        p1.add(nbT);
        p2.add(tr);
        p2.add(trT);
        p3.add(dn);
        p3.add(dnT);
        p4.add(dok);
        p4.add(doktor);

        JLabel lab1 = new JLabel("                  ");
        JLabel lab2 = new JLabel("                   ");
        pan.add(lab1);
        pan.add(cancel);
        pan.add(done);

        main.add(lab1);
        main.add(lab2);

        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                mainF.dispose();
            }
        });


        done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inzenjering?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", MyApp.username, MyApp.password);

                    String sql = "update IP set Simptomi='" + nbT.getText() + "',Tretman='" + trT.getText() + "',Doktor='" + doktor.getText() + "',Datum='" + datumT.getText() + "',DN='" + dnT.getText() + "',Disease='" + diseaseT.getText() + "' where IdPregleda='" + id + "'";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    int updated = pstmt.executeUpdate();

                    pstmt.close();

                    mainF.dispose();

                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        });

        main.add(p5);
        main.add(p6);
        main.add(p1);
        main.add(p2);
        main.add(p3);
        main.add(p4);
        main.add(pan);
        mainF.setSize(800, 560);
        mainF.add(main);
        mainF.setVisible(true);
    }
}
