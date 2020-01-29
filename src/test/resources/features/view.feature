Feature: View

  Scenario: The transformation matrix for the default orientation
    Given From point 0 0 0
    And To point 0 0 -1
    And Up vector 0 1 0
    When View transform
    Then Transform is identity matrix

  Scenario: A view transformation matrix looking in positive z direction
    Given From point 0 0 0
    And To point 0 0 1
    And Up vector 0 1 0
    When View transform
    Then Transform is scaling -1 1 -1

  Scenario: The view transformation moves the world
    Given From point 0 0 8
    And To point 0 0 0
    And Up vector 0 1 0
    When View transform
    Then Transform is translation 0 0 -8

  Scenario: An arbitrary view transformation
    Given From point 1 3 2
    And To point 4 -2 8
    And Up vector 1 1 0
    When View transform
    Then Transform is 4 x 4 matrix "-0.50709 0.50709 0.67612 -2.36643 0.76772 0.60609 0.12122 -2.82843 -0.35857 0.59761 -0.71714 0.00000 0.00000 0.00000 0.00000 1.00000"