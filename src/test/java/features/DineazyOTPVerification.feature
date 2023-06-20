Feature: Dineazy API Suite : OTP Verification

  @sanity @wip
  Scenario: OTP Sent and Verification
    Given user login to Dineasy
    When user sends OTP
    Then user should verify OTP sent is correct