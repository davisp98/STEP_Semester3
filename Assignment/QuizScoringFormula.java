import java.util.ArrayDeque;
import java.util.Deque;

public class QuizScoringFormula {
    public static int evaluate(String expr) {
        if (expr == null || expr.isEmpty()) {
            return 0;
        }

        Deque<Integer> operands = new ArrayDeque<>();
        Deque<Character> operators = new ArrayDeque<>();

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (c == ' ') {
                continue;
            }

            if (Character.isDigit(c)) {
                int num = 0;
                while (i < expr.length() && Character.isDigit(expr.charAt(i))) {
                    num = num * 10 + (expr.charAt(i) - '0');
                    i++;
                }
                i--;
                operands.push(num);
            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    operands.push(applyOp(operators.pop(), operands.pop(), operands.pop()));
                }
                if (!operators.isEmpty() && operators.peek() == '(') {
                    operators.pop();
                }
            } else if (isOperator(c)) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(c)) {
                    operands.push(applyOp(operators.pop(), operands.pop(), operands.pop()));
                }
                operators.push(c);
            }
        }

        while (!operators.isEmpty()) {
            operands.push(applyOp(operators.pop(), operands.pop(), operands.pop()));
        }

        return operands.isEmpty() ? 0 : operands.pop();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static int precedence(char op) {
        if (op == '+' || op == '-') {
            return 1;
        }
        if (op == '*' || op == '/') {
            return 2;
        }
        return 0;
    }

    private static int applyOp(char op, int b, int a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return b == 0 ? 0 : a / b;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        String expr1 = "2 * (3 + 4) - 10 / 5";
        System.out.println(evaluate(expr1));

        String expr2 = "(8 - 2) * (1 + 2) / 4";
        System.out.println(evaluate(expr2));
    }
}