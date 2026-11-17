import java.util.*;

class Candidate {
    String name;
    int[] scores;
    int total;
    int bestClimb;
    int climbStart;
    int climbEnd;
    int originalIndex;

    public Candidate(String name, int[] scores, int index) {
        this.name = name;
        this.scores = scores;
        this.originalIndex = index;
        calculateMetrics();
    }

    private void calculateMetrics() {
        total = 0;
        for (int s : scores) {
            total += s;
        }

        bestClimb = 0;
        climbStart = 1;
        climbEnd = 1;

        for (int i = 0; i < scores.length; i++) {
            int currentGain = 0;
            for (int j = i + 1; j < scores.length; j++) {
                int diff = scores[j] - scores[j - 1];
                if (scores[j] >= scores[j - 1]) {
                    currentGain += diff;
                } else {
                    break;
                }
                if (currentGain > bestClimb) {
                    bestClimb = currentGain;
                    climbStart = i + 1;
                    climbEnd = j + 1;
                }
            }
        }
    }
}

public class PlacementShortlist {
    public static void main(String[] args) {
        List<Candidate> candidates = new ArrayList<>();
        candidates.add(new Candidate("Asha", new int[]{60, 55, 70, 72, 68, 80}, 0));
        candidates.add(new Candidate("Ravi", new int[]{70, 72, 65, 60, 75, 78}, 1));
        candidates.add(new Candidate("Neha", new int[]{50, 65, 62, 70, 74, 84}, 2));
        candidates.add(new Candidate("Kiran", new int[]{80, 70, 66, 72, 70, 60}, 3));
        candidates.add(new Candidate("Mohan", new int[]{55, 60, 58, 64, 70, 73}, 4));

        candidates.sort((c1, c2) -> {
            if (c2.total != c1.total) {
                return Integer.compare(c2.total, c1.total);
            }
            return Integer.compare(c1.originalIndex, c2.originalIndex);
        });

        System.out.println("Shortlist:");
        for (int i = 0; i < candidates.size(); i++) {
            Candidate c = candidates.get(i);
            System.out.println((i + 1) + ". " + c.name + " " + c.total + " | best climb +" + c.bestClimb + " (test " + c.climbStart + " to " + c.climbEnd + ")");
        }

        int cutoff1 = 405;
        int cutoff2 = 410;
        System.out.println("CUTOFF " + cutoff1 + ": " + countCutoff(candidates, cutoff1) + " candidates");
        System.out.println("CUTOFF " + cutoff2 + ": " + countCutoff(candidates, cutoff2) + " candidates");

        findPair(candidates, 785);
        findPair(candidates, 900);
    }

    private static int countCutoff(List<Candidate> candidates, int target) {
        int count = 0;
        for (Candidate c : candidates) {
            if (c.total >= target) {
                count++;
            }
        }
        return count;
    }

    private static void findPair(List<Candidate> candidates, int target) {
        int left = 0;
        int right = candidates.size() - 1;
        while (left < right) {
            int sum = candidates.get(left).total + candidates.get(right).total;
            if (sum == target) {
                System.out.println("PAIR " + target + ": " + candidates.get(left).name + " + " + candidates.get(right).name);
                return;
            } else if (sum < target) {
                right--;
            } else {
                left++;
            }
        }
        System.out.println("PAIR " + target + ": no pair");
    }
}