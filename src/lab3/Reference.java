package lab3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Reference {

	/**
	 * Entry point for the program. Reads in the file and stores the matrix. Runs the stored matrix through the
	 * determinant method to calculate the determinant.
	 * @param args	The command line arguments -- input and output files
	 */
	public static void main(String[] args) {
		
		BufferedReader 	input;
		BufferedWriter 	output;
		List			list;
		List[]			listArray;
		LabThree		lab;
		String []		stringSplit;
		int				data;
		int				dimension;
		int				index;
		int				det;
		String			z;
		
		lab = new LabThree();
		
		//  Check for two command line arguments.
        if (args.length != 2) {
            System.out.println("Usage:  java LinkedList.LabThree [input file]" +
                " [output file]");
            System.exit(1);
        }
        
        //  Open the input and output files.
        try {
            input = new BufferedReader(new FileReader(args[0]));
            output = new BufferedWriter(new FileWriter(args[1]));
        } catch (IOException exception) {
            System.err.println(exception.toString());
            return;
        }
		
		// pass in values from input to build the lists
        // each list is a row in the matrix
		z = lab.readMatrix(input);
		dimension = 0;
		while (z != null) {
			
			try {
				dimension = Integer.parseInt(z);
			}catch (Exception e) {
				System.err.println("ERROR: " + '"' + z + '"' + "  is not a valid input value.");
				System.exit(0);
			}
			
			z = lab.readMatrix(input);
			
			// check for empty strings before parsing
        	if (z.length() == 0) {
        		continue;
        	}
        	
        	// build the lists
        	stringSplit = null;
        	listArray = new List[dimension];
        	for (int i = 0; i < dimension; i++) {
        		
        		list = new List();
        		
        		try{
        			list.setHeader(i);
        			stringSplit = z.split(" ");
        		} catch (Exception exception) {
        			System.err.println(exception);
        		}
        		
        		index = 0;
        		
        		for (int j = 0; j < dimension; j++) {
        		
        			try {
        				data = Integer.parseInt(stringSplit[index]);	//set the data variable as the integer parsed from the string
                		list.InsertNode(data); 						//insert each integer from the string as a new node in the list
        			} catch (Exception e) {
        				System.err.println("ERROR: " + '"' + z + '"' + "  is not a valid input value.");
        			}
        			
            		index++;
        		}
        		
        		listArray[i] = list;
        	       
        		z = lab.readMatrix(input);
        	}
        	       	
        	// store the end result of the determinant of the matrix
        	det = lab.Determinant(listArray);
        	
        	// send data to output
        	lab.writeOutput(listArray, det, output);
		}
		
		// Close the input and output files and return to OS.
        try {
            input.close();
            output.close();
        } catch (Exception exception) {
            System.err.println(exception.toString());
        }
        return;
	}
	
	/**
	 * Read in the strings of integers from the input file.
	 * @param input		the string in the input file.
	 * @return The string of integers.
	 */
	private String readMatrix(BufferedReader input) {
		
		String z = "";
		
        try {
             z = input.readLine();
        } catch (IOException ioException) {
            System.err.println(ioException.toString());
            System.exit(2);
        }
        
        return z;
	}
	
	/**
	 * Write the results to the output file in an easy-to-read format.
	 * @param intArray	the original matrix passed into the program
	 * @param det		the value of the determinant for the matrix.
	 * @param output	the output file.
	 */
	private void writeOutput(List[] listArray, int det, BufferedWriter output) {
		 
		int size;
		int listSize;
		int nodeValue;
		List list;
		
		size = listArray.length;
		
		 try {
			 	output.write("The following matrix:");
			 	output.newLine();
			 	output.newLine();
			 	
			 	for (int i = 0; i < size; i++) {
			
			 		list = listArray[i];
			 		
			 		listSize = list.listSize();
			 		
			 		for (int j = 0; j < listSize; j++) {
			 		
				 		nodeValue = list.nodeValue(j);
				 		output.write(nodeValue + " ");
			 		}
			 		
			 		output.newLine();
			 	}
			 	
			 	output.newLine();
			 	output.write("Has a determinant value of " + det + ".");
			 	output.newLine();
			 	output.newLine();
			 	output.write("---------------------------------------");
			 	output.newLine();
			 	output.newLine();
	        	
	        } catch (IOException ioException) {
	            System.err.println(ioException.toString());
	            System.exit(3);
	        }
	        
	        return;
	}
	
	/**
	 * Compute the minor of the matrix passed into the application.
	 * @param listArray	The matrix passed into the method.
	 * @param j			The column value to be removed to compute the minor.
	 * @return The minor of the passed-in matrix.
	 */
	public List[] Minor (List[] listArray, int j) {
		
		int		p;
		int		matrixDimension;
		int		data;
		List	list;
		List	minorList;
		List[]	minor;
		
		matrixDimension = listArray[0].listSize();
		
		// if the matrix is 1x1 - return the value in the matrix as the minor.
		// else calculate the minor of the matrix by creating a new array of lists (the minor) which excludes
		// the first row and jth column from the original matrix.
		if (matrixDimension == 1) {
			minor = listArray;
			System.out.println(Arrays.deepToString(minor));
			System.out.println("test");
			return minor;
		}
		else
			minor = new List[matrixDimension-1];
			
			p = 0;
			for (int i = 0; i < matrixDimension; i++) {
				
				list = listArray[i];
				minorList = new List();
				
				if (i == 0)
					continue;
				
				for (int z = 0; z < list.listSize(); z++) {
					
					if (z == j)
						continue;
					
					data = list.nodeValue(z);
					minorList.InsertNode(data);
				}
				
				minor[p] = minorList;

				p++;
			}
			
			return minor;
	}
	
	/**
	 * Compute the determinant of the matrix passed into the application
	 * @param listArray	The original matrix passed into the program and each subsequent matrix created by the method 'minor'.
	 * @return The value of the determinant of the matrix.
	 */
	public int Determinant (List[] listArray) {
				
		List[]		minor;
		List		list;
		int			det;
		int			i;
		int			element;
		
		det = 0;
		i = 0;
		list = listArray[0];
		
		// if the matrix is 1x1 - return that value as the determinant.
		// else compute the sum of the products and return the determinant.
		for (int j = 0; j < listArray[0].listSize(); j++) {
			
			if (list.listSize() == 1) {
				return list.nodeValue(0);
			}
			else
				element = list.nodeValue(j);
				minor = this.Minor(listArray, j);
				det += (int) Math.pow(-1, (i + j))*element*Determinant(minor);
			}

		return det;
	}

}