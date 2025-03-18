import java.util.*;
import java.io.*;

public class Main {
	static int[][] map;
	static int[] dr = {0,0,-1,1};
	static int[] dc = {1,-1,0,0};
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M,fuel;
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		fuel=Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int r,c;
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken())-1;
		c = Integer.parseInt(st.nextToken())-1;
		
		List<int[]> passengers =new ArrayList<>();
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			//승객과 목적지
			int start_r = Integer.parseInt(st.nextToken())-1;
			int start_c = Integer.parseInt(st.nextToken())-1;
			int end_r = Integer.parseInt(st.nextToken())-1;
			int end_c = Integer.parseInt(st.nextToken())-1;
			
			passengers.add(new int[] {start_r, start_c, end_r, end_c});
			
		}
		
		
		for(int taxi=0;taxi<M;taxi++) {
			//택시승객 수
			
			//택시기준으로 bfs로 맵 전체에 도달하기까지 거리 모두 구하기
			int[][] visited = new int[N][N];
			bfs(r,c, visited);
			
			int passenger_idx = -1;
			int min_dist = Integer.MAX_VALUE;
			for(int i=0;i<passengers.size();i++) {
				//한명씩 돌면서 가장 가까운사람 찾기
				int[] passenger = passengers.get(i);
				int dist = visited[passenger[0]][passenger[1]]-1;
				if(dist==-1)
					continue; //도달하지 못하는경우
				
				if(dist>fuel)
					continue;
				
				if(dist==min_dist) {
					int[] prev = passengers.get(passenger_idx);
					if((prev[0]>passenger[0])||
						(prev[0]==passenger[0] && prev[1]>passenger[1]) ) {
						passenger_idx = i;
					}
					
				}
				
				if(dist<min_dist) {
					passenger_idx=i;
					min_dist=dist;
				}
				
			}
			
			//연료부족 진행불가
			if(passenger_idx==-1) {
				System.out.println(-1);
				return;
			}
			
			//승객에게 이동
			fuel-=min_dist;
			
			int[] passenger = passengers.get(passenger_idx);
			r = passenger[0];
			c = passenger[1];
			
			//택시기준으로 bfs로 맵 전체에 도달하기까지 거리 모두 구하기
			visited = new int[N][N];
			bfs(r,c, visited);
			
			int dist = visited[passenger[2]][passenger[3]]-1;
			if(dist>fuel || dist==-1) {
				//연료부족 또는 도달하지 못하는 경우
				System.out.println(-1);
				return;
			}
			
			fuel+=dist;
			
			r=passenger[2];
			c=passenger[3];
			
			//태운 승객 제거
			passengers.remove(passenger_idx);
			
		}
		System.out.println(fuel);
		
		
	}
	
	static void bfs(int r, int c, int[][] visited) {
		visited[r][c]=1; //1부터시작... 나중에 거리계산시 꼭 1 빼주기
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {r,c});
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int k=0;k<4;k++) {
				int next_r = now[0]+dr[k];
				int next_c = now[1]+dc[k];
				
				if(next_r>=N || next_c>=N || next_r<0 || next_c<0)
					continue;
				if(map[next_r][next_c]==1 || visited[next_r][next_c]>0)
					continue;
				
				visited[next_r][next_c]=visited[now[0]][now[1]]+1;
				q.add(new int[] {next_r,next_c});
				
				
			}
		}
	}

}
