package project2;

import java.util.ArrayList;

public class IntervalNode {

    IntervalNode left, right;
    double key;
    double max; //the max value stored in this node
    double min; //the min value stored in this node 
    int height;
    boolean markBit;  //if true, this node or a child of this node holds at least one interval
    AVLTree leftAvl;
    AVLTree rightAvl;
    ArrayList<IntervalNode> nextLeft;  //arraylist with all next nodes with data in left subtree 
    ArrayList<IntervalNode> nextRight;  //arraylist with all next nodes with data in right subtree
    IntervalNode prev;  //nearest node with data that has this node as a child

    public IntervalNode(double k) {
        key = k;
        height = 1;
        left = null;
        right = null;
        markBit = false;
        leftAvl = new AVLTree();
        rightAvl = new AVLTree();
        nextLeft = new ArrayList<>();
        nextRight = new ArrayList<>();
        prev = null;
        max = Double.MIN_VALUE;
        min = Double.MAX_VALUE;
    }

    public void DeleteInterval(double left, double right) {

        // delete interval's left bound
        leftAvl.root = leftAvl.deleteNodeA(leftAvl.root, left, right);

        //delete interval's right bound
        rightAvl.root = rightAvl.deleteNodeA(rightAvl.root, right, left);

        if (left == min && leftAvl.root != null) {
            Node n;
            n = leftAvl.minValueNode(leftAvl.root);
            min = n.key;
        }
        if (right == max && leftAvl.root != null) {
            Node n;
            n = rightAvl.maxValueNode(rightAvl.root);
            min = n.key;
        }
    }

    public void addInterval(double left, double right) {
        if (left < min) {
            min = left;
        }
        if (right > max) {
            max = right;
        }
        // insert interval's left bound 
        leftAvl.root = leftAvl.insert(leftAvl.root, left, right);
        //insert interval's right bound
        rightAvl.root = rightAvl.insert(rightAvl.root, right, left);
    }

    void SearchInterval(double left, double right) {
        leftAvl.SearchInterval(leftAvl.root, left, right);
    }

    public void findIntersections(double left, double right) {
        if (left < key) {
            if (right > key) {
                //left < key < right
                leftAvl.AllIntersect(leftAvl.root);
            } else {
                //left < right < key
                if (right >= min) {
                    leftAvl.IntersectA(leftAvl.root, left, right);
                }
            }
        } else {
            //key < left < right
            if (left <= max) {
                rightAvl.IntersectB(rightAvl.root, left, right);
            }
        }

    }

}
