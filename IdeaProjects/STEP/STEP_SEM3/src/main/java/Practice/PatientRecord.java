package Practice;

public class PatientRecord {

    private String patientId;
    String wardCode;
    protected double vitalsScore;
    public String facilityName;

    public PatientRecord(String patientId, String wardCode,
                         double vitalsScore, String facilityName) {

        String trimmedId = patientId.trim();

        if (trimmedId.length() == 0 || trimmedId.length() < 4) {
            throw new IllegalArgumentException("Invalid patient ID");
        }

        this.patientId = trimmedId;
        this.wardCode = wardCode;
        this.vitalsScore = vitalsScore;
        this.facilityName = facilityName;
    }

    public String getPatientId() {
        return patientId;
    }
}
