package lab4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class HeapSort {
	private static int N  = 50;
	
	public static void main(String args[]) {
		//start recording time
		final long startTime = System.currentTimeMillis();

		//build array
		int arr[] = new int[N];
		
		String inputFilePath = args[0];
//		String outputFilePath = args[1];

		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
//		PrintWriter writer = null;

		try {
			fileReader = new FileReader(inputFilePath);
			bufferedReader = new BufferedReader(fileReader);

			// write the result to file
//			File outFile = new File(outputFilePath);
//			writer = new PrintWriter(outFile);

			// start reading file line by line
			String line;
			int i = 0;
			while ((line = bufferedReader.readLine()) != null) {
				//each line is a number, adding it to array
				arr[i] = Integer.parseInt(line);
				i++;
			}
			
			HeapSort myHeapSorter = new HeapSort();
			myHeapSorter.heapSort(arr);
			myHeapSorter.showArray(arr);
			
			//record end time
			final long endTime = System.currentTimeMillis();
			System.out.println("Total execution time in milliseconds: " + (endTime - startTime) );

			
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				bufferedReader.close();
				fileReader.close();
//				writer.close();
			} catch (IOException e) {
				System.out.println(e);
			}

		}
	}
	
	public void heapSort(int arr[]) {
		int n = arr.length;

		for (int i = n / (2 - 1); i >= 0; i--)
			heapify(arr, n, i);

		for (int i = n - 1; i >= 0; i--) {
			// move root to end
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;

			heapify(arr, i, 0);
		}
	}

	private void heapify(int arr[], int n, int i) {
		int largest = i;
		int l = 2 * i + 1;
		int r = 2 * i + 2;

		// left child
		if (l < n && arr[l] > arr[largest])
			largest = l;

		// right child
		if (r < n && arr[r] > arr[largest])
			largest = r;

		if (largest != i) {
			int swap = arr[i];
			arr[i] = arr[largest];
			arr[largest] = swap;

			heapify(arr, n, largest);
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
