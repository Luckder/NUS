public class MedianFinder {

    // TODO: Include your data structures here

    private class TreeNode {
        private int value;
        private int height;
        private int size;

        private TreeNode left;
        private TreeNode right;
        private TreeNode parent;

        public TreeNode(int val) {
            this.value = val;
            this.height = 1;
            this.size = 1;

            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    private TreeNode root;
    private int num;

    public MedianFinder() {
        // TODO: Construct/Initialise your data structures here

        this.root = null;
        this.num = 0;
    }

    public void insert(int x) {
        // TODO: Implement your insertion operation here

        num++;
        TreeNode insertMe = new TreeNode(x);

        if (this.root == null) {
            this.root = insertMe;
            return;
        }

        TreeNode current = this.root;
        TreeNode father = null;

        while (current != null) {
            father = current;

            if (current.value < insertMe.value) {
                current = current.right;
            } else if (current.value > insertMe.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        insertMe.parent = father;

        if (father.value < insertMe.value) {
            father.right = insertMe;
        } else if (father.value > insertMe.value) {
            father.left = insertMe;
        } else {
            father.right = insertMe;
        }

        rebalanceUp(father);

    }

    public int getMedian() {
        // TODO: Implement your getMedian operation here

        int rank = (num / 2) + 1;
        TreeNode resultNode = findKth(root, rank);
        int result = resultNode.value;

        deleteNode(resultNode);

        num--;

        return result;
    }

    // HELPER METHODS:

    // Helper delete method to remove nodes
    public void deleteNode(TreeNode node) {
        if (node == null) { return; }

        boolean isRoot = false;
        TreeNode OGParent = node.parent;
        isRoot = (OGParent == null);

        if (node.left == null && node.right == null) {
            // Node is a leaf
            if (isRoot) {
                // Node is the root
                root = null;
            } else {
                if (OGParent.left == node) { OGParent.left = null; }
                else { OGParent.right = null; }
            }

            rebalanceUp(OGParent);
            return;
        } else if (node.left == null) {
            // Node is a branch with one right child
            TreeNode child = node.right;

            if (isRoot) {
                // Node is the root
                root = child;
            } else {
                if (OGParent.left == node) { OGParent.left = child; }
                else { OGParent.right = child; }
            }

            child.parent = OGParent; // Update parent reference
            rebalanceUp(OGParent);
            return;
        } else if (node.right == null) {
            // Node is a branch with one left child
            TreeNode child = node.left;

            if (isRoot) {
                // Node is the root
                root = child;
            } else {
                if (OGParent.left == node) { OGParent.left = child; }
                else { OGParent.right = child; }
            }

            child.parent = OGParent; // Update parent reference
            rebalanceUp(OGParent);
            return;
        } else {
            // Node is a branch with two children
            TreeNode ioSuccessor = node.right;
            while (ioSuccessor.left != null) {
                ioSuccessor = ioSuccessor.left;
            }

            // Replacing node with its in-order successor
            node.value = ioSuccessor.value;

            deleteNode(ioSuccessor);
            return;
        }

    }

//    // Helper method to update the heights of the nodes
//    public void updateHeight(TreeNode node) {
//        if (node == null) { return; }
//
//        while (node != null) {
//            int leftH = (node.left == null) ? 0 : node.left.height;
//            int rightH = (node.right == null) ? 0 : node.right.height;
//            int newHeight = 1 + Math.max(leftH, rightH);
//
//            if (node.height == newHeight) { break; }
//
//            node.height = newHeight; // Changes parent's height
//            node = node.parent;
//        }
//    }

    // Returns balance factor of a node (left height - right height)
    private int getBalance(TreeNode node) {
        if (node == null) return 0;
        int leftH  = (node.left  == null) ? 0 : node.left.height;
        int rightH = (node.right == null) ? 0 : node.right.height;
        return leftH - rightH;
    }

    // Updates the height of a single node (does NOT walk up)
    private void refreshNode(TreeNode node) {
        if (node == null) return;
        int leftH  = (node.left  == null) ? 0 : node.left.height;
        int rightH = (node.right == null) ? 0 : node.right.height;
        node.height = 1 + Math.max(leftH, rightH);

        int leftS  = (node.left  == null) ? 0 : node.left.size;
        int rightS = (node.right == null) ? 0 : node.right.size;
        node.size = 1 + leftS + rightS;
    }

    // Left rotation around 'node'. Returns the new root of this subtree.
    private TreeNode rotateLeft(TreeNode node) {
        TreeNode newRoot = node.right;
        TreeNode movingChild = newRoot.left;

        // Perform rotation
        newRoot.left = node;
        node.right = movingChild;

        // Fix parent pointers
        newRoot.parent = node.parent;
        node.parent = newRoot;
        if (movingChild != null) movingChild.parent = node;

        // Fix grandparent's child pointer
        if (newRoot.parent == null) {
            root = newRoot;
        } else if (newRoot.parent.left == node) {
            newRoot.parent.left = newRoot;
        } else {
            newRoot.parent.right = newRoot;
        }

        // Update heights (node first, since it's now lower)
        refreshNode(node);
        refreshNode(newRoot);

        return newRoot;
    }

    // Right rotation around 'node'. Returns the new root of this subtree.
    private TreeNode rotateRight(TreeNode node) {
        TreeNode newRoot = node.left;
        TreeNode movingChild = newRoot.right;

        // Perform rotation
        newRoot.right = node;
        node.left = movingChild;

        // Fix parent pointers
        newRoot.parent = node.parent;
        node.parent = newRoot;
        if (movingChild != null) movingChild.parent = node;

        // Fix grandparent's child pointer
        if (newRoot.parent == null) {
            root = newRoot;
        } else if (newRoot.parent.left == node) {
            newRoot.parent.left = newRoot;
        } else {
            newRoot.parent.right = newRoot;
        }

        // Update heights (node first, since it's now lower)
        refreshNode(node);
        refreshNode(newRoot);

        return newRoot;
    }

    // Rebalances a single node if needed, then returns the (possibly new) local root
    private TreeNode rebalance(TreeNode node) {
        refreshNode(node);
        int balance = getBalance(node);

        // Left-heavy
        if (balance > 1) {
            if (getBalance(node.left) < 0) {
                rotateLeft(node.left);   // Left-Right case: first fix the child
            }
            return rotateRight(node);    // Left-Left case (and now also Left-Right)
        }

        // Right-heavy
        if (balance < -1) {
            if (getBalance(node.right) > 0) {
                rotateRight(node.right); // Right-Left case: first fix the child
            }
            return rotateLeft(node);     // Right-Right case (and now also Right-Left)
        }

        return node; // Already balanced
    }

    // Walks up from 'node' to the root, rebalancing at every level.
    // Call this instead of updateHeight(parent) in both add() and deleteNode().
    private void rebalanceUp(TreeNode node) {
        while (node != null) {
            TreeNode parent = node.parent; // Save before rotation may change it
            rebalance(node);
            node = parent;
        }
    }

    public TreeNode findKth(TreeNode node, int k) {
        int leftSize = (node.left == null) ? 0 : node.left.size;

        if (k == leftSize + 1) {
            return node;                 // This node is rank k
        } else if (k <= leftSize) {
            return findKth(node.left, k);
        } else {
            return findKth(node.right, k - leftSize - 1);
        }
    }

}