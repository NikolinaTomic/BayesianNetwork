package view;

import actions.BayesianNetwork;
import model.Patient;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelectSymptoms extends JFrame {

    public static ArrayList<String> selected = new ArrayList<>();
    private JFrame mainFrame = new JFrame("Ophthalmology");
    private JPanel checkPanel = new JPanel();
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel descPanel = new JPanel(new BorderLayout());

    public SelectSymptoms(Patient o) {

        Border blackLine = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder title = BorderFactory.createTitledBorder(blackLine, "Description of symptom");
        title.setTitleJustification(TitledBorder.CENTER);

        Border blackLine2 = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder title2 = BorderFactory.createTitledBorder(blackLine2, "Probability of diseases");
        title2.setTitleJustification(TitledBorder.CENTER);

        final JTextArea symptomDescription = new JTextArea();
        symptomDescription.setBorder(title);
        symptomDescription.setEditable(false);
        symptomDescription.setSize(new Dimension(500, 200));
        symptomDescription.setWrapStyleWord(true);
        symptomDescription.setLineWrap(true);
        descPanel.add(symptomDescription, BorderLayout.NORTH);

        JTextArea finalValues = new JTextArea();
        finalValues.setBorder(title2);
        finalValues.setEditable(false);
        descPanel.add(finalValues, BorderLayout.CENTER);

        JPanel daljaPan = new JPanel(new FlowLayout());
        JButton done = new JButton("Done ", MainFrame.getImage("src/main/resources/finish.png"));
        done.setVisible(false);
        daljaPan.add(done);
        descPanel.add(daljaPan, BorderLayout.SOUTH);

        checkPanel.setLayout(new BoxLayout(checkPanel, BoxLayout.Y_AXIS));
        checkPanel.setSize(new Dimension(500, 450));
        checkPanel.setPreferredSize(new Dimension(500, 450));
        JCheckBox dim_vi = new JCheckBox("Diminished vision");
        JCheckBox pain_eye = new JCheckBox("Pain in eye");
        JCheckBox redness = new JCheckBox("Eye redness");
        JCheckBox dob_vi = new JCheckBox("Double vision");
        JCheckBox lacr = new JCheckBox("Lacrimation");
        JCheckBox forig = new JCheckBox("Foreign body sensation in eye");
        JCheckBox swol = new JCheckBox("Swollen eye");
        JCheckBox clo = new JCheckBox("Cloudy eye");
        JCheckBox bli = new JCheckBox("Blindness");
        JCheckBox spots = new JCheckBox("Spots or clouds in vision");
        JCheckBox burn = new JCheckBox("Eye burns or stings");
        JCheckBox white = new JCheckBox("White discharge from eye");
        JCheckBox itchi = new JCheckBox("Itchiness of eye");
        for (String s : selected) {
            if (s.equals("diminished_vision")) {
                dim_vi.setSelected(true);
            } else if (s.equals("swollen_eye")) {
                swol.setSelected(true);
            } else if (s.equals("cloudy_eye")) {
                clo.setSelected(true);
            } else if (s.equals("blindness")) {
                bli.setSelected(true);
            } else if (s.equals("spots_or_clouds_in_vision")) {
                spots.setSelected(true);
            } else if (s.equals("eye_burns_or_stings")) {
                burn.setSelected(true);
            } else if (s.equals("white_discharge_from_eye")) {
                white.setSelected(true);
            } else if (s.equals("itchiness_of_eye")) {
                itchi.setSelected(true);
            } else if (s.equals("pain_in_eye")) {
                pain_eye.setSelected(true);
            } else if (s.equals("eye_redness")) {
                redness.setSelected(true);
            } else if (s.equals("double_vision")) {
                dob_vi.setSelected(true);
            } else if (s.equals("lacrimation")) {
                lacr.setSelected(true);
            } else if (s.equals("foreign_body_sensation_in_eye")) {
                forig.setSelected(true);
            }
        }

        JButton rbrBtn = new JButton("Evaluate ", MainFrame.getImage("src/main/resources/evaluate.jpeg"));

        BayesianNetwork bn = new BayesianNetwork(o);

        itchi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                symptomDescription.setText(bn.getDiseaseDescription("itchiness_of_eye"));
            }
        });
        white.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                symptomDescription.setText(bn.getDiseaseDescription("white_discharge_from_eye"));

            }
        });
        burn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                symptomDescription.setText(bn.getDiseaseDescription("eye_burns_or_stings"));
            }
        });
        spots.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                symptomDescription.setText(bn.getDiseaseDescription("spots_or_clouds_in_vision"));
            }
        });
        bli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                symptomDescription.setText(bn.getDiseaseDescription("blidness"));
            }
        });
        clo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                symptomDescription.setText(bn.getDiseaseDescription("cloudy_eye"));
            }
        });
        forig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                symptomDescription.setText(bn.getDiseaseDescription("foreign_body_sensation_in_eye"));
            }
        });
        swol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                symptomDescription.setText(bn.getDiseaseDescription("swollen_eye"));
            }
        });
        lacr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                symptomDescription.setText(bn.getDiseaseDescription("lacrimation"));
            }
        });
        dob_vi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                symptomDescription.setText(bn.getDiseaseDescription("double_vision"));
            }
        });
        redness.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                symptomDescription.setText(bn.getDiseaseDescription("eye_redness"));
            }
        });
        pain_eye.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                symptomDescription.setText(bn.getDiseaseDescription("pain_in_eye"));
            }
        });
        dim_vi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                symptomDescription.setText(bn.getDiseaseDescription("diminished_vision"));
            }
        });

        rbrBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                // TODO Auto-generated method stub
                done.setVisible(true);
                ArrayList<String> selectBayesian = new ArrayList<>();
                if (dim_vi.isSelected()) {
                    selectBayesian.add("diminished_vision");
                }
                if (redness.isSelected()) {
                    selectBayesian.add("eye_redness");
                }
                if (pain_eye.isSelected()) {
                    selectBayesian.add("pain_in_eye");
                }
                if (lacr.isSelected()) {
                    selectBayesian.add("lacrimation");
                }
                if (dob_vi.isSelected()) {
                    selectBayesian.add("double_vision");
                }
                if (swol.isSelected()) {
                    selectBayesian.add("swollen_eye");
                }
                if (forig.isSelected()) {
                    selectBayesian.add("foreign_body_sensation_in_eye");
                }
                if (clo.isSelected()) {
                    selectBayesian.add("cloudy_eye");
                }
                if (bli.isSelected()) {
                    selectBayesian.add("blidness");
                }
                if (spots.isSelected()) {
                    selectBayesian.add("spots_or_clouds_in_vision");
                }
                if (burn.isSelected()) {
                    selectBayesian.add("eye_burns_or_stings");
                }
                if (white.isSelected()) {
                    selectBayesian.add("white_discharge_from_eye");
                }
                if (itchi.isSelected()) {
                    selectBayesian.add("itchiness_of_eye");
                }

                finalValues.setText(bn.findProbabilityOfDiseases(selectBayesian));

            }
        });

        done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ArrayList<String> symptoms = new ArrayList<String>();
                if (dim_vi.isSelected())
                    symptoms.add("diminished vision");
                if (pain_eye.isSelected())
                    symptoms.add("pain in eye");
                if (redness.isSelected())
                    symptoms.add("eye redness");
                if (dob_vi.isSelected())
                    symptoms.add("double vision");
                if (lacr.isSelected())
                    symptoms.add("lacrimation");
                if (forig.isSelected())
                    symptoms.add("foreign body sensation in eye");
                if (swol.isSelected())
                    symptoms.add("swollen eye");
                if (clo.isSelected())
                    symptoms.add("cloudy eye");
                if (bli.isSelected())
                    symptoms.add("blindness");
                if (spots.isSelected())
                    symptoms.add("spots of clouds in vision");
                if (burn.isSelected())
                    symptoms.add("eye burns of stings");
                if (white.isSelected())
                    symptoms.add("white discharge from eye");
                if (itchi.isSelected())
                    symptoms.add("itchiness of eye");
                String selectedSymptoms = "";
                for (String s : symptoms) {
                    selectedSymptoms += s;
                    selectedSymptoms += ", ";
                }
                try {
                    selectedSymptoms = selectedSymptoms.substring(0, selectedSymptoms.length() - 2);
                    System.out.println(selectedSymptoms);
                } catch (Exception e) {
                }
                AddData addDataFrame = new AddData(selectedSymptoms, o.getJmbg(), "", "", o);
                mainFrame.dispose();
            }
        });

        JLabel simpt = new JLabel("Symptoms: ");
        checkPanel.add(simpt);
        checkPanel.add(itchi);
        checkPanel.add(white);
        checkPanel.add(burn);
        checkPanel.add(spots);
        checkPanel.add(bli);
        checkPanel.add(clo);
        checkPanel.add(swol);
        checkPanel.add(forig);
        checkPanel.add(lacr);
        checkPanel.add(dim_vi);
        checkPanel.add(pain_eye);
        checkPanel.add(redness);
        checkPanel.add(dob_vi);
        checkPanel.add(rbrBtn);

        JButton back = new JButton("Back", MainFrame.getImage("src/main/resources/eyeIcon.png"));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainFrame.dispose();
                try {
                    MainFrame mf = new MainFrame();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        JPanel tug = new JPanel(new BorderLayout());
        tug.add(checkPanel, BorderLayout.WEST);
        tug.add(descPanel, BorderLayout.EAST);
        JPanel proba = new JPanel(new BorderLayout());
        proba.add(tug, BorderLayout.NORTH);
        mainPanel.add(proba, BorderLayout.CENTER);
        mainPanel.add(back, BorderLayout.SOUTH);
        mainFrame.add(mainPanel);
        mainFrame.setSize(1100, 600);

        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

}
