package mynetwork;

import java.util.Random;

public class Computer {

    private int OS;
    private boolean infected;
    private int index;
    final int[] chances = {2, 40, 12};
    final String systems[] = {"Linux  ", "Windows", "Mac    "};

    public Computer(int type) {
        this.OS = type;

    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public String getOS() {
        return systems[OS];
    }

    public boolean isInfected() {
        return infected;
    }

    public int getChance() {
        return chances[OS];
    }

    public boolean TryInfect() {
        Random r = new Random();
        int res = r.nextInt(100) + 1;
//        System.out.println("Virus: " + res + " vs. " + this.getChance() + " " + this.getOS() + "(" + this.getIndex() + ")");
        this.infected = (this.getChance() >= res);
        return this.infected;
    }
}
