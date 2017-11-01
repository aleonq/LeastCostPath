package crossover.home.mvp.practice.taru.leastcostpathgrigwrap.workers;

import java.util.ArrayList;
import java.util.Collections;

import crossover.home.mvp.practice.taru.leastcostpathgrigwrap.pojos.Node;

public class NodeGridrocessor {
    // static int cost[][] = { { 4, 5, 6 }, { 7, 8, 9 }, { 1, 2, 3 } };

    // static int cost[][] = { { 1, 2, 3, 4 },{ 5, 6, 7, 8 }, { 9, 10, 11, 12 },
    // { 13, 14, 15, 16 } };
    // static int cost[][] = { { 3, 4, 1, 2, 8, 6 }, { 6, 1, 8, 2, 7, 4 }, { 5,
    // 9, 3, 9, 9, 5 }, { 8, 4, 1, 3, 2, 6 },
    // { 3, 7, 2, 8, 6, 4 } };
//    static int cost[][] = {{19, 10, 19, 10, 19}, {21, 23, 20, 19, 12}, {20, 12, 20, 11, 10}};
    // static int cost[][] = { { 3, 4, 1, 2, 8, 6 }, { 6, 1, 8, 2, 7, 4 }, { 5,
    // 9, 3, 9, 9, 5 }, { 8, 4, 1, 3, 2, 6 },
    // { 3, 7, 2, 8, 6, 4 } };
    // static int cost[][] = { { 1, 9, 6, 3 }, { 2, 8, 1, 2 }, { 3, 1, 7, 1 } };
    // static int cost[][] = { { 1, 9, 1, 9, 9, 9 }, { 9, 1, 7, 2, 1, 5 }, { 5,
    // 4, 3, 1, 3, 1 } };

    //    static int cost[][] = {{1, 2, 3, 4, 5, 6, 7, 1, 9, 8}, {1, 2, 3, 4, 2, 6, 7, 8, 9, 8}, {1, 2, 3, 4, 3, 6, 7, 8, 9, 8},
//            {1, 2, 3, 4, 5, 6, 7, 9, 9, 8}, {1, 2, 3, 4, 5, 6, 7, 8, 9, 2},
//            {1, 2, 3, 4, 5, 6, 7, 5, 9, 8}, {1, 2, 3, 4, 5, 6, 7, 8, 9, 2},
//            {1, 2, 3, 4, 5, 6, 7, 3, 9, 8}, {1, 2, 3, 4, 5, 6, 7, 8, 9, 5}, {1, 2, 3, 5, 5, 6, 7, 8, 9, 8}};
    static int[][] cost;
    static int ht;// = cost.length;
    static int lt;// = cost[0].length;
    static Node[][] nodes;

    public static String main(int[][] grid) {
        cost = grid;
        ht = cost.length;
        lt = cost[0].length;
        nodes = new Node[ht][lt];
        inflateNodes();
        calculatePaths();
        // print();
        return getMin();
    }

    static void calculatePaths() {
        for (int i = 0; i < ht; i++) {
            nodes[i][0].addToCostSumList(nodes[i][0].val);
        }
        for (int j = 1; j < lt; j++) {
            for (int i = 0; i < ht; i++) {
                // get up
                for (int s : nodes[goUp(i)][(j - 1)].getCostsumList()) {
                    nodes[i][j].addToCostSumList(nodes[i][j].val + s);
                }
                // get straight
                for (int s : nodes[goStraight(i)][(j - 1)].getCostsumList()) {
                    nodes[i][j].addToCostSumList(nodes[i][j].val + s);
                }
                // get down
                for (int s : nodes[goDown(i)][(j - 1)].getCostsumList()) {
                    nodes[i][j].addToCostSumList(nodes[i][j].val + s);
                }
                // System.out.println("sums size: " +
                // nodes[i][j].getCostsumList().size());
            }
        }
    }

    static String getMin() {
        ArrayList<Integer> sums = new ArrayList<>();
        for (int i = 0; i < ht; i++) {
            sums.addAll(nodes[i][lt - 1].getCostsumList());
        }
        int minVal = Collections.min(sums);
        int minIndex = sums.indexOf(minVal);
        System.out.println("MIN COST: " + minVal + ", size:" + sums.size() + ", minIndex:" + minIndex);
        return getPath(minIndex, lt - 1, minVal);
    }

    static String getPath(int index, int j, int minVal) {
        int div, mod, node, prev = -1;
        StringBuffer sb = new StringBuffer();
        for (; j >= 0; j--) {
            div = (int) (index / (int) Math.pow(3, j));
            prev = getNode(prev, div);
            sb.append(prev + " ");
            mod = (int) (index % (int) Math.pow(3, j));
            index = mod;
        }
//        System.out.println("Path " + sb.reverse().toString());
        return minVal + "\n" + sb.reverse();
    }

    static int getNode(int prev, int div) {
        int node;
        if (prev != -1) {
            div--;
            node = prev + div;
            if (node == -1) {
                node = ht - 1;
            } else if (node == ht) {
                node = 0;
            }
        } else {
            node = div;
        }
        // System.out.print(" " + node + " ");
        return node;
    }

    static int goUp(int i) {
        if (i == 0) {
            return cost.length - 1;
        } else {
            return i - 1;
        }
    }

    static int goDown(int i) {
        if (i == cost.length - 1) {
            return 0;
        } else {
            return i + 1;
        }
    }

    static int goStraight(int i) {
        return i;
    }

    static void inflateNodes() {
        for (int i = 0; i < ht; i++) {
            for (int j = 0; j < lt; j++) {
                nodes[i][j] = new Node(cost[i][j]);
            }
        }
    }

    public static void print() {
        for (int i = 0; i < ht; i++) {
            for (int j = 0; j < lt; j++) {
                nodes[i][j].toString();
            }
        }
    }
}
