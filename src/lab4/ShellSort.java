package lab4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class ShellSort {
	private static int N = 50;

	public static void main(String args[]) {
		// start recording time
		final long startTime = System.currentTimeMillis();

		// build array
		int arr[] = new int[N];
		int gapArr[] = {1, 10, 30, 60, 120, 360, 1080, 3240, 9720, 29160};

		String inputFilePath = args[0];
		// String outputFilePath = args[1];

		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		// PrintWriter writer = null;

		try {
			fileReader = new FileReader(inputFilePath);
			bufferedReader = new BufferedReader(fileReader);

			// write the result to file
			// File outFile = new File(outputFilePath);
			// writer = new PrintWriter(outFile);

			// start reading file line by line
			String line;
			int i = 0;
			while ((line = bufferedReader.readLine()) != null) {
				// each line is a number, adding it to array
				arr[i] = Integer.parseInt(line);
				i++;
			}

			// sorting
			ShellSort myShellSorter = new ShellSort();
			int[] validGaps = getValidGapArr(gapArr);			
			
//			for(int j = 0; j < validGaps.length; j++) {
//				System.out.print(validGaps[j] + " ");
//			}
			myShellSorter.shellSort(arr, validGaps);
//			myShellSorter.showArray(arr);

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
				// writer.close();
			} catch (IOException e) {
				System.out.println(e);
			}

		}
	}

	public static int[] getValidGapArr(int[] gapArr) {
		for (int i = gapArr.length - 1; i >= 0; i--) {
			
			if (gapArr[i -1] < N && gapArr[i] > N) {
				return Arrays.copyOfRange(gapArr, 0, i - 1);
					
			}
		}
		return gapArr;
	}

	/*
	 * shell sort implementation
	 */
	public void shellSort(int[] arr, int[] gapArr) {
		for (int gapIndex = gapArr.length - 1; gapIndex >= 0; gapIndex--) {
			int gap = gapArr[gapIndex];
			
			if (gap > 0) {
				System.out.println("Gap is " + gap);

				for (int i = gap; i < arr.length; i++) {
					int temp = arr[i];

					int j;
					for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
						arr[j] = arr[j - gap]; // swap
					}

					arr[j] = temp;
				}

				showArray(arr);
			}
		}


	}

	private void showArray(int arr[]) {
		System.out.println("Sorted array:");

		int n = arr.length;
		for (int i = 0; i < n; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}
