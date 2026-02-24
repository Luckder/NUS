public class Solution {

    private class TreeNode {
        int team;
        int height;

        TreeNode left, right;

        TreeNode(int team) {
            this.team = team;
            this.height = 1;
        }
    }

    private TreeNode root;
    private int treeSize;
    private final int[] solves;
    private final long[] penalty;
    private final boolean[] inTree;

    public Solution(int numTeams) {
        this.solves = new int[numTeams + 1];
        this.penalty = new long[numTeams + 1];
        this.inTree = new boolean[numTeams + 1];
        this.treeSize = 0;
        this.root = null;
    }

    private boolean isBetter(int teamA, int teamB) {

        if (solves[teamA] != solves[teamB]) {
            return solves[teamA] > solves[teamB];
        }

        return penalty[teamA] < penalty[teamB];
    }

    private int compare(int teamA, int teamB) {

        if (solves[teamA] != solves[teamB]) { return solves[teamA] > solves[teamB] ? -1 : 1; }
        if (penalty[teamA] != penalty[teamB]) { return penalty[teamA] < penalty[teamB] ? -1 : 1; }

        return teamA < teamB ? -1 : (teamA == teamB ? 0 : 1);
    }

    private int height(TreeNode n) { return n == null ? 0 : n.height; }
    private int bFactor(TreeNode n) { return n == null ? 0 : height(n.left) - height(n.right); }

    private void updateHeight(TreeNode n) {

        int hl = height(n.left);
        int hr = height(n.right);

        n.height = (hl > hr ? hl : hr) + 1;
    }

    private TreeNode rotateRight(TreeNode y) {

        TreeNode x = y.left;
        TreeNode T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private TreeNode rotateLeft(TreeNode x) {

        TreeNode y = x.right;
        TreeNode T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    private TreeNode balance(TreeNode n) {

        if (n == null) { return null; }

        updateHeight(n);

        int balance = bFactor(n);

        if (balance > 1) {
            if (bFactor(n.left) < 0) n.left = rotateLeft(n.left);
            return rotateRight(n);
        }

        if (balance < -1) {
            if (bFactor(n.right) > 0) n.right = rotateRight(n.right);
            return rotateLeft(n);
        }

        return n;
    }

    private TreeNode insert(TreeNode n, int team) {
        if (n == null) { return new TreeNode(team); }

        int cmp = compare(team, n.team);

        if (cmp < 0) { n.left = insert(n.left, team); }
        else if (cmp > 0) { n.right = insert(n.right, team); }

        return balance(n);
    }

    private TreeNode delete(TreeNode n, int team) {

        if (n == null) { return null; }

        int cmp = compare(team, n.team);

        if (cmp < 0) { n.left = delete(n.left, team); }
        else if (cmp > 0) { n.right = delete(n.right, team); }

        else {

            if (n.left == null) { return n.right; }
            if (n.right == null) { return n.left; }

            TreeNode temp = n.right;

            while (temp.left != null) { temp = temp.left; }
            int successorTeam = temp.team;
            n.right = delete(n.right, successorTeam);
            n.team = successorTeam;

        }

        return balance(n);
    }

    private int findMax(TreeNode n) {

        while (n.right != null) { n = n.right; }

        return n.team;
    }

    public int update(int team, long newPenalty) {
        if (team != 1) {

            if (inTree[team]) {

                root = delete(root, team);
                treeSize--;
                inTree[team] = false;

            }

            solves[team]++;
            penalty[team] += newPenalty;

            if (isBetter(team, 1)) {

                root = insert(root, team);
                treeSize++;
                inTree[team] = true;

            }

        } else {

            solves[1]++;
            penalty[1] += newPenalty;

            while (root != null) {

                int worst = findMax(root);

                if (!isBetter(worst, 1)) {

                    root = delete(root, worst);
                    treeSize--;
                    inTree[worst] = false;

                } else {
                    break;
                }

            }
        }

        return treeSize + 1;
    }
}