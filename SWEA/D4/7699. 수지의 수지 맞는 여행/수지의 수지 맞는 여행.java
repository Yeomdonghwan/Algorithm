import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * R행 C열 까지 있다.
 * 수지는 1,1에 있으며 
 * 같은 알파벳을 최대한 보지 않도록 하는 방법*/
public class Solution {
	static final int dr[] = {1, 0, -1, 0};
	static final int dc[] = {0, 1, 0, -1};
	
	static int R,C;
	static int map[][];
	static boolean isVisited[][];
	static int result;
	
	static void dfs(int cnt, int flag,int curR, int curC) {
		result = Math.max(result, cnt);
		
		isVisited[curR][curC] = true;
		
		for(int i = 0; i < 4; i++) {
			int nr = curR + dr[i];
			int nc = curC + dc[i];
			
			if(isValid(nr,nc) && !isVisited[nr][nc]) {
				if((flag & (1 << map[nr][nc])) == 0) {

					dfs(cnt + 1, (flag|(1<<map[nr][nc])), nr, nc);
				}
				
			}
		}
		
		isVisited[curR][curC] = false;
	}
	
	static boolean isValid(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0  && nc < C;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new int [R][C];
			isVisited = new boolean[R][C];
			
			result = 0;
			
			for(int i = 0 ; i < R; i++) {
				String str = br.readLine();
				for(int j = 0; j < C; j++) {
					map[i][j] = str.charAt(j) - 'A';
				}
			}
			
			
			dfs(1, 1<<map[0][0], 0, 0);
			
			System.out.printf("#%d %d\n",t,result);
		}
		
	}

}
