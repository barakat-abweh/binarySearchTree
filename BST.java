
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

public class BST<E extends Comparable<E>> extends AbstractTree<E> {
	protected TreeNode<E> root;
	protected int size = 0;

	/** Create a default binary tree */
	public BST() {
	}

	/** Create a binary tree from an array of objects */
	public BST(E[] objects) {
		for (int i = 0; i < objects.length; i++)
			insert(objects[i]);
	}

	@Override
	public int findHeight() {
		if (this.isEmpty()) {
			return 0;
		} else {
			TreeNode<E> node = root;
			return findHeight(node);
		}
	}

	private int findHeight(TreeNode<E> aNode) {
		int heightLeft = 0;
		int heightRight = 0;
		if (aNode.left != null)
			heightLeft = findHeight(aNode.left);
		if (aNode.right != null)
			heightRight = findHeight(aNode.right);
		if (heightLeft > heightRight) {
			return heightLeft + 1;
		} else {
			return heightRight + 1;
		}
	}

	public TreeNode<E> AccessNode(E e) {
		TreeNode<E> current = root; // Start from the programmed by Barakat Abwe
									// root

		while (current != null) {
			if (e.compareTo(current.element) < 0) {
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				current = current.right;
			} else
				// element matches current.element
				return current; // Element is found
		}
		System.out.println("element is not in the tree");

		return null;
	}

	public void Successor(E e) {
		if (search(e)) { // programmed by Barakat Abwe
			if (e.compareTo(getMax()) == 0)
				System.out.println("this is the largest element in the tree");
			else {

				TreeNode<E> X = AccessNode(e);
				if (X.right != null) {
					X = X.right;
					while (X.left != null)
						X = X.left;
				} else {
					X = X.father;
					while (X.element.compareTo(e) < 0)
						X = X.father;
				}
				System.out.println(X.element);
			}

		} else
			System.out.println("the element is not in the tree");
	}

	public void Predessore(E e) {
		if (search(e)) {// programmed by Barakat Abwe
			if (e.compareTo(getMin()) == 0)
				System.out.println("this is the smallest element in the tree");
			else {
				TreeNode<E> X = AccessNode(e);
				if (X.left != null) {
					X = X.left;
					while (X.right != null)
						X = X.right;
				} else {
					X = X.father;
					while (X.element.compareTo(e) > 0)
						X = X.father;
				}
				System.out.println(X.element);
			}

		} else
			System.out.println("the element is not in the tree");
	}

	/** Returns true if the element is in the tree */
	public boolean search(E e) {
		TreeNode<E> current = root; // Start from the root

		while (current != null) {
			if (e.compareTo(current.element) < 0) {
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				current = current.right;
			} else
				// element matches current.element
				return true; // Element is found
		}

		return false;
	}

	@Override
	/** Insert element o into the binary tree
	 * Return true if the element is inserted successfully */
	public boolean insert(E e) {
		if (root == null) {
			root = createNewNode(e);
			root.father = null;
		}// Create a new root
		else {
			// Locate the parent node
			TreeNode<E> parent = null;
			TreeNode<E> current = root;
			while (current != null)
				if (e.compareTo(current.element) < 0) {
					parent = current;
					current = current.left;
				} else if (e.compareTo(current.element) > 0) {
					parent = current;
					current = current.right;
				} else
					return false; // Duplicate node not inserted

			// Create the new node and attach it to the parent node
			TreeNode<E> x = createNewNode(e);
			if (e.compareTo(parent.element) < 0)
				parent.left = x;
			else
				parent.right = x;

			x.father = parent;
		}

		size++;
		return true; // Element inserted successfully
	}

	protected TreeNode<E> createNewNode(E e) {
		return new TreeNode<>(e);
	}

	@Override
	/** Inorder traversal from the root */
	public void inorder() {
		inorder(root);
	}

	/** Inorder traversal without recersion **/

	public void inOrder() {
		Stack<TreeNode> nodesStore = new Stack<TreeNode>();
		nodesStore.push(root);
		while (!nodesStore.isEmpty()) {
			while (root.left != null) {
				root = root.left;
				nodesStore.push(root);
			}

			TreeNode<E> currentNode = nodesStore.pop();
			System.out.print(currentNode.element + "  ");

			if (currentNode.right != null) {
				nodesStore.push(currentNode.right);
				root = currentNode.right;
			}
		}
	}

	public void desOrder() {
		desOrder(this.root);

	}

	private void desOrder(TreeNode<E> root) {
		if (root == null)
			return;
		desOrder(root.right);
		System.out.print(root.element + "  ");
		desOrder(root.left);
	}

	public void printL() {
		if(root==null)System.out.println("The tree is empty");
		else{
		LinkedList<TreeNode> List = new LinkedList<TreeNode>();
		TreeNode<Integer> a = new TreeNode<Integer>(0);
		List.add(root);
		for (int i = 0; i < (int) Math.pow(2, this.findHeight()); i++) {
			if (List.get(i).left != null) {
				List.addLast(List.get(i).left);
			} else
				List.addLast(a);
			if (List.get(i).right != null) {
				List.addLast(List.get(i).right);
			} else
				List.addLast(a);
		}
		this.draw(List);}
	}

