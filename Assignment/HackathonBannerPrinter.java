import java.util.ArrayDeque;
import java.util.Deque;

public class HackathonBannerPrinter {
    public static String expandBanner(String code) {
        if (code == null || code.isEmpty()) {
            return "";
        }

        Deque<Integer> countStack = new ArrayDeque<>();
        Deque<StringBuilder> stringStack = new ArrayDeque<>();
        StringBuilder currentString = new StringBuilder();
        int currentCount = 0;

        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);

            if (Character.isDigit(c)) {
                currentCount = currentCount * 10 + (c - '0');
            } else if (c == '[') {
                countStack.push(currentCount);
                stringStack.push(currentString);
                currentCount = 0;
                currentString = new StringBuilder();
            } else if (c == ']') {
                int repeatTimes = countStack.pop();
                StringBuilder prevString = stringStack.pop();
                for (int k = 0; k < repeatTimes; k++) {
                    prevString.append(currentString);
                }
                currentString = prevString;
            } else {
                currentString.append(c);
            }
        }

        return currentString.toString();
    }

    public static void main(String[] args) {
        String code1 = "3[ab]2[c]";
        System.out.println(expandBanner(code1));

        String code2 = "2[x3[y]]z";
        System.out.println(expandBanner(code2));
    }
}