
public class LinkedList {
	
	private Node head;
	private Node tail;
	private int size;
	private class Node{
		private Object data;
		private Node next;
		public Node(Object input) {
			this.data = input;
			this.next = null;
		}
		public String toString() {
			return String.valueOf(this.data);
		}
	}
	public void addFirst(Object input) {
		Node newNode = new Node(input);
		newNode.next = head;
		head = newNode;
		size++;
		if(head.next == null) {
			tail = head;
		}
	}
	
	public void addLast(Object input) {
		Node newNode = new Node(input);
		
		if(size == 0) {
			addFirst(input);
		}else {
			tail.next = newNode;
			tail = newNode;
			size++;
		}
	}
	public Node node(int index) {
		Node x = head;
		for( int i = 0 ; i < index ; i++) {
			x = x.next;
		}
		return x;
	}
	public void add(int k , Object input ) {
		if( k == 0 ) { // k : index
			addFirst(input);
		}else {
			Node temp1 = node(k-1);
			Node temp2 = temp1.next;
			Node newNode = new Node(input);
			temp1.next = newNode;
			newNode.next = temp2;
			size++;
			if(newNode.next == null) {
				tail = newNode;
			}
		}
	}
	 // [10 , 20 ,30 ]
	public String toString() {
		if(head == null) {
			return "";
		}
		Node temp = head;
		String str = "[";
		
		while(temp.next != null) {
			str += temp.data + " , "; // [ 10 , 20 , 
			temp = temp.next;
		}
		
		str += temp.data; // Because tail node's next is null which means it doesn't have chance to add its data
		
		return str+"]";
	}
	
	public Object removeFirst() {
		Node temp = head;
		head = head.next;
		Object returnData = temp.data;
		temp = null;
		size--;
		return returnData;
	}
	
	public Object remove(int k ) {
		if( k == 0 ) {
			return removeFirst();
		}
		Node temp = node(k-1);
		Node todoDeleted = temp.next;
		temp.next = temp.next.next;
		Object returnData =  todoDeleted.data;
		if(todoDeleted == tail) {
			tail = temp;
		}
		todoDeleted = null;
		size--;
		return returnData;
	}
	
	public Object removeLast() {
		return remove(size-1);
	} // 그냥 tail만 제거하면 안되는 이유 : linkedList는 제거 하려는 노드 앞에 노드까지도 정보를 바꿔줘야 하기 때문에 앞에서부터 하나씩 node.next를 업데이트 시켜줘야 한다.
	// 그래서 singleLinkedList는 노드가 많아질수록 마지막 노드를 제거하는데 데이터가 많이 소요된다.
	
	public int size() {
		return size;
	}
	public Object get(int k) {
		Node temp = node(k);
		return temp.data;
	}
	public int indexOf(Object data) {
		
		Node temp = head;
		int index = 0 ;
		
		while(temp.next != null) {
			temp = temp.next;
			index++;
			if(temp.next == null) {
				return -1;
			}
		}
		return index;
	}
	
	
	
	
}
