import java.util.*;
import java.io.*;
public class Solution {

	static int N,W,H;
	static int[] balls;
	static int[][] map;
	static int ans=Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			balls = new int[N];
			map = new int[H][W];
			for(int i=0;i<H;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<W;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			//N개 구슬 떨어뜨릴 좌표 모든 경우의 수 찾기
			ans=Integer.MAX_VALUE;
			bruteForce(0);
			
			bw.append("#"+test_case+" "+ans+"\n");
		}
		bw.flush();
		bw.close();

	}
	static void bruteForce(int step) {
		if(step==N) {
//			System.out.println(Arrays.toString(balls));
			
			//balls[] 의 값으로 시뮬레이션
			int[][] simulationMap = new int[H][W];
			for(int i=0;i<H;i++) {
				for(int j=0;j<W;j++) {
					simulationMap[i][j]=map[i][j]; //복제
				}
			}
			
			int count = simulation(simulationMap);
			ans=Math.min(count, ans);
			
			return;
		}
		
		for(int i=0;i<W;i++) {
			balls[step]=i;
			bruteForce(step+1);
		}
	}
	
	static int simulation(int[][] simulationMap) {
		
		for(int i=0;i<N;i++) {
			//공 하나씩 꺼냄
			int r = 0;
			int c = balls[i];
			while(r<H) {
				if(simulationMap[r][c]==0) {
					r++;
				}else {
					//터뜨리기
					if(simulationMap[r][c]==1) {
						simulationMap[r][c]=0;
					}else {
						explode(simulationMap, r,c);	
					}
					break;
				}
			}
			
			dropBrick(simulationMap);
//			System.out.println(i+"번째 폭발");
//			for(int l=0;l<H;l++) {
//				for(int j=0;j<W;j++) {
//					System.out.print(simulationMap[l][j]+ " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
		}
		
		
		//블럭개수 세기
		int count=0;
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				if(simulationMap[i][j]>0) {
					count++;
				}
			}
		}
		return count;
	}
	
	//블럭 폭발시키기. 연쇄작용 포함
	static void explode(int[][] simulationMap, int r, int c) {
		int length = simulationMap[r][c]-1;
		simulationMap[r][c]=0;
		
		int[] dr = {0,0,-1,1};
		int[] dc = {1,-1,0,0};
		for(int i=1;i<=length;i++)
		{
			//4방 탐색
			for(int k=0;k<4;k++) {
				int next_r = r+dr[k]*i;
				int next_c = c+dc[k]*i;
				if(next_r<0||next_c<0||next_r>=H||next_c>=W)
					continue;
				
				if(simulationMap[next_r][next_c]==1) {
					simulationMap[next_r][next_c]=0;
				}else if(simulationMap[next_r][next_c]>1) {
					explode(simulationMap,next_r,next_c);
				}
			}
		}
	}
	
	//블럭 내리기
	static void dropBrick(int[][] simulationMap) {
		for(int c=0;c<W;c++) {
			int idx=H-1;
			for(int r=H-1;r>=0;r--) {
				if(simulationMap[r][c]>0)
				{
					if(idx==r)
					{
						simulationMap[idx--][c]=simulationMap[r][c];
					
					}else {
						simulationMap[idx--][c]=simulationMap[r][c];
						simulationMap[r][c]=0;
					}
				}
			}

		}
	}

}
