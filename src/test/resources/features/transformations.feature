Feature: Transformations

  Scenario: Multiplying by a translation matrix
    Given Translation 5.0 -3.0 2.0
    And Point -3.0 4.0 5.0
    Then Transforming a point gives 2.0 1.0 7.0

  Scenario: Multiplying by the inverse of a translation matrix
    Given Translation 5.0 -3.0 2.0
    And Inverse transform
    And Point -3.0 4.0 5.0
    Then Transforming a point gives -8.0 7.0 3.0

  Scenario: Translation does not affect vectors
    Given Translation 5.0 -3.0 2.0
    And Vector -3.0 4.0 5.0
    Then Transforming a vector gives vector

  Scenario: A scaling matrix applied to a point
    Given Scaling 2.0 3.0 4.0
    And Point -4.0 6.0 8.0
    Then Transforming a point gives -8.0 18.0 32.0

  Scenario: A scaling matrix applied to a vector
    Given Scaling 2.0 3.0 4.0
    And Vector -4.0 6.0 8.0
    Then Transforming a vector gives -8.0 18.0 32.0

  Scenario: Multiplying by the inverse of a scaling matrix
    Given Scaling 2.0 3.0 4.0
    And Vector -4.0 6.0 8.0
    And Inverse transform
    Then Transforming a vector gives -2.0 2.0 2.0

  Scenario: Reflection is scaling by a negative value
    Given Scaling -1.0 1.0 1.0
    And Point 2.0 3.0 4.0
    Then Transforming a point gives -2.0 3.0 4.0

  Scenario: Rotating a point around the x axis half quarter
    Given Point 0.0 1.0 0.0
    And Rotation by x pi by 4.0
    Then Transforming a point gives 0.0 0.7 0.7

  Scenario: Rotating a point around the x axis full quarter
    Given Point 0.0 1.0 0.0
    And Rotation by x pi by 2.0
    Then Transforming a point gives 0.0 0.0 1.0

  Scenario: The inverse of an x-rotation rotates in the opposite direction
    Given Point 0.0 1.0 0.0
    And Rotation by x pi by 4.0
    And Inverse transform
    Then Transforming a point gives 0.0 0.7 -0.7

  Scenario: Rotating a point around the y axis half quarter
    Given Point 0.0 0.0 1.0
    And Rotation by y pi by 4.0
    Then Transforming a point gives 0.7 0.0 0.7

  Scenario: Rotating a point around the y axis full quarter
    Given Point 0.0 0.0 1.0
    And Rotation by y pi by 2.0
    Then Transforming a point gives 1.0 0.0 0.0

  Scenario: Rotating a point around the z axis half quarter
    Given Point 0.0 1.0 0.0
    And Rotation by z pi by 4.0
    Then Transforming a point gives -0.7 0.7 0.0

  Scenario: Rotating a point around the z axis full quarter
    Given Point 0.0 1.0 0.0
    And Rotation by z pi by 2.0
    Then Transforming a point gives -1.0 0.0 0.0

  Scenario: A shearing transformation moves x in proportion to y
    Given Shearing 1.0 0.0 0.0 0.0 0.0 0.0
    And Point 2.0 3.0 4.0
    Then Transforming a point gives 5.0 3.0 4.0

  Scenario: A shearing transformation moves x in proportion to z
    Given Shearing 0.0 1.0 0.0 0.0 0.0 0.0
    And Point 2.0 3.0 4.0
    Then Transforming a point gives 6.0 3.0 4.0

  Scenario: A shearing transformation moves y in proportion to x
    Given Shearing 0.0 0.0 1.0 0.0 0.0 0.0
    And Point 2.0 3.0 4.0
    Then Transforming a point gives 2.0 5.0 4.0

  Scenario: A shearing transformation moves y in proportion to z
    Given Shearing 0.0 0.0 0.0 1.0 0.0 0.0
    And Point 2.0 3.0 4.0
    Then Transforming a point gives 2.0 7.0 4.0

  Scenario: A shearing transformation moves z in proportion to x
    Given Shearing 0.0 0.0 0.0 0.0 1.0 0.0
    And Point 2.0 3.0 4.0
    Then Transforming a point gives 2.0 3.0 6.0

  Scenario: A shearing transformation moves z in proportion to y
    Given Shearing 0.0 0.0 0.0 0.0 0.0 1.0
    And Point 2.0 3.0 4.0
    Then Transforming a point gives 2.0 3.0 7.0

  Scenario: Individual transformations are applied in sequence
    Given Point 1.0 0.0 1.0
    And Rotation by x pi by 2.0
    And Second scaling 5.0 5.0 5.0
    And Third translation 10.0 5.0 7.0
    When Second point multiply
    Then Second point 1.0 -1.0 0.0
    When Third point multiply
    Then Third point 5.0 -5.0 0.0
    When Forth point multiply
    Then Forth point 15.0 0.0 7.0

  Scenario: Chained transformations must be applied in reverse order
    Given Point 1.0 0.0 1.0
    And Rotation by x pi by 2.0
    And Second scaling 5.0 5.0 5.0
    And Third translation 10.0 5.0 7.0
    When Multiply three matrices
    Then Three matrices multiplication on point 15.0 0.0 7.0

  Scenario: A sphere's default transformation
    Given Create sphere for transform 1
    Then Transformed sphere equals identityMatrix

  Scenario: Changing a sphere's transformation
    Given Create sphere for transform 1
    And Translation 2.0 3.0 4.0
    When Set transformation for sphere
    Then Transformed sphere equals translation 2.0 3.0 4.0

  Scenario: Intersecting a scaled sphere with a ray
    Given Create ray 0.0 0.0 -5.0 0.0 0.0 1.0
    And Create sphere for transform 1
    And Scaling 2.0 2.0 2.0
    When Set transformation for sphere
    And Intersection ray sphere
    Then Size of intersections is 2
    And Intersections time 0 is 3.0
    And Intersections time 1 is 7.0

  Scenario: Intersecting a translated sphere with a ray
    Given Create ray 0.0 0.0 -5.0 0.0 0.0 1.0
    And Create sphere for transform 1
    And Translation 5.0 0.0 0.0
    When Set transformation for sphere
    And Intersection ray sphere
    Then Size of intersections is 0