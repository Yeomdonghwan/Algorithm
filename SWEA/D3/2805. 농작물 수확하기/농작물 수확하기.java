import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			
			int[][] map = new int[N][N];
			
			for(int i=0;i<N;i++) {
				String line = br.readLine();
				for(int j=0;j<N;j++) {
					map[i][j]=line.charAt(j)-'0'; 
				} 
			}
			int sum=0;
			for(int i=0;i<=N/2;i++) {
				for(int j=N/2-i;j<=N/2+i;j++) {
					sum+=map[i][j]; 
				}

			}
			for(int i=N/2+1;i<N;i++) {
				for(int j=N/2-(N-i-1);j<=N/2+(N-i-1);j++) {
					sum+=map[i][j]; 
				} 
			}
			
			bw.append("#"+test_case+" "+sum+"\n");
			 
		}
		bw.flush();
		bw.close();
		
	}

}
