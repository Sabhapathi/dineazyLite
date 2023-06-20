Feature: Dineazy API Suite : LOGIN

  @e2e
  Scenario Outline: Validate Login to API
    Given user login with Credentials "<username>" and "<password>"

  Examples:
  |username               |password   |
  |swati.p@itprofound.com |Anish##0104|
  |girish.pr@itprofound.com |Test@1234|



