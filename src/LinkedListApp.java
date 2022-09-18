
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
		LinkedList.ListIterator i = numbers.listIterator();
		while( i.hasNext()) {
			System.out.println(i.next());
		}
		
	}

}
