
public class LinkedListApp {

	public static void main(String[] args) {
		
		LinkedList numbers = new LinkedList();
		numbers.addLast(10);
		numbers.addLast(20);
		numbers.addLast(30);
		
		LinkedList.ListIterator i = numbers.listIterator();
		i.add(5); // [5 , 10 , 20 , 30]
		//i.next();
		//i.add(15);
		while(i.hasNext()) { //좋은 방법은 아니다. 따라서 Doubly LinkedList가 필요한것(prev로 서로연결됨)
			if( (int)i.next() == 20) {
				i.remove();
			}
		}
		System.out.println(numbers);
		
		
		
		
	}
	


}
