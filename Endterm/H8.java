import java.util.*;

public class H8 {

	public static void main(String[] args) {
		CQueue<Integer> iq = new CQueue<>(5); // CQueue의 크기는 5, 최대 4개의 객체 저장 가능
		CQueue<Rect> rectq = new CQueue<>(5);
		CQueue<Circle> cq = new CQueue<>(5);

		for(int i=1;i < 10;i++) {
			if(!iq.add(i)) break;  // 4까지만 추가되고 이후엔 for-loop를 빠져 나감!!
		}

		rectq.add(new Rect(10,20,"red"));
		rectq.add(new Rect(4,30,"blue"));
		rectq.add(new Rect(11,20,"white"));
		rectq.add(new Rect(4,20,"yellow"));
		rectq.add(new Rect(1,1,"black"));	// CQueue의 크기가 5이므로 이 객체는 추가 안됨

		cq.add(new Circle(20,"red"));
		cq.add(new Circle(40,"blue"));
		cq.add(new Circle(10,"white"));
		cq.add(new Circle(4,"yellow"));
		cq.add(new Circle(1,"black"));		// CQueue의 크기가 5이므로 이 객체는 추가 안됨

		// 각 CQueue의 내용을 출력
		System.out.println("[CQueue에 객체 추가] =========================================");
		System.out.println("크기="+iq.size()+"|| "+iq);
		System.out.println("크기="+rectq.size()+"|| "+rectq);
		System.out.println("크기="+cq.size()+"|| "+cq);

		iq.remove(); iq.remove(); // 2개의 정수  항목 삭제
		iq.add(11); iq.add(22);	// 2개의 정수 추가
		rectq.remove(); rectq.remove(); // 2개의 Rect 객체 삭제
		rectq.add(new Rect(11,11,"orange")); rectq.add(new Rect(22,22,"pink"));	// 2개의 Rect 객체 추가
		cq.remove(); cq.remove(); // 2개의 Circle  객체 삭제
		cq.add(new Circle(11,"orange")); cq.add(new Circle(22,"pink"));  // 2개의 Circle 객체 추가

		// 각 CQueue의 내용을 출력
		System.out.println("[CQueue에 객체 삭제 후 추가] =========================================");
		System.out.println("크기="+iq.size()+"|| "+iq);
		System.out.println("크기="+rectq.size()+"|| "+rectq);
		System.out.println("크기="+cq.size()+"|| "+cq);
	}
}
