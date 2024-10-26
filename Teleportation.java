import java.util.Random;

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
		  long begin_time = System.nanoTime();
		
		  int k = 1; //max number of haunted galaxies to use feel free to change when testing
	  
		  int n = 10; //number of galaxies
		  
	      Random rd = new Random(); // creating Random object
	      int[][] weight_arr = new int[n][n];
	      
	      for (int i = 0; i < n; i++) {
	    	  for (int j = 0; j < n; j++) {
	    		  if(j==i) {
	    			  weight_arr[i][j] = 0;
	    		  }else if( rd.nextInt(10) % 4 == 0 && weight_arr[i][j] == 0 && weight_arr[j][i] == 0) { //semi random infinity values
	    			  weight_arr[i][j] =  10000000; 
	    			  weight_arr[j][i] = 10000000;
	    	  	  }else if (weight_arr[i][j] == 0 && weight_arr[j][i] == 0){ //trying to not set a value that's already set
	    			  int val = rd.nextInt(10);
	    	  		  weight_arr[i][j] = val;
	    			  weight_arr[j][i] = val;
	    		  }
	    	  }
	      }
	      int [] haunted_arr = new int [n];
		  for(int i =0; i < n; i++) {
			  haunted_arr[i] = rd.nextInt(2);
		  }
 
		 
		  int[][] ret_t = teleport(k,weight_arr,haunted_arr); //returns final distance matrix that uses at most k haunted galaxies
		  		  
		  //System.out.println(Arrays.deepToString(ret_t)); //tele with at most k haunted galaxies
		  long end_time = System.nanoTime();  
		  long time = end_time-begin_time;
		  
		  System.out.println("Execution time (ns): "+time + "  When n is: " + n);
	}

}
