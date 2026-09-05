class SrmStudent {
    private String name;
    private String regNo;
    private HostelFeeAccount feeAccount;
    private HostelRoom room;

    public static int totalStudents = 0;

    public SrmStudent(String name, String regNo, HostelFeeAccount feeAccount) {
        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        this.room = null;
        totalStudents++;
    }

    public void assignRoom(HostelRoom room) {
        if (room != null && room.allot(this.name)) {
            this.room = room;
        }
    }

    public String fullStatus() {
        String roomDisplay = (room != null) ? room.getRoomNo() : "unallotted";
        return name + " | Due: Rs " + feeAccount.getDue() + " | Room: " + roomDisplay;
    }
}

public class F5_CapstoneSystem {
    public static void main(String[] args) {
        HostelRoom r1 = new HostelRoom("C-214", 3, 2);
        HostelRoom r2 = new HostelRoom("C-507", 2, 1);

        HostelFeeAccount acc1 = new HostelFeeAccount("RA01", 200000, 60000);
        HostelFeeAccount acc2 = new HostelFeeAccount("RA02", 200000, 20000);
        HostelFeeAccount acc3 = new HostelFeeAccount("RA03", 200000, 0);

        acc3.pay(-5000);

        SrmStudent s1 = new SrmStudent("Ravi", "RA01", acc1);
        SrmStudent s2 = new SrmStudent("Anitha", "RA02", acc2);
        SrmStudent s3 = new SrmStudent("Karthik", "RA03", acc3);

        s1.assignRoom(r1);
        s2.assignRoom(r2);

        System.out.println(s1.fullStatus());
        System.out.println(s2.fullStatus());
        System.out.println(s3.fullStatus());
        System.out.println("Total students: " + SrmStudent.totalStudents);
    }
}