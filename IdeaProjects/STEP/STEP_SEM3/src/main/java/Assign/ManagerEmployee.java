package Assign;

class ManagerEmployee extends Employee {

    private double teamBonus;

    ManagerEmployee(String empId, String empName,
                    double salary, double teamBonus) {

        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}
