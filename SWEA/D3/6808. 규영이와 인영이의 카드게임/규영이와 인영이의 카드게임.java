import java.util.*;
import java.io.*;

public class Solution {

	static int[] arr_A;
	static int[] arr_B;
	static int[] arr_perm;
	static boolean[] visited_B;
	static int score_A;
	static int score_B;
	static int ans_win;
	static int ans_lose;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			st = new StringTokenizer(br.readLine());
			arr_A = new int[9];
			arr_B = new int[9];
			
			boolean[] numbers = new boolean[19];
			for(int i=0;i<9;i++) {
				arr_A[i]=Integer.parseInt(st.nextToken());
				numbers[arr_A[i]]=true;
			}
			
			int idx=0;
			for(int i=1;i<=18;i++) {
				if(!numbers[i]) {
					arr_B[idx++] = i;
				}
			}
			
			arr_perm = new int[9];
			visited_B = new boolean[9];
			score_A=0;
			score_B=0;
			ans_win=0;
			ans_lose=0;
			dfs(0);
			bw.append("#"+test_case+" "+ans_win+" "+ans_lose+"\n");
		}
		bw.flush();
		bw.close();
		
	}
	
	static void dfs(int step) {
		//B의 카드 덱 순열
		if(step==9) {
			if(score_A>score_B) {
				ans_win++;
			}else if(score_B>score_A){
				ans_lose++;
			}
			return;
		}
		
		for(int i=0;i<9;i++) {
			if(visited_B[i])	continue;
			arr_perm[step]=arr_B[i];
			visited_B[i]=true;
			
			int score=arr_A[step]+arr_perm[step];
			if(arr_A[step]>arr_perm[step]) {
				score_A+=score;
			}else {
				score_B+=score;
			}
			
			dfs(step+1);
			
			if(arr_A[step]>arr_perm[step]) {
				score_A-=score;
			}else {
				score_B-=score;
			}
			
			
			visited_B[i]=false;
			
		}
	}

}
