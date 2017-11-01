package crossover.home.mvp.practice.taru.leastcostpathgrigwrap.pojos;

import java.util.ArrayList;

public class Node {
	ArrayList<Integer> costSumList;

	public Node(int val) {
		this.val = val;
		costSumList = new ArrayList<>();
//		costSumList.add(val);
	}

	public int val;

	public void addToCostSumList(int cs) {
		costSumList.add(cs);
	}

	void clearCostSumList() {
		costSumList.clear();
	}

	public ArrayList<Integer> getCostsumList() {
		return costSumList;
	}

	@Override
	public String toString() {
		System.out.print("Node:: val:" + val + ", ");
		for (int c : costSumList) {
			System.out.print(c + ", ");
		}
		System.out.println();
		return super.toString();
	}
}

class CostSum {
	int up, st, dn;

	int min() {
		if (up < st) {
			return (up < dn) ? up : dn;
		} else {
			return (st < dn) ? st : dn;
		}
	}

	@Override
	public String toString() {
		System.out.println("CostSum:: up:" + up + ", st:" + st + ", dn:" + dn + ",  Min:" + min());
		return super.toString();
	}
}