	private void draw(LinkedList<TreeNode> List) {
		
		for (int i = 0; i < this.findHeight(); i++) {
			if (size == 0)
				break;
			else {
				System.out.print("level " + (i + 1) + " : ");
				for (int j = 0; j < (int) Math.pow(2, i); j++)
					if ((int) List.getFirst().element != 0) {
						System.out.print(List.removeFirst().element + "  ");
					} else
						List.removeFirst();
			}
			System.out.println();
		}

	}

	public void print() {
		if(root==null)System.out.println("The tree is empty");
		else{
		LinkedList<TreeNode> List = new LinkedList<TreeNode>();
		TreeNode<Integer> a = new TreeNode<Integer>(0);
		List.add(root);
		for (int i = 0; i < (int) Math.pow(2, this.findHeight()); i++) {
			if (List.get(i).left != null) {
				List.addLast(List.get(i).left);
			} else
				List.addLast(a);
			if (List.get(i).right != null) {
				List.addLast(List.get(i).right);
			} else
				List.addLast(a);
		}

		// send Levles
		LinkedList<TreeNode> listLevel = new LinkedList();

		int m = (int) Math.pow(findHeight(), 2) - 1;

		for (int i = 0; i < this.findHeight(); i++) {
			if (size == 0)
				break;
			else {
				for (int j = 0; j < (int) Math.pow(2, i); j++) {
					listLevel.add(List.removeFirst());

				}
				this.drawLevel(listLevel, m);
				m = m / 2;

				listLevel.clear();
			}}

		}

	}

	public void drawLevel(LinkedList<TreeNode> List, int o) {
		int t = o / 4 + (o + 2) / 2;
		int n = List.size();
		String A = "", B = "";
		if (n == 1) {
			for (int i = 0; i < t; i++) {
				System.out.print(" ");
			}
			for (int i = 0; i < n; i++)
				System.out.print(List.get(i).element);
			System.out.println();
		} else {
			for (int i = 0; i < o * 0.9; i++) {
				System.out.print(" ");
			}
			for (int j = 1; j < n; j += 2) {
				if ((int) (List.get(j - 1).element) == 0)
					A = "  ";
				else
					A = List.get(j - 1).element + "";
				if ((int) (List.get(j).element) == 0)
					B = "  ";
				else
					B = List.get(j).element + "";
				if (j != 1)
					for (int k = 0; k < o + 2; k++)
						System.out.print(" ");
				System.out.print(A);
				for (int k = 0; k < o; k++)
					System.out.print(" ");
				System.out.print(B);
			}
			System.out.println();

		}
	}

	public AVLTree<E> balance() {

		AVLTree<E> tree = new AVLTree<>();
		LinkedList<TreeNode> List = new LinkedList<TreeNode>();

		List.add(root);
		for (int i = 0; i < size; i++) {
			if (List.get(i).left != null) {
				List.addLast(List.get(i).left);
			}
			if (List.get(i).right != null) {
				List.addLast(List.get(i).right);
			}
		}
		while (!List.isEmpty()) {
			TreeNode<E> y = (TreeNode<E>) List.removeFirst();
			tree.insert(y.element);
		}
		return tree;

	}

	public void deleteXMulti(int x) {
		LinkedList<TreeNode> List = new LinkedList<TreeNode>();
		List.add(root);
		for (int i = 0; i < size; i++) {
			if (List.get(i).left != null) {
				List.addLast(List.get(i).left);
			}
			if (List.get(i).right != null) {
				List.addLast(List.get(i).right);
			}
		}
		while (!List.isEmpty()) {
			TreeNode<E> node = List.removeFirst();
			if( (Integer) node.element % (int)x == 0) {
				System.out.print(node.element + " ");
				this.delete((E) node.element);
			}
		}

		System.out.println();
	}

	public E getMax() {
		return getMax(root);
	}

	private E getMax(TreeNode<E> root) {

		while (root.right != null)
			root = root.right;

		return root.element;
	}

	/** Inorder traversal from a subtree */
	protected void inorder(TreeNode<E> root) {
		if (root == null)
			return;
		inorder(root.left);
		System.out.print(root.element + " ");
		inorder(root.right);
	}

	@Override
	/** Postorder traversal from the root */
	public void postorder() {
		postorder(root);
	}

