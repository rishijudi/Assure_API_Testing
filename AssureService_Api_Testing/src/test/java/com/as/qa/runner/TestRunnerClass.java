package com.as.qa.runner;

import com.as.qa.base.BaseSetup;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestRunnerClass  {
//
//    private String bearerToken;
//
//    @BeforeClass
//    public String setupBearerToken() {
//        String accessKey = loadProperties().getProperty("AccessKey");
//        System.out.println("AccessKey: " + accessKey);
//        String secretKey = loadProperties().getProperty("SecretKey");
//        System.out.println("SecretKey: " + secretKey);
//        String baseUrl = loadProperties().getProperty("BaseURI");
//        System.out.println("BaseURI: " + baseUrl);
//        String appId = loadProperties().getProperty("appCode");
//        System.out.println("AppCode: " + appId);
//        
//        String bearerToken = getBearerToken(accessKey, secretKey, baseUrl, appId);
//    }

    @Test
    public void testing() {
//		String endpoint = "https://qa3-api.in.invicara.com/assuresvc/api/v1/assets";
//		Map<String, String> queryParams = new HashMap<>();
//		queryParams.put("primaryClassificationId", "massurervt_2C8q8PXa%3Am+ifc+enum");
//		queryParams.put("nsfilter", "massurervt1_76xpj1NU");
//
// 
//		Response response = getRequest(endpoint, queryParams);
//
//		int statusCode = getStatusCode(response);
//		String responseBody = getResponseBody(response);
//		System.out.println("statusCode ::"+statusCode);
//		System.out.println("responseBody::"+responseBody);
//		Assert.assertEquals(statusCode, 200, "Expected status code 200");
        // Use bearerToken for your test
        System.out.println("Bearer Token:");

        // Example: Make API calls with the authenticated token
        // Response response = getRequest("/some_endpoint", null);
        // Assert.assertNotNull(response);
        // Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println("Test pass");
    }
}
