public class Class {
	
	public static void main(String[] potato) {
		
		Node head = new Node(4);
		Node nodeB = new Node(3);
		Node nodeC = new Node(3);
		Node nodeD = new Node(3);
		Node nodeE = new Node(3);
		Node nodeF = new Node(3);
		
		head.next = nodeB;
		nodeB.next = nodeC;
		nodeC.next = nodeD;
		nodeD.next = nodeE;
		nodeE.next = nodeF;
		
		System.out.println(countNodes(head) + " total nodes.");
	}

	static int countNodes(Node current) {
		int count = 1; //Starts with one
		while(current.next != null) {
			count++;
			current = current.next;
		}
		return count;
	}
}

class Node {
	int data;
	Node next;
	
	public Node(int data) {
		this.data = data;
	}
}