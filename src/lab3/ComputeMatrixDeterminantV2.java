package lab3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

public class ComputeMatrixDeterminantV2 {

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
			String[] lineArr;
			int n = 0;

			while ((line = bufferedReader.readLine()) != null) {
				lineArr = line.split(" ");
				n = lineArr.length;

				if (n == 1) {
					writer.println(lineArr[0]);
					writer.println("Determinant is " + lineArr[0]);

					System.out.println(lineArr[0]);
					System.out.println("Determinant is " + lineArr[0]);
				} else if (n > 1) {
					// create matrix
					System.out.println("Creating " + n + "X" + n + " matrix...");							
					LinkedListMatrix matrix = new LinkedListMatrix();						
					matrix.addMatrixRow(lineArr);

					// read next n-1 line to build the matrix
					for (int i = 1; i < n; i++) {
						if ((line = bufferedReader.readLine()) != null) {

							lineArr = line.split(" ");
							int nTemp = lineArr.length;

							if (nTemp == n) {
								matrix.addMatrixRow(lineArr);
							} else {
								System.out.println(line + " is invalid.");
								System.exit(0);
							}

						}
					}
										
					// print the matrix				
					String data = matrix.showData();
					System.out.print(data);					
					writer.print(data);
					
					// do the compute
					int determinant = computeDeterminant(matrix);
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

	private static int computeDeterminant(LinkedListMatrix matrix) {
		int det = 0;

		// when matrix is 1X1
		if (matrix.getMatrixSize() == 1) {
			return det = matrix.head.getFirst();
		} else {
			//loop through head
			Iterator<Integer> it = matrix.head.iterator();
			int i = 0;
			
			while(it.hasNext()) {
				int headValue = it.next();	
				LinkedListMatrix minMatrix = matrix.getMinor(headValue);
				
				det += (int) Math.pow(-1, i) * headValue * computeDeterminant(minMatrix);
				i++;
			}
		}

		return det;
	}

}
