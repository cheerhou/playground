package lab3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LinkedListMatrix {

	protected LinkedList<Integer> head;
	protected List<LinkedList<Integer>> matrix;
	
	public LinkedListMatrix() {
		head = new LinkedList<Integer>();
		matrix = new ArrayList<>();
	}
		
	public void addMatrixRow(String[] row) {
		head.add(Integer.parseInt(row[0]));
		
		LinkedList<Integer> tempRow = new LinkedList<Integer>();
		
		for(int i = 0; i < row.length; i++) {
			tempRow.add(Integer.parseInt(row[i]));
		}
		
		matrix.add(tempRow);
	}
	
	public void addMatrixRow(LinkedList<Integer> row) {
		head.add(row.getFirst());
		matrix.add(row);
	}
	
	public int getMatrixSize() {
		return head.size();
	}
	
	public LinkedListMatrix getMinor(int rowNum) {
		LinkedListMatrix minorMatrix = new LinkedListMatrix();
		
		for(int i = 0; i < matrix.size(); i++) {		
			if(i != rowNum) {//skip the row where head sit
				LinkedList<Integer> tempRow = new LinkedList<>(matrix.get(i));
				tempRow.removeFirst();
				minorMatrix.addMatrixRow(tempRow);
			}
				
		}
			
		return minorMatrix;
	}
	
	public String showData() {
		String data = "";
		for(LinkedList<Integer> row : matrix) {
			Iterator<Integer> it = row.iterator();
			while(it.hasNext()) {
				data += it.next() + " ";
			}
			data += "\n";
		}
		
		return data;
	}
}
