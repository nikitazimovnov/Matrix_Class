package com.example.matrix;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Matrix {
    @Getter
    private double[][] matrix;

    public int getDim(int dim) {
        if (dim == 0)
            return  matrix.length;

        if (dim == 1)
            return matrix[0].length;

        return 0;
    }

    public int elementsCount() {
        return getDim(0) * getDim(1);
    }

    public boolean isIdentity() {
        for (int i = 0; i < getDim(0); i++) {
            for (int j = 0; j < getDim(1); j++) {
                if ((i != j && matrix[i][j] != 0) || (i == j && matrix[i][j] != 1))
                    return false;
            }
        }
        return true;
    }

    public boolean isQuadratic() {
        return getDim(0) == getDim(1);
    }

    public double getTrace() {
        int minDim = Math.min(getDim(0), getDim(1));
        double trace = 0;
        for (int i = 0; i < minDim; i++) {
            trace += matrix[i][i];
        }
        return trace;
    }

    public static Matrix Plus(Matrix matrix1, Matrix matrix2) throws MatrixException {
        if (matrix1.getDim(0) != matrix2.getDim(0) || matrix1.getDim(1) != matrix2.getDim(1))
            throw new MatrixException("Invalid plus operation.\nIncorrect matrix sizes.");

        double[][] result = new double[matrix1.getDim(0)][matrix1.getDim(1)];
        for (int i = 0; i < matrix1.getDim(0); i++) {
            for (int j = 0; j < matrix1.getDim(1); j++) {
                result[i][j] = matrix1.getMatrix()[i][j] + matrix2.getMatrix()[i][j];
            }
        }
        return new Matrix(result);
    }

    public static Matrix Minus(Matrix matrix1, Matrix matrix2) throws MatrixException {
        if (matrix1.getDim(0) != matrix2.getDim(0) || matrix1.getDim(1) != matrix2.getDim(1))
            throw new MatrixException("Invalid minus operation.\nIncorrect matrix sizes.");

        double[][] result = new double[matrix1.getDim(0)][matrix1.getDim(1)];
        for (int i = 0; i < matrix1.getDim(0); i++) {
            for (int j = 0; j < matrix1.getDim(1); j++) {
                result[i][j] = matrix1.getMatrix()[i][j] - matrix2.getMatrix()[i][j];
            }
        }
        return new Matrix(result);
    }

    @Override
    public String toString(){
        var sb = new StringBuilder();
        for (int i = 0; i < getDim(0); i++) {
            for (int j = 0; j < getDim(1); j++)
                sb.append(matrix[i][j]).append("\t");
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Matrix m = (Matrix) o;

        if (getDim(0) != m.getDim(0) || getDim(1) != m.getDim(1))
            return false;

        for (int i = 0; i < getDim(0); i++) {
            for (int j = 0; j < getDim(1); j++) {
                if (m.getMatrix()[i][j] != matrix[i][j])
                    return false;
            }
        }
        return true;
    }
}
