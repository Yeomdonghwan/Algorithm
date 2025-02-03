import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1;test_case<=T;test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[n+1][n+1];
			for(int i=0;i<=n;i++) {
				for(int j=0;j<=n;j++) {
					map[i][j]=Integer.MAX_VALUE;
				}
			}
			for(int i=1;i<=n;i++) {
				map[i][i]=0;
			}
			
			for(int i=0;i<m;i++) {
				st = new StringTokenizer(br.readLine());

				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				if(map[s][e]>t)
					map[s][e]=map[e][s]=t;
			}
			for(int i=0;i<w;i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				if(map[s][e]>-t)
					map[s][e]=t*(-1);
				
				
			}
			

			
			boolean ans=false;
			
			esc:
			for(int k=1;k<=n;k++) {
				for(int i=1;i<=n;i++) {
					for(int j=1;j<=n;j++) {
						if(map[i][k]==Integer.MAX_VALUE || map[k][j]==Integer.MAX_VALUE)	continue;
						map[i][j]=Math.min(map[i][j], map[i][k]+map[k][j]);
						
						if(map[i][i]<0) {
							System.out.println("YES");
							ans=true;
							break esc;
						}
						
						
					}
				}
			}
			
			
			if(ans==false)
				System.out.println("NO");
		}
	}

}
