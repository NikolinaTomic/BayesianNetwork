package actions;

import model.Patient;
import unbbayes.io.BaseIO;
import unbbayes.io.NetIO;
import unbbayes.io.exception.LoadException;
import unbbayes.prs.Node;
import unbbayes.prs.bn.JunctionTreeAlgorithm;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.util.extension.bn.inference.IInferenceAlgorithm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BayesianNetwork {
    public Map<String, Float> sortedMapRBR = new HashMap<>();
    private ArrayList<HashMap<String, Map<String, Float>>> allRankingLists = new ArrayList<HashMap<String, Map<String, Float>>>();
    private Patient o;
    private ProbabilisticNetwork net = new ProbabilisticNetwork("app");
    private List<Node> nodeList;


    public BayesianNetwork(Patient o) {
        this.o = o;

        try {
            BaseIO io = new NetIO();
            net = (ProbabilisticNetwork) io.load(new File("src/main/resources/BayesianNetwork.net"));
            nodeList = net.getNodes();

        } catch (LoadException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public String findProbabilityOfDiseases(ArrayList<String> selected) {

        IInferenceAlgorithm algorithm = new JunctionTreeAlgorithm();
        algorithm.setNetwork(net);
        algorithm.run();

        ProbabilisticNode age = (ProbabilisticNode) net.getNode("Age");
        ProbabilisticNode race = (ProbabilisticNode) net.getNode("Race");
        ProbabilisticNode gender = (ProbabilisticNode) net.getNode("Sex");

        age.addFinding(getPatientYearGroup(o.getAge()));
        race.addFinding(getPatientRaceGroup(o.getRace()));
        gender.addFinding(getPatientGenderGroup(o.getGender()));

        try {
            net.updateEvidences();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        allRankingLists = new ArrayList<>();
        for (Node node : nodeList) {
            for (String select : selected) {
                if (node.getName().equals(select)) {
                    HashMap<String, Float> map = new HashMap<>();
                    for (int i = 0; i < node.getStatesSize(); i++) {
                        String disease = node.getStateAt(i);
                        Float probability = ((ProbabilisticNode) node).getMarginalAt(i);
                        map.put(disease, probability);
                    }
                    System.out.println(node.getName() + "/n");
                    HashMap<String, Map<String, Float>> symptomList = new HashMap<String, Map<String, Float>>();
                    symptomList.put(node.getName(), map); //hash mapa simptom / rang lista
                    allRankingLists.add(symptomList);
                }
            }
        }

        for (HashMap<String, Map<String, Float>> map : allRankingLists) {
            for (String symptom : map.keySet()) {
                RankingList.printMap(map.get(symptom));
            }
        }

        System.out.println("-----------------------------");
        CalculationOfTopDisease calculationTop = new CalculationOfTopDisease(allRankingLists);
        HashMap<String, Float> calculatedMap = calculationTop.calculation();
        sortedMapRBR = RankingList.sortByComparator(calculatedMap, false);
        RankingList.printMap(sortedMapRBR);
        return calculationTop.printOfProbabilitiesRBR(sortedMapRBR);
    }

    public String getDiseaseDescription(String symptom) {
        for (Node node : nodeList) {
            if (node.getName().equals(symptom)) {
                return node.getDescription();
            }
        }
        return "";
    }

    private int getPatientYearGroup(int patientYears) {
        if (patientYears == 0) {
            return 0;
        } else if (patientYears >= 1 && patientYears < 5) {
            return 1;
        } else if (patientYears >= 5 && patientYears < 15) {
            return 2;
        } else if (patientYears >= 15 && patientYears < 30) {
            return 3;
        } else if (patientYears >= 30 && patientYears < 45) {
            return 4;
        } else if (patientYears >= 45 && patientYears < 60) {
            return 5;
        } else if (patientYears >= 60 && patientYears < 75) {
            return 6;
        } else {
            return 7;
        }
    }

    private int getPatientRaceGroup(String patientRace) {
        if (patientRace.equals("Black")) {
            return 0;
        } else if (patientRace.equals("Hispanic")) {
            return 1;
        } else if (patientRace.equals("White")) {
            return 2;
        } else {
            return 3;
        }
    }

    private int getPatientGenderGroup(String patientGender) {
        if (patientGender.equals("Male")) {
            return 0;
        } else {
            return 1;
        }
    }
}
