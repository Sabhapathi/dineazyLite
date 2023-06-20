Feature: Dineazy API Suite : Profile

  @sanity
  Scenario: Verify Profile details
    Given user login to Dineasy
    When user gets the profile
    Then user should verify that "swati" profile is found

