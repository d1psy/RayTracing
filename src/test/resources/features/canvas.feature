Feature: Canvas

  Scenario: Creating a canvas
    Given Canvas 10, 20
    Then Canvas width is 10
    And Canvas height is 20
    And Every pixel of color is 0

  Scenario: Writing pixels to a canvas
    Given Canvas 10, 20
    And Color to paint 1.0 0.0 0.0
    When Write to pixel 2 3
    Then Pixel at 2 3 is 1.0 0.0 0.0