
public class LinkedListApp {

	public static void main(String[] args) {
		
		LinkedList numbers = new LinkedList();
		numbers.addFirst(10);
		numbers.addFirst(20);
		numbers.addFirst(30);
		/*
		numbers.addLast(30);
		numbers.addLast(30);
		numbers.addLast(30);
		*/
		System.out.println(numbers.node(0));
		numbers.add(1, 100);		
		System.out.println(numbers.node(1)); 
	}

}
