import java.util.*;
import java.io.*;

public class Solution {

	static int[][] map;
	static final int EMPTY=0;
	static final int ROAD=1;
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		for(int test_case = 1; test_case<= 10; test_case++) {
            
		int T = Integer.parseInt(br.readLine());
			map = new int[100][100];
			for(int i=0;i<100;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<100;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
		
			int goal=0;
			for(int i=0;i<100;i++) {
				if(map[99][i]==2) {
					goal = i;
					break;
				}
			}
			
			int r=99, c=goal;
			while(r>0) {

				//좌회전 ..
				if(c-1>=0 && map[r][c-1]==ROAD) {
					
					//왼쪽 길을 따라 쭉 이동
					while(c-1>=0 && map[r][c-1]==ROAD) {
						c--;
					}
					
				}else if(c+1<100 && map[r][c+1]==ROAD) {
					//우회전
					
					while(c+1<100 && map[r][c+1]==ROAD) {
						c++;
					}
				}

				//직진
				r--;

			}
			
			bw.append("#"+test_case+" "+c+"\n");
				
		}
		bw.flush();
		bw.close();
		
	}

}
