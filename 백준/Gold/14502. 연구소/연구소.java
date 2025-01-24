import java.util.*;
import java.io.*;

public class Main {
	static class Point{
		int x,y;
		public Point(int x, int y) {
			this.x=x;
			this.y=y;
		}
		
		@Override
		public String toString() {
			return String.format("Point [x=%d, y=%d]",x,y);
		}
	}
	static List<Point> list = new ArrayList<>();
	static Stack<Point> set = new Stack<>();
	static int[][] map;
	static int n,m;
	static boolean[][] visited;
	static final int WALL=1;
	static final int VIRUS=2;
	static final int ROAD=0;
	static int rs = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		visited = new boolean[n][m];
		
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==ROAD)
					list.add(new Point(i,j));
			}
		}
		
		dfs(0);
		
		System.out.println(rs);
		
	}
	static void dfs(int step) {

		
		if(set.size()==3){
//			System.out.println(set.toString());
			
			int[][] map_random_wall = new int[n][m];
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					map_random_wall[i][j]=map[i][j];
//					System.out.print(map_random_wall[i][j]+" ");
				}
//				System.out.println();
			}
//			
			for(int i=0;i<set.size();i++) {
				//벽 세울 세 곳
				int r = set.get(i).x;
				int c = set.get(i).y;
//				System.out.println(r+","+c);
				map_random_wall[r][c]=WALL;
			}
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					if(map_random_wall[i][j]==VIRUS) {
						bfs(map_random_wall,i,j);
					}
				}
			}
			
			int count=0;
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					if(map_random_wall[i][j]==ROAD) {
						count++;
					}
				}
			}
			rs=Math.max(count, rs);
			
			return;
		}
		if(list.size()==step)
			return;
		
		set.add(list.get(step));
		dfs(step+1);
		set.pop();
		
		dfs(step+1);

	}
	
	static void bfs(int[][] map,int startR,int startC) {
		Queue<Integer> rQueue = new LinkedList<>();
		Queue<Integer> cQueue = new LinkedList<>();
		
		int[] dr = {0,0,-1,1};
		int[] dc = {1,-1,0,0};
		
		rQueue.add(startR);
		cQueue.add(startC);
		
		while(!rQueue.isEmpty()) {
			int nowR = rQueue.poll();
			int nowC = cQueue.poll();
			
			visited[nowR][nowC]=true;
			map[nowR][nowC]=VIRUS;
			
			for(int i=0;i<4;i++) {
				int nextR = nowR+dr[i];
				int nextC = nowC+dc[i];
				
				if(nextR<0 || nextR>=n || nextC >=m || nextC<0)
					continue;
				
				if(map[nextR][nextC]!=ROAD)	
					continue;
				
				rQueue.add(nextR);
				cQueue.add(nextC);
			}
		}
	}
}
