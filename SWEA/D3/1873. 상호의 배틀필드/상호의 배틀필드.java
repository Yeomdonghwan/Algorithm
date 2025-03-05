import java.util.*;
import java.io.*;

public class Solution {

	static final int LEFT = 1;
	static final int RIGHT = 0;
	static final int UP = 2;
	static final int DOWN = 3;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int[] dr = {0,0,-1,1};//우좌상하
		int[] dc = {1,-1,0,0};
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1;test_case<=T; test_case++) {
			bw.append("#"+test_case+" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			int H,W;
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			char[][] map = new char[H][W];
			int r=0,c=0;
			int direction=0;
			for(int i=0;i<H;i++) {
				String input = br.readLine();
				for(int j=0;j<W;j++) {
					map[i][j]=input.charAt(j);
					if(map[i][j]=='<') {
						r=i;
						c=j;
						direction=LEFT;
					}else if(map[i][j]=='>') {
						r=i;
						c=j;
						direction=RIGHT;
						
					}else if(map[i][j]=='v') {
						r=i;
						c=j;
						direction=DOWN;
						
					}else if(map[i][j]=='^') {
						r=i;
						c=j;
						direction=UP;
						
					}
				}
			}
			
			int N = Integer.parseInt(br.readLine());
			String input = br.readLine();
			for(int i=0;i<N;i++) {
				char command = input.charAt(i);
				
				if(command=='U' || command=='D' || command=='L' || command=='R') {
					
					char car='.';
					if(command=='U') {
						direction=UP;
						car='^';	
					}
					else if(command=='D')
					{
						direction=DOWN;
						car='v';
					}
					else if(command=='L') {
						car='<';
						direction=LEFT;	
					}
					else if(command=='R')
					{
						direction=RIGHT;
						car = '>';
					}
					
					map[r][c] = car;
					
					int next_r = r+dr[direction];
					int next_c = c+dc[direction];
					if(next_r<0 || next_c<0 || next_r>=H|| next_c >=W)
						continue;
					if(map[next_r][next_c]!= '.')	continue;

					map[r][c]='.';
					r=next_r;
					c=next_c;
					
					map[next_r][next_c]=car;
					
				}else {
					//shoot
					int bomb_r = r;
					int bomb_c = c;

					while(true) {
						bomb_r += dr[direction];
						bomb_c += dc[direction];
						
						if(bomb_r>=H || bomb_c >= W || bomb_r <0 || bomb_c <0)
							break;;
						
						if(map[bomb_r][bomb_c]=='#')
							break;
						

						if(map[bomb_r][bomb_c]=='*')
						{
							map[bomb_r][bomb_c]='.';
							break;
						}

					}
				}
				
//				bw.append(command+"\n");
//				for(int z=0;z<H;z++) {
//					for(int j=0;j<W;j++) {
//						bw.append(map[z][j]);
//					}
//					bw.append("\n");
//				}
//				bw.append("\n\n");
			}
			
			for(int i=0;i<H;i++) {
				for(int j=0;j<W;j++) {
					bw.append(map[i][j]);
				}
				bw.append("\n");
			}
			
			
		}
		bw.flush();
		bw.close();
	}

}
