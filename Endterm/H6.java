public class H6 {
	public static void main(String[] args) {
		Set	set1 = new Set(args[1]);
		Set	set2 = new Set(args[2]);
		Set	result;
		
		result = set1.Union(set2);
		result = set1.Minus(set2);
		result = set1.Intersect(set2);
	}
}
class Set {
	StringBuffer	element;

	public Set(String	e) {	// 문자열 e를 집합 형태의 문자열로 element에 초기화

	}

	public Set Union(Set s) {
		return null;
	}

	public Set Minus(Set s) {
		return null;
	}

	public Set Intersect(Set s) {
		return null; 
	}

	public int Inclusion(Set s) {	// 집합 this와 s의 포함관계를 -1, 0, 1로 반환한다.
		return 0;
	}

	public String toString() {
		return "";
	}
}