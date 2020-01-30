Feature: Refractions

  Scenario Outline: Finding n1 and n2 at various intersections
    Given Glass sphere with transform scaling 2 2 2 and material refractive 1.5
    And Second glass sphere with transform translation 0 0 -0.25 and material refractive 2
    And Third glass sphere with transform translation 0 0 0.25 and material refractive 2.5
    And Glass ray point 0 0 -4 vector 0 0 1
    And Intersections 2 first 2.75 second 3.25 third 4.75 second 5.25 third 6 first
    When Prepare computations for glass <index>
    Then Computations first <n1>
    And Computations second <n2>
    Examples:
      | index | n1  | n2  |
      | 0     | 1.0 | 1.5 |
      | 1     | 1.5 | 2.0 |
      | 2     | 2.0 | 2.5 |
      | 3     | 2.5 | 2.5 |
      | 4     | 2.5 | 1.5 |
      | 5     | 1.5 | 1.0 |