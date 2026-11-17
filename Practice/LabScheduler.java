import java.util.*;

class JobInfo {
    String name;
    int remainingTime;

    public JobInfo(String name, int totalTime) {
        this.name = name;
        this.remainingTime = totalTime;
    }
}

public class LabScheduler {
    public static void schedule(int quantum, List<JobInfo> initialJobs) {
        Queue<JobInfo> queue = new ArrayDeque<>(initialJobs);
        int currentTime = 0;
        List<String> slices = new ArrayList<>();
        Map<String, Integer> finishTimes = new LinkedHashMap<>();

        while (!queue.isEmpty()) {
            JobInfo job = queue.poll();
            int runTime = Math.min(quantum, job.remainingTime);
            int start = currentTime;
            currentTime += runTime;
            int end = currentTime;
            slices.add(job.name + "(" + start + "-" + end + ")");

            job.remainingTime -= runTime;
            if (job.remainingTime > 0) {
                queue.offer(job);
            } else {
                finishTimes.put(job.name, currentTime);
            }
        }

        System.out.println(String.join(" ", slices));
        StringBuilder finishStr = new StringBuilder("finish ");
        List<String> finishParts = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : finishTimes.entrySet()) {
            finishParts.add(entry.getKey() + " " + entry.getValue());
        }
        System.out.println("finish " + String.join(", ", finishParts));
    }

    public static void main(String[] args) {
        List<JobInfo> jobs = Arrays.asList(
            new JobInfo("P1", 5),
            new JobInfo("P2", 3),
            new JobInfo("P3", 7)
        );
        schedule(3, jobs);
    }
}