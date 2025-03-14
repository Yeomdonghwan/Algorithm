import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine()); //원하는 피자 크기
		
		int M,N;
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		int[] pizzaA = new int[M];
		int[] pizzaB = new int[N];
		int[] pieceA = new int[K+1];
		int[] pieceB = new int[K+1];
		for(int i=0;i<M;i++) {
			pizzaA[i]=Integer.parseInt(br.readLine());
			//1조각으로 만들수있는 무게 ++
			pieceA[pizzaA[i]]++;
		}
		for(int i=0;i<N;i++) {
			pizzaB[i]=Integer.parseInt(br.readLine());
			if(pizzaB[i]<=K)
				pieceB[pizzaB[i]]++;
		}
		
		 for(int size=2; size<M; size++) {
			 //size별로 만들수있는 모든경우의수 계산

			 int sum=0;
			 for(int i=0;i<size;i++) {
				 sum+=pizzaA[i];
			 }
			 //0부터 size개 조각들의 합으로 만든 피자크기
			 if(sum<=K)
				 pieceA[sum]++;
//			 System.out.println(sum);
			 
			 for(int idx=1;idx<M;idx++) {
				 sum-=pizzaA[idx-1]; //이전거 앞꺼 없애고
				 sum+=pizzaA[(idx+size-1)%M];//새로운 숫자 더함
				 if(sum<=K) {
					 pieceA[sum]++;
				 }
//				 System.out.println(sum);
			 }
			
		 }
		int sum=0;
		for(int i=0;i<M;i++) {
			sum+=pizzaA[i];
		}
		//다 더한경우
		if(sum<=K)
			pieceA[sum]++;
		
		//Bㅠㅣ자
		for(int size=2;size<N;size++) {
			//B피자의 조각 계산
			 sum=0;
			 for(int i=0;i<size;i++) {
				 sum+=pizzaB[i];
			 }
			 if(sum<=K)
				 pieceB[sum]++;
			 

			 for(int idx=1;idx<N;idx++) {
				 sum-=pizzaB[idx-1]; //이전거 앞꺼 없애고
				 sum+=pizzaB[(idx+size-1)%N];//새로운 숫자 더함
				 if(sum<=K) {
					 pieceB[sum]++;
				 }
//				 System.out.println(sum);
			 }
		}
		sum=0;
		for(int i=0;i<N;i++) {
			sum+=pizzaB[i];
		}
		if(sum<=K)
			pieceB[sum]++;
//		
//		System.out.println(Arrays.toString(pieceA));
//		System.out.println(Arrays.toString(pieceB));

		
		int ans=0;
		ans+=pieceA[K];
		ans+=pieceB[K];
		for(int i=0;i<K;i++) {
			ans+=pieceA[i]*pieceB[K-i];
		}
		System.out.println(ans);
	}

}
