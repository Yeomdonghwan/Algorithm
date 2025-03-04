import java.util.*;
import java.io.*;

public class Main {

	static int[][] map;
	static int N;
	static int limit;
	static int[] dr = {0,0,-1,1};
	static int[] dc = {-1,1,0,0};
	static boolean[][] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		int max_level = 0;
		int answer=0;
		StringTokenizer st;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				max_level = Math.max(max_level, map[i][j]);
			}
		}
		
		for(int level = 0; level<=max_level; level++) {
			visited = new boolean[N][N];
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(map[i][j]<=level)
						visited[i][j]=true;
				}
			}
			int count=0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(!visited[i][j]) {
						dfs(i,j);
						count++;
					}
				}
			}
			
			answer=Math.max(answer, count);
		}
		
		
		
		

		System.out.println(answer);
	}
	
	static void dfs(int r,int c) {
		
		visited[r][c]=true;//방문처리  
		
		for(int k=0;k<4;k++) {
			//4방 탐색
			
			int next_r = r+dr[k];
			int next_c = c+dc[k];
			
			if(next_r<0 || next_c<0 ||next_r>=N || next_c>=N)
				continue;
			if(visited[next_r][next_c]) 
				continue;

			//방문하지 않았다면
			dfs(next_r,next_c); //방문
		
		}
	}

}
