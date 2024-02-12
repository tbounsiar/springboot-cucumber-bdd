# Created by Tahar at 02/02/2024
Feature: All crud requests testing

  Background:
    Given The data table address.sql exists
    And The data table "address" contains data from address.csv


  Scenario Template: Test <api> CRUD READ
    Given The data table <sqlFile> exists
    And The data table "<tableName>" contains data from <csvFile>
    When Run GET "<url>" request
    Then Expect the response status code to be: <status>
    And Expect the response body to be: <jsonFile>
    Examples:
      | api | sqlFile | tableName | csvFile | url | status | jsonFile |
      | client | client/client.sql | client | client/client.csv | /api/v1/client | 200 | client/all.json |
      | client | client/client.sql | client | client/client.csv | /api/v1/client/1 | 200 | client/1.json |


