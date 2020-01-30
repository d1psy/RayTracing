Feature: World

  Background:
    Given Create world
    And Light point -10 10 -10 and color 1 1 1
    And Create sphere color 0.8 1 0.6 diffuse 0.7 specular 0.2
    And Create second sphere scaling 0.5 0.5 0.5
    When Add light and spheres
    Then World contains light -10 10 -10 1 1 1
    And World contains first sphere
    And World contains second sphere

  Scenario: Intersect a world with a ray
    Given Ray in world point 0 0 -5 vector 0 0 1
    When Intersect world
    Then Intersections count 4
    And Intersection world 0 is 4
    And Intersection world 1 is 4.5
    And Intersection world 2 is 5.5
    And Intersection world 3 is 6

  Scenario: Precomputing the state of an intersection
    Given Ray in world point 0 0 -5 vector 0 0 1
    And Create sphere shape
    And Get intersection from shape 4
    When Prepare computations
    Then Computations time is intersections time
    And Computations object is intersections object
    And Computations point is 0 0 -1
    And Computations eye vector is 0 0 -1
    And Computations normal vector is 0 0 -1

  Scenario: The hit, when an intersection occurs on the outside
    Given Ray in world point 0 0 -5 vector 0 0 1
    And Create sphere shape
    And Get intersection from shape 4
    When Prepare computations
    Then Computation inside false

  Scenario: The hit, when an intersection occurs on the inside
    Given Ray in world point 0 0 0 vector 0 0 1
    And Create sphere shape
    And Get intersection from shape 1
    When Prepare computations
    Then Computations point is 0 0 1
    And Computations eye vector is 0 0 -1
    And Computation inside true
    And Computations normal vector is 0 0 -1

  Scenario: Shading an intersection
    Given Ray in world point 0 0 -5 vector 0 0 1
    And Shape is first object
    And Get intersection from shape 4
    When Prepare computations
    And Shade hit
    Then Computation color 0.38066 0.47583 0.2855

  Scenario: Shading an intersection from the inside
    Given Light point 0 0.25 0 and color 1 1 1
    And Ray in world point 0 0 0 vector 0 0 1
    And Set new light to world
    And Shape is second object
    And Get intersection from shape 0.5
    When Prepare computations
    And Shade hit
    Then Computation color 0.90498 0.90498 0.90498
    And Ray in world point 0 0 0 vector 0 0 1

  Scenario: The color when a ray misses
    Given Ray in world point 0 0 -5 vector 0 1 0
    When Color at
    Then World color is 0 0 0

  Scenario: The color when a ray hits
    Given Ray in world point 0 0 -5 vector 0 0 1
    When Color at
    Then World color is 0.38066 0.47583 0.2855

  Scenario: The color with an intersection behind the ray
    Given Outer is first in world
    And Outer material ambient is 1
    And Inner is second in world
    And Inner material ambient is 1
    And Ray in world point 0 0 0.75 vector 0 0 -1
    When Color at
    Then Color is inner

  Scenario: The shadow when an object is between the point and the light
    Given Point in world 10 -10 10
    Then Is shadowed

  Scenario: There is no shadow when an object is behind the light
    Given Point in world -20 20 -20
    Then Is not shadowed