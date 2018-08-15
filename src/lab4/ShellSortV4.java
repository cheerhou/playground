package lab4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ShellSortV4 {
	public static final int FILE_SIZE = 50;
	//my own increments
	public static final int[] GAP_ARR = {};

	public static void main(String args[]) {
		// start recording time
		final long startTime = System.currentTimeMillis();

		// build array
		int arr[] = new int[FILE_SIZE];
		
		String inputFilePath = args[0];

		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		try {
			fileReader = new FileReader(inputFilePath);
			bufferedReader = new BufferedReader(fileReader);

			// start reading file line by line
			String line;
			int i = 0;
			while ((line = bufferedReader.readLine()) != null) {
				// each line is a number, adding it to array
				arr[i] = Integer.parseInt(line);
				i++;
			}

			// sorting based on the given gap array
			ShellSort myShellSorter = new ShellSort(FILE_SIZE);
			int[] validGaps = myShellSorter.getValidGapArr(GAP_ARR);			
			myShellSorter.shellSort(arr, validGaps);
			myShellSorter.showArray(arr);

			// record end time
			final long endTime = System.currentTimeMillis();
			System.out.println("Total execution time in milliseconds: " + (endTime - startTime));

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				bufferedReader.close();
				fileReader.close();
			} catch (IOException e) {
				System.out.println(e);
			}

		}
	}

}
