package Assign;


import java.util.*;

public class Q3 {
    interface Capability {
        String name();
        boolean set(String value);
    }

    static class Power implements Capability {
        String value = "OFF";

        public String name() { return "Power"; }

        public boolean set(String v) {
            if (!v.equalsIgnoreCase("ON")
                    && !v.equalsIgnoreCase("OFF")) return false;
            value = v.toUpperCase();
            return true;
        }
    }

    static class Brightness implements Capability {
        int value;

        public String name() { return "Brightness"; }

        public boolean set(String v) {
            try {
                int n = Integer.parseInt(v);
                if (n < 0 || n > 100) return false;
                value = n;
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    static class Temperature implements Capability {
        int value = 22;

        public String name() { return "Temperature"; }

        public boolean set(String v) {
            try {
                int n = Integer.parseInt(v);
                if (n < 16 || n > 30) return false;
                value = n;
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    static class Device {
        String name;
        Map<String, Capability> capabilities = new LinkedHashMap<>();

        Device(String name) { this.name = name; }

        void add(Capability c) {
            capabilities.put(c.name().toLowerCase(), c);
            System.out.println(name + ": " + c.name()
                    + " capability added.");
        }

        boolean supports(String c) {
            return capabilities.containsKey(c.toLowerCase());
        }

        boolean apply(String c, String v) {
            Capability capability = capabilities.get(c.toLowerCase());

            if (capability == null) return false;

            if (!capability.set(v)) {
                if (c.equalsIgnoreCase("Temperature"))
                    System.out.println("Rejected: " + name
                            + " temperature must be between 16°C and 30°C.");
                else if (c.equalsIgnoreCase("Brightness"))
                    System.out.println("Rejected: Brightness must be 0–100%.");
                else
                    System.out.println("Rejected: Invalid " + c + " value.");
                return false;
            }

            if (c.equalsIgnoreCase("Power"))
                System.out.println(name + ": " + v.toUpperCase() + ".");
            else if (c.equalsIgnoreCase("Brightness"))
                System.out.println(name + ": brightness set to " + v + "%.");
            else if (c.equalsIgnoreCase("Temperature"))
                System.out.println(name + ": temperature set to " + v + "°C.");
            else
                System.out.println(name + ": " + c + " set to " + v + ".");

            return true;
        }
    }

    static class Step {
        String capability, value;

        Step(String c, String v) {
            capability = c;
            value = v;
        }
    }

    static class Scene {
        String name;
        List<Step> steps = new ArrayList<>();

        Scene(String name) { this.name = name; }

        void addStep(String c, String v) {
            steps.add(new Step(c, v));
        }

        void run(List<Device> devices) {
            System.out.println("Scene '" + name + "' started.");
            int count = 0;

            for (Step s : steps) {
                for (Device d : devices) {
                    if (d.supports(s.capability)
                            && d.apply(s.capability, s.value)) {
                        count++;
                    }
                }
            }

            System.out.println("Scene '" + name
                    + "' completed: " + count + " actions applied.");
        }
    }

    public static void main(String[] args) {
        Device ac = new Device("Lab AC");
        ac.add(new Power());
        ac.add(new Temperature());

        Device lights = new Device("Ceiling Lights");
        lights.add(new Power());
        lights.add(new Brightness());

        Device projector = new Device("Projector");
        projector.add(new Power());

        List<Device> devices = Arrays.asList(ac, lights, projector);

        Scene scene = new Scene("Lecture Mode");
        scene.addStep("Power", "ON");
        scene.addStep("Brightness", "40");
        scene.addStep("Temperature", "24");
        scene.run(devices);

        ac.apply("Temperature", "12");
        projector.add(new Brightness());
        projector.apply("Brightness", "70");
    }
}

