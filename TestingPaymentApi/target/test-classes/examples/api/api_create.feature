Feature: Test Create An Order

  Background:
    * url 'http://localhost:8080/api'

  Scenario: Create an order
    Given path '/create-clientOrder/1'
    When method post
    Then status 201