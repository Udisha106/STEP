package Assign;


import java.util.*;

public class Q1 {
    interface ScoringRule {
        double calculate(int idea, int execution, int presentation);
    }

    static class InnovationRule implements ScoringRule {
        public double calculate(int i, int e, int p) {
            return i * 0.5 + e * 0.3 + p * 0.2;
        }
    }

    static class OpenRule implements ScoringRule {
        public double calculate(int i, int e, int p) {
            return (i + e + p) / 3.0;
        }
    }

    static class Student {
        String name;
        Student(String name) {
            this.name = name;
        }
    }

    static class Track {
        String name;
        ScoringRule rule;

        Track(String name, ScoringRule rule) {
            this.name = name;
            this.rule = rule;
        }
    }

    static class Team {
        String name;
        List<Student> members;
        Track track;
        Project project;

        Team(String name, List<Student> members, Track track) {
            this.name = name;
            this.members = members;
            this.track = track;
        }
    }

    static class Project {
        String name;
        Team team;
        boolean scored = false;

        Project(String name, Team team) {
            this.name = name;
            this.team = team;
        }
    }

    static class Hackathon {
        List<Team> teams = new ArrayList<>();
        Set<Student> registered = new HashSet<>();
        boolean published = false;
        boolean judging = false;

        boolean register(Team t) {
            if (published || judging) {
                System.out.println("Registration failed: Registration is closed.");
                return false;
            }

            if (t.members.size() < 2 || t.members.size() > 4) {
                System.out.println(
                        "Registration failed: A team must have 2 to 4 members.");
                return false;
            }

            for (Student s : t.members) {
                if (registered.contains(s)) {
                    System.out.println("Registration failed: " + s.name
                            + " already belongs to a team.");
                    return false;
                }
            }

            teams.add(t);
            registered.addAll(t.members);

            System.out.println("Team " + t.name + " registered ("
                    + t.members.size() + " members, " + t.track.name + " track).");
            return true;
        }

        Project submit(Team t, String name) {
            if (published || judging || !teams.contains(t)) {
                System.out.println("Project submission failed.");
                return null;
            }

            if (t.project != null) {
                System.out.println("Submission failed: Only one project allowed.");
                return null;
            }

            t.project = new Project(name, t);
            System.out.println("Project '" + name
                    + "' submitted by " + t.name + ".");
            return t.project;
        }

        void score(Project p, int i, int e, int pr) {
            if (published) {
                System.out.println(
                        "Rescore rejected: Results have already been published.");
                return;
            }

            if (!judging || p == null) {
                System.out.println("Scoring failed: Judging has not started.");
                return;
            }

            if (i < 0 || i > 10 || e < 0 || e > 10
                    || pr < 0 || pr > 10) {
                System.out.println("Scoring failed: Ratings must be 0 to 10.");
                return;
            }

            double result = p.team.track.rule.calculate(i, e, pr);
            p.scored = true;

            System.out.println("Score recorded for '" + p.name + "'.");
            System.out.printf(Locale.US, "Final score: %.2f%n", result);
        }

        void publish() {
            published = true;
            System.out.println("Results published.");
        }
    }

    public static void main(String[] args) {
        Hackathon h = new Hackathon();

        Track innovation = new Track("Innovation", new InnovationRule());
        Track open = new Track("Open", new OpenRule());

        Student a = new Student("Asha");
        Student r = new Student("Ravi");
        Student n = new Student("Neha");
        Student k = new Student("Kiran");

        Team byteBusters = new Team("ByteBusters",
                Arrays.asList(a, r, n), innovation);
        Team soloCoder = new Team("SoloCoder",
                Arrays.asList(k), open);

        h.register(byteBusters);
        h.register(soloCoder);

        Project p = h.submit(byteBusters, "SmartAttend");

        h.judging = true;
        h.score(p, 8, 7, 9);
        h.publish();
        h.score(p, 10, 7, 9);
    }
}

