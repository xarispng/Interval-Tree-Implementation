package project2;

public class IntervalTree {

    IntervalNode root;

    // A utility function to get the height of the tree 
    int height(IntervalNode N) {
        if (N == null) {
            return 0;
        }

        return N.height;
    }

    // A utility function to get maximum of two integers 
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // A utility function to right rotate subtree rooted with y 
    // See the diagram given above. 
    IntervalNode rightRotate(IntervalNode y) {
        IntervalNode x = y.left;
        IntervalNode T2 = x.right;

        // Perform rotation 
        x.right = y;
        y.left = T2;

        // Update heights 
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root 
        return x;
    }

    // A utility function to left rotate subtree rooted with x 
    // See the diagram given above. 
    IntervalNode leftRotate(IntervalNode x) {
        IntervalNode y = x.right;
        IntervalNode T2 = y.left;

        // Perform rotation 
        y.left = x;
        x.right = T2;

        //  Update heights 
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root 
        return y;
    }

    // Get Balance factor of node N 
    int getBalance(IntervalNode N) {
        if (N == null) {
            return 0;
        }

        return height(N.left) - height(N.right);
    }

    IntervalNode insert(IntervalNode node, double key) {

        /* 1.  Perform the normal BST insertion */
        if (node == null) {
            return (new IntervalNode(key));
        }

        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else // Duplicate keys not allowed 
        {
            return node;
        }

        /* 2. Update height of this ancestor node */
        node.height = 1 + max(height(node.left),
                height(node.right));

        /* 3. Get the balance factor of this ancestor 
              node to check whether this node became 
              unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there 
        // are 4 cases Left Left Case 
        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }

        // Right Right Case 
        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }

        // Left Right Case 
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case 
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    //Euresh katallilou kombou kai eisagwgh akrwn diasthmatos sthn kombolista tou
    IntervalNode insertInterval(IntervalNode node, double low, double high, IntervalNode lastDataNode, IntervalNode father) {

        //osous kombous diapername kata thn eisagwgh tou diasthmatos 8etoume markbit=true
//        node.markBit = true;

        //an kombos exei plhroforia
        if (node.leftAvl.root != null) {
            lastDataNode = node;
        }

        if (low < node.key) {
            if (high > node.key) {
                //low < node key < high
                //insert interval to this node
                node.addInterval(low, high);
                if (lastDataNode != node && lastDataNode != null) {
                    if (lastDataNode.key > node.key) {
                        lastDataNode.nextLeft.add(node);
                        node.prev = lastDataNode;
                        for (int i = 0; i < lastDataNode.nextLeft.size(); i++) {
                            IntervalNode n = lastDataNode.nextLeft.get(i);
                            if (node.key > n.key && father.key < n.key) {
                                node.nextLeft.add(n);
                                lastDataNode.nextLeft.remove(n);
                            } else if (node.key < n.key && father.key > n.key) {
                                node.nextRight.add(n);
                                lastDataNode.nextLeft.remove(n);
                            }
                        }
                    } else {
                        lastDataNode.nextRight.add(node);
                        node.prev = lastDataNode;
                        for (int i = 0; i < lastDataNode.nextRight.size(); i++) {
                            IntervalNode n = lastDataNode.nextRight.get(i);
                            if (node.key > n.key && father.key < n.key) {
                                node.prev = lastDataNode;
                                lastDataNode.nextRight.remove(n);
                            } else if (node.key < n.key && father.key > n.key) {
                                node.nextRight.add(n);
                                lastDataNode.nextRight.remove(n);
                            }
                        }
                    }
                    return node;
                }
            } else {
                //low < high < node key 
                node.left = insertInterval(node.left, low, high, lastDataNode, node);
            }
        } else {
            //node key<low<high 
            node.right = insertInterval(node.right, low, high, lastDataNode, node);
        }
        return node;
    }

    public void search(IntervalNode root, int key) {
        if (root == null || root.height == key) {
            System.out.print(root.key + " ");
        }
        if (root.left != null) {
            search(root.left, key);
        }
        if (root.right != null) {
            search(root.right, key);
        }
    }

    void searchInterval(IntervalNode node, double left, double right) {

        if (left < node.key) {
            if (right > node.key) {
                //low < node key < high
                AVLTree.clubis2 = false;
                node.SearchInterval(left, right);
                if (AVLTree.clubis2 == true) {
                    System.out.println("This interval is stored in the interval tree.");
                } else {
                    System.out.println("This interval is not stored in the interval tree.");
                }
            } else {
                //low < high < node key 
                searchInterval(node.left, left, right);
            }
        } else {
            //node key<low<high 
            searchInterval(node.right, left, right);
        }

    }

    public void findIntersections(IntervalNode node, double left, double right) {
        boolean found = false;
        if (node.leftAvl.root != null) {
            found = true;
            if (left < node.key && node.key < right) {
                node.findIntersections(left, right);
                if (!node.nextLeft.isEmpty()) {
                    for (int i = 0; i < node.nextLeft.size(); i++) {
                        this.findIntersections(node.nextLeft.get(i), left, right);
                    }
                }
                if (!node.nextRight.isEmpty()) {
                    for (int i = 0; i < node.nextRight.size(); i++) {
                        this.findIntersections(node.nextRight.get(i), left, right);
                    }
                }
            } else if (right < node.key) {
                node.findIntersections(left, right);
                if (!node.nextLeft.isEmpty()) {
                    for (int i = 0; i < node.nextLeft.size(); i++) {
                        this.findIntersections(node.nextLeft.get(i), left, right);
                    }
                }
            } else if (node.key < left) {
                node.findIntersections(left, right);
                if (!node.nextRight.isEmpty()) {
                    for (int i = 0; i < node.nextRight.size(); i++) {
                        this.findIntersections(node.nextRight.get(i), left, right);
                    }
                }
            }
        }

        if (found == false) {
            if (node.left != null) {
                this.findIntersections(node.left, left, right);
            }

            if (node.right != null) {
                this.findIntersections(node.right, left, right);
            }
        }

    }

    IntervalNode deleteInterval(IntervalNode node, double low, double high) {
        if (low < node.key) {
            if (high > node.key) {
                //low < node key < high
                //delete interval if exists in this node
                AVLTree.clubis2 = false;
                node.SearchInterval(low, high);
                if (AVLTree.clubis2 == false) {
                    System.out.println("This interval is not stored in the interval tree.");
                } else {
                    node.DeleteInterval(low, high);
                    if (node.leftAvl.root == null) { //if node has no information after deletion
                        if (node.prev == null) {
                            if (!node.nextLeft.isEmpty()) {
                                for (int i = 0; i < node.nextLeft.size(); i++) {
                                    IntervalNode n = node.nextLeft.get(i);
                                    n.prev = null;
                                    node.nextLeft.remove(n);
                                    if (node.key > n.key) {
                                        node.prev.nextLeft.add(n);
                                        node.prev.nextLeft.remove(node);
                                    } else {
                                        node.prev.nextRight.add(n);
                                        node.prev.nextRight.remove(node);
                                    }
                                }
                            }
                            if (!node.nextRight.isEmpty()) {
                                for (int i = 0; i < node.nextRight.size(); i++) {
                                    IntervalNode n = node.nextRight.get(i);
                                    n.prev = null;
                                    node.nextRight.remove(n);
                                    if (node.key > n.key) {
                                        node.prev.nextLeft.add(n);
                                        node.prev.nextLeft.remove(node);
                                    } else {
                                        node.prev.nextRight.add(n);
                                        node.prev.nextRight.remove(node);
                                    }
                                }
                            }
                        } else {
                            if (!node.nextLeft.isEmpty()) {
                                for (int i = 0; i < node.nextLeft.size(); i++) {
                                    IntervalNode n = node.nextLeft.get(i);
                                    n.prev = node.prev;
                                    node.nextLeft.remove(n);
                                    if (node.key > n.key) {
                                        node.prev.nextLeft.add(n);
                                        node.prev.nextLeft.remove(node);
                                    } else {
                                        node.prev.nextRight.add(n);
                                        node.prev.nextRight.remove(node);
                                    }
                                }
                            }
                            if (!node.nextRight.isEmpty()) {
                                for (int i = 0; i < node.nextRight.size(); i++) {
                                    IntervalNode n = node.nextRight.get(i);
                                    n.prev = node.prev;
                                    node.nextRight.remove(n);
                                    if (node.key > n.key) {
                                        node.prev.nextLeft.add(n);
                                        node.prev.nextLeft.remove(node);
                                    } else {
                                        node.prev.nextRight.add(n);
                                        node.prev.nextRight.remove(node);
                                    }
                                }
                            }
                        }
                    }
                    System.out.println("Interval deleted.");
                }
            } else {
                //low < high < node key 
                node.left = deleteInterval(node.left, low, high);
            }
        } else {
            //node key<low<high 
            node.right = deleteInterval(node.right, low, high);
        }
        return node;
    }

}
