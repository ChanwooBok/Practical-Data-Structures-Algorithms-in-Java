
public class QueueApp {

	public static void main(String[] args) {
		Queue myQueue = new Queue(5);
		
		myQueue.insert(100);
		myQueue.insert(200);
		myQueue.insert(300);
		myQueue.insert(400);
		myQueue.insert(500);
		myQueue.insert(600);
		myQueue.insert(700);
		
		myQueue.view();
		
		
	}

}
