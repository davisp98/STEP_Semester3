class BrokenSrmStudent {
    static String name;
    static String regNo;
    static int attendance;

    public BrokenSrmStudent(String n, String r, int a) {
        name = n;
        regNo = r;
        attendance = a;
    }
}

class FixedSrmStudent {
    private String name;
    private String regNo;
    private int attendance;

    private static String university = "SRM";
    private static int admissionCount = 1010;

    public FixedSrmStudent(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;
        admissionCount++;
        this.regNo = "RA23110030" + admissionCount;
    }

    public void printIdCard() {
        System.out.println(name + " | " + regNo);
    }

    public static void printTotalAdmissions() {
        System.out.println("Students admitted so far: " + (admissionCount - 1010));
    }
}

public class F4_StaticBoundary {
    public static void main(String[] args) {
        BrokenSrmStudent b1 = new BrokenSrmStudent("Ravi", "RA01", 85);
        BrokenSrmStudent b2 = new BrokenSrmStudent("Meera", "RA02", 90);
        System.out.println("Broken version:");
        System.out.println(BrokenSrmStudent.name);
        System.out.println(BrokenSrmStudent.name);

        System.out.println("\nFixed version:");
        FixedSrmStudent s1 = new FixedSrmStudent("Ravi", 85);
        FixedSrmStudent s2 = new FixedSrmStudent("Meera", 90);
        s1.printIdCard();
        s2.printIdCard();
        FixedSrmStudent.printTotalAdmissions();
    }
}