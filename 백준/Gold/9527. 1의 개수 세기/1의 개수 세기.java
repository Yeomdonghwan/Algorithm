import java.util.Scanner;

public class Main {
	static long[] dp = new long[55]; //2의 index승일 때 0~2^idx 까지의 1의개수 누적합
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long A = sc.nextLong();
		long B = sc.nextLong();
		
		setDp();
		
		System.out.println(getOne(B) - getOne(A-1));
		

	}
	
	static void setDp() {
		dp[0]=1;
		for(int i=1;i<55;i++) {
			//dp[i] = 2^(i-1) + 2 * dp[i-1]
			dp[i]=(1L<<i) + 2*dp[i-1];
		}
	}
	
	static long getOne(long num) {
		long count = num & 1;
		int n = (int) (Math.log(num)/Math.log(2)); //num보다 작은 것 중 가장 큰 2의 거듭제곱수
		for(int i=n; i>0; i--) {//왼쪽서부터 한자리씩 ...
			if((num & (1L<<i))==0)	continue; //0인 자리는 신경x
			
			count+=(dp[i-1] + (num - (1L << i) + 1L));
			num -= (1L << i); //처리한 숫자 없애기
		}
		return count;
	}

}
