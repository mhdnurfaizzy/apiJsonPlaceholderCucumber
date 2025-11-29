package stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

public class apiStep {

    private String endpoint;
    private Response response;
    private Map<String, String> requestBody;

    @Given("I set the POST endpoint {string}")
    public void i_set_the_post_endpoint(String url) {
        endpoint = "https://jsonplaceholder.typicode.com/posts";
    }

    @When("I create a new post with:")
    public void i_create_a_new_post_with(Map<String, String> dataTable) {
        requestBody = dataTable;

        response = given()
                .header("Content-type", "application/json")
                .body(dataTable)
                .when()
                .post(endpoint);
    }

    @Then("the response should contain the same post data")
    public void the_response_should_contain_the_same_post_data() {
        assertNotNull("Response is null!", response);

        assertEquals(requestBody.get("title"), response.jsonPath().getString("title"));
        assertEquals(requestBody.get("body"), response.jsonPath().getString("body"));
        assertEquals(Integer.parseInt(requestBody.get("userId")), response.jsonPath().getInt("userId"));
    }

    @Then("the response should match the JSON schema {string}")
    public void the_response_should_match_the_json_schema(String schemaFile) {
        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/" + schemaFile));
    }

    // ================== RETRIEVE POSTS =====================

    @Given("I set the GET endpoint {string}")
    public void i_set_the_get_endpoint(String url) {
        endpoint = "https://jsonplaceholder.typicode.com/posts";
    }

    @When("I send a GET request")
    public void i_send_a_get_request() {
        response = given()
                .when()
                .get(endpoint);
    }

    @Then("each post in the response should have a non-null id")
    public void each_post_should_have_non_null_id() {
        assertNotNull("Response is null. Make sure GET request was sent.", response);

        List<Map<String, Object>> posts = response.jsonPath().getList("");
        assertThat("Response should contain posts", posts.size(), greaterThan(0));

        for (Map<String, Object> post : posts) {
            assertThat("Post ID should not be null", post.get("id"), notNullValue());
        }
    }

    // ================== DELETE POST =====================

    @Given("I set the DELETE endpoint {string}")
    public void i_set_the_delete_endpoint(String url) {
        endpoint = "https://jsonplaceholder.typicode.com/posts/1";
    }

    @When("I send a DELETE request")
    public void i_send_a_delete_request() {
        response = given()
                .when()
                .delete(endpoint);
        System.out.println("Response: " + response.getBody().asString());

    }

    @Then("the status code should be {int}")
    public void the_status_code_should_be(Integer status) {
        assertEquals(status.intValue(), response.getStatusCode());
    }

    @Then("the response body should indicate successful deletion")
    public void the_response_body_should_indicate_successful_deletion() {
        // JSONPlaceholder returns {} for DELETE
        assertEquals("{}", response.getBody().asString().trim());
    }

    // ================== UPDATE POST =====================

    @Given("I set the PUT endpoint {string}")
    public void i_set_the_put_endpoint(String url) {
        endpoint = "https://jsonplaceholder.typicode.com/posts/1";
    }

    @When("I update the post with:")
    public void i_update_the_post_with(Map<String, String> dataTable) {
        requestBody = dataTable;

        response = given()
                .header("Content-type", "application/json")
                .body(dataTable)
                .when()
                .put(endpoint);
        System.out.println("Response: " + response.getBody().asString());

    }

    @Then("the response should contain the updated post data")
    public void the_response_should_contain_the_updated_post_data() {
        assertEquals(requestBody.get("title"), response.jsonPath().getString("title"));
        assertEquals(requestBody.get("body"), response.jsonPath().getString("body"));
        assertEquals(Integer.parseInt(requestBody.get("userId")), response.jsonPath().getInt("userId"));
    }
}
