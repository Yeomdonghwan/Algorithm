import java.util.*;
import java.io.*;
public class Solution {

	static int[] arr;
	static int N;
	static int count=0;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			N = Integer.parseInt(br.readLine());

			arr=new int[N];
			count=0;
			bruteForce(0);
			bw.append("#"+test_case+" "+count+"\n");
		}
		bw.flush();
		bw.close();
	}
	
	static void bruteForce(int step) {
		
		if(step==N) {
			count++;
			return;
		}
		
		for(int i=0;i<N;i++) {
			//오른쪽으로 한칸씩 가면서 탐색 . ..
			
			boolean flag=true;
			//i열 선택할 수 있늕니 확인 하장 ..
			for(int j=0;j<step;j++) {
				if(arr[j]==i)
				{
					flag=false;
					break;
					
				}
				if(Math.abs(j-step) ==Math.abs( arr[j]-i))

				{
					flag=false;
					break;
					
				}
			}
			
			if(!flag)
				continue;
			
			arr[step]=i;
			bruteForce(step+1);
		}
		
		
	}

}
