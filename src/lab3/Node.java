package lab3;

public class Node {
	int value;
	Node downNode;
	Node rightNode;

	public Node(int value) {
		this.value = value;
		rightNode = null;
		downNode = null;
	}

//	public Node buildNode(int[][] arr, int i, int j, int n) {
//		if (i > n - 1 || j > n - 1 || i < 0 || j < 0) {
//			return null;
//		}
//
//		Node tempNode = new Node(arr[i][j]);
//		tempNode.downNode = buildNode(arr, i + 1, j, n);
//		tempNode.rightNode = buildNode(arr, i, j + 1, n);
//
//		return tempNode;
//	}
//
//	public void showData(Node head) {
//		Node right;
//		Node down = head;
//
//		while (down != null) {
//			right = down;
//
//			while (right != null) {
//				System.out.print(right.value + " ");
//				right = right.downNode;
//			}
//
//			System.out.println();
//			down = down.rightNode;
//		}
//	}

}
