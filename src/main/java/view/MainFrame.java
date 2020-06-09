package view;

import app.MyApp;
import model.Patient;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;


public class MainFrame extends JFrame {
    public JFrame mainFr = new JFrame("Ophthalmology");
    private int sel = -500;

    public MainFrame() throws SQLException {
        JPanel gl = new JPanel();
        gl.setLayout(new BoxLayout(gl, BoxLayout.Y_AXIS));
        final JPanel patient = new JPanel(new BorderLayout());
        patient.setName("Patients");

        String[] columnNames = {
                "Name", "Last name", "Age", "Address", "Phone", "Mail", "Subscribed doctor", "JMBG", "Gender", "Race"
        };

        String sql = "SELECT * FROM Karton";

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inzenjering?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", MyApp.username, MyApp.password);

        PreparedStatement st = conn.prepareStatement(sql);
        DefaultTableModel dtm = new DefaultTableModel(columnNames, 0);
        final JTable table = new JTable(dtm);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            String name = rs.getString("Ime");
            String surname = rs.getString("Prezime");
            String age = rs.getString("Godina");
            String address = rs.getString("Adresa");
            String tel = rs.getString("Telefon");
            String mail = rs.getString("Mail");
            String doctor = rs.getString("zaduzeniLekar");
            String jmbg = rs.getString("JMBG");
            String gender = rs.getString("Pol");
            String race = rs.getString("Rasa");

            dtm.addRow(new Object[]{name, surname, age, address, tel, mail, doctor, jmbg, gender, race});
        }

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                sel = table.getSelectedRow();
            }
        });
        table.setDefaultEditor(Object.class, null);
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        JButton user = new JButton("Add Patient       ", getImage("src/main/resources/user.png"));

        user.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                try {
                    AddPatient pp = new AddPatient();
                    mainFr.dispose();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        JButton userR = new JButton("Remove Patient", getImage("src/main/resources/userR.png"));
        JButton userE = new JButton("Edit Patient       ", getImage("src/main/resources/edit.png"));
        userE.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                if (sel == -500) {
                    JOptionPane.showMessageDialog(null,
                            "Select user first!");
                } else {
                    Vector<String> vektor = (Vector<String>) ((DefaultTableModel) table.getModel()).getDataVector().elementAt(table.getSelectedRow());
                    try {
                        EditPatient iw = new EditPatient(vektor);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    mainFr.dispose();
                }
            }
        });

        userR.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (sel == -500) {
                    JOptionPane.showMessageDialog(null,
                            "Select user first!");
                } else {
                    String podatak = (String) table.getValueAt(sel, getColumnByName(table, "JMBG"));
                    DeletePatient du = new DeletePatient(podatak);
                    du.deletePatient();
                    if (JOptionPane.showConfirmDialog(null, "Delete this user?") == JOptionPane.YES_OPTION) {
                        du.deletePatientsHospitalRecord();

                        ((DefaultTableModel) table.getModel()).removeRow(sel);
                        table.repaint();
                        table.revalidate();
                        patient.repaint();
                        patient.revalidate();
                    }
                }
            }
        });

        JButton symptoms = new JButton("Add symptoms ", getImage("src/main/resources/symptoms.png"));
        symptoms.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                if (sel == -500) {
                    JOptionPane.showMessageDialog(null,
                            "Select user first!");
                } else {
                    Vector<String> selectedRow = (Vector<String>) ((DefaultTableModel) table.getModel()).getDataVector().elementAt(table.getSelectedRow());

                    String godString = selectedRow.get(2);
                    int god = Integer.parseInt(godString);
                    String race = selectedRow.get(9);
                    String gender = selectedRow.get(8);
                    Patient o = new Patient();
                    o.setAge(god);
                    o.setRace(race);
                    o.setGender(gender);
                    o.setJmbg((String) table.getValueAt(sel, getColumnByName(table, "JMBG")));
                    SelectSymptoms.selected = new ArrayList<String>();
                    SelectSymptoms mw = new SelectSymptoms(o);
                    mainFr.dispose();
                }
            }
        });

        JButton history = new JButton(" Medical record ", getImage("src/main/resources/history.png"));
        history.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (sel == -500) {
                    JOptionPane.showMessageDialog(null,
                            "Select user first!");
                } else {
                    ExaminationHistoryView iw = new ExaminationHistoryView();
                    try {
                        iw.draw((String) table.getValueAt(sel, getColumnByName(table, "JMBG")));
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        });

        gl.add(user);
        gl.add(userR);
        gl.add(userE);
        gl.add(symptoms);
        gl.add(history);

        patient.add(gl, BorderLayout.WEST);
        patient.add(scrollPane, BorderLayout.CENTER);

        mainFr.setBounds(0, 0, 1200, 700);
        mainFr.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFr.setLocationRelativeTo(null);
        mainFr.add(patient);
        mainFr.setVisible(true);
    }

    public static ImageIcon getImage(String path) {
        ImageIcon icon = new ImageIcon(path);
        java.awt.Image image = icon.getImage(); // transform it
        image = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        return new ImageIcon(image);
    }

    private int getColumnByName(JTable table, String name) {
        for (int i = 0; i < table.getColumnCount(); ++i)
            if (table.getColumnName(i).equals(name))
                return i;
        return -1;
    }


}
