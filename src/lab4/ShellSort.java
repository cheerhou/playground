package lab4;

import java.util.Arrays;

public class ShellSort {
	private int fileSize;
	
	
	public ShellSort(int fileSize) {
		super();
		this.fileSize = fileSize;
	}

	/*
	 * get valid gaps based on file size
	 */
	public int[] getValidGapArr(int[] gapArr) {
		for (int i = gapArr.length - 1; i >= 0; i--) {
			
			if (gapArr[i -1] < fileSize && gapArr[i] > fileSize) {
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
//				System.out.println("Gap is " + gap);

				for (int i = gap; i < arr.length; i++) {
					int temp = arr[i];

					int j;
					for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
						arr[j] = arr[j - gap]; // swap
					}

					arr[j] = temp;
				}

//				showArray(arr);
			}
		}


	}

	/*
	 * display sorted array content
	 */
	public void showArray(int arr[]) {
		System.out.println("Sorted array:");

		int n = arr.length;
		for (int i = 0; i < n; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

}
