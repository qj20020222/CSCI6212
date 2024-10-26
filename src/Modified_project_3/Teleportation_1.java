package Modified_project_3;


import java.util.Arrays;

public class Teleportation_1 {

	
	public static int[][] APSP(int n, int [][] weight, int[] haunted) {
		//init distance array with weights between all the edges
		//find the shortest path
        for (int k = 0; k < n; k++) {
            if (haunted[k] != 1){
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        weight[i][j] = Math.min(weight[i][j], weight[i][k] + weight[k][j]); 
                    }
                }
            }
        }

		return weight;
	}
	
	
	public static int[][] teleport(int k, int [][] weight, int [] haunted) {
		
		int n = weight[0].length; 
		int[][] d = new int[n][n]; 

		d = APSP(n,weight,haunted);
		if(k == 0) {
			return APSP(n,weight,haunted); //if k=0 APSP no haunted galaxies allowed
		}
			
        for (int c = 1; c <= k; c++) {
            int[][] newd = new int[n][n];  // 用于存储新一轮计算的最短路径

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int bestHauntedGalaxy = 10000000;  // 初始化为一个大值
                    for (int g = 0; g < n; g++) {
                        if (haunted[g] == 1) {
                            bestHauntedGalaxy = Math.min(bestHauntedGalaxy, d[i][g] + d[g][j]);
                        }
                    }
                    newd[i][j] = Math.min(d[i][j], bestHauntedGalaxy);  // 更新最短路径
                }
            }

            d = newd;  // 用新的路径覆盖旧的路径
        }

        return d;  // 返回最终结果
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


