import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Set;

public class H7 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("P100.txt"));
		ArrayList<Product> prod = new ArrayList<>();
		
		while(in.hasNextLine()) {
			prod.add(new Product(in.next(), in.next(), in.nextFloat(), in.nextInt()));
		}
		System.out.println("1번 결과 출력 : ====================================== ");
		Collections.sort(prod);
		for (Product p : prod)
			System.out.println(p);


		System.out.println("2번 결과 출력 : ====================================== ");
		// 제품 번호의 역순으로 정렬한 후 출력
		Collections.sort(prod, new SortProducts().new noCmp());
		for (Product p : prod)
			System.out.println(p);

		System.out.println("3번 결과 출력 : ====================================== ");
		// 제품명 순, 재고 수량 순으로 정렬한 후 출력
		Collections.sort(prod, new SortProducts().new name_stock_Cmp());
		for (Product p : prod)
			System.out.println(p);

		System.out.println("4번 결과 출력 : ====================================== ");
		// 무게의 역순으로 정렬한 후 출력
		Collections.sort(prod, new SortProducts().new weight_no_Cmp());
		for (Product p : prod)
			System.out.println(p);

		System.out.println("5번 결과 출력 : ====================================== ");
		// 제품 번호를 키로 Product 객체를 HashMap에 저장한 후 제품 번호의 역순으로 HashMap의 내용을 출력
		HashMap<String, Product> prodMap = new HashMap<>();
		for (Product p : prod)
			prodMap.put(p.no, p);

		Collections.sort(prod, new SortProducts().new noCmp());
		for (Product p : prod)
			System.out.println(prodMap.get(p.no));
	}

}

class Product implements Comparable<Product> {
	String	name; // 제품명
	String	no;  // 제품 번호 (중복되지 않게 설정)
	float	weight;  // 무게(kg)
	int		inStock;  // 재고 수량
	
	public Product(String n, String no, float w, int st) {
		name = n;
		this.no = no;
		weight = w;
		inStock = st;
	}
	
	public String toString() {
		return String.format("[%s] %s, 무게 %fKg, 재고 %d개",no, name, weight, inStock);
	}

	@Override
	public int compareTo(Product o) {
		return inStock == o.inStock ? (int) (weight == o.weight ? name.compareTo(o.name) : (Float.compare(o.weight, weight))) : inStock - o.inStock;
	}
}

class SortProducts {
	class noCmp implements Comparator<Product> {
		@Override
		public int compare(Product o1, Product o2) {
			return o2.no.compareTo(o1.no);
		}
	}
	class name_stock_Cmp implements Comparator<Product> {
		@Override
		public int compare(Product o1, Product o2) {
			return o1.name.equalsIgnoreCase(o2.name) ? Integer.compare(o1.inStock, o2.inStock) : o1.name.compareTo(o2.name);
		}
	}
	class weight_no_Cmp implements Comparator<Product> {
		@Override
		public int compare(Product o1, Product o2) {
			return Float.compare(o1.weight, o2.weight) == 0.0 ? o1.no.compareTo(o2.no) : Float.compare(o2.weight, o1.weight);
		}
	}
}
