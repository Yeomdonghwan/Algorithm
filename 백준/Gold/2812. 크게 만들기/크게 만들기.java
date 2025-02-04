import java.io.*;
import java.util.*;

public class Main {
	static final int DELETED= -1;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		String nums = br.readLine();
		int[] num_arr = new int[nums.length()];
		for(int i=0;i<nums.length();i++) {
			num_arr[i]=nums.charAt(i)-'0';
		}

		for(int i=0;i<num_arr.length;i++) {
			//i번째 숫자와 그 앞 숫자들 비교
			for(int j=i-1;k>0 && j>=0;j--) {
				if(num_arr[j]==DELETED)
					continue;
				if(num_arr[i]>num_arr[j]) {
					num_arr[j]=DELETED;
					k--;
				}else {
					break;
				}
			}
		}
        //k개를 다 지우지 못한 경우 뒤에서부터 삭제
		for(int i=num_arr.length-1;k>0 && i>=0;i--) {
			if(num_arr[i]==DELETED)
				continue;
			num_arr[i]=DELETED;
			k--;
		}
		for(int i=0;i<num_arr.length;i++) {
			if(num_arr[i]!=DELETED)
				sb.append(num_arr[i]);
		}

		bw.append(sb.toString());
		bw.flush();
		bw.close();
	}
}
