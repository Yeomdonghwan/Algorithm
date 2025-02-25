import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
	
		int[][] edges = new int[N+1][N+1];
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				edges[i][j]=Integer.MAX_VALUE;	
			}
		}
		
		for(int i=1;i<=N;i++) {
			edges[i][i]=0;
		}
		
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
		
			edges[start][end]=Math.min(cost, edges[start][end]);
		}
		
		for(int k=1;k<=N;k++) {
			for(int a=1;a<=N;a++) {
				for(int b=1;b<=N;b++) {
					if(edges[a][k]==Integer.MAX_VALUE || edges[k][b]==Integer.MAX_VALUE)
						continue;
					edges[a][b]=Math.min(edges[a][b], edges[a][k]+edges[k][b]);
				}
			}
		}
		
		int max_distance = 0;
		for(int i=1;i<=N;i++) {
			max_distance = Math.max(edges[i][X]+edges[X][i],max_distance);
			
		}
		
//		for(int i=1;i<=N;i++) {
//			for(int j=1;j<=N;j++) {
//				System.out.printf("%5d ", edges[i][j]);	
//			}
//			System.out.println();
//		}
//		
		System.out.println(max_distance);
		
	
	}

}
