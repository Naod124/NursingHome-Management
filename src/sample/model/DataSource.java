package sample.model;

import java.util.ArrayList;

public class DataSource {

    private static DataSource ourInstance = new DataSource();

    private ArrayList<Patient> Patient;


    private DataSource(){

    }
    public static DataSource getInstance(){
        return ourInstance;
    }

    public ArrayList<sample.model.Patient> getPatient() {
        return Patient;
    }

    public void setPatient(ArrayList<sample.model.Patient> patient) {
        Patient = patient;
    }




}
