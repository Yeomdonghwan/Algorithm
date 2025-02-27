import java.util.*;
import java.io.*;

public class Solution {
	
	static int[] expect;
	static int[] price;
	static int minCost=Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case<=T ; test_case++) {
			price = new int[4];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0;i<4;i++) {
				price[i]=Integer.parseInt(st.nextToken());
			}
			int[] schedule = new int[12];
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<12;i++) {
				schedule[i]=Integer.parseInt(st.nextToken());
			}
			
			expect = new int[12];
			
			//1일권, 한달권으로 계산
			for(int i=0;i<12;i++) {
				expect[i]=Math.min(price[0]*schedule[i],price[1]);
			}
			minCost=Integer.MAX_VALUE;
			//3달권 계산
			bruteForce(0,0);
            minCost=Math.min(minCost, price[3]);
			bw.append("#"+test_case+" "+minCost+"\n");
			
		}
		bw.flush();
		bw.close();

	}
	static void bruteForce(int step,int totalCost) {
		if(step>=12) {
			minCost=Math.min(minCost, totalCost);
			return;
		}
		
			//3달권 사는경우
			bruteForce(step+3, totalCost+price[2]); 

			//3달권 안사는경우
			bruteForce(step+1, totalCost+expect[step]);

	}

}
