package testBaseNtUrls;

import org.junit.Before;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class TestBaseAgromonitoring {
	protected RequestSpecification spec;
	
	@Before
	public void setUp() {
		spec = new RequestSpecBuilder().
						setBaseUri("http://api.agromonitoring.com").
						build();
	}
}
