package lab;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ComputeMatrixDeterminant {

	public static void main(String[] args) {
		String inputFilePath = args[0];
		String outputFilePath = args[1];

		try {
			FileReader fileReader = new FileReader(inputFilePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			// write the result to file
			File outFile = new File(outputFilePath);
			PrintWriter writer = new PrintWriter(outFile);

			// start reading file line by line
			String line;
			int lineIndex = -1;
			String[] lineArr;
			int n = 0;

			while ((line = bufferedReader.readLine()) != null) {
				lineIndex++;
				lineArr = line.split(" ");
				n = lineArr.length;

				if (n == 1) {

				} else if (n > 1) {
					// create matrix
					int[][] matrix = new int[n][n];
					// add current line to matrix
					matrix = addMartrixRow(0, matrix, lineArr);

					// read next n-1 line
					for (int i = 1; i < n; i++) {
						if ((line = bufferedReader.readLine()) != null) {
							lineIndex++;

							lineArr = line.split(" ");
							int nTemp = lineArr.length;

							if (nTemp == n) {
								matrix = addMartrixRow(i, matrix, lineArr);
							} else {
								System.out.println(line + " is invalid.");
								System.exit(0);
							}

						}
					}
					
					// TODO do the compute
					int determinant = computeDeterminant(matrix);
					System.out.println("Determinant is " + determinant);

					// TODO write to file
				}

			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	private static int computeDeterminant(int[][] matrix) {

		// when matrix is 1X1
		if (matrix.length == 1 && matrix[0].length == 1) {
			return matrix[0][0];
		} else {

			for (int i = 1; i < matrix.length; i++) {
				for (int j = 1; j < matrix[i].length; j++) {

					matrix = minor(i, j, matrix);
					return (int) Math.pow(-1, i + j) * matrix[i][j] * computeDeterminant(matrix);
				}
			}
		}

		return 0;

	}

	private static int[][] minor(int elementRow, int elementCol, int[][] matrix) {
		int n = matrix.length - 1;
		int[][] minorMatrix = new int[n][n];

		for (int i = 0; i < matrix.length; i++) {
			if (i != elementCol) {
				for (int j = 0; j < matrix[i].length; j++) {
					int minorRow = i;
					int minorCol = j;

					if (i > elementRow) {
						minorRow--;
					}

					if (j > elementCol) {
						minorCol--;
					}

					minorMatrix[minorRow][minorCol] = matrix[i][j];

				}
			}

		}

		return minorMatrix;
	}

	/**
	 * adding the given array to the specific row of the matrix
	 * 
	 * @param rowNum
	 * @param matrix
	 * @param row
	 * @return
	 */
	private static int[][] addMartrixRow(int rowNum, int[][] matrix, String[] row) {
		for (int i = 0; i < row.length; i++) {
			matrix[rowNum][i] = Integer.parseInt(row[i]);
		}

		return matrix;
	}
	


}
