package Assign;

public class Main {

    public static void main(String[] args) {

        // ==================================================
        // F1 - LIBRARY FINE SYSTEM
        // ==================================================

        System.out.println("===== F1 =====");

        BookIssue[] issues = {

                new BookIssue(
                        "Clean Code",
                        "Aditi",
                        18
                ),

                new BookIssue(
                        "Effective Java",
                        "Rohan",
                        5
                ),

                new BookIssue(
                        "Refactoring",
                        "Karan",
                        0
                ),

                new BookIssue(
                        "DSA Handbook",
                        "Meera",
                        21
                ),

                new BookIssue(
                        "Design Patterns",
                        "Divya",
                        9
                )
        };

        for (BookIssue issue : issues) {

            if (issue.isSeverelyOverdue()) {

                System.out.println(
                        issue.title +
                                " - " +
                                issue.daysOverdue +
                                " days - Severely overdue"
                );

            } else {

                System.out.println(
                        issue.title +
                                " - " +
                                issue.daysOverdue +
                                " days - OK"
                );
            }
        }

        System.out.println(
                "Total fine collected: Rs " +
                        BookIssue.totalFineCollected(issues)
        );


        // ==================================================
        // F2 - EMPLOYEE INHERITANCE
        // ==================================================

        System.out.println("\n===== F2 =====");

        Employee plain =
                new Employee(
                        "E101",
                        "Karan",
                        40000
                );

        ManagerEmployee manager =
                new ManagerEmployee(
                        "E102",
                        "Divya",
                        70000,
                        8000
                );

        InternEmployee intern =
                new InternEmployee(
                        "E103",
                        "Meera",
                        12000,
                        10000
                );

        if (plain instanceof ManagerEmployee) {

            ManagerEmployee m =
                    (ManagerEmployee) plain;

            System.out.println(
                    "Manager effective pay: Rs " +
                            m.effectiveSalary()
            );

        } else if (plain instanceof InternEmployee) {

            InternEmployee i =
                    (InternEmployee) plain;

            System.out.println(
                    "Intern effective pay: Rs " +
                            i.effectiveSalary()
            );

        } else {

            System.out.println(
                    "Plain employee pay: Rs " +
                            plain.getSalary()
            );
        }

        if (manager instanceof ManagerEmployee) {

            System.out.println(
                    "Manager effective pay: Rs " +
                            manager.effectiveSalary()
            );
        }

        if (intern instanceof InternEmployee) {

            System.out.println(
                    "Intern effective pay: Rs " +
                            intern.effectiveSalary()
            );
        }


        // ==================================================
        // F3 - PARKING ALLOCATION
        // ==================================================

        System.out.println("\n===== F3 =====");

        ParkingSlot[] slots1 = {

                new ParkingSlot(
                        "A1", 4, 3
                ),

                new ParkingSlot(
                        "A2", 5, 5
                )
        };

        System.out.println(
                "Slots: A1 (3/4), A2 (5/5)"
        );

        ParkingSlot.safeAllot(
                slots1,
                "TN09AB1234"
        );

        System.out.println(
                "Slots: A1 (" +
                        slots1[0].occupiedCount +
                        "/4), A2 (" +
                        slots1[1].occupiedCount +
                        "/5)"
        );

        ParkingSlot[] slots2 = {

                new ParkingSlot(
                        "A1", 4, 4
                ),

                new ParkingSlot(
                        "A2", 5, 5
                )
        };

        ParkingSlot.safeAllot(
                slots2,
                "TN09AB1234"
        );


        // ==================================================
        // F4 - LIBRARY MEMBERSHIP
        // ==================================================

        System.out.println("\n===== F4 =====");

        // ---------- Broken Version ----------

        System.out.println("Broken version:");

        BrokenLibraryMember aditi =
                new BrokenLibraryMember(
                        "Aditi",
                        "LM-1001",
                        2
                );

        BrokenLibraryMember rohan =
                new BrokenLibraryMember(
                        "Rohan",
                        "LM-1002",
                        3
                );

        System.out.println(aditi.name);
        System.out.println(rohan.name);

        // Both show Rohan because static fields
        // are shared by all objects.


        // ---------- Fixed Version ----------

        System.out.println("\nFixed version:");

        // Reset counter only for demonstration
        LibraryMember.memberCount = 0;

        LibraryMember member1 =
                new LibraryMember(
                        "Aditi",
                        2
                );

        LibraryMember member2 =
                new LibraryMember(
                        "Rohan",
                        3
                );

        member1.printMemberCard();
        member2.printMemberCard();

        LibraryMember.printTotalMembers();


        // ==================================================
        // F5 - HR + PARKING MINI SYSTEM
        // ==================================================

        System.out.println("\n===== F5 =====");

        CompanyEmployeeRecord.totalRecords = 0;

        // Employee objects
        ManagerEmployee divyaManager =
                new ManagerEmployee(
                        "E201",
                        "Divya",
                        70000,
                        8000
                );

        Employee karanEmployee =
                new Employee(
                        "E202",
                        "Karan",
                        40000
                );

        InternEmployee meeraIntern =
                new InternEmployee(
                        "E203",
                        "Meera",
                        12000,
                        10000
                );

        // Employee records
        CompanyEmployeeRecord divya =
                new CompanyEmployeeRecord(
                        "Divya",
                        "E201",
                        divyaManager
                );

        CompanyEmployeeRecord karan =
                new CompanyEmployeeRecord(
                        "Karan",
                        "E202",
                        karanEmployee
                );

        CompanyEmployeeRecord meera =
                new CompanyEmployeeRecord(
                        "Meera",
                        "E203",
                        meeraIntern
                );

        // Parking slots
        ParkingSlot slot1 =
                new ParkingSlot(
                        "A1", 1, 0
                );

        ParkingSlot slot2 =
                new ParkingSlot(
                        "A2", 1, 0
                );

        // Allot parking to only two employees
        divya.slot = slot1;
        slot1.allot("Divya");

        karan.slot = slot2;
        slot2.allot("Karan");

        // Meera intentionally has no parking

        // Print complete profiles
        System.out.println(
                divya.fullProfile()
        );

        System.out.println(
                karan.fullProfile()
        );

        System.out.println(
                meera.fullProfile()
        );

        System.out.println(
                "Total records: " +
                        CompanyEmployeeRecord.totalRecords
        );
    }
}
