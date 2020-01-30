Feature: Planes

  Scenario: The first normal of a plane is constant everywhere
    Given Plane
    When Plane normal at 0 0 0
    Then Plane normal vector 0 1 0

  Scenario: The second normal of a plane is constant everywhere
    Given Plane
    When Plane normal at 10 0 -10
    Then Plane normal vector 0 1 0

  Scenario: The third normal of a plane is constant everywhere
    Given Plane
    When Plane normal at -5 0 150
    Then Plane normal vector 0 1 0
