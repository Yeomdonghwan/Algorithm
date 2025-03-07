import java.io.*;
import java.util.*;

public class Main {
	static int R,C;
	static int[][] map;
	static final int EMPTY = 0;
	static final int CHEESE = 1;
	static boolean[][] visited;
	static int[] dr = {0,0,-1,1};
	static int[] dc = {1,-1,0,0};
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		int count=0;
		for(int i=0;i<R;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<C;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==CHEESE)
					count++;
			}
		}
		int prev_count = count;
		int day_count=0;
		while(count!=0) {
			day_count++;
			//치즈녹이기
			visited = new boolean[R][C];
			dfs(0,0); //공기에서 시작
			
			prev_count=count;
			count=0;
			for(int i=0;i<R;i++) {
				for(int j=0;j<C;j++) {
					if(map[i][j]==CHEESE)
						count++;
				}
			}
		}
		
		System.out.println(day_count);
		System.out.println(prev_count);
		
		
	}
	
	static void dfs(int r, int c) {
		visited[r][c]=true;
		
		for(int k=0;k<4;k++) {
			int next_r = r+dr[k];
			int next_c = c+dc[k];
			if(next_r<0 || next_c <0 ||next_r >=R || next_c >=C)
				continue;
			if(visited[next_r][next_c])
				continue;
			
			if(map[next_r][next_c]==CHEESE) {
				map[next_r][next_c]=EMPTY; //녹이기
				visited[next_r][next_c]=true; //녹인곳 방문하지않도록 방문처리
			}else if(map[next_r][next_c]==EMPTY){
				dfs(next_r,next_c);
			}
		}
	}

}
