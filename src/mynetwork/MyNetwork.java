package mynetwork;

import java.util.ArrayList;
import java.util.Random;

public class MyNetwork {

    static final int DELAY = 2000;
    static final int MAX_COUNT = 8;
    static final int MIN_COUNT = 3;
    static boolean matrix[][];
    static int count = 0;
    static int infectedCount;
    static Thread thread = new Thread(new Working());
    static Random r = new Random();
    static ArrayList<Computer> list = new ArrayList<>();
    static ArrayList<Computer> newlyInfected = new ArrayList<>();

    public void setLink(int from, int to) {
        matrix[from][to] = true;
        matrix[to][from] = true;
    }

    public boolean isConnected(int a, int b) {
        return (matrix[a][b]);
    }

    public static void main(String[] params) throws InterruptedException {
        count = r.nextInt(MAX_COUNT - MIN_COUNT) + MIN_COUNT + 1;
        matrix = new boolean[count][count];
        int i = 0;
        while (i < count) {
            Computer comp = new Computer(r.nextInt(3));
            if (comp.TryInfect()) {
                infectedCount++;
            }
            comp.setIndex(i);
            list.add(comp);
            for (int j = 0; j < i; j++) {
                boolean link = r.nextInt(2) > 0;
                matrix[i][j] = link;
                matrix[j][i] = link;
            }

            matrix[i][i] = false;
            i++;
        }
        printStart();
        printMatrix();
        (new Working()).main();
    }

    public static void printMatrix() {
        String s;
        System.out.print(" ");
        for (int i = 1; i <= count; i++) {
            System.out.print("   " + i);
        }
        System.out.println();
        for (int i = 1; i <= count; i++) {
            System.out.print(i);
            for (int j = 1; j <= count; j++) {
                if (matrix[i - 1][j - 1]) {
                    s = "   1";
                } else {
                    s = "   0";
                }
                System.out.print(s);
            }
            System.out.println();
        }

    }

    public static void printStart() {
        int k = 0;
        for (Computer i : list) {
            k++;
            System.out.println(k + ". OS=" + i.getOS() + ". Infected=" + i.isInfected());
        }
        System.out.println();
    }

    static class Working implements Runnable {

        void main() throws InterruptedException {

            while (true) {
                Thread.sleep(DELAY);
                (new Thread(this)).start();
            }
        }

        @Override
        public void run() {
            for (int i = 0; i < count; i++) {
                if (list.get(i).isInfected()) {
                    for (int j = 0; j < i; j++) {
                        Computer target = list.get(j);
                        if (matrix[i][j] && !target.isInfected()) {
                            if (target.TryInfect()) {
                                infectedCount++;
                                newlyInfected.add(target);
                                System.out.println("infection: " + (i+1) + "->" + (j+1));
                            }
                        }
                    }
                }
            }
            System.out.println(newlyInfected.size() + " computers are infected");
            newlyInfected.clear();

        }
    }
}
