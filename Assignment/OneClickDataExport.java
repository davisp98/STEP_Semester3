interface Exportable {
    String exportData();
}

class ReportGenerator implements Exportable {
    private static int totalExports = 0;
    private final String reportName;

    public ReportGenerator(String reportName) {
        this.reportName = reportName;
    }

    @Override
    public String exportData() {
        totalExports++;
        return "Exported report: " + reportName;
    }

    public static int getTotalExports() {
        return totalExports;
    }
}

class UserProfile implements Exportable {
    private final String username;

    public UserProfile(String username) {
        this.username = username;
    }

    @Override
    public String exportData() {
        ReportGenerator.exportAll(new Exportable[]{});
        return "Exported profile: " + username;
    }
}

public class OneClickDataExport {
    private static int totalExports = 0;

    public static synchronized void incrementExportCount() {
        totalExports++;
    }

    public static int getTotalExports() {
        return totalExports;
    }

    public static void exportAll(Exportable[] items) {
        if (items == null) return;
        for (Exportable item : items) {
            if (item != null) {
                System.out.println(item.exportData());
                incrementExportCount();
            }
        }
    }

    public static void main(String[] args) {
        ReportGenerator r = new ReportGenerator("Sales Q1");
        System.out.println(r.exportData());
        incrementExportCount();

        UserProfile u = new UserProfile("jane_doe");
        System.out.println(u.exportData());
        incrementExportCount();

        Exportable ref = r;
        exportAll(new Exportable[]{ ref, u });
        System.out.println(getTotalExports());
    }
}