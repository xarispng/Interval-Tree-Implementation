package project2;

import java.util.ArrayList;

public class Node {

    double key;
    int height;
    Node left, right;
    ArrayList<Double> arr;

    Node(double d, double e) {
        key = d;
        height = 1;
        arr = new ArrayList<>();
        arr.add(e);
    }

}
