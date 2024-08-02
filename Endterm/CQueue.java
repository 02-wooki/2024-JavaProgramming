import java.util.concurrent.atomic.AtomicReferenceArray;

public class CQueue<T> {
	private Object[] v;
	private int	front, rear, limit;
	
	public CQueue(int s) {
		v = new Object[s];

		front = 0;
		rear = 0;
		
		limit = s;
	}
	
	public int size() {
		return (rear - front + limit) % limit;
	}
	
	public boolean add(T item) {

		if (size() >= limit - 1)
			return false;

		v[rear] = item;
		rear = (rear + 1) % limit;

		return true;
	}

	public T remove() {

		if (front == rear)
			return null;

		T t = (T) v[front];
		front = (front + 1) % limit;

		return null;
	}
	
	public String toString() {
		String tmp = "";

		for (int i = 0; i < size(); i++)
			tmp += "[" + v[(front + i) % limit] + "]";

		return tmp;
	}
}
