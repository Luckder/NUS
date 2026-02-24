import com.sun.source.tree.Tree;

import javax.management.RuntimeErrorException;

/**
 * Scapegoat Tree class
 *
 * This class contains an implementation of a Scapegoat tree.
 */

public class SGTree {
    /**
     * TreeNode class.
     *
     * This class holds the data for a node in a binary tree.
     *
     * Note: we have made things public here to facilitate problem set grading/testing.
     * In general, making everything public like this is a bad idea!
     *
     */
    public static class TreeNode {
        int key;
        public TreeNode left = null;
        public TreeNode right = null;
        public TreeNode parent = null;

        //Problem 2a
        int weight;

        TreeNode(int k) {
            key = k;
        }
    }

    // Root of the binary tree
    public TreeNode root = null;

    /**
     * Counts the number of nodes in the subtree rooted at node
     *
     * @param node the root of the subtree
     * @return number of nodes
     */
    public int countNodes(TreeNode node) {
        // TODO: Implement this

        if (node == null) {
            return 0;
        } else {
            return 1 + countNodes(node.left) + countNodes(node.right);
        }

    }

    /**
     * Builds an array of nodes in the subtree rooted at node
     *
     * @param node the root of the subtree
     * @return array of nodes
     */
    public TreeNode[] enumerateNodes(TreeNode node) {
        // TODO: Implement this

        if (node == null) {
            return new TreeNode[0];
        }

        int num = countNodes(node);

        TreeNode[] result = new TreeNode[num];

        InOrder(node, result, new int[]{0});

        return result;
    }

    // Helper method
    public void InOrder(TreeNode node, TreeNode[] result, int[] index) {

        if (node == null) {
            return;
        }

        //First left
        InOrder(node.left, result, index);

        //Then root
        result[index[0]] = node;
        index[0]++;

        //Last right
        InOrder(node.right, result, index);
    }

    /**
     * Builds a tree from the list of nodes
     * Returns the node that is the new root of the subtree
     *
     * @param nodeList ordered array of nodes
     * @return the new root node
     */
    public TreeNode buildTree(TreeNode[] nodeList) {
        // TODO: Implement this

        if (nodeList == null) { throw new RuntimeErrorException(new Error("Invalid Array!")); }

        return buildTreeHelper(nodeList, 0, nodeList.length - 1);
    }

    // Helper Method
    public TreeNode buildTreeHelper(TreeNode[] nodeList, int start, int end) {

        if (start > end) { return null; }

        int mid = start + (end - start) / 2;
        TreeNode root = nodeList[mid];

        root.left = buildTreeHelper(nodeList, start, mid - 1);
        root.right = buildTreeHelper(nodeList, mid + 1, end);

        if (root.left != null) { root.left.parent = root; }
        if (root.right != null) { root.right.parent = root; }

        //Problem 2b
        int left = 0;
        int right = 0;

        if (root.left != null) { left = root.left.weight; }
        if (root.right != null) { right = root.right.weight; }

        root.weight = 1 + left + right;

        return root;
    }

    /**
     * Determines if a node is balanced. If the node is balanced, this should return true. Otherwise, it should return
     * false. A node is unbalanced if either of its children has weight greater than 2/3 of its weight.
     *
     * @param node a node to check balance on
     * @return true if the node is balanced, false otherwise
     */
    public boolean checkBalance(TreeNode node) {
        // TODO: Implement this
        // if v is a child of u and weight(v) > (2/3)weight(u), unbalanced
        int u = node.weight;
        int v1 = 0;
        int v2 = 0;

        if (node.left != null) { v1 = node.left.weight; }
        if (node.right != null) { v2 = node.right.weight; }

        if (v1 > ((float) 2 / 3) * u || v2 > ((float) 2 / 3) * u) {
            return false;
        } else {
            return true;
        }

    }

    /**
    * Rebuilds the subtree rooted at node
    * 
    * @param node the root of the subtree to rebuild
    */
    public void rebuild(TreeNode node) {
        // Error checking: cannot rebuild null tree
        if (node == null) {
            return;
        }

        TreeNode p = node.parent;
        TreeNode[] nodeList = enumerateNodes(node);
        TreeNode newRoot = buildTree(nodeList);

        if (p == null) {
            root = newRoot;
        } else if (node == p.left) {
            p.left = newRoot;
        } else {
            p.right = newRoot;
        }

        newRoot.parent = p;
    }

    /**
    * Inserts a key into the tree
    *
    * @param key the key to insert
    */
    public void insert(int key) {
        if (root == null) {
            root = new TreeNode(key);

            //Problem 2a
            root.weight = 1;

            return;
        }

        insert(key, root);
    }

    // Helper method to insert a key into the tree
    private void insert(int key, TreeNode node) {
        if (key <= node.key) {
            if (node.left == null) {
                node.left = new TreeNode(key);
                node.left.parent = node;
                node.left.weight = 1;
            } else {
                insert(key, node.left);
            }
        } else {
            if (node.right == null) {
                node.right = new TreeNode(key);
                node.right.parent = node;
                node.right.weight = 1;
            } else {
                insert(key, node.right);
            }
        }

        // Problem 2a
        int left = 0;
        int right = 0;

        if (node.left != null) { left = node.left.weight; }
        if (node.right != null) { right = node.right.weight; }

        node.weight = 1 + left + right;

        if (!checkBalance(node)) {
            rebuild(node);
        }
    }

    // Simple main function for debugging purposes
    public static void main(String[] args) {
        SGTree tree = new SGTree();
        for (int i = 0; i < 100; i++) {
            tree.insert(i);
        }
        tree.rebuild(tree.root);
    }
}
