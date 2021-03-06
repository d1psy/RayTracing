Feature: Tuple

  Scenario: A tuple with w=1.0 is a point
    Given Tuple x=4.3, y=-4.2, z=3.1, w=1.0
    Then get x = 4.3
    And get y = -4.2
    And get z = 3.1
    And get w = 1.0
    And is a point true
    And is a vector false

  Scenario: A tuple with w=0.0 is a vector
    Given Tuple x=4.3, y=-4.2, z=3.1, w=0.0
    Then get x = 4.3
    And get y = -4.2
    And get z = 3.1
    And get w = 0.0
    And is a point false
    And is a vector true

  Scenario: Point equals tuple
    Given Point axis=1.0
    Then are equal

  Scenario: Vector equals tuple
    Given Vector axis=1.0
    Then are equal

  Scenario: Adding two tuples
    Given Tuple x=2.0, y=3.0, z=4.0, w=0.0
    And Second tuple x=-2.0, y=-5.0, z=1.0, w=1.0
    Then combine tuples equals 0.0, -2.0, 5.0, 1.0

  Scenario: Subtracting two points
    Given Point x=2.0, y=3.0, z=4.0
    And Second point x=-2.0, y=-5.0, z=1.0
    Then subtract equals vector 4.0, 8.0, 3.0

  Scenario: Subtracting point and vector
    Given Point x=3.0, y=5.0, z=-3.0
    And Second vector x=2.0, y=3.0, z=1.0
    Then subtract equals point 1.0, 2.0, -4.0

  Scenario: Subtracting two vectors
    Given Vector x=3.0, y=5.0, z=-3.0
    And Second vector x=2.0, y=3.0, z=1.0
    Then subtract equals vector 1.0, 2.0, -4.0

  Scenario: Subtracting vector from a zero vector
    Given Vector x=0.0, y=0.0, z=0.0
    And Second vector x=2.0, y=-4.0, z=3.0
    Then subtract equals vector -2.0, 4.0, -3.0

  Scenario: Negate tuple
    Given Tuple x=4.0, y=-3.0, z=-2.0, w=4.0
    Then Negate tuple x=-4.0, y=3.0, z=2.0, w=-4.0

  Scenario: Multiply a tuple by scalar
    Given Tuple x=1.0, y=2.0, z=-3.0, w=-4.0
    Then Multiply tuple by 3.5 equals 3.5, 7.0, -10.5, -14.0

  Scenario: Multiply a tuple by fraction
    Given Tuple x=1.0, y=2.0, z=-3.0, w=-4.0
    Then Multiply tuple by 0.5 equals 0.5, 1.0, -1.5, -2.0

  Scenario: Divide a tuple by scalar
    Given Tuple x=1.0, y=2.0, z=-3.0, w=-4.0
    Then Divide tuple by 2 equals 0.5, 1.0, -1.5, -2.0

  Scenario: Computing the magnitude of vector(1, 0, 0)
    Given Vector x=1.0, y=0.0, z=0.0
    Then Magnitude equals 1.0

  Scenario: Computing the magnitude of vector(0, 1, 0)
    Given Vector x=0.0, y=1.0, z=0.0
    Then Magnitude equals 1.0

  Scenario: Computing the magnitude of vector(0, 0, 1)
    Given Vector x=0.0, y=0.0, z=1.0
    Then Magnitude equals 1.0

  Scenario: Computing the magnitude of vector(1, 2, 3)
    Given Vector x=1.0, y=2.0, z=3.0
    Then Magnitude equals 3.74166

  Scenario: Computing the magnitude of vector(-1, -2, -3)
    Given Vector x=-1.0, y=-2.0, z=-3.0
    Then Magnitude equals 3.74166

  Scenario: Normalizing vector(4, 0, 0) gives (1, 0, 0)
    Given Vector x=4.0, y=0.0, z=0.0
    Then Normalize equals 1.0, 0.0, 0.0

  Scenario: Normalizing vector(1, 2, 3)
    Given Vector x=1.0, y=2.0, z=3.0
    Then Normalize equals 0.26726, 0.53452, 0.80178

  Scenario: The magnitude of a normalized vector
    Given Vector x=1.0, y=2.0, z=3.0
    When Normalize
    Then Magnitude equals 1.0

  Scenario: Dot product of two vectors
    Given Vector x=1.0, y=2.0, z=3.0
    And Second vector x=2.0, y=3.0, z=4.0
    Then Dot equals 20.0

  Scenario: The cross product of two vectors
    Given Vector x=1.0, y=2.0, z=3.0
    And Second vector x=2.0, y=3.0, z=4.0
    Then Cross vectors -1.0, 2.0, -1.0
    And Reverse cross vectors 1.0, -2.0, 1.0

  Scenario: Colors are red, green, blue Tuples
    Given Color red=-0.5 green=0.4 blue=1.7
    Then Red is -0.5
    And Green is 0.4
    And Blue is 1.7

  Scenario: Two colors addition
    Given Color red=0.9 green=0.6 blue=0.75
    And Second color red=0.7 green=0.1 blue=0.25
    Then Adding colors equals 1.6 0.7 1.0

  Scenario: Two colors give subtraction
    Given Color red=0.9 green=0.6 blue=0.75
    And Second color red=0.7 green=0.1 blue=0.25
    Then Subtracting colors equals 0.2 0.5 0.5

  Scenario: Multiply a color by scalar
    Given Color red=0.2 green=0.3 blue=0.4
    Then Multiply tuple by 2.0 equals 0.4, 0.6, 0.8, 0.0

  Scenario: Multiply a color on color
    Given Color red=1.0 green=0.2 blue=0.4
    And Second color red=0.9 green=1.0 blue=0.1
    Then Multiply tuples equals 0.9, 0.2, 0.04, 0.0

  Scenario: Reflecting a vector approaching at 45°
    Given Vector x=1.0, y=-1.0, z=0.0
    And Second vector x=0.0, y=1.0, z=0.0
    When Reflect vectors
    Then Reflected vector 1.0 1.0 0.0

  Scenario: Reflecting a vector off a slanted surface
    Given Vector x=0.0, y=-1.0, z=0.0
    And Second vector x=0.70711, y=0.70711, z=0.0
    When Reflect vectors
    Then Reflected vector 1.0 0.0 0.0

