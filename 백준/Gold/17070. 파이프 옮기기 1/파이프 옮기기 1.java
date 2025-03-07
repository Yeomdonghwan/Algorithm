import java.util.*;
import java.io.*;

public class Main {

	static int n,ans=0;
	static int[][] map;
	static final int X_DIRECTION = 1;
	static final int Y_DIRECTION = 2;
	static final int DIAGONAL_DIRECTION =0;
	static int[] dr = {1,0,1};//대각선, 가로, 세로
	static int[] dc = {1,1,0};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for(int i=0;i<n;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
	
		}
		
		dfs(0,1,X_DIRECTION);
		
		System.out.println(ans);
	}
	
	static void dfs(int r, int c, int direction) {
//		System.out.println(r+","+c+","+direction);
		if(r==n-1 && c==n-1) {
			ans++;
			return;
		}

		int next_r_X = r+dr[X_DIRECTION];
		int next_c_X = c+dc[X_DIRECTION];
		
		int next_r_Y = r+dr[Y_DIRECTION];
		int next_c_Y = c+dc[Y_DIRECTION];
		
		int next_r_DIAGONAL = r+dr[DIAGONAL_DIRECTION];
		int next_c_DIAGONAL = c+dc[DIAGONAL_DIRECTION];
		if(direction==X_DIRECTION) {
			//오른쪽으로 가는경우
			if((next_r_X<n && next_c_X<n) && map[next_r_X][next_c_X]==0) {
				dfs(next_r_X, next_c_X, X_DIRECTION);
			}
			
			//대각선으로 가는경우
			if((next_r_DIAGONAL<n && next_c_DIAGONAL<n)&& map[next_r_X][next_c_X]==0
					&& map[next_r_Y][next_c_Y]==0
					&& map[next_r_DIAGONAL][next_c_DIAGONAL]==0) {
				dfs(next_r_DIAGONAL, next_c_DIAGONAL, DIAGONAL_DIRECTION);
			}
			
			
		}else if(direction==Y_DIRECTION) {
			//대각선으로 가는경우
			if((next_r_DIAGONAL<n && next_c_DIAGONAL<n)&& map[next_r_X][next_c_X]==0
					&& map[next_r_Y][next_c_Y]==0
					&& map[next_r_DIAGONAL][next_c_DIAGONAL]==0) {
				dfs(next_r_DIAGONAL, next_c_DIAGONAL, DIAGONAL_DIRECTION);
			}
			
			//아래로 가는경우
			if((next_r_Y<n && next_c_Y<n) && map[next_r_Y][next_c_Y]==0) {
				dfs(next_r_Y, next_c_Y, Y_DIRECTION);
			}
			
		}else if(direction==DIAGONAL_DIRECTION) {
			//오른쪽
			if((next_r_X<n && next_c_X<n) && map[next_r_X][next_c_X]==0) {
				dfs(next_r_X, next_c_X, X_DIRECTION);
			}
			
			//대각선
			if((next_r_DIAGONAL<n && next_c_DIAGONAL<n)&& map[next_r_X][next_c_X]==0
					&& map[next_r_Y][next_c_Y]==0
					&& map[next_r_DIAGONAL][next_c_DIAGONAL]==0) {
				dfs(next_r_DIAGONAL, next_c_DIAGONAL, DIAGONAL_DIRECTION);
			}
			
			//아래
			if((next_r_Y<n && next_c_Y<n) && map[next_r_Y][next_c_Y]==0) {
				dfs(next_r_Y, next_c_Y, Y_DIRECTION);
			}
		}
		
	}

}
