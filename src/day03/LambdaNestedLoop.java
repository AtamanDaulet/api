package day03;

import java.util.stream.IntStream;

public class LambdaNestedLoop {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 1; i <= 10; i++) {
			int sum =0;
			for (int j = 0; j <= i+3; j++) {
				sum +=j;
			}
			System.out.print(sum + " ");
		}
		System.out.println("-----------");
		
		IntStream.rangeClosed(1, 10).
		mapToObj(t -> IntStream.rangeClosed(t, t+3).sum()).forEach(System.out::println);
		//Nested loop with Functional Programming
//        IntStream.rangeClosed(1, 10).mapToObj(t->IntStream.rangeClosed(t, t+3).sum()).forEach(System.out::println);
		
	}

}
