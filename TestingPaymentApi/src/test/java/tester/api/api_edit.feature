Feature: sample karate test script

  Background:
    * url 'http://localhost:8080/api'

  Scenario: Change an order
    * def orderProducts =
      """
      [
        {
          "id": null,
          "cost": "90000",
          "quantity": "3",
          "product": {
            "id": 1,
            "name": "Vinilo Edicion Estandar",
            "price": 30000
          }
        },
        {
          "id": null,
          "cost": "70000",
          "quantity": "1",
          "product": {
            "id": 2,
            "name": "Vinilo Edicion Deluxe",
            "price": 70000
          }
        }
      ]
      """

    Given path '/edit-clientOrder/1'
    And request orderProducts
    When method put
    Then status 200