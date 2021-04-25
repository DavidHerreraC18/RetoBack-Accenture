Feature: sample karate test script

  Background:
    * url 'http://localhost:8080/api'
    
  Scenario: Delete an Order
      Given path '/delete-clientOrder/1'
      When method delete
      Then status 200