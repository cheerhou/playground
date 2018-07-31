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
	
	public LinkedListMatrix getMinor(int value) {
		LinkedListMatrix minorMatrix = new LinkedListMatrix();
		boolean found = false;
		for(int i = 0; i < matrix.size(); i++) {	
			LinkedList<Integer> tempRow = new LinkedList<>(matrix.get(i));
			if(!found && tempRow.getFirst() == value) {//first time found, skip the row
				found = true;
			}else if((found && tempRow.getFirst() == value) || (tempRow.getFirst() != value)) { //second time found, do sth
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
