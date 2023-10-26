Feature: Customer details retrieval
  Retrival of customer details with encrypted content

  Scenario Outline: get customer details happy path
    Given user provided valid request with "<customerId>"
    When customer API is called
    Then customer details returned to user
    And encrypted content contains customer contacts
    Examples:
      | customerId         |
      | test-customer-id-1 |
      | test-customer-id-2 |

  Scenario: get customer details bad request error
    Given user provided request without customerId
    When customer API is called
    Then error response returned to user
