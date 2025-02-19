import java.util.*;
import java.io.*;

public class Solution {

	static int N;
	static int[][] xys;
	static int ans,distance;
	static boolean[] visited;
	static int prev_r, prev_c;
	static int goal_r, goal_c;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case<=T; test_case++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			xys = new int[N][2];
			
			int start_r = Integer.parseInt(st.nextToken());
			int start_c = Integer.parseInt(st.nextToken());
			goal_r = Integer.parseInt(st.nextToken());
			goal_c = Integer.parseInt(st.nextToken());
			for(int i=0;i<N;i++) {
				xys[i][0] = Integer.parseInt(st.nextToken());
				xys[i][1] = Integer.parseInt(st.nextToken());
			}
			prev_r = start_r;
			prev_c = start_c;
			
			ans=Integer.MAX_VALUE;
			distance=0;
			visited=new boolean[N];
			
			dfs(0);
			bw.append("#"+test_case+" "+ans+"\n");

		}
		bw.flush();
		bw.close();

	}
	
	static void dfs(int step){
		if(step==N) {
			//마지막 지점 - 집 거리 계산
			int last_distance = Math.abs((goal_r - prev_r))+Math.abs((goal_c-prev_c));
			distance+=last_distance;
			ans=Math.min(ans, distance);

			//원복
			distance-=last_distance;
			return;
		}
		
		for(int i=0;i<N;i++) {
			if(visited[i])	continue;
			//이전 지점과 현재 지점 거리 계산. i==0인 경우 prev는 회사임
			int new_distance = Math.abs((xys[i][0]-prev_r))+Math.abs((xys[i][1]-prev_c));
			
			//가지치기
			if(distance+new_distance>=ans)	continue;
			
			int origin_prev_r = prev_r;
			int origin_prev_c = prev_c;
			
			prev_r = xys[i][0];
			prev_c = xys[i][1];
			distance += new_distance;
			visited[i]=true;
			dfs(step+1);
			
			//원복
			distance-=new_distance;
			prev_r = origin_prev_r;
			prev_c = origin_prev_c;
			visited[i]=false;
			
		}
	}

}
