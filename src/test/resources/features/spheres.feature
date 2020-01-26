Feature: Spheres

  Scenario: The normal on a sphere at a point on the x axis
    Given Create new sphere 1
    When Normal sphere point 1.0 0.0 0.0
    Then Normal gives vector 1.0 0.0 0.0

  Scenario: The normal on a sphere at a point on the y axis
    Given Create new sphere 1
    When Normal sphere point 0.0 1.0 0.0
    Then Normal gives vector 0.0 1.0 0.0

  Scenario: The normal on a sphere at a point on the z axis
    Given Create new sphere 1
    When Normal sphere point 0.0 0.0 1.0
    Then Normal gives vector 0.0 0.0 1.0

  Scenario: The normal on a sphere at a nonaxial point
    Given Create new sphere 1
    When Normal sphere point 0.57735 0.57735 0.57735
    Then Normal gives vector 0.57735 0.57735 0.57735

  Scenario: The normal is a normalized vectorpoint
    Given Create new sphere 1
    When Normal sphere point 0.57735 0.57735 0.57735
    Then Normalize normal gives normal

  Scenario: Computing the normal on a translated sphere
    Given Create new sphere 1
    And Set transform translation 0.0 1.0 0.0
    When Normal sphere point 0.0 1.70711 -0.70711
    Then Normal gives vector 0.0 0.70711 -0.70711

  Scenario: Computing the normal on a transformed sphere
    Given Create new sphere 1
    And Multiply scaling 1.0 0.5 1.0 by rotation 5.0
    And Set transform of existing
    When Normal sphere point 0.0 0.70711 -0.70711
    Then Normal gives vector 0.0 0.968 -0.25096