import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException{
		Scanner scanner = new Scanner(System.in);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = scanner.nextInt();
		for(int test_case = 1; test_case<=T ; test_case++) {
			bw.append("#"+test_case+"\n");
			
			int N = scanner.nextInt();
			int[][] map = new int[N][N];
			
			int num=1;
			int r=0,c=0;
			int[] dr= {0,1,0,-1};//우,하,좌,상
			int[] dc= {1,0,-1,0};//우,하,좌,상
			
			int direction=0;
			int straightCount=1;
			int straightLimit=N;
			int directionChangeCount=1;
			while(num<=N*N) {
				//정해진 만큼 직진 한 경우 방향전환
				if(straightCount==straightLimit) {
					straightCount=0;
					direction=(direction+1) %4;
					directionChangeCount++;
				}
				
				//방향전환 두번한 경우 직진거리 감소
				if(directionChangeCount==2) {
					straightLimit--;
					directionChangeCount=0;
				}

				map[r][c]=num++;
				
				straightCount++;
				r+=dr[direction];
				c+=dc[direction];
			}
			


			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					bw.append(map[i][j]+" ");
				}
				bw.append("\n");
			}
			
		}
		
		bw.flush();
		bw.close();
	}

}
