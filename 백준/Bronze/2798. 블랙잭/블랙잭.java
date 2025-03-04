import java.util.*;
import java.io.*;

public class Main {

	static int ans=0;
	static int N,M;
	static int[] combination;
	static int[] input;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		input = new int[N];
		combination = new int[3];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			input[i]=Integer.parseInt(st.nextToken());
		}
		
		dfs(0,0,0);
		System.out.println(ans);
		
	}
	
	static void dfs(int step, int count, int sum) {
		if(count==3) {
//			System.out.println(Arrays.toString(combination)+","+sum);
			ans=Math.max(ans, sum);
			return;
		}
		
		for(int i=step;i<N;i++) {
			if(sum+input[i]>M)	continue;
			
			combination[count]=input[i];
			dfs(i+1, count+1, sum+input[i]);
		}
	}

}
