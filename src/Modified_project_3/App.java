package Modified_project_3;

import java.util.Random;

public class App {
    public static double timecac(int n){    
        Random rd = new Random(); // creating Random object
	    int[][] weight_arr = new int[n][n];
	    int k = 1; 
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
        stopwatch time = new stopwatch();
        @SuppressWarnings("unused")
        int[][] ret_t = Teleportation_1.teleport(k,weight_arr,haunted_arr);
        return time.elapsedTime();                  
    }

    public static void main(String[] args) {
        for (int N = 3; N <= 1000000; N = N*3){
            double time1 = Math.log(timecac(N))/Math.log(3);
            System.out.printf("%7d %5.5f", N, time1);
        }
    }
}
