abstract class Ride {
    protected double distance;

    public Ride(double distance) {
        this.distance = distance;
    }

    public abstract double calculateFare();
}

class Auto extends Ride {
    public Auto(double distance) {
        super(distance);
    }

    @Override
    public double calculateFare() {
        return 30 + 15 * distance;
    }
}

class Cab extends Ride {
    private double surge;

    public Cab(double distance, double surge) {
        super(distance);
        this.surge = surge;
    }

    @Override
    public double calculateFare() {
        return (50 + 20 * distance) * surge;
    }
}

class Bike extends Ride {
    private int waitingMinutes;

    public Bike(double distance, int waitingMinutes) {
        super(distance);
        this.waitingMinutes = waitingMinutes;
    }

    @Override
    public double calculateFare() {
        return 20 + 10 * distance + 2 * waitingMinutes;
    }
}

public class RideFareEngine {
    public static void main(String[] args) {
        Ride[] rides = {
            new Auto(4),
            new Cab(6, 1.5),
            new Bike(3, 5)
        };

        double total = 0;
        String[] types = {"Auto", "Cab", "Bike"};

        for (int i = 0; i < rides.length; i++) {
            double fare = rides[i].calculateFare();
            total += fare;
            System.out.println(types[i] + ": " + (int)fare);
        }
        System.out.println("Total: " + (int)total);
    }
}