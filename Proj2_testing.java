package csciP2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Proj2_testing {

	
	//merging sorted lists greedy method- takes a list of int lists
	static ArrayList<Integer> merSorLis(ArrayList<ArrayList<Integer>> lists) {
	  	//build the heap
		PriorityQueue<ArrayList<Integer>> heap = new PriorityQueue<>(new ARLSTComparator());
		//add all the lists to the 'heap'
		for (ArrayList<Integer> list : lists) {
            heap.add(list);
        }
		
		int listSize = heap.size();
		//for i=1 to n-1 remove the two smallest elements,merge, and add new element
		for (int i = 0; i <= listSize-2; i++) { 

			ArrayList<Integer> a = heap.poll(); //gets the smallest list
			ArrayList<Integer> b = heap.poll(); //gets the second smallest list 
			
			a.addAll(b); //merging
			Collections.sort(a); //sorting not needed for this
			
			heap.add(a); //adding the merged list back to the heap
			
		}
		
		return heap.poll();
	}
	
	public static void main(String[] args) {

		final long startTime = System.currentTimeMillis(); //for getting exec time
		
		ArrayList<Integer> arr1 = new ArrayList<Integer>(Collections.nCopies(10, 10)); //added/removed lists for various values of n
		
		ArrayList<Integer> arr2 = new ArrayList<Integer>(Collections.nCopies(3, 15));
		
		ArrayList<Integer> arr3 = new ArrayList<Integer>(Collections.nCopies(2, 20));
	
		ArrayList<Integer> arr4 = new ArrayList<Integer>(Collections.nCopies(5, 2));

		ArrayList<Integer> arr5 = new ArrayList<Integer>(Collections.nCopies(33, 8));

		ArrayList<Integer> arr6 = new ArrayList<Integer>(Collections.nCopies(16, 7));

		ArrayList<Integer> arr7 = new ArrayList<Integer>(Collections.nCopies(4, 4));
		
		ArrayList<Integer> arr8 = new ArrayList<Integer>(Collections.nCopies(100, 0));

		ArrayList<Integer> arr9 = new ArrayList<Integer>(Collections.nCopies(3, 9));

		ArrayList<Integer> arr10 = new ArrayList<Integer>(Collections.nCopies(21, 11));
		
		ArrayList<ArrayList<Integer>> input = new ArrayList<ArrayList<Integer>>();
		input.add(arr1); 
		input.add(arr2);
		input.add(arr3);
		input.add(arr4); 
		input.add(arr5);
		input.add(arr6);
		input.add(arr7); 
		input.add(arr8);
		input.add(arr9);
		input.add(arr10); 
		
		ArrayList<Integer> finalList = merSorLis(input); //calling the greedy ver of the func
		
		System.out.println("final list: " + finalList);
		
		final long endTime = System.currentTimeMillis(); //for getting exec time

		System.out.println("Execution time in ms: " + (endTime - startTime)); 
	}

}


class ARLSTComparator implements Comparator<ArrayList<Integer>> {

    @Override
    public int compare(ArrayList<Integer> a1, ArrayList<Integer>  a2) {
        int value =  a1.size()-a2.size();
      
        if (value < 0) {
            return -1;
        }
        else if (value > 0) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
