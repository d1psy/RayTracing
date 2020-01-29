Feature: camera

  Scenario: Constructing a camera
    Given Hsize 160
    And Vsize 120
    And FoV 2
    When Create camera
    Then Hsize is 160
    And Vsize is 120
    And FoV is 2
    And Camera transform is identity matrix

  Scenario: The pixel size for a horizontal canvas
    Given Hsize 200
    And Vsize 125
    And FoV 2
    When Create camera
    Then Camera pixel size is 0.01

  Scenario: The pixel size for a vertical canvas
    Given Hsize 125
    And Vsize 200
    And FoV 2
    When Create camera
    Then Camera pixel size is 0.01

  @aaa
  Scenario: Constructing a ray through the center of the canvas
    Given Hsize 201
    And Vsize 101
    And FoV 2
    When Create camera
    And Create ray for pixel 100 50
    Then Camera ray origin 0 0 0
    And Camera ray direction 0 0 -1

  @aaa
  Scenario: Constructing a ray through a corner of the canvas
    Given Hsize 201
    And Vsize 101
    And FoV 2
    When Create camera
    And Create ray for pixel 0 0
    Then Camera ray origin 0 0 0
    And Camera ray direction 0.66519 0.33259 -0.66851

  @aaa
  Scenario: Constructing a ray when the camera is transformed
    Given Hsize 201
    And Vsize 101
    And FoV 2
    When Create camera
    And Camera transform is y rotation 4 by translation 0 -2 5
    And Create ray for pixel 100 50
    Then Camera ray origin 0 2 -5
    And Camera ray direction 0.70711 0 -0.70711