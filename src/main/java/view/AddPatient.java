package view;

import app.MyApp;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddPatient {
    private JFrame mainF = new JFrame("Ophthalmology");

    public AddPatient() throws IOException {
        JPanel left = new JPanel();

        JButton cancel = new JButton("Cancel");
        JButton save = new JButton("Save");

        JPanel dug = new JPanel();
        dug.setLayout(new FlowLayout());

        dug.add(cancel);
        dug.add(save);
        JPanel buttons = new JPanel();
        buttons.setLayout(new BorderLayout());
        buttons.add(dug, BorderLayout.EAST);

        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(buttons, BorderLayout.SOUTH);
        JLabel labela = new JLabel("                                                                                Pacient's card"
                + "                                            "
                + "                                            "
                + "                                              "
                + "                                               "
                + "                                                 "
                + "                                                "
                + ""
                + ""
                + "");
        labela.setBorder(new LineBorder(Color.DARK_GRAY));
        panel.add(labela, BorderLayout.NORTH);
        //----------------------------------------------//
        JLabel name = new JLabel("Name:      ");
        final JTextField nameT = new JTextField();
        nameT.setSize(20, 50);
        nameT.setPreferredSize(new Dimension(100, 20));
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        p1.add(name);
        p1.add(nameT);
        //*********************************************//
        JLabel surname = new JLabel("Last name:");
        final JTextField surnameT = new JTextField();
        surnameT.setPreferredSize(new Dimension(100, 20));
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        p2.add(surname);
        p2.add(surnameT);
        //*********************************************//
        JLabel address = new JLabel("Address:   ");
        final JTextField addressT = new JTextField();
        addressT.setPreferredSize(new Dimension(100, 20));
        JPanel p3 = new JPanel();
        p3.setLayout(new FlowLayout());
        p3.add(address);
        p3.add(addressT);
        //*********************************************//
        JLabel jmbg = new JLabel("JMBG:      ");
        final JTextField jmbgT = new JTextField();
        jmbgT.setPreferredSize(new Dimension(100, 20));
        JPanel p4 = new JPanel();
        p4.setLayout(new FlowLayout());
        p4.add(jmbg);
        p4.add(jmbgT);
        //*********************************************//
        JLabel il = new JLabel("Subscribed doctor:");
        String[] dok = {"Nema", "Srdjan", "Milica", "Ivan", "Nina"};
        final JComboBox doktori = new JComboBox(dok);
        JPanel p5 = new JPanel();
        p5.setLayout(new FlowLayout());
        p5.add(il);
        p5.add(doktori);
        //*********************************************//
        JLabel tel = new JLabel("Phone:      ");
        final JTextField telT = new JTextField();
        telT.setPreferredSize(new Dimension(100, 20));
        JPanel p6 = new JPanel();
        p6.setLayout(new FlowLayout());
        p6.add(tel);
        p6.add(telT);
        //*********************************************//
        JLabel mail = new JLabel("E-mail:      ");
        final JTextField mailT = new JTextField();
        mailT.setPreferredSize(new Dimension(100, 20));
        JPanel p7 = new JPanel();
        p7.setLayout(new FlowLayout());
        p7.add(mail);
        p7.add(mailT);
        //*********************************************//
        JLabel god = new JLabel("Age:");
        ArrayList<String> years_tmp = new ArrayList<String>();
        for (int years = 0; years < 110; years++) {
            years_tmp.add(years + "");
        }
        final JComboBox jComboBox1 = new JComboBox(years_tmp.toArray());

        JPanel p8 = new JPanel();
        p8.setLayout(new FlowLayout());
        p8.add(god);
        p8.add(jComboBox1);

        //*********************************************//
        JLabel rasa = new JLabel("          Race:");
        final JRadioButton b1 = new JRadioButton("Black");
        final JRadioButton b2 = new JRadioButton("White");
        final JRadioButton b3 = new JRadioButton("Hispanic");
        final JRadioButton b4 = new JRadioButton("Other");
        ButtonGroup bg = new ButtonGroup();
        bg.add(b1);
        bg.add(b2);
        bg.add(b3);
        bg.add(b4);

        JPanel p9 = new JPanel();
        p9.setLayout(new FlowLayout());
        p9.add(rasa);
        p9.add(b1);
        p9.add(b2);
        p9.add(b3);
        p9.add(b4);
        //*********************************************//
        JLabel gender = new JLabel("Gender:");
        final JRadioButton b11 = new JRadioButton("Male");
        final JRadioButton b22 = new JRadioButton("Female");
        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(b11);
        bg2.add(b22);

        JPanel p10 = new JPanel();
        p10.setLayout(new FlowLayout());
        p10.add(gender);
        p10.add(b11);
        p10.add(b22);


        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


                // TODO Auto-generated method stub
                if (jmbgT.getText().equals("") || (!b1.isSelected() && !b2.isSelected() && !b3.isSelected() && !b4.isSelected()) || (!b11.isSelected() && !b22.isSelected())) {
                    JOptionPane.showMessageDialog(null, "JMBG, Race and Gender must not be empty");
                    jmbgT.setBackground(Color.RED);
                } else {

                    try {
                        //otvaranje konekcije
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inzenjering?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", MyApp.username, MyApp.password);
                        String poll = "";
                        String ss = "";
                        if (b1.isSelected())
                            ss = "Black";
                        if (b2.isSelected())
                            ss = "White";
                        if (b3.isSelected())
                            ss = "Hispanic";
                        if (b4.isSelected())
                            ss = "Other";
                        if (b11.isSelected())
                            poll = "Male";
                        if (b22.isSelected())
                            poll = "Female";
                        String sql = "insert into Karton (Ime, Prezime,Godina,Adresa,Telefon,Mail,zaduzeniLekar,JMBG,Pol,Rasa) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                        PreparedStatement pstmt = conn.prepareStatement(sql);

                        pstmt.setString(1, nameT.getText());
                        pstmt.setString(2, surnameT.getText());
                        pstmt.setString(3, (String) jComboBox1.getSelectedItem());
                        pstmt.setString(4, addressT.getText());
                        pstmt.setString(5, telT.getText());
                        pstmt.setString(6, mailT.getText());
                        pstmt.setString(7, (String) doktori.getSelectedItem());
                        pstmt.setString(8, jmbgT.getText());
                        if (b1.isSelected())
                            pstmt.setString(10, "Black");
                        if (b2.isSelected())
                            pstmt.setString(10, "White");
                        if (b3.isSelected())
                            pstmt.setString(10, "Hispanic");
                        if (b4.isSelected())
                            pstmt.setString(10, "Other");
                        if (b11.isSelected())
                            pstmt.setString(9, "Male");
                        if (b22.isSelected())
                            pstmt.setString(9, "Female");

                        //izvrsavanje upita
                        int updated = pstmt.executeUpdate();
                        System.out.println("Dodato " + updated + " redova.");

                        pstmt.close();
                    } catch (Exception ee) {
                        JOptionPane.showInternalMessageDialog(null, "JMBG already exists");
                        mainF.revalidate();
                        mainF.repaint();
                        return;
                    }
                    try {
                        MainFrame mf = new MainFrame();
                        mainF.dispose();
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    mainF.dispose();


                }
            }

        });

        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    MainFrame mf = new MainFrame();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                mainF.dispose();
            }
        });

        left.add(p1);
        left.add(p2);
        left.add(p3);
        left.add(p4);
        left.add(p5);
        left.add(p6);
        left.add(p7);
        left.add(p8);
        left.add(p9);
        left.add(p10);
        panel.add(left, BorderLayout.WEST);

        mainF.setSize(800, 600);
        mainF.setLocationRelativeTo(null);
        mainF.add(panel);
        mainF.setVisible(true);
    }

}
