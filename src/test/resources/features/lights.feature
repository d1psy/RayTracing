Feature: Lights

  Background:
    Given Material 1.0 1.0 1.0
    And Light position 0.0 0.0 0.0

  Scenario: A point light has a position and intensity
    Given Intensity 1.0 1.0 1.0
    When Create light
    Then Intensity is 1.0 1.0 1.0
    And Light position is 0.0 0.0 0.0

  Scenario: The default material
    Then Material color 1.0 1.0 1.0
    And Material ambient is 0.1
    And Material diffuse is 0.9
    And Material specular is 0.9
    And Material shininess is 200.0

  Scenario: Lighting with the eye between the light and the surface
    Given Eye vector 0.0 0.0 -1.0
    And Normal vector 0.0 0.0 -1.0
    And Point light point 0.0 0.0 -10.0 color 1.0 1.0 1.0
    When Create lighting result
    Then Result is color 1.9 1.9 1.9

  Scenario: Lighting with the eye between light and surface, eye offset 45°
    Given Eye vector 0.0 0.70711 -0.70711
    And Normal vector 0.0 0.0 -1.0
    And Point light point 0.0 0.0 -10.0 color 1.0 1.0 1.0
    When Create lighting result
    Then Result is color 1.0 1.0 1.0

  Scenario: Lighting with eye opposite surface, light offset 45°
    Given Eye vector 0.0 0.0 -1.0
    And Normal vector 0.0 0.0 -1.0
    And Point light point 0.0 10.0 -10.0 color 1.0 1.0 1.0
    When Create lighting result
    Then Result is color 0.7 0.7 0.7

  Scenario: Lighting with eye in the path of the reflection vector
    Given Eye vector 0.0 -0.70711 -0.70711
    And Normal vector 0.0 0.0 -1.0
    And Point light point 0.0 10.0 -10.0 color 1.0 1.0 1.0
    When Create lighting result
    Then Result is color 1.6 1.6 1.6

  Scenario: Lighting with the light behind the surface
    Given Eye vector 0.0 0.0 -1.0
    And Normal vector 0.0 0.0 -1.0
    And Point light point 0.0 0.0 10.0 color 1.0 1.0 1.0
    When Create lighting result
    Then Result is color 0.1 0.1 0.1

  Scenario: Lighting with the surface in shadow
    Given Eye vector 0.0 0.0 -1.0
    And Normal vector 0.0 0.0 -1.0
    And Point light point 0.0 0.0 -10.0 color 1.0 1.0 1.0
    And Is in shadow
    When Create lighting result
    Then Result is color 0.1 0.1 0.1




