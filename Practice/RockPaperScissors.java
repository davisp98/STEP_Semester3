import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    private static final String[] MOVES = {"Rock", "Paper", "Scissors"};

    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }
        if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equalsIgnoreCase("Scissors")) ||
            (playerMove.equalsIgnoreCase("Paper") && computerMove.equalsIgnoreCase("Rock")) ||
            (playerMove.equalsIgnoreCase("Scissors") && computerMove.equalsIgnoreCase("Paper"))) {
            return "Player Wins";
        }
        return "Computer Wins";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int totalRounds = 5;

        String[][] roundRecords = new String[totalRounds][4];
        int wins = 0, losses = 0, draws = 0;

        for (int i = 0; i < totalRounds; i++) {
            System.out.print("Round " + (i + 1) + " - Enter move (Rock, Paper, Scissors): ");
            String playerMove = scanner.nextLine().trim();
            String computerMove = MOVES[random.nextInt(3)];

            String result = playRound(playerMove, computerMove);

            if (result.equals("Player Wins")) wins++;
            else if (result.equals("Computer Wins")) losses++;
            else draws++;

            roundRecords[i][0] = String.valueOf(i + 1);
            roundRecords[i][1] = playerMove;
            roundRecords[i][2] = computerMove;
            roundRecords[i][3] = result;
        }

        System.out.println("\n----------------- Summary Table -----------------");
        System.out.printf("%-7s | %-12s | %-14s | %-13s%n", "Round", "Player Move", "Computer Move", "Result");
        System.out.println("-------------------------------------------------");
        for (String[] record : roundRecords) {
            System.out.printf("%-7s | %-12s | %-14s | %-13s%n", record[0], record[1], record[2], record[3]);
        }
        System.out.println("-------------------------------------------------");

        double winPercentage = ((double) wins / totalRounds) * 100;
        System.out.printf("Wins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%%n", wins, losses, draws, winPercentage);
        
        scanner.close();
    }
}