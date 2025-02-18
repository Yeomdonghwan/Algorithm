import java.util.*;
import java.io.*;

public class Main {

	static int[] dr = {1,0,-1};
	static int n,m, ans=0;
	static char[][] map;
	static boolean find=false;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		
		for(int i=0;i<n;i++) {
			String str = br.readLine();
			for(int j=0;j<m;j++) {
				map[i][j] = str.charAt(j);
			}
		}          
		
		for(int i=n-1;i>=0;i--) {
			find=false;
			dfs(i,0);
		}

		System.out.println(ans);
	}
	
	static void dfs(int r, int c) {
		if(find) {
			return;
		}
		
		if(c==m-1) {
			ans++;
			find=true;
			return;
		}
		
		map[r][c]='X';
		
		for(int k=0;k<3;k++) {
			//3방향 탐색
			int next_r = r+dr[k];
			int next_c = c+1;
			if(next_r >=n || next_c>=m || next_r<0 || next_c<0)
				continue;
			
			if(map[next_r][next_c]=='.')
				dfs(next_r, next_c);
		}
	}

}
