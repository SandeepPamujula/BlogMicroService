package com.cisco.BlogMicroService;

//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.hazelcast.util.Base64;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

import static com.jayway.restassured.RestAssured.*;
//import org.apache.commons.codec.binary.Base64;
//import java.util.Base64;
@RunWith(VertxUnitRunner.class)
public class AppTest {
	
	String baseURL = "http://localhost:8082";
	String userName = "sand";
	String password = "pass";
	Vertx vertx;
	
	
	@Before
    public void before(TestContext context) {
		VertxOptions options = new VertxOptions().setWorkerPoolSize(10);
		vertx = Vertx.vertx(options);
		vertx.deployVerticle(BlogMicroVerticle.class.getName(), stringAsyncResult -> {
			System.out.println(BlogMicroVerticle.class.getName() + "Deployment Completed");
		});
    }
	@After
    public void after(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

	@Test
	public void test001_validateBlogsGet() {
		
		String url = baseURL + "/Services/rest/blogs";
		System.out.println(url);
		given().
		when().
			get(url).
		then().
			statusCode(200);
//			body("[0].id", equalTo("55716669eec5ca2b6ddf5626")).
//			body("[1].id", equalTo("559e4331c203b4638a00ba1a"));
	}
//	@Test
//	public void test002_validatePostNewBlog(){
//		
//		String url = baseURL + "/Services/rest/blogs";
//		String headers = "sande:pass:sandeep:pamujlua:{\"timestamp\":1462608539,\"machineIdentifier\":820102,\"processIdentifier\":19386,\"counter\":4621142,\"timeSecond\":1462608539,\"time\":1462608539000,\"date\":1462608539000}\"";
//		given()
//			.when()
//				.header("Authorization", "Basic"+Base64.encode(headers.getBytes()))
//				.post(url)
//			.then()
//				.statusCode(204);
//	}
}
