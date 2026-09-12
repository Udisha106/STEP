package WEEK2.Practice_prg;

public class StudentRecordParser {

    static void parseStudentRecord(String csvLine) {

        String[] fields = csvLine.split(",");

        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        System.out.println("Name: " + fields[0] +
                " | Roll No: " + fields[1] +
                " | Dept: " + fields[2]);
    }

    static void run() {

        String csvLine = "Udisha,RA2511003011714,CSE";

        System.out.println("\n--- CSV Student Record Parser ---");
        parseStudentRecord(csvLine);
    }
}
