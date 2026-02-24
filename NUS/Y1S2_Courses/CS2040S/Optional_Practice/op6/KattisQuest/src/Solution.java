public class Solution {
    // TODO: Include your data structures here

    // Declaration
    private static class TreeNode {

        // Declaration
        long energy;
        long value;

        int height;

        TreeNode left;
        TreeNode right;
        TreeNode parent;

        // Constructor
        public TreeNode(long energy, long value) {
            this.energy = energy;
            this.value = value;

            this.height = 1;

            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    private TreeNode root;

    // Constructor
    public Solution() {
        // TODO: Construct/Initialise your data structures here

        this.root = null;

    }

    public void add(long energy, long value) {
        // TODO: Implement your insertion operation here

        TreeNode newNode = new TreeNode(energy, value);

        // Early exit for empty tree
        if (this.root == null) {
            this.root = newNode;
            return;
        }

        TreeNode current = this.root;
        TreeNode parent = null;

        // BST Insertion: Sort by Energy, then by Value
        while (current != null) {
            parent = current; // Shifts current's reference to parent before moving down the tree

            // Primary Sort: Energy (Higher energy goes right)
            if (newNode.energy < current.energy) {
                current = current.left;
            } else if (newNode.energy > current.energy) {
                current = current.right;
            } else { // newNode.energy == current.energy

                // Secondary Sort: Gold Value (Higher value goes right)
                if (newNode.value < current.value) {
                    current = current.left;
                } else if (newNode.value > current.value) {
                    current = current.right;
                } else {
                    // Both energy and value are the same, place it in the right subtree
                    current = current.right;
                }
            }
        }

        // Attach the node to parent
        newNode.parent = parent;

        if (newNode.energy < parent.energy) {
            parent.left = newNode;
        } else if (newNode.energy > parent.energy) {
            parent.right = newNode;
        } else { // Energies are equal
            if (newNode.value < parent.value) {
                parent.left = newNode;
            } else if (newNode.value > parent.value) {
                parent.right = newNode;
            } else {
                // Both energy and value are the same, place it as the right child
                parent.right = newNode;
            }
        }

        // Update heights up the tree
        rebalanceUp(parent);
    }

    public long query(long remainingEnergy) {
        // TODO: Implement your query operation here

        long gold = 0L;

        // Step 3 in Kattis's Quest
        while (remainingEnergy > 0) {
            TreeNode bestNode = null;
            TreeNode current = root;

            // Step 1 in Kattis's Quest
            while (current != null) {
                if (current.energy <= remainingEnergy) {
                    bestNode = current;   // This is a candidate
                    current = current.right; // Try to find something better to the right
                } else {
                    current = current.left;  // Too expensive, must look for smaller energy
                }
            }

            // Repeat until no more valid nodes are found
            if (bestNode == null) { break; }

            // Update gold and remaining energy
            gold += bestNode.value;
            long toSubtract = bestNode.energy;

            deleteNode(bestNode);

            remainingEnergy -= toSubtract;

        }

        return gold;
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
            node.energy = ioSuccessor.energy;
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
    private void refreshHeight(TreeNode node) {
        if (node == null) return;
        int leftH  = (node.left  == null) ? 0 : node.left.height;
        int rightH = (node.right == null) ? 0 : node.right.height;
        node.height = 1 + Math.max(leftH, rightH);
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
        refreshHeight(node);
        refreshHeight(newRoot);

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
        refreshHeight(node);
        refreshHeight(newRoot);

        return newRoot;
    }

    // Rebalances a single node if needed, then returns the (possibly new) local root
    private TreeNode rebalance(TreeNode node) {
        refreshHeight(node);
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

}