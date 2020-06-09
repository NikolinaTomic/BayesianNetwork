package model;

import java.util.ArrayList;

public class Patient {
    private int age = 0;
    private String gender = "Male";
    private String race = "White";
    private String jmbg = "";
    private ArrayList<String> symptoms = new ArrayList<String>();

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public ArrayList<String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(ArrayList<String> symptoms) {
        this.symptoms = symptoms;
    }


    @Override
    public String toString() {
        return "Patient [age=" + age + ", gender=" + gender + ", race=" + race + ", symptoms=" + symptoms + "]";
    }


}
