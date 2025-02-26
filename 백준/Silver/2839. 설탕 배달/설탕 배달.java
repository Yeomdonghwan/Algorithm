import java.util.*;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		//오키로, 삼키로짜리 있삼..
		int fiveBag = N/5;
		int threeBag = 0;
		N=N%5;
		
		boolean flag=false;
		do {
			if(N%3==0) {
				threeBag=N/3;
				flag=true;
				break;
			}else {
				fiveBag--;
				N+=5;
			}
		}while(fiveBag>=0);
		
		if(fiveBag>=0) {
			System.out.println(fiveBag+threeBag);
		}else {
			System.out.println(-1);
		}
			
	}

}
