import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int map[][];
	static int cctv[][];
	static int r, c, result, size;
	
	static int dr[] = {1, 0, -1, 0};
	static int dc[] = {0, 1, 0, -1};
	static final int[][][] direct = {
	        {}, 
	        {{0},{1},{2},{3}}, 
	        {{0,2},{1,3}}, 
	        {{0,1},{1,2},{2,3},{3,0}}, 
	        {{0,1,2},{1,2,3},{2,3,0},{3,0,1}}, 
	        {{0,1,2,3}}
	    };
	
	static boolean isValid(int nc, int nr) {
		return nr < r && nr >= 0 && nc < c && nc >= 0; 
	}
	
	static int process(int rr, int cc, int cctv1, int direction) {
		int result = 0;
		
		for(int i : direct[cctv1][direction]) {
			int nr = rr + dr[i];
			int nc = cc + dc[i];
			while(isValid(nc,nr) && map[nr][nc] != 6) {
				if(cctv[nr][nc] == 0 )
					result++;
				
				cctv[nr][nc]++;
				
				nr += dr[i];
				nc += dc[i];
			}
		}
		
		return result;
	}
	
	static void process2(int rr, int cc, int cctv1, int direction) {
		for(int i : direct[cctv1][direction]) {
			int nr = rr + dr[i];
			int nc = cc + dc[i];
			while(isValid(nc,nr) && map[nr][nc] != 6) {
				cctv[nr][nc]--;
				
				nr += dr[i];
				nc += dc[i];
			}
		}
	}
	
	static private void dfs(int current,int rr, int cc) {
		result = Math.min(result, size - current);
		//print();
		
		for(int i = rr; i < r; i++) {
			for(int j = (i == rr)?cc:0; j < c; j++) {
				if(map[i][j] != 6 && map[i][j] != 0) {
					for(int k = 0; k < direct[map[i][j]].length; k++) {
						dfs(current + process(i, j, map[i][j], k), i, j + 1);
						process2(i, j, map[i][j], k);
					}
				}
					
			}
		}
	}
	
	public static void print() {
		for(int i = 0 ; i < r; i++) {
			for(int j = 0 ; j < c; j++) {
				System.out.print(cctv[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("--------------------------------");
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new int[r][c];
		cctv = new int[r][c];
		size = r * c; //맵의 전체 크기
		
		for(int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0) {
					cctv[i][j] = 1;
					size--;
				}
			}
		}
		
		result = size;
		
		dfs(0,0,0);
		
		System.out.println(result);
	}
}