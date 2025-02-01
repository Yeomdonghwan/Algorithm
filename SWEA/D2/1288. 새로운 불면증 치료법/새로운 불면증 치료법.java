import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{


        Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{
		
            int masking_nums = 0;
            int check = 0;
            check = (1<<10)-1 | check;

            int n = sc.nextInt();
            int now_num= n;
            while(true){
                //현재 본 숫자 마스킹
                int temp = now_num;
                while(temp>0){
                    int last_num = temp%10;

                    masking_nums = (1<<last_num)|masking_nums;

                    temp/=10;
                }

                //다 봤는지 체크
                if(check==masking_nums){
                    System.out.println("#"+test_case+" "+now_num);
                    break;
                }
                
                //숫자 N더하기
                now_num+=n;
            }
		}
	}
}