Feature: JSONPlaceholder Posts API

  Scenario: Create a new post
    Given I set the POST endpoint "/posts"
    When I create a new post with:
      | title  | Learn API Testing                                |
      | body   | Practicing API testing with JSONPlaceholder      |
      | userId | 101                                              |
    Then the response should contain the same post data
    And the response should match the JSON schema "createPostSchema.json"

  Scenario: Retrieve all posts
    Given I set the GET endpoint "/posts"
    When I send a GET request
    Then each post in the response should have a non-null id
    And the response should match the JSON schema "retrieveSchema.json"

  Scenario: Delete a post
    Given I set the DELETE endpoint "/posts/1"
    When I send a DELETE request
    Then the status code should be 200
    And the response body should indicate successful deletion
    And the response should match the JSON schema "deleteSchema.json"

  Scenario: Update a post
    Given I set the PUT endpoint "/posts/1"
    When I update the post with:
      | title  | Updated Post Title             |
      | body   | This is the updated body content. |
      | userId | 99                              |
    Then the response should contain the updated post data
    And the response should match the JSON schema "updateSchema.json"
