
import java.util.*;

public class TestBST {
	static Scanner in;

	public static void main(String[] args) {
		in = new Scanner(System.in);
		BST<Integer> tree = new BST<>();
		while (true) {
			System.out.println();
			System.out
					.println("choose the operation you want to do : \n"
							+ "1 : build the BST\n2 : Ready tree with inserted nodes\n3 : Display tree in ascending order without recursion\n4 : Display Tree in descending order"
							+ "\n5 : Display tree in ascending order with recursion\n6 : Find the max of the tree\n7 : find the min of the tree\n"
						+ "8 : Draw the tree level by level\n9 : Draw the tree \n10 : balance the tree\n11 : find the successor\n12 : find the predessore\n"
						+ "13 : Delete all Multiples of x\n14 : exit");
		int select = in.nextInt();
		switch (select) {
		case 1:
			System.out.println("enter the number of nodes you want to enter");
			int x = in.nextInt();
			for (int i = 0; i < x; i++) {
				System.out
						.println("enter the number you want to add to the tree");
				tree.insert(in.nextInt());
			}
			break;
		case 2: {
			 tree.insert(60); tree.insert(50); tree.insert(40);
			 tree.insert(20); tree.insert(15); tree.insert(10);
			 tree.insert(8); tree.insert(30); tree.insert(55);
			 tree.insert(53); tree.insert(57); tree.insert(58);
			 tree.insert(60); tree.insert(90); tree.insert(70);
			 tree.insert(65); tree.insert(75); tree.insert(77);
			 tree.insert(79); tree.insert(90); tree.insert(120);
			 tree.insert(200); tree.insert(180); tree.insert(3);
			 tree.insert(1); tree.insert(-1); tree.insert(300);
			 tree.insert(-1000);
			break;
		}
		case 3:
			tree.inOrder();
			break;
		case 4:
			tree.desOrder();
			break;
		case 5:
			tree.inorder();
			break;
		case 6:
			System.out.println(tree.getMax());
			break;
		case 7:
			System.out.println(tree.getMin());
			break;
		case 8:
			tree.printL();
			break;
		case 9:
			tree.print();
			break;
		case 10:
			tree = tree.balance();
			break;
		case 11:
			System.out
					.println("enter the number you want to find its successor");
			tree.Successor(in.nextInt());
			break;
		case 12:
			System.out
					.println("enter the number you want to find its predessore");
			tree.Predessore(in.nextInt());
			break;
		case 13:
			System.out
					.println("enter the number you want to delete its multiples");
			tree.deleteXMulti(in.nextInt());
			break;
		case 14:
			System.exit(0);
		}
	}
}

 }