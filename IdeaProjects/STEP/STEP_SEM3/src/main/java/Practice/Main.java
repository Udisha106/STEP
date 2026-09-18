package Practice;

public class Main {

    public static void main(String[] args) {

        // =========================
        // PROBLEM 1
        // =========================

        System.out.println("PROBLEM 1");

        System.out.println(
                AccessRuleEngine.classifyAccess(
                        "private",
                        "SAME_CLASS"
                )
        );

        System.out.println(
                AccessRuleEngine.classifyAccess(
                        "default",
                        "DIFFERENT_PACKAGE"
                )
        );

        String[][] attempts = {
                {"protected", "SAME_PACKAGE"},
                {"protected", "DIFFERENT_PACKAGE"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
                AccessRuleEngine.summarizeBatch(attempts)
        );


        // =========================
        // PROBLEM 2
        // =========================

        System.out.println("\nPROBLEM 2");

        System.out.println(
                AccessRuleEngine.classifyAccess(
                        "protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
                )
        );

        System.out.println(
                AccessRuleEngine.classifyAccess(
                        "protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
                )
        );

        System.out.println(
                AccessRuleEngine.describeContext(
                        "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
                )
        );


        // =========================
        // PROBLEM 3
        // =========================

        System.out.println("\nPROBLEM 3");

        double[] initialReadings = {
                36.5,
                -2,
                37.1
        };

        PatientVitals v =
                new PatientVitals(initialReadings);

        double[] readings = v.getAllReadings();

        for (int i = 0; i < readings.length; i++) {
            System.out.print(readings[i] + " ");
        }

        System.out.println();

        System.out.println("Average = " + v.getAverage());

        double[] copy = v.getAllReadings();

        copy[0] = 999;

        System.out.println(
                "First reading = " + v.getAllReadings()[0]
        );


        // =========================
        // PROBLEM 4
        // =========================

        System.out.println("\nPROBLEM 4");

        PatientProfile p1 =
                new PatientProfile("Arjun Iyer");

        System.out.println(p1.getPatientId());

        PatientProfile p2 =
                new PatientProfile(
                        "MT2026-0142",
                        "Arjun Iyer"
                );

        System.out.println(p2.getPatientId());

        PatientProfile p3 =
                new PatientProfile();

        p3.setPatientId("MT2026-0142");
        p3.setPatientId("HACKED-0000");

        System.out.println(p3.getPatientId());

        p3.setLockerPin("1234");


        // =========================
        // PROBLEM 5
        // =========================

        System.out.println("\nPROBLEM 5");

        String[] medicines = {
                "MED-A",
                "MED-B"
        };

        DischargeSummary d =
                new DischargeSummary(
                        "MT2026-0142",
                        medicines
                );

        String[] codes = d.getMedicationCodes();

        codes[0] = "TAMPERED";

        System.out.println(
                d.getMedicationCodes()[0]
        );

        DischargeSummary[] batch = {

                new CriticalCareDischargeSummary(
                        "MT001",
                        new String[]{"MED-X"},
                        4
                ),

                null,

                new DischargeSummary(
                        "MT002",
                        new String[]{"MED-Y"}
                )
        };

        System.out.println(
                DischargeSummary.processNightlyBatch(batch)
        );
    }
}
