import java.util.*;

public class StudentMarksGrid {
    public static void main(String[] args) {
        List<String> subjects = Arrays.asList("Math", "Sci", "Eng");
        Map<String, Map<String, Integer>> studentMarks = new LinkedHashMap<>();

        Map<String, Integer> anu = new LinkedHashMap<>();
        anu.put("Math", 80); anu.put("Sci", 90); anu.put("Eng", 70);
        studentMarks.put("Anu", anu);

        Map<String, Integer> ravi = new LinkedHashMap<>();
        ravi.put("Math", 60); ravi.put("Sci", 85);
        studentMarks.put("Ravi", ravi);

        Map<String, Integer> meena = new LinkedHashMap<>();
        meena.put("Math", 95); meena.put("Sci", 75); meena.put("Eng", 88);
        studentMarks.put("Meena", meena);

        StringBuilder totalsStr = new StringBuilder("Totals ");
        for (Map.Entry<String, Map<String, Integer>> entry : studentMarks.entrySet()) {
            int total = entry.getValue().values().stream().mapToInt(Integer::intValue).sum();
            totalsStr.append(entry.getKey()).append(" ").append(total).append(", ");
        }
        System.out.println(totalsStr.substring(0, totalsStr.length() - 2));

        StringBuilder toppersStr = new StringBuilder("toppers ");
        for (String subj : subjects) {
            String topStudent = "";
            int maxScore = -1;
            for (Map.Entry<String, Map<String, Integer>> entry : studentMarks.entrySet()) {
                Integer score = entry.getValue().get(subj);
                if (score != null && score > maxScore) {
                    maxScore = score;
                    topStudent = entry.getKey();
                }
            }
            toppersStr.append(subj).append(" ").append(topStudent).append(" ").append(maxScore).append(", ");
        }
        System.out.println(toppersStr.substring(0, toppersStr.length() - 2));
    }
}