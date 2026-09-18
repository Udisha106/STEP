package Practice;

class SrmStudentF5 {

    String name;
    String regNo;

    HostelFeeAccount feeAccount;
    HostelRoom room;

    static int totalStudents = 0;

    SrmStudentF5(String name, String regNo,
                 HostelFeeAccount feeAccount) {

        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;

        room = null;

        totalStudents++;
    }

    String fullStatus() {

        String roomStatus;

        if (room == null) {
            roomStatus = "unallotted";
        } else {
            roomStatus = room.roomNo;
        }

        return name +
                " | Due: Rs " +
                feeAccount.getDue() +
                " | Room: " +
                roomStatus;
    }
}
