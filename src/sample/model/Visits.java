package sample.model;

public class Visits {
    private int time;
    private String PatientFirstName;
    private String PatientLastName;
    private String Type;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getPatientFirstName() {
        return PatientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        PatientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return PatientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        PatientLastName = patientLastName;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getIdVisit() {
        return idVisit;
    }

    public void setIdVisit(String idVisit) {
        this.idVisit = idVisit;
    }

    private String idVisit;
}
