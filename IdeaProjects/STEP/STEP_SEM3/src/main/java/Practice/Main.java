package Practice;

public class Main {

    public static void main(String[] args) {

        // ==================================================
        // F1 - ATTENDANCE SYSTEM
        // ==================================================

        System.out.println("===== F1 =====");

        SrmStudent[] students = {
                new SrmStudent("Ravi", "RA01", 82),
                new SrmStudent("Anitha", "RA02", 68),
                new SrmStudent("Karthik", "RA03", 91),
                new SrmStudent("Meera", "RA04", 74),
                new SrmStudent("Suresh", "RA05", 60)
        };

        for (SrmStudent student : students) {

            if (student.isEligible()) {
                System.out.println(
                        student.name + " - " +
                                student.attendance +
                                "% - Eligible"
                );
            } else {
                System.out.println(
                        student.name + " - " +
                                student.attendance +
                                "% - Detained"
                );
            }
        }

        System.out.println(
                "Class average: " +
                        SrmStudent.classAverage(students) +
                        "%"
        );


        // ==================================================
        // F2 - FEE ACCOUNT
        // ==================================================

        System.out.println("\n===== F2 =====");

        FeeAccount plain =
                new FeeAccount("RA01", 150000, 0);

        plain.pay(150000);

        HostelFeeAccount hostel =
                new HostelFeeAccount("RA02", 200000, 0);

        hostel.payInTwoInstallments(60000);

        ScholarshipFeeAccount scholarship =
                new ScholarshipFeeAccount(
                        "RA03", 180000, 0, 20
                );

        // instanceof
        if (hostel instanceof HostelFeeAccount) {
            System.out.println(
                    "Hostel account detected."
            );
        }

        if (scholarship instanceof ScholarshipFeeAccount) {
            System.out.println(
                    "Scholarship account detected."
            );
        }

        System.out.println(
                "Plain account due: Rs " +
                        plain.getDue()
        );

        System.out.println(
                "Hostel account due: Rs " +
                        hostel.getDue()
        );

        System.out.println(
                "Scholarship account effective due: Rs " +
                        scholarship.effectiveDue()
        );


        // ==================================================
        // F3 - HOSTEL ROOM
        // ==================================================

        System.out.println("\n===== F3 =====");

        HostelRoom[] rooms1 = {
                new HostelRoom("C-214", 3, 2),
                new HostelRoom("C-507", 2, 2)
        };

        System.out.println(
                "Rooms: C-214 (2/3), C-507 (2/2)"
        );

        HostelRoom.safeAllot(rooms1, "Divya");

        System.out.println(
                "Rooms: C-214 (" +
                        rooms1[0].occupied +
                        "/3), C-507 (" +
                        rooms1[1].occupied +
                        "/2)"
        );

        HostelRoom[] rooms2 = {
                new HostelRoom("C-214", 2, 2),
                new HostelRoom("C-507", 2, 2)
        };

        HostelRoom.safeAllot(rooms2, "Divya");


        // ==================================================
        // F4 - INSTANCE VS STATIC
        // ==================================================

        System.out.println("\n===== F4 =====");

        SrmStudentF4 ravi =
                new SrmStudentF4("Ravi", 82);

        SrmStudentF4 meera =
                new SrmStudentF4("Meera", 74);

        ravi.printIdCard();
        meera.printIdCard();

        SrmStudentF4.printTotalAdmissions();


        // ==================================================
        // F5 - COMPLETE MINI SYSTEM
        // ==================================================

        System.out.println("\n===== F5 =====");

        SrmStudentF5 student1 =
                new SrmStudentF5(
                        "Ravi",
                        "RA01",
                        new HostelFeeAccount(
                                "RA01", 150000, 10000
                        )
                );

        SrmStudentF5 student2 =
                new SrmStudentF5(
                        "Anitha",
                        "RA02",
                        new HostelFeeAccount(
                                "RA02", 200000, 20000
                        )
                );

        SrmStudentF5 student3 =
                new SrmStudentF5(
                        "Karthik",
                        "RA03",
                        new HostelFeeAccount(
                                "RA03", 200000, 0
                        )
                );

        HostelRoom room1 =
                new HostelRoom("C-214", 1, 0);

        HostelRoom room2 =
                new HostelRoom("C-507", 1, 0);

        // Allot rooms to only two students
        student1.room = room1;
        room1.allot(student1.name);

        student2.room = room2;
        room2.allot(student2.name);

        // Third student intentionally remains unallotted

        // Valid payment
        student1.feeAccount.pay(0);

        // Valid payment
        student2.feeAccount.pay(0);

        // Rejected payment
        student3.feeAccount.pay(-5000);

        // Display status
        System.out.println(student1.fullStatus());
        System.out.println(student2.fullStatus());
        System.out.println(student3.fullStatus());

        System.out.println(
                "Total students: " +
                        SrmStudentF5.totalStudents
        );
    }
}
