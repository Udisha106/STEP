package Practice;


import java.time.*;
import java.util.*;

public class Q4 {
    enum Status {
        PENDING, APPROVED, REJECTED
    }

    static abstract class Employee {
        String name;

        Employee(String name) {
            this.name = name;
        }

        abstract int maxLeaveDays();
    }

    static class FullTimeEmployee extends Employee {
        FullTimeEmployee(String name) {
            super(name);
        }

        int maxLeaveDays() {
            return 30;
        }
    }

    static class PartTimeEmployee extends Employee {
        PartTimeEmployee(String name) {
            super(name);
        }

        int maxLeaveDays() {
            return 10;
        }
    }

    static class LeaveRequest {
        Employee employee;
        LocalDate start, end;
        Status status = Status.PENDING;

        LeaveRequest(Employee employee, LocalDate start, LocalDate end) {
            this.employee = employee;
            this.start = start;
            this.end = end;
        }

        void review(Status newStatus) {
            if (status != Status.PENDING) {
                System.out.println("Cannot change status: "
                        + status + " request cannot revert to Pending.");
                return;
            }

            if (newStatus == Status.PENDING) {
                System.out.println("Invalid review status.");
                return;
            }

            status = newStatus;
            System.out.println("Leave request for " + employee.name
                    + " " + status.toString().toLowerCase() + ".");
            System.out.println("Status: " + status.toString().charAt(0)
                    + status.toString().substring(1).toLowerCase());
        }
    }

    static class LeaveManager {
        List<LeaveRequest> requests = new ArrayList<>();

        LeaveRequest submit(Employee e, LocalDate start, LocalDate end) {
            if (end.isBefore(start)) {
                System.out.println("Invalid leave dates.");
                return null;
            }

            long days = end.toEpochDay() - start.toEpochDay() + 1;

            if (days > e.maxLeaveDays()) {
                System.out.println("Leave request exceeds policy.");
                return null;
            }

            LeaveRequest r = new LeaveRequest(e, start, end);
            requests.add(r);

            System.out.println("Leave request submitted by " + e.name
                    + " for " + start + " to " + end + ".");
            System.out.println("Status: Pending");
            return r;
        }
    }

    public static void main(String[] args) {
        LeaveManager manager = new LeaveManager();

        Employee john = new FullTimeEmployee("John Doe");
        Employee jane = new PartTimeEmployee("Jane Smith");

        LeaveRequest r1 = manager.submit(john,
                LocalDate.parse("2024-10-10"),
                LocalDate.parse("2024-10-12"));

        if (r1 != null) {
            r1.review(Status.APPROVED);
        }

        manager.submit(jane,
                LocalDate.parse("2024-11-01"),
                LocalDate.parse("2024-11-05"));

        if (r1 != null) {
            r1.review(Status.PENDING);
        }
    }
}
