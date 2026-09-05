class SrmStudent {
    private String name;
    private String regNo;
    private int attendance;

    public SrmStudent(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }

    public void addAttendanceUpdate(int newAttendance) {
        this.attendance = newAttendance;
    }

    public boolean isEligible() {
        return this.attendance >= 75;
    }

    public static double classAverage(SrmStudent[] students) {
        if (students == null || students.length == 0) return 0.0;
        double total = 0;
        for (SrmStudent s : students) {
            total += s.attendance;
        }
        return total / students.length;
    }

    public String getName() {
        return name;
    }

    public int getAttendance() {
        return attendance;
    }
}

public class F1_AttendanceSystem {
    public static void main(String[] args) {
        SrmStudent[] students = {
            new SrmStudent("Ravi", "RA01", 82),
            new SrmStudent("Anitha", "RA02", 68),
            new SrmStudent("Karthik", "RA03", 91),
            new SrmStudent("Meera", "RA04", 74),
            new SrmStudent("Suresh", "RA05", 60)
        };

        for (SrmStudent s : students) {
            String status = s.isEligible() ? "Eligible" : "Detained";
            System.out.printf("%s %d%% %s%n", s.getName(), s.getAttendance(), status);
        }

        System.out.printf("Class average: %.1f%%%n", SrmStudent.classAverage(students));
    }
}