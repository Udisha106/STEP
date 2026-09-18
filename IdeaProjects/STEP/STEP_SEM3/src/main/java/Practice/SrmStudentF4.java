package Practice;

class SrmStudentF4 {

    // Instance fields - different for every student
    String name;
    String regNo;
    int attendance;

    // Static fields - shared by all students
    static String university = "SRM University";
    static int admissionCount = 0;

    SrmStudentF4(String name, int attendance) {

        this.name = name;
        this.attendance = attendance;

        admissionCount++;

        this.regNo = "RA2311003010" + admissionCount;
    }

    void printIdCard() {
        System.out.println(name + " | " + regNo);
    }

    static void printTotalAdmissions() {
        System.out.println(
                "Students admitted so far: " + admissionCount
        );
    }
}