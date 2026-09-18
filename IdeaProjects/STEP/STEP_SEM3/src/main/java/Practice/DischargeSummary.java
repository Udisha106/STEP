package Practice;

import java.util.regex.Pattern;

public  class DischargeSummary {

    private final String patientId;
    private final String[] medicationCodes;

    static {
        System.out.println("DischargeSummary system initialized.");
    }

    public DischargeSummary(String patientId, String[] medicationCodes) {

        if (medicationCodes == null) {
            throw new IllegalArgumentException("Invalid medication codes");
        }

        for (int i = 0; i < medicationCodes.length; i++) {

            if (!Pattern.matches("MED-[A-Z]", medicationCodes[i])) {
                throw new IllegalArgumentException("Invalid medication code");
            }
        }

        this.patientId = patientId;

        this.medicationCodes = new String[medicationCodes.length];

        for (int i = 0; i < medicationCodes.length; i++) {
            this.medicationCodes[i] = medicationCodes[i];
        }
    }

    public String[] getMedicationCodes() {

        String[] copy = new String[medicationCodes.length];

        for (int i = 0; i < medicationCodes.length; i++) {
            copy[i] = medicationCodes[i];
        }

        return copy;
    }

    public DischargeSummary withCorrectedMedication(int index,
                                                    String newCode) {

        if (index < 0 || index >= medicationCodes.length) {
            throw new IllegalArgumentException("Invalid index");
        }

        if (!Pattern.matches("MED-[A-Z]", newCode)) {
            throw new IllegalArgumentException("Invalid medication code");
        }

        String[] newCodes = getMedicationCodes();

        newCodes[index] = newCode;

        if (this instanceof CriticalCareDischargeSummary) {

            CriticalCareDischargeSummary c =
                    (CriticalCareDischargeSummary) this;

            return new CriticalCareDischargeSummary(
                    patientId,
                    newCodes,
                    c.getIcuDays()
            );
        }

        return new DischargeSummary(patientId, newCodes);
    }

    static String processNightlyBatch(DischargeSummary[] summaries) {

        int processed = 0;
        int nullSkipped = 0;
        int critical = 0;
        int routine = 0;

        for (int i = 0; i < summaries.length; i++) {

            if (summaries[i] == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (summaries[i] instanceof CriticalCareDischargeSummary) {
                critical++;
            } else {
                routine++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + critical + " critical-care | "
                + routine + " routine";
    }
}


final class CriticalCareDischargeSummary extends DischargeSummary {

    private final int icuDays;

    public CriticalCareDischargeSummary(String patientId,
                                        String[] medicationCodes,
                                        int icuDays) {

        super(patientId, medicationCodes);
        this.icuDays = icuDays;
    }

    public int getIcuDays() {
        return icuDays;
    }
}
