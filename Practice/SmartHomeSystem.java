import java.util.*;

interface Dimmable {
    void dim(int level);
}

interface Schedulable {
    void schedule(String time);
}

interface EnergyTrackable {
    void energy();
}

abstract class Device {
    protected String id;
    protected boolean isOn;

    public Device(String id) {
        this.id = id;
        this.isOn = false;
    }

    public void turnOn() {
        this.isOn = true;
        System.out.println(id + " is ON");
    }

    public void turnOff() {
        this.isOn = false;
        System.out.println(id + " is OFF");
    }

    public String getId() {
        return id;
    }
}

class Light extends Device implements Dimmable, Schedulable {
    public Light(String id) {
        super(id);
    }

    @Override
    public void dim(int level) {
        System.out.println(id + " dimmed to " + level);
    }

    @Override
    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Fan extends Device implements Schedulable {
    public Fan(String id) {
        super(id);
    }

    @Override
    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Plug extends Device implements EnergyTrackable {
    private int kwh;

    public Plug(String id, int kwh) {
        super(id);
        this.kwh = kwh;
    }

    @Override
    public void energy() {
        System.out.println(id + " energy " + kwh + " kWh");
    }
}

public class SmartHomeSystem {
    public static void main(String[] args) {
        Map<String, Device> devices = new HashMap<>();
        devices.put("L1", new Light("L1"));
        devices.put("F1", new Fan("F1"));
        devices.put("P1", new Plug("P1", 12));

        Device l1 = devices.get("L1");
        l1.turnOn();

        Device f1 = devices.get("F1");
        Device p1 = devices.get("P1");

        if (l1 instanceof Dimmable) {
            ((Dimmable) l1).dim(40);
        } else {
            System.out.println(l1.getId() + " rejected: DIM unsupported");
        }

        if (f1 instanceof Dimmable) {
            ((Dimmable) f1).dim(30);
        } else {
            System.out.println(f1.getId() + " rejected: DIM unsupported");
        }

        if (f1 instanceof Schedulable) {
            ((Schedulable) f1).schedule("22:00");
        } else {
            System.out.println(f1.getId() + " rejected: SCHEDULE unsupported");
        }

        if (p1 instanceof EnergyTrackable) {
            ((EnergyTrackable) p1).energy();
        } else {
            System.out.println(p1.getId() + " rejected: ENERGY unsupported");
        }

        if (l1 instanceof EnergyTrackable) {
            ((EnergyTrackable) l1).energy();
        } else {
            System.out.println(l1.getId() + " rejected: ENERGY unsupported");
        }
    }
}