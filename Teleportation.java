import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Teleportation {

	
	public static int[][] APSP(int n, int [][] weight, int[] haunted) {
		
		int[][][] d = new int[n][n][n]; 
		
		//init distance array with weights between all the edges
		for (int i = 0; i < n; i++) { 
			for (int j = 0; j < n; j++) { 
				if(haunted[i] == 0 && haunted[j] == 0) {
					d[0][i][j] = weight[i][j];		
				}else {
					d[0][i][j] = 1000000; //inf / haunted galaxy, ignore it
				}
			}
		}

		//find the shortest path
		for (int k = 1; k < n; k++) { 
			for (int i = 0; i < n; i++) { 
				for (int j = 0; j < n; j++) { 
				
					d[k][i][j] = Math.min(d[k-1][i][j], (d[k-1][i][k] + d[k-1][k][j]));
					
				}
			}
		}
		return d[n-1];
	}
	
	
	public static int[][] teleport(int k, int [][] weight, int [] haunted) {
		
		int n = weight[0].length; 

		int[][][] ret = new int[n][n][n]; 
		int[][][] d = new int[n][n][n]; 
		int[] not_haunted = new int[n];

		if(k == 0) {
			d[0] = APSP(n,weight,haunted); //if k=0 APSP no haunted galaxies allowed
		}else {
			d[0] = APSP(n,weight,not_haunted); //haunted galaxies are allowed so we want c(i,j) for all possible teleportations
		}
		
		
		for (int c = 1; c < k; c++) { 
			for (int i = 0; i < n; i++) { 
				for (int j = 0; j < n; j++) { 
					
					int bhg = 10000000; //finding the value of the best haunted galaxy to use
					
					
					for (int g = 0; g < n; g++) { //only looking at haunted galaxies
						if(haunted[g] == 1) {
							bhg = Math.min(bhg, d[c-1][i][g] + d[0][g][j]); //finding the value for the best haunted galaxy to use
						}
					}
					//same as APSP - either use the haunted galaxy or don't
					d[c][i][j] = Math.min(d[c-1][i][j], bhg); 
					
				}
			}
		}
		
		
		if(k==0) {
			return d[0];
		}else {
			return d[k-1];
		}
	}
	
	
	public static void main(String[] args) {
		//using 100000 as a stand in for infinity  
		//FYI 3d arrays are of order k, i, j as opposed to i,k,j as in the textbook 
		
		//init array n*n
		  int[][] weight_arr = { { 0, 4,100000,2,100000 }, 
				  				 { 4, 0,2,100000,100000 }, 
				  				 { 100000,2,0,1, 1 }, 
				  				 {2,100000,1, 0, 6 }, 
				  				 { 100000, 100000,1,6,0 } };
		
		  //for testing purposes/numerical results create diff size arrays and edit k.
		  
		  //setting n
		  int n = weight_arr.length;
		  int k = 1; //max number of haunted galaxies to use 
		  
		  int [] haunted_arr = new int[n]; //0s by default good for base case
		  //int [] not_haunted_arr = new int[n];
		  
		  //setting haunted galaxies
		  haunted_arr[2] = 1; 
		  haunted_arr[1] = 1;
		  
		  //int[][][] ret = APSP(n,weight_arr,not_haunted_arr); testing all pairs shortest path
		  
		  int[][] ret_t = teleport(k,weight_arr,haunted_arr); //returns final distance matrix that uses at most k haunted galaxies
		  
		  //System.out.println(Arrays.deepToString(ret[n-1])); //apsp final

		  
		  System.out.println(Arrays.deepToString(ret_t)); //tele with at most k haunted galaxies
	}

}


