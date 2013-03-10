package mynetwork;

import java.util.Random;

public class Computer {

    private int OS;
    private boolean infected;
    final int[] chances = {2, 40, 12};         // вероятность заражения "в процентах"
    final String systems[] = {"Linux  ", "Windows", "Mac    "};

    public Computer(int type) {
        this.OS = type;

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
        this.infected = (this.getChance() >= res);
        return this.infected;
    }
}