	/** Postorder traversal from a subtree */
	protected void postorder(TreeNode<E> root) {
		if (root == null)
			return;
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.element + " ");
	}

	@Override
	/** Preorder traversal from the root */
	public void preorder() {
		preorder(root);
	}

	/** Preorder traversal from a subtree */
	protected void preorder(TreeNode<E> root) {
		if (root == null)
			return;
		System.out.print(root.element + " ");
		preorder(root.left);
		preorder(root.right);
	}

	/**
	 * This inner class is static, because it does not access any instance
	 * members defined in its outer class
	 */
	public static class TreeNode<E extends Comparable<E>> {
		protected E element;
		protected TreeNode<E> left;
		protected TreeNode<E> right;
		protected TreeNode<E> father;

		public TreeNode(E e) {
			element = e;
		}
	}

	public E getFather(E x) {

		return this.AccessNode(x).father.element;
	}

	@Override
	/** Get the number of nodes in the tree */
	public int getSize() {
		return size;
	}

	/** Returns the root of the tree */
	public TreeNode<E> getRoot() {
		return root;
	}

	/** Returns a path from the root leading to the specified element */
	public java.util.ArrayList<TreeNode<E>> path(E e) {
		java.util.ArrayList<TreeNode<E>> list = new java.util.ArrayList<>();
		TreeNode<E> current = root; // Start from the root

		while (current != null) {
			list.add(current); // Add the node to the list
			if (e.compareTo(current.element) < 0) {
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				current = current.right;
			} else
				break;
		}

		return list; // Return an array list of nodes
	}

	@Override
	/** Delete an element from the binary tree.
	 * Return true if the element is deleted successfully
	 * Return false if the element is not in the tree */
	public boolean delete(E e) {
		// Locate the node to be deleted and also locate its parent node
		TreeNode<E> parent = null;
		TreeNode<E> current = root;
		while (current != null) {
			if (e.compareTo(current.element) < 0) {
				parent = current;
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				parent = current;
				current = current.right;
			} else
				break; // Element is in the tree pointed at by current
		}

		if (current == null)
			return false; // Element is not in the tree

		// Case 1: current has no left child
		if (current.left == null) {
			// Connect the parent with the right child of the current node
			if (parent == null) {
				root = current.right;
			} else {
				if (e.compareTo(parent.element) < 0)
					parent.left = current.right;
				else
					parent.right = current.right;
			}
		} else {
			// Case 2: The current node has a left child
			// Locate the rightmost node in the left subtree of
			// the current node and also its parent
			TreeNode<E> parentOfRightMost = current;
			TreeNode<E> rightMost = current.left;

			while (rightMost.right != null) {
				parentOfRightMost = rightMost;
				rightMost = rightMost.right; // Keep going to the right
			}

			// Replace the element in current by the element in rightMost
			current.element = rightMost.element;

			// Eliminate rightmost node
			if (parentOfRightMost.right == rightMost)
				parentOfRightMost.right = rightMost.left;
			else
				// Special case: parentOfRightMost == current
				parentOfRightMost.left = rightMost.left;
		}
		current.father = null;
		size--;
		return true; // Element deleted successfully
	}

	@Override
	/** Obtain an iterator. Use inorder. */
	public java.util.Iterator<E> iterator() {
		return new InorderIterator();
	}

	// Inner class InorderIterator
	private class InorderIterator implements java.util.Iterator<E> {
		// Store the elements in a list
		private java.util.ArrayList<E> list = new java.util.ArrayList<>();
		private int current = 0; // Point to the current element in list

		public InorderIterator() {
			inorder(); // Traverse binary tree and store elements in list
		}

		/** Inorder traversal from the root */
		private void inorder() {
			inorder(root);
		}

		/** Inorder traversal from a subtree */
		private void inorder(TreeNode<E> root) {
			if (root == null)
				return;
			inorder(root.left);
			list.add(root.element);
			inorder(root.right);
		}

		@Override
		/** More elements for traversing? */
		public boolean hasNext() {
			if (current < list.size())
				return true;

			return false;
		}

		@Override
		/** Get the current element and move to the next */
		public E next() {
			return list.get(current++);
		}

		@Override
		/** Remove the current element */
		public void remove() {
			delete(list.get(current)); // Delete the current element
			list.clear(); // Clear the list
			inorder(); // Rebuild the list
		}
	}

	public E getMin() {
		TreeNode<E> current = root;
		while (current.left != null) {
			current = current.left;
		}
		return current.element;
	}

	/*
	 * public void predessore(E x) { if ((int) x == (int) this.getMin()) {
	 * System.out.println("this is the smallest element in the tree"); return; }
	 * 
	 * if (!this.search(x)) {
	 * System.out.println("this element is not in the tree"); return; }
	 * 
	 * Integer y = new Integer((int) x - 1); boolean z = true; while (z && (int)
	 * x >= (int) this.getMin()) { while (!this.search((E) y)) { y = y - 1; } z
	 * = false;
	 * 
	 * } if (!z) System.out.println(y);
	 * 
	 * }
	 */

	/*
	 * public void successor(E x) { if ((int) x == (int) this.getMax()) {
	 * System.out.println("this is the largest element in the tree"); return; }
	 * if (!this.search(x)) {
	 * System.out.println("this element is not in the tree"); return; } Integer
	 * y = new Integer((int) x + 1); boolean z = true; while (z && (int) x <
	 * (int) this.getMax()) { while (!this.search((E) y)) { y = y + 1; } z =
	 * false; } if (!z) System.out.println(y); }
	 */

	/** Remove all elements from the tree */
	public void clear() {
		root = null;
		size = 0;
	}
}