Feature: Intersection

  Scenario: An intersection encapsulates t and object
    Given Create intersection sphere 1
    When Intersection 3.5 sphere
    Then Intersection time 3.5
    And Object is sphere

  Scenario: Aggregating intersections
    Given Create intersection sphere 1
    And Intersection 1.0 sphere
    And Second intersection 2.0 sphere
    When List from two intersections
    Then List size is 2
    And List time 0 is 1.0
    And List time 1 is 2.0

  Scenario: The hit, when all intersections have positive t
    Given Create intersection sphere 1
    And Intersection 1.0 sphere
    And Second intersection 2.0 sphere
    When List from two intersections
    And Hit intersections list
    Then Expected intersection 0

  Scenario: The hit, when some intersections have negative t
    Given Create intersection sphere 1
    And Intersection -1.0 sphere
    And Second intersection 1.0 sphere
    When List from two intersections
    And Hit intersections list
    Then Expected intersection 1

  Scenario: The hit, when all intersections have negative t
    Given Create intersection sphere 1
    And Intersection -3.0 sphere
    And Second intersection -2.0 sphere
    When List from two intersections
    And Hit intersections list
    Then Expected intersection is nothing

  Scenario: The hit is always the lowest non negative intersection
    Given Create intersection sphere 1
    And Intersection 5.0 sphere
    And Second intersection 7.0 sphere
    And Third intersection -3.0 sphere
    And Forth intersection 2.0 sphere
    When List from four intersections
    And Hit intersections list
    Then Expected intersection 3

  @aaa
  Scenario: Precomputing the reflection vector
    Given Create shape plane
    And Create ray for plane point 0 1 -1 vector 0 -0.70711 0.70711
    And Intersection 1.41421 shape
    When Prepare computations for plane
    Then Reflect is vector 0 0.70711 0.70711
