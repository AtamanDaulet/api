package day01;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class File01 {

	public static void main(String[] args) throws IOException {
		//Read the text inside the LambdaFile05
		Files.lines(Paths.get("src/day01/LambdaFile01.txt"),StandardCharsets.ISO_8859_1).forEach(System.out::println);
//		new BufferedReader(new InputStreamReader(new FileInputStream("src/day01/LambdaFile01.txt"),"utf-8"));
		
//		List<String> lines = Files.readAllLines(Paths.get("src/day01/LambdaFile01.txt"));
		
		System.out.println("====================");
	/*	
		//Read the text inside the LambdaFile05 in uppercases
		Files.lines(Paths.get("/API/src/day01/LambdaFile01.txt")).map(String::toUpperCase).forEach(System.out::println);
		
		System.out.println("====================");
		
		//Read just the first row from the file LambdaFile05 in lower cases
		//1.Way:
		Files.lines(Paths.get("/API/src/day01/LambdaFile01.txt")).limit(1).map(String::toLowerCase).forEach(System.out::println);
		//2.Way:
		System.out.println(Files.lines(Paths.get("/API/src/day01/LambdaFile01.txt")).map(String::toLowerCase).findFirst());
*/
	}

}
