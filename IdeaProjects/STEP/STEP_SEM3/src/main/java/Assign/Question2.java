package Assign;


interface Exportable {
    String exportData();
}

class ExportTracker {
    static int totalExports = 0;
}

class ReportGenerator implements Exportable {
    private String reportName;

    ReportGenerator(String reportName) {
        this.reportName = reportName;
    }

    public String exportData() {
        ExportTracker.totalExports++;
        return "Exported report: " + reportName;
    }

    static int getTotalExports() {
        return ExportTracker.totalExports;
    }
}

class UserProfile implements Exportable {
    private String username;

    UserProfile(String username) {
        this.username = username;
    }

    public String exportData() {
        ExportTracker.totalExports++;
        return "Exported profile: " + username;
    }

    static int getTotalExports() {
        return ExportTracker.totalExports;
    }
}

public class Question2 {
    static void exportAll(Exportable[] items) {
        for (Exportable item : items) {
            System.out.println(item.exportData());
        }
    }

    public static void main(String[] args) {
        ReportGenerator r = new ReportGenerator("Sales Q1");
        UserProfile u = new UserProfile("jane_doe");

        Exportable ref = r;

        exportAll(new Exportable[]{ref, u});

        System.out.println("Total exports: "
                + ReportGenerator.getTotalExports());
    }
}
