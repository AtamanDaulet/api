package techproedenglish01.techproedenglish01api;

public class Review {
	/*
	 * 1)Which ways do you know for Assertion? 
	 * 		a)body(path, Matchers) 
	 * 		b)JsonPath ==>
				 * For navigating in Json Data / JsonPath Class has many specific methods like
				 * getString() etc. 
	 * 		c)GSON ==> For converting Json Data to Java Object
	 * 			(De-Serialization) - GSON can do "Serialization" as well 
	 * 		d)ObjectMapper Class
				 * ==> For converting Json Data to Java Object (De-Serialization) - GSON can do
				 * "Serialization" as well 
	 * 		e)POJO Classes ==> Create POJO Class for response
	 * 			Json Data, then create POJO Objects and use it in assertion
	 * 
	 * 2)Which ways do you know to store "Request Body"(Post, Put, Patch) or
	 * "Expected Data"(Get, Delete)? 
		 * a)Map 
		 * b)JSONObject 
		 * c)GSON 
		 * d)ObjectMapper Class
		 * e)POJO Classes
	 * 
	 * 3)How many different ways do you know in Hard Assertion? 
		 * a)body()
		 * c)assertEquals(), assertTrue(), assertFalse()
	 * 
	 * 3)How many steps do you use in Soft Assertion? 
		 * a)Creation SoftAssert Object
		 * b)Using assertEquals(), assertTrue(), assertFalse() methods with the
			 * SoftAssert Object 
		* c)Using assertAll() with the SoftAssert Object ==> Do not
			 * forget
	 * 
	 * 4)How do you set the base url? 
		 * a)I use "RequestSpecification Interface" as data type 
		 * b)I use RequestSpecBuilder() to create object. 
		 * c)I use setBaseUrl()
		 	* with base url as a parameter then I use build() method. (Optional to tell)
		 * d)I use JUnit "@Before" annotation at the top of the method to make it run
		 	* before every test method. (Optional to tell)
	 */
}
