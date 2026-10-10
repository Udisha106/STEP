package Assign;


interface Attackable {
    String attack();
    String attack(String weaponName);
}

interface Defendable {
    String defend();
}

abstract class GameCharacter {
    private static int counter = 1000;
    private final String characterId;

    GameCharacter() {
        characterId = "CH-" + (++counter);
    }

    public String getCharacterId() {
        return characterId;
    }

    public abstract String getSpecialMove();
}

class Warrior extends GameCharacter
        implements Attackable, Defendable {
    private String name;

    Warrior(String name) {
        this.name = name;
    }

    public String attack() {
        return name + " strikes with a blade";
    }

    public String attack(String weaponName) {
        return name + " strikes with an " + weaponName;
    }

    public String defend() {
        return name + " raises a shield";
    }

    public String getSpecialMove() {
        return name + " unleashes Whirlwind Slash";
    }
}

class Trap implements Defendable {
    private String trapType;

    Trap(String trapType) {
        this.trapType = trapType;
    }

    public String defend() {
        return trapType + " triggers automatically";
    }
}

public class Question4 {
    static void resolveDefense(Defendable[] combatants) {
        for (Defendable c : combatants) {
            System.out.println(c.defend());
        }
    }

    public static void main(String[] args) {
        Warrior w = new Warrior("Kael");
        Trap t = new Trap("Spike Pit");

        System.out.println(w.attack());
        System.out.println(w.attack("Iron Sword"));
        System.out.println(w.defend());
        System.out.println(w.getSpecialMove());
        System.out.println(w.getCharacterId());

        resolveDefense(new Defendable[]{w, t});
    }
}
