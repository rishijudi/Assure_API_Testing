package com.as.qa.base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseSetup {

	private RequestSpecification requestSpecification;
	String bearerToken;

	@BeforeMethod
	public void Setup() {
		BaseSetup1();
		setupBearerToken();

	}

	public String setupBearerToken() {
		String accessKey = loadProperties().getProperty("AccessKey");
		String secretKey = loadProperties().getProperty("SecretKey");
		String baseUrl = loadProperties().getProperty("BaseURI");
		String appId = loadProperties().getProperty("appCode");
		bearerToken = getBearerToken(accessKey, secretKey, baseUrl, appId);
		System.out.println(bearerToken);
		return bearerToken;
	}

	public void BaseSetup1() {
		RestAssured.baseURI = loadProperties().getProperty("BaseURI");
		requestSpecification = RestAssured.given().contentType(ContentType.JSON);
	}

	public static Properties loadProperties() {
		Properties properties = new Properties();
		try (FileInputStream fis = new FileInputStream("src/test/java/com/as/qa/utility/config.properties")) {
			properties.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	public String getBearerToken(String accessKey, String secretKey, String baseUrl, String appId) {
		try {
			String endpoint1 = "https://" + accessKey + ":" + secretKey + "@" + baseUrl
					+ "/passportsvc/api/v1/auth/token?appId=" + appId;
			System.out.println(endpoint1);

			Response response = requestSpecification.log().all().post(endpoint1);
			if (response.getStatusCode() == 200) {
				System.out.println(response.getBody().asString());
				return "Bearer " + response.jsonPath().getString("access_token");
			} else {
				System.out.println("Failed to obtain Bearer token. Status code: " + response.getStatusCode());
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Response getRequest(String endpoint, Map<String, String> queryParams) {
		try {
			requestSpecification.header("Authorization", bearerToken);

			if (queryParams != null && !queryParams.isEmpty()) {
				endpoint = constructEndpointWithQueryParams(endpoint, queryParams);
			}
			Response response = requestSpecification.log().all().get(endpoint);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private String constructEndpointWithQueryParams(String endpoint, Map<String, String> queryParams) {
		StringBuilder urlWithParams = new StringBuilder(endpoint);
		if (queryParams != null && !queryParams.isEmpty()) {
			urlWithParams.append("?");
			for (Map.Entry<String, String> entry : queryParams.entrySet()) {
				urlWithParams.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
			}
			urlWithParams.deleteCharAt(urlWithParams.length() - 1);
		}
		return urlWithParams.toString();
	}

	public Response postRequest(String endpoint, Map<String, String> queryParams1, Object requestBody) {
		try {
			requestSpecification.header("Authorization", bearerToken);

			if (queryParams1 != null && !queryParams1.isEmpty()) {
				endpoint = constructEndpointWithQueryParams(endpoint, queryParams1);
			}
			Response response = requestSpecification.body(requestBody).log().all().post(endpoint);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
//        try {
//            return requestSpecification.body(requestBody)
//                    .log().all().post(endpoint);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
	}

	public Response putRequest(String endpoint, Object requestBody) {
		try {
			return requestSpecification.body(requestBody).log().all().put(endpoint);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Response deleteRequest(String endpoint) {
		try {
			return requestSpecification.log().all().delete(endpoint);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int getStatusCode(Response response) {
		return response.getStatusCode();
	}

	public String getResponseBody(Response response) {
		return response.getBody().asString();
	}

	public String getValueFromResponse(Response response, String jsonPath) {
		return response.jsonPath().getString(jsonPath);
	}

}

