import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			List<Integer> list = new ArrayList<>();
			int[] arr = new int[n];
			int max=0;
			for(int i=0;i<n;i++) {
				int num = Integer.parseInt(st.nextToken());
				max=Math.max(num, max);
				arr[i]=num;
			}
			int k=0; //남은양이 홀수인 수의 개수
			int days=0; //날짜
			int remainTotal=0;
			for(int i=0;i<n;i++) {
				int num = arr[i];
				
				int remain = max-num;
				if(remain>0) {
					if(remain%2==1)//남은 양이 홀수라면 홀수날짜에 물을 줘야함 확정
					{	k++;
						remainTotal+=(remain-1);//미리 물 주고 넣음
					}else {
						remainTotal+=remain;
					}
				
				}
			
			}
			
			if(k>0) {
				days=2*(k-1)+1;
				remainTotal-=(2*(k-1));
				
				if(remainTotal<=0) {
					//끝남
					bw.append("#"+test_case+" "+days+"\n");
					continue;
				}
	
				//다음날 물주는거 1로 맞추기 위해서 하루 더 보냄
				days++;
				remainTotal-=2;
				if(remainTotal<=0) {
					//끝남
					bw.append("#"+test_case+" "+days+"\n");
					continue;
				}
			}
			// 이제 남은 숫자들은 다 짝수임
			days+=4*(remainTotal/6);
			remainTotal%=6;
			if(remainTotal==2) {
				days+=2;
			}else if(remainTotal==4) {
				days+=3;
			}
			bw.append("#"+test_case+" "+days+"\n");
		}
		bw.flush();
		bw.close();
	}
}