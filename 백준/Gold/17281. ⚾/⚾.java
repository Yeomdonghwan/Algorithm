import java.util.*;
import java.io.*;
public class Main {

	static int[] player = new int[9];
	static boolean[] visited = new boolean[9];
	static boolean[] base;
	static int[][] baseballGame;
	static int N;
	static int maxScore=0;
	public static void main(String[] args) throws Exception, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		baseballGame = new int[N][9];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<9;j++){
				baseballGame[i][j] = Integer.parseInt(st.nextToken()); //i이닝의 j번선수 행동예측
			}
		}
		
		player[3]=0;
		visited[0]=true;
		//0~8번 선수 순서 순열 처리
		perm(0);
		
		System.out.println(maxScore);
	}
	static void perm(int step) {
		if(step==3) {
			perm(step+1);
			return; //4번타자는 이미 0으로 결정
		}
		if(step==9) {
			//순열 찾음
			int score = doBaseball();
			maxScore = Math.max(score, maxScore);
			return;
		}
		for(int i=1;i<9;i++) {
			if(visited[i])	continue;
			
			visited[i]=true;
			player[step]=i;
			perm(step+1);
			visited[i]=false;
		}
	}
	
	static int doBaseball() {
		int idx=0; //선수 인덱스
		int out=0; //아웃 횟수
		int score=0; //점수
		
		
		for(int i=0;i<N;i++) {
			//N번의 이닝 실행
			base = new boolean[3]; //1루,2루,3루
			while(out<3) {
				int command = baseballGame[i][player[idx]];
				
				if(command==0) {
					out++;
				}else {
					score += hitAndRun(command);	
				}
				
				idx=(idx+1)%9;
			}
			out=0;
			
		}
		return score;
		
	}
	
	static int hitAndRun(int command) {
		int score=0;
		for(int i=0;i<command;i++) {
			if(base[2]) {
				score++;
			}
			base[2]=base[1];
			base[1]=base[0];
			if(i==0) {
				base[0]=true;	
			}else {
				base[0]=false;
			}
		}
		return score;
	}
	

}
