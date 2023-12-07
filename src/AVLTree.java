package project2;

public class AVLTree {

    Node root;
    public static boolean clubis;
    public static boolean clubis2;

    // A utility function to get the height of the tree 
    int height(Node N) {
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
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

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
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation 
        y.left = x;
        x.right = T2;

        // Update heights 
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root 
        return y;
    }

    // Get Balance factor of node N 
    int getBalance(Node N) {
        if (N == null) {
            return 0;
        }

        return height(N.left) - height(N.right);
    }

    Node insert(Node node, double key, double e) {

        /* 1. Perform the normal BST insertion */
        if (node == null) {
            return (new Node(key, e));
        }

        if (key < node.key) {
            node.left = insert(node.left, key, e);
        } else if (key > node.key) {
            node.right = insert(node.right, key, e);
        } else { // Duplicate keys not allowed 
            node.arr.add(e);
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

    Node minValueNode(Node node) {
        Node current = node;

        /* loop down to find the leftmost leaf */
        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    Node maxValueNode(Node node) {
        Node current = node;

        /* loop down to find the leftmost leaf */
        while (current.right != null) {
            current = current.right;
        }

        return current;
    }

    Node deleteNodeA(Node root, double key, double e) {

        if (root == null) {
            return root;
        }

        // If the key to be deleted is smaller than  
        // the root's key, then it lies in left subtree  
        if (key < root.key) {
            root.left = deleteNodeA(root.left, key, e);
        } // If the key to be deleted is greater than the  
        // root's key, then it lies in right subtree  
        else if (key > root.key) {
            root.right = deleteNodeA(root.right, key, e);
        } // if key is same as root's key, then this is the node  
        // to be deleted  
        else {
            int pos = root.arr.indexOf(e);
            if (pos != -1) {
                root.arr.remove(pos);

                if (root.arr.size() == 0) {
                    root = deleteNode(root, key);
                }
            }
        }
        return root;
    }

    Node deleteNode(Node root, double key) {
        // STEP 1: PERFORM STANDARD BST DELETE  
        if (root == null) {
            return root;
        }

        // If the key to be deleted is smaller than  
        // the root's key, then it lies in left subtree  
        if (key < root.key) {
            root.left = deleteNode(root.left, key);
        } // If the key to be deleted is greater than the  
        // root's key, then it lies in right subtree  
        else if (key > root.key) {
            root.right = deleteNode(root.right, key);
        } // if key is same as root's key, then this is the node  
        // to be deleted  
        else {

            // node with only one child or no child  
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left) {
                    temp = root.right;
                } else {
                    temp = root.left;
                }

                // No child case  
                if (temp == null) {
                    temp = root;
                    root = null;
                } else // One child case  
                {
                    root = temp; // Copy the contents of  
                }                                // the non-empty child  
            } else {

                // node with two children: Get the inorder  
                // successor (smallest in the right subtree)  
                Node temp = minValueNode(root.right);

                // Copy the inorder successor's data to this node  
                root.key = temp.key;

                // Delete the inorder successor  
                root.right = deleteNode(root.right, temp.key);
            }

            // If the tree had only one node then return  
            if (root == null) {
                return root;
            }

            // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE  
            root.height = max(height(root.left), height(root.right)) + 1;

            // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether  
            // this node became unbalanced)  
            int balance = getBalance(root);

            // If this node becomes unbalanced, then there are 4 cases  
            // Left Left Case  
            if (balance > 1 && getBalance(root.left) >= 0) {
                return rightRotate(root);
            }

            // Left Right Case  
            if (balance > 1 && getBalance(root.left) < 0) {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }

            // Right Right Case  
            if (balance < -1 && getBalance(root.right) <= 0) {
                return leftRotate(root);
            }

            // Right Left Case  
            if (balance < -1 && getBalance(root.right) > 0) {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }
        }

        return root;
    }

    void SearchInterval(Node node, double left, double right) {

        if (node == null) {
            AVLTree.clubis2=false;
        } else {
            if (left < node.key) {
                SearchInterval(node.left, left, right);
            } else if (left > node.key) {
                SearchInterval(node.right, left, right);
            } else if (left == node.key) {
                if (node.arr.contains(right)) {
                    AVLTree.clubis2 = true;
                } else {
                    AVLTree.clubis2=false;;
                }
            }
        }
    }

    public void AllIntersect(Node n) {
        // print all intervals
        if (n != null) {
            for (int i = 0; i < n.arr.size(); i++) {
                System.out.println(n.key + " , " + n.arr.get(i) + " ");
                clubis = true;
            }
            AllIntersect(n.left);
            AllIntersect(n.right);

        }
    }

    public void IntersectA(Node n, double left, double right) {
        //This method is only for leftAvl !!!!	

        if (n != null) {
            if (right > n.key) {

                for (int i = 0; i < n.arr.size(); i++) {
                    System.out.println(n.key + " , " + n.arr.get(i) + " ");
                    clubis = true;
                }

                IntersectA(n.left, left, right);
                IntersectA(n.right, left, right);
            } else if (right == n.key) {

                for (int i = 0; i < n.arr.size(); i++) {
                    System.out.println(n.key + " , " + n.arr.get(i) + " ");
                    clubis = true;
                }
                IntersectA(n.left, left, right);

            } else {
                IntersectA(n.left, left, right);
            }
        }

    }

    public void IntersectB(Node n, double left, double right) {
        //This method is only for rightAvl !!!	

        if (n != null) {
            if (left < n.key) {

                for (int i = 0; i < n.arr.size(); i++) {
                    System.out.println(n.arr.get(i) + " , " + n.key + " ");
                    clubis = true;
                }

                IntersectB(n.left, left, right);
                IntersectB(n.right, left, right);
            } else if (left == n.key) {

                for (int i = 0; i < n.arr.size(); i++) {
                    System.out.println(n.arr.get(i) + " , " + n.key + " ");
                    clubis = true;
                }
                IntersectB(n.right, left, right);

            } else {
                IntersectB(n.right, left, right);
            }
        }

    }

}
