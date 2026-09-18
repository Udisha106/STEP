package Assign;

class CompanyEmployeeRecord {

    String name;
    String empId;

    Employee employee;
    ParkingSlot slot;

    static int totalRecords = 0;

    CompanyEmployeeRecord(String name,
                          String empId,
                          Employee employee) {

        this.name = name;
        this.empId = empId;
        this.employee = employee;

        // Initially no parking is assigned
        this.slot = null;

        totalRecords++;
    }

    String fullProfile() {

        String slotStatus;

        if (slot == null) {
            slotStatus = "no parking assigned";
        } else {
            slotStatus = slot.slotNo;
        }

        double pay;

        // Decide which salary method to use
        if (employee instanceof ManagerEmployee) {

            ManagerEmployee manager =
                    (ManagerEmployee) employee;

            pay = manager.effectiveSalary();

        } else if (employee instanceof InternEmployee) {

            InternEmployee intern =
                    (InternEmployee) employee;

            pay = intern.effectiveSalary();

        } else {

            pay = employee.getSalary();
        }

        return name +
                " | Pay: Rs " +
                pay +
                " | Slot: " +
                slotStatus;
    }
}
