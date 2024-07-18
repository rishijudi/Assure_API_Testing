package com.as.qa.test;

import com.as.qa.base.BaseSetup;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class AssetsOfInterest extends BaseSetup {

	@Test(priority = 0)
	public void testGetAssureAssetsEndpoint() throws IOException {
		 String baseUrl = loadProperties().getProperty("BaseURIH");
	        String assurePath = loadProperties().getProperty("assurePath");
	        String classId = loadProperties().getProperty("ClassId");
	        String nsfilter = loadProperties().getProperty("nsfilter");
	        
	        // Set the endpoint and query parameters
	        String endPointUrl = "/assets";
	        Map<String, String> queryParams = new HashMap<>();
	        queryParams.put("primaryClassificationId", classId);
	        queryParams.put("nsfilter", nsfilter);
	        String endpoint = baseUrl + assurePath + endPointUrl;

	        // Send the GET request
	        Response response = getRequest(endpoint, queryParams);

	        // Assert the status code
	        int statusCode = getStatusCode(response);
	        System.out.println(statusCode);
	        String asPrettyString = response.getBody().asString();
	        System.out.println(asPrettyString);
	        Assert.assertEquals(statusCode, 200, "Expected status code 200");
	        response.then().assertThat().body(matchesJsonSchemaInClasspath("AssetsOfInterest_Json\\get_assure_assets.json"));
	        long responseTime = response.time();
	        System.out.println("Response time: " + responseTime + " ms");
	        Assert.assertTrue(responseTime < 400, "Response time is too slow");
	        }
	@Test(priority = 1)
	public void testPostAssureAssetCodesByEquivalencesAndPrimaryClassification() throws IOException {
		 String baseUrl = loadProperties().getProperty("BaseURIH");
	        String assurePath = loadProperties().getProperty("assurePath");
	        String nsfilter = loadProperties().getProperty("nsfilter");
	        String endPointUrl = "/assetCodesWithEquivalency";
	        Map<String, String> queryParams1 = new HashMap<>();
	        queryParams1.put("nsfilter", nsfilter);
	        String endpoint = baseUrl + assurePath + endPointUrl;
	        Map<String, Object> body = new HashMap<>();
	        body.put("primaryClassificationId", "massurervt_2C8q8PXa:m ifc enum");
	        body.put("equivalencies",new String[]{"m Operations Class"});
	        Response res = postRequest(endpoint,queryParams1,body);
	        int statusCode = getStatusCode(res);
	        res.then().assertThat().body(matchesJsonSchemaInClasspath("AssetsOfInterest_Json\\assetsCodesWithEquivalency.json"));
	        long responseTime = res.time();
	        System.out.println("Response time: " + responseTime + " ms");
	        Assert.assertTrue(responseTime < 2000, "Response time is too slow");
}}
