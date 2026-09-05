import java.util.Scanner;

public class BankTransactionReferenceValidator {

    static String normalizeReference(String raw) {
        String trimmed = raw.trim();
        if (trimmed.length() < 3) {
            return trimmed.toUpperCase();
        }
        String bankCode = trimmed.substring(0, 3).toUpperCase();
        String rest = trimmed.substring(3);
        return bankCode + rest;
    }

    static String validateAndFormat(String reference) {
        String normalized = normalizeReference(reference);

        if (normalized.length() != 14) {
            return "Invalid: wrong length";
        }

        String bankCode = normalized.substring(0, 3);
        for (int i = 0; i < bankCode.length(); i++) {
            if (!Character.isLetter(bankCode.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        String body = normalized.substring(3); // 11 chars: 6 date digits + 5 seq digits
        for (int i = 0; i < body.length(); i++) {
            if (!Character.isDigit(body.charAt(i))) {
                return "Invalid: reference body must be digits";
            }
        }

        String datePart = body.substring(0, 6); // ddMMyy
        String seqPart = body.substring(6);     // 5-digit sequence

        String dd = datePart.substring(0, 2);
        String MM = datePart.substring(2, 4);
        String yy = datePart.substring(4, 6);

        StringBuilder sb = new StringBuilder();
        sb.append("[").append(bankCode).append("] ");
        sb.append("DATE: ").append(dd).append("/").append(MM).append("/").append(yy);
        sb.append(" | SEQ: ").append(seqPart);

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter transaction reference: ");
        String raw = sc.nextLine();

        System.out.println(validateAndFormat(raw));

        sc.close();
    }
}
