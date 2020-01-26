Feature: Rays

  Scenario: Creating and querying a ray
    Given Origin point 1.0 2.0 3.0
    And Direction vector 4.0 5.0 6.0
    When Create ray
    Then Ray origin is origin
    And Ray direction is direction

  Scenario: Computing a point from a distance
    Given Create ray origin 2.0 3.0 4.0 direction 1.0 0.0 0.0
    Then Position 0 is point 2.0 3.0 4.0
    And Position 1 is point 3.0 3.0 4.0
    And Position -1 is point 1.0 3.0 4.0
    And Position 2.5 is point 4.5 3.0 4.0

  Scenario: A ray intersects a sphere at two points
    Given Create ray origin 0.0 0.0 -5.0 direction 0.0 0.0 1.0
    And Create sphere 1
    When Intersects
    Then Count is 2
    And Count 0 is 4.0
    And Count 1 is 6.0

  Scenario: A ray intersects a sphere at a tangent
    Given Create ray origin 0.0 1.0 -5.0 direction 0.0 0.0 1.0
    And Create sphere 1
    When Intersects
    Then Count is 2
    And Count 0 is 5.0
    And Count 1 is 5.0

  Scenario: A ray misses a sphere
    Given Create ray origin 0.0 2.0 -5.0 direction 0.0 0.0 1.0
    And Create sphere 1
    When Intersects
    Then Count is 0

  Scenario: A ray originates inside a sphere
    Given Create ray origin 0.0 0.0 0.0 direction 0.0 0.0 1.0
    And Create sphere 1
    When Intersects
    Then Count is 2
    And Count 0 is -1.0
    And Count 1 is 1.0

  Scenario: A sphere is behind a ray
    Given Create ray origin 0.0 0.0 5.0 direction 0.0 0.0 1.0
    And Create sphere 1
    When Intersects
    Then Count is 2
    And Count 0 is -6.0
    And Count 1 is -4.0

  Scenario: Translating a ray
    Given Create ray origin 1.0 2.0 3.0 direction 0.0 1.0 0.0
    And Translation matrix 3.0 4.0 5.0
    When Transform ray
    Then Transformed ray origin 4.0 6.0 8.0
    And Transformed ray direction 0.0 1.0 0.0

  Scenario: Scaling a ray
    Given Create ray origin 1.0 2.0 3.0 direction 0.0 1.0 0.0
    And Scaling matrix 2.0 3.0 4.0
    When Transform ray
    Then Transformed ray origin 2.0 6.0 12.0
    And Transformed ray direction 0.0 3.0 0.0