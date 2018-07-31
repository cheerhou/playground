package lab2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;

public class ComputeMatrixDeterminant {

	public static void main(String[] args) {
		String inputFilePath = args[0];
		 String outputFilePath = args[1];

		 FileReader fileReader = null;
		 BufferedReader bufferedReader = null;
		 PrintWriter writer = null;
		 
		try {
			fileReader = new FileReader(inputFilePath);
			bufferedReader = new BufferedReader(fileReader);

			// write the result to file
			File outFile = new File(outputFilePath);
			writer = new PrintWriter(outFile);

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
					writer.println(lineArr[0]);
					writer.println("Determinant is " + lineArr[0]);
					
					System.out.println(lineArr[0]);
					System.out.println("Determinant is " + lineArr[0]);
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

					//print the matrix
					printMatirx(writer, matrix);

					//do the compute
					int determinant = computeDeterminant(matrix);	

					// write the result to file			
					System.out.println("Determinant is " + determinant);
					writer.println("Determinant is " + determinant);
				}
							
			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				bufferedReader.close();
				fileReader.close();
				writer.close();
			} catch (IOException e) {
				System.out.println(e);
			}
			
		}

	}

	private static int computeDeterminant(int[][] matrix) {
		int det = 0;

		// when matrix is 1X1
		if (matrix.length == 1 && matrix[0].length == 1) {
			det = matrix[0][0];
		} else {

			for (int i = 0; i < matrix.length; i++) {
				int j = 0;
				det += (int) Math.pow(-1, i + j) * matrix[i][j] * computeDeterminant(minor(i, j, matrix));
			}
		}

		return det;

	}

	private static int[][] minor(int elementRow, int elementCol, int[][] matrix) {
		int n = matrix.length - 1;
		int[][] minorMatrix = new int[n][n];

		for (int i = 0; i < matrix.length; i++) {

			for (int j = 0; j < matrix[i].length; j++) {
				int minorRow = i;
				int minorCol = j;

				if (i != elementRow && j != elementCol) {

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

	private static void printMatirx(PrintWriter writer, int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				writer.print(matrix[i][j] + " ");
				System.out.print(matrix[i][j] + " ");
			}
			
			writer.println();
			System.out.println();
		}
	}

}
