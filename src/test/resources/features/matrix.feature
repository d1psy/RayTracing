Feature: Matrix

  Scenario: Constructing and inspecting a 4x4 matrix
    Given The following 4 x 4 matrix "1.0 2.0 3.0 4.0 5.5 6.5 7.5 8.5 9.0 10.0 11.0 12.0 13.5 14.5 15.5 16.5"
    Then Matrix 0 0 = 1.0
    And Matrix 0 3 = 4.0
    And Matrix 1 0 = 5.5
    And Matrix 1 2 = 7.5
    And Matrix 2 2 = 11.0
    And Matrix 3 0 = 13.5
    And Matrix 3 2 = 15.5

  Scenario: A 2x2 matrix ought to be representable
    Given The following 2 x 2 matrix "-3.0 5.0 1.0 -2.0"
    Then Matrix 0 0 = -3.0
    And Matrix 0 1 = 5.0
    And Matrix 1 0 = 1.0
    And Matrix 1 1 = -2.0

  Scenario: A 3x3 matrix ought to be representable
    Given The following 3 x 3 matrix "-3.0 5.0 0.0 1.0 -2.0 -7.0 0.0 1.0 1.0"
    Then Matrix 0 0 = -3.0
    And Matrix 1 1 = -2.0
    And Matrix 2 2 = 1.0

  Scenario: Matrices equality with identical matrices
    Given The following 4 x 4 matrix "1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 8.0 7.0 6.0 5.0 4.0 3.0 2.0"
    And Second matrix 4 x 4 "1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 8.0 7.0 6.0 5.0 4.0 3.0 2.0"
    Then Matrices are equal

  Scenario: Matrices are not identical
    Given The following 4 x 4 matrix "1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 8.0 7.0 6.0 5.0 4.0 3.0 2.0"
    And Second matrix 4 x 4 "1.0 3.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 8.0 7.0 6.0 5.0 4.0 3.0 2.0"
    Then Matrices are not equal

  Scenario: Multiplying two matrices
    Given The following 4 x 4 matrix "1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 8.0 7.0 6.0 5.0 4.0 3.0 2.0"
    And Second matrix 4 x 4 "-2.0 1.0 2.0 3.0 3.0 2.0 1.0 -1.0 4.0 3.0 6.0 5.0 1.0 2.0 7.0 8.0"
    Then Multiply 4 x 4 matrix equals "20.0 22.0 50.0 48.0 44.0 54.0 114.0 108.0 40.0 58.0 110.0 102.0 16.0 26.0 46.0 42.0"

  Scenario: A matrix multiplied by a tuple
    Given The following 4 x 4 matrix "1.0 2.0 3.0 4.0 2.0 4.0 4.0 2.0 8.0 6.0 4.0 1.0 0.0 0.0 0.0 1.0"
    And Tuple 1.0 2.0 3.0 1.0
    Then Multiply matrix by tuple equals 18.0 24.0 33.0 1.0

  Scenario: Multiplying a matrix by the identity matrix
    Given The following 4 x 4 matrix "1.0 2.0 3.0 4.0 2.0 4.0 4.0 2.0 8.0 6.0 4.0 1.0 0.0 0.0 0.0 1.0"
    Then Multiply by identity matrix equals original

  Scenario: Multiplying the identity matrix by a tuple
    Given Tuple 1.0 2.0 3.0 1.0
    Then Multiply identity matrix by tuple equals tuple

  Scenario: Transposing a matrix
    Given The following 4 x 4 matrix "0.0 9.0 3.0 0.0 9.0 8.0 0.0 8.0 1.0 8.0 5.0 3.0 0.0 0.0 5.0 8.0"
    Then Transpose matrix equals "0.0 9.0 1.0 0.0 9.0 8.0 8.0 0.0 3.0 0.0 5.0 5.0 0.0 8.0 3.0 8.0"

  Scenario: Calculating the determinant of a 2x2 matrix
    Given The following 2 x 2 matrix "1.0 5.0 -3.0 2.0"
    Then Determinant = 17

  Scenario: A submatrix of a 3x3 matrix is a 2x2 matrix
    Given The following 3 x 3 matrix "1.0 5.0 0.0 -3.0 2.0 7.0 0.0 6.0 -3.0"
    Then Submatrix 0 2 is following 2 x 2 "-3.0 2.0 0.0 6.0"

  Scenario: A submatrix of a 4x4 matrix is a 3x3 matrix
    Given The following 4 x 4 matrix "-6.0 1.0 1.0 6.0 -8.0 5.0 8.0 6.0 -1.0 0.0 8.0 2.0 -7.0 1.0 -1.0 1.0"
    Then Submatrix 2 1 is following 3 x 3 "-6.0 1.0 6.0 -8.0 8.0 6.0 -7.0 -1.0 1.0"

  Scenario: Calculating a minor of a 3x3 matrix
    Given The following 3 x 3 matrix "3.0 5.0 0.0 2.0 -1.0 -7.0 6.0 -1.0 5.0"
    And Submatrix 1 0
    Then Determinant of submatrix = 25.0
    And Minor of matrix 1 0 25.0

  Scenario: Calculating a cofactor of a 3x3 matrix
    Given The following 3 x 3 matrix "3.0 5.0 0.0 2.0 -1.0 -7.0 6.0 -1.0 5.0"
    Then Minor of matrix 0 0 -12.0
    And Cofactor of matrix 0 0 -12.0
    And Minor of matrix 1 0 25.0
    And Cofactor of matrix 1 0 -25.0

  Scenario: Calculating the determinant of a 3x3 matrix
    Given The following 3 x 3 matrix "1.0 2.0 6.0 -5.0 8.0 -4.0 2.0 6.0 4.0"
    Then Cofactor of matrix 0 0 56.0
    And Cofactor of matrix 0 1 12.0
    And Cofactor of matrix 0 2 -46.0
    And Determinant = -196.0

  Scenario: Calculating the determinant of a 4x4 matrix
    Given The following 4 x 4 matrix "-2.0 -8.0 3.0 5.0 -3.0 1.0 7.0 3.0 1.0 2.0 -9.0 6.0 -6.0 7.0 7.0 -9.0"
    Then Cofactor of matrix 0 0 690.0
    And Cofactor of matrix 0 1 447.0
    And Cofactor of matrix 0 2 210.0
    And Cofactor of matrix 0 3 51.0
    And Determinant = -4071.0

  Scenario: Testing an invertible matrix for invertibility
    Given The following 4 x 4 matrix "6.0 4.0 4.0 4.0 5.0 5.0 7.0 6.0 4.0 -9.0 3.0 -7.0 9.0 1.0 7.0 -6.0"
    Then Determinant = -2120.0
    And Matrix is invertible

  Scenario: Testing a noninvertible matrix for invertibility
    Given The following 4 x 4 matrix "-4.0 2.0 -2.0 -3.0 9.0 6.0 2.0 6.0 0.0 -5.0 1.0 -5.0 0.0 0.0 0.0 0.0"
    Then Determinant = 0.0
    And Matrix is not invertible

  Scenario: Calculating the inverse of a matrix
    Given The following 4 x 4 matrix "-5.0 2.0 6.0 -8.0 1.0 -5.0 1.0 8.0 7.0 7.0 -6.0 -7.0 1.0 -3.0 7.0 4.0"
    And Inverse matrix
    Then Determinant = 532.0
    And Cofactor of matrix 2 3 -160.0
    And Inverse 3 2 = -0.30075
    And Cofactor of matrix 3 2 105.0
    And Inverse 2 3 = 0.19737
    And Inverse is the following 4 x 4 matrix "0.21805 0.45113 0.24060 -0.04511 -0.80827 -1.45677 -0.44361 0.52068 -0.07895 -0.22368 -0.05263 0.19737 -0.52256 -0.81391 -0.30075 0.30639"

  Scenario: Calculating the inverse of another matrix
    Given The following 4 x 4 matrix "8.0 -5.0 9.0 2.0 7.0 5.0 6.0 1.0 -6.0 0.0 9.0 6.0 -3.0 0.0 -9.0 -4.0"
    And Inverse matrix
    Then Inverse is the following 4 x 4 matrix "-0.15385 -0.15385 -0.28205 -0.53846 -0.07692 0.12308 0.02564 0.03077 0.35897 0.35897 0.43590 0.92308 -0.69231 -0.69231 -0.76923 -1.92308"

  Scenario: Calculating the inverse of a third matrix
    Given The following 4 x 4 matrix "9.0 3.0 0.0 9.0 -5.0 -2.0 -6.0 -3.0 -4.0 9.0 6.0 4.0 -7.0 6.0 6.0 2.0"
    And Inverse matrix
    Then Inverse is the following 4 x 4 matrix "-0.04074 -0.07778 0.14444 -0.22222 -0.07778 0.03333 0.36667 -0.33333 -0.02901 -0.14630 -0.10926 0.12963 0.17778 0.06667 -0.26667 0.33333"

  Scenario: Multiplying a product by its inverse
    Given The following 4 x 4 matrix "3.0 -9.0 7.0 3.0 3.0 -8.0 2.0 -9.0 -4.0 4.0 4.0 1.0 -6.0 5.0 -1.0 1.0"
    And Second matrix 4 x 4 "8.0 2.0 2.0 2.0 3.0 -1.0 7.0 0.0 7.0 0.0 5.0 4.0 6.0 -2.0 0.0 5.0"
    And Third matrix is multiplication
    Then First matrix is third multiplied by inversed second
