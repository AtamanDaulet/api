package day01;

import java.math.BigInteger;
import java.util.OptionalInt;
import java.util.Scanner;
import java.util.stream.IntStream;

public class IntStream01 {

	public static void main(String[] args) {
		int arr[] = {1,2,3,4,5};
		int x = 5;
		System.out.println(sum(arr));
		System.out.println(sum2(x));
		System.out.println(sum3(x));
		////Create a method to find the sum of even integers from x to y by using "functional programming"
		int x1 = 1 ;
		int y1 = 5;
		System.out.println(sum4(x1,y1));
		System.out.println(sum5(y1));
		System.out.println(sum6(x));
		System.out.println(sum7(4));
		System.out.println(pow8(4));
		System.out.println(pow9(-4));
		System.out.println(pow10(0 , 0));
		//Create a method to calculate the factorial of any number (5!=1x2x3x4x5)
		factorial (x);
	}
	public static BigInteger factorial2(int x) {		
		return IntStream.rangeClosed(1, x).mapToObj(BigInteger::valueOf).
				reduce(BigInteger.ONE, BigInteger::multiply);
	}
	public static int factorial(int x) {
		return IntStream.rangeClosed(1, x).reduce(1, Math::multiplyExact);
	}
	public static double pow10( int base, int pow) {		
		double result = IntStream.iterate(base, t -> t*base).limit(Math.abs(pow)).reduce(base, (x,y) -> y);
		
		if(base > 0 && pow > 0 ) {return result;}
		if(base > 0 && pow < 0 ) {return 1.0/result;}
		if(base < 0 && pow > 0 && pow%2 !=0 ) {return result;}
		if(base < 0 && pow > 0 && pow%2 ==0 ) {return -result;}
		if(base < 0 && pow < 0 && pow%2 !=0 ) {return 1.0/result;}
		if(base < 0 && pow < 0 && pow%2 ==0 ) {return 1.0/result;}
		if(base == 0 && pow > 0 ) {return 0;}
		if(base == 0 && pow < 0 ) {
			System.err.println("Using negative powers for zero is not acceptable");
			return -1111111;}
		if(base < 0 && pow == 0 ) {return 1;}
		if(base > 0 && pow == 0 ) {return 1;}
		if(base == 0 && pow == 0 ) {
			System.err.println("0th powers of ) is undefined");
			return -1111111;}
		
		return 0;
	}
	//Create a method to find the any negative power of 2 by using "functional programming"(Ex: -4th power of 2 is 1/2 * 1/2 * 1/2 * 1/2)
	public static double pow9( int pow) {
		int result = IntStream.iterate(2, t -> t*2).limit(Math.abs(pow)).reduce(2, (x,y) -> y);
		return 1.0/result;
	}
	public static int pow8( int pow) {
		return IntStream.iterate(3, t -> t*3).limit(pow).reduce(3, (x,y) -> y);
	}
	//Create a method to find the any positive power of 3 by using "functional programming" (Ex: 4th power of 3 is 3*3*3*3)
	public static OptionalInt sum7( int pow) {
		return IntStream.iterate(3, t -> t*3).limit(pow).reduce((x,y) -> y);
	}
	
	public static int sum6( int x) {
		return IntStream.iterate(1, t -> t+2).limit(x).sum();
	}
	
	public static int sum5( int y) {
		return IntStream.rangeClosed(1, y).filter(t -> t%2 != 0).sum();
	}
	
	public static int sum4(int x , int y) {
		return IntStream.rangeClosed(x, y).filter(t -> t%2 == 0).sum();
	}


	public static int sum3(int x) {
		return IntStream.rangeClosed(1, x).sum();
		
	}
	public static int sum2(int x) {
		return IntStream.range(1, x+1).sum();
		
	}

	public static int sum(int[] arr) {
		int sum = 0;
		for (int i : arr) {
			sum+=i;
		}		
		return	sum;
	}

}
