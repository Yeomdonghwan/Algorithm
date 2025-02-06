import java.io.*;
import java.util.*;

public class Main {
	static final int WHITE = 0;
	static final int RED = 1;
	static final int BLUE = 2;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//하,우,좌,상
		int[] dr = {1,0,0,-1};
		int[] dc = {0,1,-1,0};
		
		int n = Integer.parseInt(st.nextToken());//체스판 크기
		int k = Integer.parseInt(st.nextToken());//말 개수
		int[][] map = new int [n][n];
		Stack<Integer>[][] stack_map = new Stack[n][n];
		//말 정보를 저장. i번째 말 [i][0]=행 좌표 [i][1]=열 좌표 i[2]=방향
		int[][] piece = new int[k][3];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				stack_map[i][j]=new Stack<>();//스택맵 초기화
			}
		}
		
		//초기설정: 체스판에 말 올려두기
		for(int i=0;i<k;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;//행
			int c = Integer.parseInt(st.nextToken())-1;//열
			int d = Integer.parseInt(st.nextToken())%4;//방향. 4는 0으로 넣음
			
			piece[i][0]=r;
			piece[i][1]=c;
			piece[i][2]=d;
			
			stack_map[r][c].add(i);
			if(stack_map[r][c].size()==4) {
				System.out.println("0");
				return;
			}
		}
		
		int round=0;
		
		esc:
		for(round=1;round<1000;round++) {
			for(int i=0;i<k;i++) {
				//말 하나씩 
				
				//현재 말의 위치와 진행방향 꺼냄
				int r = piece[i][0];
				int c = piece[i][1];
				int d = piece[i][2];
				
				int next_r = r+dr[d];
				int next_c = c+dc[d];
				if(next_r>=n||next_c>=n||next_r<0||next_c<0 || map[next_r][next_c]==BLUE) {
					//벽에 박는경우 또는 다음칸이 파란색인 경우
					//방향 반대로
					switch(d) {
					case 0:
						d=3; //하->상
						break;
					case 1:	//우->좌
						d=2;
						break;
					case 2: //좌->우
						d=1;
						break;
					case 3://상->하
						d=0;
						break;
					}
					//말 진행방향 갱신
					piece[i][2]=d;
					
					//다음 위치 재계산
					next_r = r+dr[d];
					next_c = c+dc[d];
					
				}
				
				if(next_r>=n||next_c>=n||next_r<0||next_c<0)
					continue;
				else if(map[next_r][next_c]==WHITE) {
					Stack<Integer> temp = new Stack<>(); //임시스택
					
					while(!stack_map[r][c].isEmpty()) {
						//임시스택으로 이동
						int pop_piece = stack_map[r][c].pop();
						temp.push(pop_piece);
						
						if(pop_piece==i) {
							//i번째 말이 나왔다면 멈춤
							break;
						}
					}
					while(!temp.isEmpty()) {
						//임시스택에서 다음위치로 이동
						int pop_piece = temp.pop();
						stack_map[next_r][next_c].push(pop_piece);

						//위치갱신
						piece[pop_piece][0]=next_r;
						piece[pop_piece][1]=next_c;
					}
					
				}else if(map[next_r][next_c]==RED) {
					while(!stack_map[r][c].isEmpty()) {
						//바로 다음 위치로 넣음.
						int pop_piece = stack_map[r][c].pop();
						stack_map[next_r][next_c].push(pop_piece);
						
						//위치갱신
						piece[pop_piece][0]=next_r;
						piece[pop_piece][1]=next_c;
						
						if(pop_piece==i) {
							//i번째 말이 나왔다면 멈춤
							break;
						}
					}
				}
				
				if(stack_map[next_r][next_c].size()>=4) {
					
					break esc;
				}

				
			}
		}
		round=(round==1000)?-1:round;
		System.out.println(round);
		
		
		
		
	
}
	}
