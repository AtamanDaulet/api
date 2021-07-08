package day02;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class day02Paths {

	public static void main(String[] args) throws IOException {		
		 //Read the file by using "Functional Programming"
        Files.lines(Paths.get("src/day02/LambdaFile02.txt")).forEach(System.out::println);

      
        System.out.println("========================");

        //Read the file in upper cases by using "Functional Programming"
        Files.lines(Paths.get("src/day02/LambdaFile02.txt")).map(t -> t.toUpperCase()).forEach(System.out::println);

        System.out.println("========================");

        //Read the file like just first row in upper case by using "Functional Programming"
        //1.Way:
        Files.lines(Paths.get("src/day02/LambdaFile02.txt")).limit(1).map(t -> t.toUpperCase()).forEach(System.out::println);
        //2.Way:
        System.out.println(Files.lines(Paths.get("src/day02/LambdaFile02.txt")).map(t -> t.toUpperCase()).findFirst());

        System.out.println("========================");

        //Read the file like just second row in lower case by using "Functional Programming"
        //1.Way:
        Files.lines(Paths.get("src/day02/LambdaFile02.txt")).skip(1).limit(1).map(String::toLowerCase).forEach(System.out::println);
        //2.Way:
        System.out.println(Files.lines(Paths.get("src/day02/LambdaFile02.txt")).skip(1).map(String::toLowerCase).findFirst());

        System.out.println("========================");

        //Find the word "lambda"(ignore cases) exists in how many lines
        System.out.println(Files.lines(Paths.get("src/day02/LambdaFile02.txt")).filter(t->t.toLowerCase().contains("lambda")).count());

        System.out.println("========================");

       //Print all different words from the file in a list
        List<String> words = Files.
                                lines(Paths.get("src/day02/LambdaFile02.txt")).
                                map(t->t.toLowerCase().replaceAll("_"," ").split(" ")).
                                flatMap(Arrays::stream).
                                map(t->t.replaceAll("\\W", "")).
                                distinct().
                                collect(Collectors.toList());
        System.out.println(words);

        System.out.println("========================");

        //Print all words from the file in natural order after converting them to upper cases on the console
        Files.
                lines(Paths.get("src/day02/LambdaFile02.txt")).
                map(t->t.toUpperCase().replaceAll("_"," ").split(" ")).
                flatMap(Arrays::stream).
                map(t->t.replaceAll("\\W", "")).
                distinct().
                sorted().
                forEach(System.out::println);

        System.out.println("========================");

        //Find the number of the word "LAMBDA" in the file. (Convert to upper case then find)
        long numOfLambda = Files.
                                lines(Paths.get("src/day02/LambdaFile02.txt")).
                                map(t->t.toUpperCase().replaceAll("_"," ").split(" ")).
                                flatMap(Arrays::stream).
                                map(t->t.replaceAll("\\W", "")).
                                filter(t->t.contains("LAMBDA")).
                                count();
        System.out.println(numOfLambda);

        System.out.println("========================");

        //Print the distinct words which contain "M" in natural order in the file. (Convert to upper case then find)
        Files.
                lines(Paths.get("src/day02/LambdaFile02.txt")).
                map(t->t.toUpperCase().replaceAll("_"," ").split(" ")).
                flatMap(Arrays::stream).
                map(t->t.replaceAll("\\W", "")).
                filter(t->t.contains("M")).
                distinct().
                sorted().
                forEach(System.out::println);

        System.out.println("========================");

        //Find the number of characters except spaces and punctuation marks used in the file
        long numOfChar = Files.
                            lines(Paths.get("src/day02/LambdaFile02.txt")).
                            map(t->t.replaceAll("_","").replaceAll("\\W","").
                            split("")).
                            flatMap(Arrays::stream).
                            count();
        System.out.println(numOfChar);

	}

}
