package project2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Project2 {

    public static void main(String[] args) throws IOException {

        IntervalTree tree = new IntervalTree();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("----------Create your Interval tree !!----------");

        System.out.println("-->  Give left bound of the universe:");
        System.out.print("-->  ");

        String line = br.readLine();
        int low = Integer.parseInt(line);

        System.out.println();
        System.out.println("-->  Give right bound of the universe:");
        System.out.print("-->  ");
        line = br.readLine();
        int high = Integer.parseInt(line);
        while (high < 0 || low < 0 || high <= low) {

            System.out.println("----------ERROR !!----------");
            System.out.println("Negative bounds are not allowed");
            System.out.println("Right should be greater than left !");

            System.out.println("----------------------------");

            System.out.println("-->  Give left bound of the universe:");
            System.out.print("-->  ");

            line = br.readLine();
            low = Integer.parseInt(line);

            System.out.println();
            System.out.println("-->  Give right bound of the universe:");
            System.out.print("-->  ");
            line = br.readLine();
            high = Integer.parseInt(line);
        }

        high++;

        for (int i = low; i < high; i++) {
            tree.root = tree.insert(tree.root, i + 0.5);
            tree.root = tree.insert(tree.root, i);
        }
        tree.root = tree.insert(tree.root, high);

        System.out.println("--> This is your interval tree's node keys organized by depth:");

        for (int i = tree.root.height; i >= 1; i--) {
            tree.search(tree.root, i);
            System.out.println("");
        }
        int option = 0;

        while (option != 6) {
            System.out.println();
            System.out.println();
            System.out.println("----------Choose Option----------");
            System.out.println("1. Add interval to tree.");
            System.out.println("2. Search for an interval in tree.");
            System.out.println("3. Find intersections. ");
            System.out.println("4. Delete interval from tree. ");
            System.out.println("5. Insert 1000000 random intervals in tree. ");
            System.out.println("6. Exit.");
            System.out.println("(type number of option)");
            System.out.print("-->");

            line = br.readLine();
            option = Integer.parseInt(line);

            if (option == 1) {

                System.out.println("-->Give left bound of interval");
                System.out.print("-->");

                line = br.readLine();
                int intervalLow = Integer.parseInt(line);

                System.out.println("-->Give right bound of interval");
                System.out.print("-->");

                line = br.readLine();
                int intervalHigh = Integer.parseInt(line);

                while ((intervalLow > high || intervalLow < low) || intervalHigh <= intervalLow) {

                    System.out.println("----------ERROR !!----------");
                    System.out.println("Left bound of interval should belong in the universe");
                    System.out.println("Right should be greater than left !");

                    System.out.println("----------------------------");

                    System.out.println("-->Give left bound of interval");
                    System.out.print("-->");

                    line = br.readLine();
                    intervalLow = Integer.parseInt(line);

                    System.out.println("-->Give right bound of interval");
                    System.out.print("-->");

                    line = br.readLine();
                    intervalHigh = Integer.parseInt(line);

                }

                tree.insertInterval(tree.root, intervalLow, intervalHigh, null, null);

            } else if (option == 2) {
                System.out.println("-->Give left bound of interval");
                System.out.print("-->");

                line = br.readLine();
                int intervalLow = Integer.parseInt(line);

                System.out.println("-->Give right bound of interval");
                System.out.print("-->");

                line = br.readLine();
                int intervalHigh = Integer.parseInt(line);

                while ((intervalLow > high || intervalLow < low) || intervalHigh <= intervalLow) {

                    System.out.println("----------ERROR !!----------");
                    System.out.println("Left bound of interval should belong in the universe");
                    System.out.println("Right should be greater than left !");

                    System.out.println("----------------------------");

                    System.out.println("-->Give left bound of interval");
                    System.out.print("-->");

                    line = br.readLine();
                    intervalLow = Integer.parseInt(line);

                    System.out.println("-->Give right bound of interval");
                    System.out.print("-->");

                    line = br.readLine();
                    intervalHigh = Integer.parseInt(line);

                }

                tree.searchInterval(tree.root, intervalLow, intervalHigh);

            } else if (option == 3) {
                System.out.println("-->Give left bound of interval");
                System.out.print("-->");

                line = br.readLine();
                int intervalLow = Integer.parseInt(line);

                System.out.println("-->Give right bound of interval");
                System.out.print("-->");

                line = br.readLine();
                int intervalHigh = Integer.parseInt(line);

                while ((intervalLow > high || intervalLow < low) || intervalHigh <= intervalLow) {

                    System.out.println("----------ERROR !!----------");
                    System.out.println("Left bound of interval should belong in the universe");
                    System.out.println("Right should be greater than left !");

                    System.out.println("----------------------------");

                    System.out.println("-->Give left bound of interval");
                    System.out.print("-->");

                    line = br.readLine();
                    intervalLow = Integer.parseInt(line);

                    System.out.println("-->Give right bound of interval");
                    System.out.print("-->");

                    line = br.readLine();
                    intervalHigh = Integer.parseInt(line);

                }
                AVLTree.clubis = false;
                tree.findIntersections(tree.root, intervalLow, intervalHigh);
                if (AVLTree.clubis != true) {
                    System.out.println("There are no intersections");

                }
            } else if (option == 4) {
                System.out.println("-->Give left bound of interval");
                System.out.print("-->");

                line = br.readLine();
                int intervalLow = Integer.parseInt(line);

                System.out.println("-->Give right bound of interval");
                System.out.print("-->");

                line = br.readLine();
                int intervalHigh = Integer.parseInt(line);

                while ((intervalLow > high || intervalLow < low) || intervalHigh <= intervalLow) {

                    System.out.println("----------ERROR !!----------");
                    System.out.println("Left bound of interval should belong in the universe");
                    System.out.println("Right should be greater than left !");

                    System.out.println("----------------------------");

                    System.out.println("-->Give left bound of interval");
                    System.out.print("-->");

                    line = br.readLine();
                    intervalLow = Integer.parseInt(line);

                    System.out.println("-->Give right bound of interval");
                    System.out.print("-->");

                    line = br.readLine();
                    intervalHigh = Integer.parseInt(line);

                }
                tree.deleteInterval(tree.root, intervalLow, intervalHigh);
            } else if (option == 5) {

                Random rand = new Random();
                int randomNum1;
                int randomNum2;
                long startTime = System.nanoTime();
                for (int i = 1; i <= 1048576; i++) {
                    do {
                        randomNum1 = rand.nextInt((high - low) + 1) + low;
                        randomNum2 = rand.nextInt((high - low) + 1) + low;
                    } while (randomNum2 == randomNum1);
                    if (randomNum1 > randomNum2) {
                        tree.insertInterval(tree.root, randomNum2, randomNum1, null, null);
                    } else {
                        tree.insertInterval(tree.root, randomNum1, randomNum2, null, null);
                    }
                }
                long estimatedTime = System.nanoTime() - startTime;
                double seconds = (double) estimatedTime / 1_000_000_000.0;
                System.out.println("1000000 random intervals inserted in " + (float) seconds + " seconds.");
            }
        }
        System.exit(0);
    }
}
