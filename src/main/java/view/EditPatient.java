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
import java.util.Vector;

public class EditPatient {
    private JFrame mainF = new JFrame("Ophthalmology");

    public EditPatient(Vector<String> v) throws IOException {
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
        JLabel label = new JLabel("                                                                                Patient's card"
                + "                                            "
                + "                                            "
                + "                                              "
                + "                                               "
                + "                                                 "
                + "                                                "
                + ""
                + ""
                + "");
        label.setBorder(new LineBorder(Color.DARK_GRAY));
        panel.add(label, BorderLayout.NORTH);
        //----------------------------------------------//
        JLabel name = new JLabel("Name:      ");
        final JTextField nameT = new JTextField();
        nameT.setText(v.get(0));
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
        surnameT.setText(v.get(1));
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        p2.add(surname);
        p2.add(surnameT);
        //*********************************************//
        JLabel address = new JLabel("Address:  ");
        final JTextField addressT = new JTextField();
        addressT.setText(v.get(3));
        addressT.setPreferredSize(new Dimension(100, 20));
        JPanel p3 = new JPanel();
        p3.setLayout(new FlowLayout());
        p3.add(address);
        p3.add(addressT);
        //*********************************************//
        JLabel jmbg = new JLabel("JMBG:   ");
        final JTextField jmbgT = new JTextField();
        jmbgT.setEditable(false);
        jmbgT.setText(v.get(7));
        jmbgT.setPreferredSize(new Dimension(100, 20));
        JPanel p4 = new JPanel();
        p4.setLayout(new FlowLayout());
        p4.add(jmbg);
        p4.add(jmbgT);
        //*********************************************//
        JLabel il = new JLabel("Subscribed doctor:");
        String[] dok = {"Nema", "Srdjan", "Milica", "Ivan", "Nina"};
        final JComboBox doctors = new JComboBox(dok);
        doctors.setSelectedItem((String) v.get(6));
        JPanel p5 = new JPanel();
        p5.setLayout(new FlowLayout());
        p5.add(il);
        p5.add(doctors);
        //*********************************************//
        JLabel tel = new JLabel("Phone:   ");
        final JTextField telT = new JTextField();
        telT.setText(v.get(4));
        telT.setPreferredSize(new Dimension(100, 20));
        JPanel p6 = new JPanel();
        p6.setLayout(new FlowLayout());
        p6.add(tel);
        p6.add(telT);
        //*********************************************//
        JLabel mail = new JLabel("E-mail:    ");
        final JTextField mailT = new JTextField();
        mailT.setText(v.get(5));
        mailT.setPreferredSize(new Dimension(100, 20));
        JPanel p7 = new JPanel();
        p7.setLayout(new FlowLayout());
        p7.add(mail);
        p7.add(mailT);
        //*********************************************//
        JLabel god = new JLabel("Age:");
        ArrayList<String> years_tmp = new ArrayList<String>();
        for (int years = 1; years < 110; years++) {
            years_tmp.add(years + "");
        }
        final JComboBox jComboBox1 = new JComboBox(years_tmp.toArray());
        jComboBox1.setSelectedItem((String) v.get(2));
        JPanel p8 = new JPanel();
        p8.setLayout(new FlowLayout());
        p8.add(god);
        p8.add(jComboBox1);
        //*********************************************//
        JLabel race = new JLabel("Race:");
        final JRadioButton b1 = new JRadioButton("Black");
        final JRadioButton b2 = new JRadioButton("White");
        final JRadioButton b3 = new JRadioButton("Hispanic");
        final JRadioButton b4 = new JRadioButton("Other");
        ButtonGroup bg = new ButtonGroup();
        bg.add(b1);
        bg.add(b2);
        bg.add(b3);
        bg.add(b4);
        if (v.get(9).equals("Black"))
            b1.setSelected(true);
        if (v.get(9).equals("White"))
            b2.setSelected(true);
        if (v.get(9).equals("Hispanic"))
            b3.setSelected(true);
        if (v.get(9).equals("Other"))
            b4.setSelected(true);

        JPanel p9 = new JPanel();
        p9.setLayout(new FlowLayout());
        p9.add(race);
        p9.add(b1);
        p9.add(b2);
        p9.add(b3);
        p9.add(b4);
        //*********************************************//
        JLabel pol = new JLabel("Gender:");
        final JRadioButton b11 = new JRadioButton("Male");
        final JRadioButton b22 = new JRadioButton("Female");
        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(b11);
        bg2.add(b22);
        if (v.get(8).equals("Male"))
            b11.setSelected(true);
        if (v.get(8).equals("Female"))
            b22.setSelected(true);

        JPanel p10 = new JPanel();
        p10.setLayout(new FlowLayout());
        p10.add(pol);
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
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inzenjering?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", MyApp.username, MyApp.password);

                        String rasa = "White";
                        String pol = "Male";
                        if (b1.isSelected())
                            rasa = "Black";
                        if (b2.isSelected())
                            rasa = "White";
                        if (b3.isSelected())
                            rasa = "Hispanic";
                        if (b4.isSelected())
                            rasa = "Other";
                        if (b11.isSelected())
                            pol = "Male";
                        if (b22.isSelected())
                            pol = "Female";
                        String sql = "update Karton set Ime='" + nameT.getText() + "',Prezime='" + surnameT.getText() + "',Godina='" + (String) jComboBox1.getSelectedItem() + "',Adresa='" + addressT.getText() + "',Telefon='" + telT.getText() + "',Mail='" + mailT.getText() + "',zaduzeniLekar='" + (String) doctors.getSelectedItem() + "',Pol='" + pol + "',Rasa='" + rasa + "' where JMBG='" + jmbgT.getText() + "'";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        int updated = pstmt.executeUpdate();

                        pstmt.close();

                        mainF.dispose();

                    } catch (Exception ee) {
                        ee.printStackTrace();
                    }
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
