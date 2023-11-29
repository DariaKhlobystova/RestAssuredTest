Feature: Scenario to test CRUD flow

  Scenario: Test for end to end CRUD operations
    Given we have a booking request
      | firstname | lastname | additionalneeds | depositpaid | checkout   | checkin    | totalprice |
      | Jerry     | Helpert  | mineral water   | true        | 2024-02-02 | 2024-01-01 | 1000       |
    When we send the request to create booking api
    Then  HTTP response status code should be 200
    And validate that response has bookingid

    When store the booking from created response

    When we "retrieve" the previously created booking
    Then  HTTP response status code should be 200

    When we "update" the previously created booking
    Then  HTTP response status code should be 200

    When we partial-update the previously created booking with below data
    | additionalneeds |
    | breakfast       |
    Then  HTTP response status code should be 200

    When we "delete" the previously created booking
    Then  HTTP response status code should be 201