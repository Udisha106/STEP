package Practice;


import java.util.*;

interface Dimmable {
    void dim(int level);
}

interface Schedulable {
    void schedule(String time);
}

interface EnergyMonitor {
    void showEnergy();
}

abstract class Device {
    String id;
    boolean on = false;

    Device(String id) {
        this.id = id;
    }

    void turnOn() {
        on = true;
        System.out.println(id + " is ON");
    }
}

class Light extends Device implements Dimmable, Schedulable {
    Light(String id) {
        super(id);
    }

    public void dim(int level) {
        System.out.println(id + " dimmed to " + level);
    }

    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Fan extends Device implements Schedulable {
    Fan(String id) {
        super(id);
    }

    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Plug extends Device implements EnergyMonitor {
    double energy;

    Plug(String id, double energy) {
        super(id);
        this.energy = energy;
    }

    public void showEnergy() {
        System.out.println(id + " energy " + energy + " kWh");
    }
}

public class Q1 {
    static void command(Device d, String cmd, String value) {
        switch (cmd) {
            case "ON":
                d.turnOn();
                break;

            case "DIM":
                if (d instanceof Dimmable) {
                    ((Dimmable) d).dim(Integer.parseInt(value));
                } else {
                    System.out.println(d.id + " rejected: DIM unsupported");
                }
                break;

            case "SCHEDULE":
                if (d instanceof Schedulable) {
                    ((Schedulable) d).schedule(value);
                } else {
                    System.out.println(d.id + " rejected: SCHEDULE unsupported");
                }
                break;

            case "ENERGY":
                if (d instanceof EnergyMonitor) {
                    ((EnergyMonitor) d).showEnergy();
                } else {
                    System.out.println(d.id + " rejected: ENERGY unsupported");
                }
                break;
        }
    }

    public static void main(String[] args) {
        Map<String, Device> devices = new HashMap<>();

        devices.put("L1", new Light("L1"));
        devices.put("F1", new Fan("F1"));
        devices.put("P1", new Plug("P1", 12));

        command(devices.get("L1"), "ON", "");
        command(devices.get("L1"), "DIM", "40");
        command(devices.get("F1"), "DIM", "30");
        command(devices.get("F1"), "SCHEDULE", "22:00");
        command(devices.get("P1"), "ENERGY", "");
        command(devices.get("L1"), "ENERGY", "");
    }
}
