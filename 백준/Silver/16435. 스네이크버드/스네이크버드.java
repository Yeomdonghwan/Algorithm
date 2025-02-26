import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int[] height = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(height);
		
		for(int i=0;i<N;i++) {
			if(height[i]<=L) {
				L++;
			}else {
				break;
			}
		}
		System.out.println(L);
	}

}
