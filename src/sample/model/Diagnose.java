package sample.model;

public class Diagnose {
    private String getDiagnoseName;
    private String diagnoseDisc ;
    private String startDate ;
    private String occuredDate ;
    private Medicine medicine;

    public Diagnose(String getDiagnoseName, String diagnoseDisc, String startDate) {
        this.getDiagnoseName = getDiagnoseName;
        this.diagnoseDisc = diagnoseDisc;
        this.startDate = startDate;
    }

    public String getGetDiagnoseName() {
        return getDiagnoseName;
    }

    public void setGetDiagnoseName(String getDiagnoseName) {
        this.getDiagnoseName = getDiagnoseName;
    }

    public String getDiagnoseDisc() {
        return diagnoseDisc;
    }

    public void setDiagnoseDisc(String diagnoseDisc) {
        this.diagnoseDisc = diagnoseDisc;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getOccuredDate() {
        return occuredDate;
    }

    public void setOccuredDate(String occuredDate) {
        this.occuredDate = occuredDate;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }
}
