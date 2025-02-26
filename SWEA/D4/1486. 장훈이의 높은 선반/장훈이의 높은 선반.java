import java.util.*;
import java.io.*;
import java.io.FileInputStream;

class Solution
{
	static Stack<Integer> stack = new Stack<>();
//	static int sum=0;
	static int N,B;
	static int[] arr;
	static int ans=Integer.MAX_VALUE;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T=Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			arr=  new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			ans=Integer.MAX_VALUE;
			dfs(0,0);
//			System.out.println(ans-B);
			bw.append("#"+test_case+" "+(ans-B)+"\n");
		}
		bw.flush();
		bw.close();
	}
	
	static void dfs(int step, int sum) {
		if(step==N || sum>=B ) {
			if(sum>=B) {
				ans = Math.min(ans, sum);
			}
			return;
		}
		
		stack.add(arr[step]);
		dfs(step+1, sum+arr[step]);
		stack.pop();
		
		dfs(step+1,sum);
	}
}