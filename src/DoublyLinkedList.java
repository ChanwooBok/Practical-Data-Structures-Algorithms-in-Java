
public class DoublyLinkedList {
	
	private Node head;
	private Node tail;
	private int size;
	private class Node{
		private Object data;
		private Node next;
		private Node prev;
		public Node(Object input) {
			this.data = input;
			this.next = null;
			this.prev = null;
		}
		public String toString() {
			return String.valueOf(this.data);
		}
	}
	public void addFirst(Object input) {
		Node newNode = new Node(input);
		newNode.next = head;
		
		if(head != null) {
			head.prev = newNode;			
		}
		
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
			newNode.prev = tail;
			tail = newNode;
			size++;
		}
	}
	public Node node(int index) {
		 // 각 노드가 next , prev로 앞뒤로 연결되어 있으므로, 사이즈에 따라 head에서부터 탐색하는게 효율적일수도 ,tail서부터 탐색하는게 효율적일수도 있다.
		if( index < size/2) { //head부터가 효율적.
			Node x = head;
			for( int i = 0 ; i < index ; i++) {
				x = x.next;
			}
			return x;	
		}else { // tail부터가 효율적.
			Node x = tail;
			for(int i = size-1 ; i > index ; i--) {
				x = x.prev;
			}
			return x;
		}
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
			if(temp2 != null) { // temp1이 null일 확률은 없지만(temp1이 null인 경우는 addFirst로 처리하기 때문), temp2는 노드가 1개일때 add하는경우에는 Null이 된다.따라서 if문 처리
				temp2.prev = newNode;
			}
			newNode.prev = temp1;
			size++;
			if(newNode.next == null) { // temp2가 null일 경우가 이 경우에 해당한다. newNode뒤에 아무 노드도 없는 상황.
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
		if(head != null){ // 노드가 1개만 존재할시에는 head = head.next 에서 head.next가 null이므로 head가 null이 될 가능성이 있으므로 체크해준다.
			head.prev = null; //  prev 끊어주기. 
		}
		Object returnData = temp.data;
		temp = null;
		size--;
		return returnData;
	}
	
	public Object remove(int k ) {
		if( k == 0 ) {
			return removeFirst();
		}
		Node temp = node(k-1); //노드를 제거하려면 그 전 노드를 반드시 알아야한다. ->모든 노드는 링크되어있기때문에..
		Node todoDeleted = temp.next;
		temp.next = temp.next.next;
		Object returnData =  todoDeleted.data;
		if(todoDeleted == tail) {
			tail = temp; // 삭제하려는 노드가 tail 이면 temp로 설정한다. 
		}
		if(temp.next.prev !=null) { //노드가 2개뿐인경우, temp.next.prev 가null이므로 이 경우도 체크해준다. 
			temp.next.prev = temp;
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
	public ListIterator listIterator() {
		return new ListIterator();
	}
	class ListIterator{
		private Node next;
		private Node lastReturned;
		private int nextIndex;
		
		ListIterator(){
			next = head;
		}
		
		public Object next() {
			lastReturned = next; // 한번이라도 next()를 수행했다면 lastReturned가 존재할것이고,그게바로 선택된 노트이다.
			next = next.next;
			nextIndex++; // next()를 수행한 횟수 -> hasNext()구현을 위해 세어줘 한다.
			return lastReturned.data;
		}
		// singly linkedList 에서는 previous와 같은 기능 구현이 불가능하다. 왜냐하면, next로 다음노드는 알 수 있지만 앞 노드는 알 방법이 없기 때문.
		public boolean hasNext() {
			return nextIndex < size(); // nextIndex=size()  인 경우에는 next=null이다.
		}
		public void add(Object input) {
			Node newNode = new Node(input);
			//first input
			
			// 맨 처음 add 와 중간에 add 하는것을 구분해주어야 한다. 
			// 어떻게? lastReturned의 값의 존재유무로 해주겠다. ( 있다는것은 next()를 수행했다는것 -> 중간이란뜻, 없다면 next()를 한번도 수행하지 않았다는것-> 맨 처음이란뜻 )
			if(lastReturned == null) {
				head = newNode;
				newNode.next = next;
			}else if(lastReturned != null) {
				lastReturned.next = newNode;
				newNode.next = next.next;
			}
			lastReturned = newNode; // 항상 새로 넣은 노드를 할당하기.
			nextIndex++; //새로운 노드가 앞이든 뒤든 추가 될 때마다 더해주기.
			size++;
		}
		public void remove() {
			if(nextIndex == 0 ) {
				throw new IllegalStateException();
			}
			DoublyLinkedList.this.remove(nextIndex-1); // 지우고자 하는 타겟은 lastReturned 노드이다. ( 선택된 노드 )
			nextIndex--;
		}
	}
	
	
	
}
