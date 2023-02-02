Feature: Validating Place APIs

  @AddPlace
  Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>", "<language>" and "<address>"
    When user calls "addPlaceAPI" with "Post" http request
    Then the API call is sucess with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "getPlaceAPI"
    Examples:
      | name    | language | address            |
      | AAhouse | English  | World cross center |
     # | Poonam  | English  | A201, ahmedabad    |

  @DeletePlace
  Scenario: Verify if Delete Place functionality is working
    Given DeletePlace payload
    When user calls "deletePlaceAPI" with "Post" http request
    Then the API call is sucess with status code 200
    And "status" in response body is "OK"