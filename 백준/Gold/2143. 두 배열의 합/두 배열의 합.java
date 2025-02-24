import java.util.*;
import java.io.*;
public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		int[] A = new int[n];
		Map<Integer,Integer> Amap = new HashMap<>();
		st= new StringTokenizer(br.readLine());
		int sum=0;
		for(int i=0;i<n;i++) {
			int num = Integer.parseInt(st.nextToken());
			A[i]=num;
			sum+=num;
			if(!Amap.containsKey(sum)) {
				Amap.put(sum, 1);
			}else {
				Amap.put(sum,Amap.get(sum)+1);
			}
		}
		
		for(int i=1;i<n;i++) {
			sum=0;
			for(int j=i;j<n;j++) {
				sum+=A[j];
			
				if(!Amap.containsKey(sum)) {
					Amap.put(sum, 1);
				}else {
					Amap.put(sum,Amap.get(sum)+1);
				}
			}
		}
		
		int m = Integer.parseInt(br.readLine());

		int[] B = new int[m];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<m;i++) {
			int num = Integer.parseInt(st.nextToken());
			B[i]=num;
		}
		long ans=0;
		for(int i=0;i<m;i++) {
			sum=0;
			for(int j=i;j<m;j++) {
				sum+=B[j];
				
				if(Amap.containsKey(T-sum)) {
					ans+=Amap.get(T-sum);
				}
			}
		}
		
		System.out.println(ans);
	}

}
