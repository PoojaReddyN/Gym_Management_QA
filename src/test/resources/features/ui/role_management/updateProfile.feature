Feature: UI Testing for update Profile

  Background: Navigating to the Gym Management Application
    Given I am on the Login Page of the Gym Management Application
    And I enter email and password

  @Regression
  Scenario Outline: Validating the Update Profile Page
    Given I click on the Edit account Profile button
    Then I should navigate to the update profile page
    And I enter "<name>", "<Your Target>", and "<Your Preferable Activity>"
    And I click on Save changes button
    Then I should see "<message>" in the "<locator>"

    Examples:Name is required
      | name     | Your Target | Your Preferable Activity | message                                  | locator |
      | John Doe | weight loss | crossfit                 | Profile Updated Successfully             | toast   |
      |          | Muscle Gain | Yoga                     |                         | inline   |
      | 123      | Muscle Gain | Yoga                     | Name can only contain letters and spaces | inline   |
      | Pooja    |             | crossfit                 | Profile Updated Successfully             | toast   |
      | Rakesh   | Endurance   |                          | Profile Updated Successfully             | toast   |