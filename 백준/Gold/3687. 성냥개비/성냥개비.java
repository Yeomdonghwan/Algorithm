import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		int[] matchesToMinNum = {-1,-1,1,7,4,2,0,8};
		for(int test_case = 1; test_case<=T; test_case++) {
			int N = Integer.parseInt(br.readLine());

			/**
			 * 최소값 구하기
			 * 성냥개비 7개로 만들 수 있는 8로 가득채워 자릿수를 줄이고,
			 * 앞에서부터 숫자를 줄일 수 있는지 확인
			 * 
			 * */
			int minNumLength = N/7;
			if(N%7>0) {
				minNumLength++;
				int[] minNum = new int[minNumLength];
				
				//배열에 각 자릿수에서 사용할 담배개수 저장
				minNum[0]=N%7;
				for(int i=1;i<minNumLength;i++) {
					minNum[i]=7;
				}
				
				//앞에서부터 두 숫자씩 보면서 최적화
				//ex)[3개,7개] 로 만들 수 있는 숫자는 78
				//ex)[5개,5개] 로 만들면 22
				
				if(!(minNumLength==1 || minNum[0]==2 || minNum[0]==6 || minNum[0]==5)) {
					//숫자 길이가 1 => 더이상 최적화할수업슴
					//첫 숫자 만들 성냥개수가 2개 => 1. 이미 최적임
					//첫 숫자 만들 성냥개수가 5개 => 2. 이미 최적임
					//첫 숫자 만들 성냥개수가 6개 => 더이상 최적화할수없음
				
					//=>
					//첫 숫자가 1,3,4인 경우
	
					//첫 숫자 처리하기.
					if(minNum[0]==1) {
						minNum[0]+=1;
						minNum[1]-=1;
					}else if(minNum[0]==3 || minNum[0]==4) {
						//첫숫자는 0이될 수 없으므로 5개를 사용해 숫자2를 만듦.
						minNum[1]-=(5-minNum[0]);
						minNum[0]=5;
					}
					
					//두번째 숫자~ 끝까지 처리
					//뒤의 성냥을 빌려 현재숫자를 6으로 만들고자 함.
					for(int i=2;i<minNumLength;i++) {
						int now = minNum[i-1];
						int next = minNum[i];
						
						if(now==6){

							break;
							
						}
						minNum[i]-=6-(now);
						minNum[i-1]=6;
					}
					
					
				}
				
				if(minNum[0]==6)
					bw.append('6');
				else
					bw.append(String.valueOf(matchesToMinNum[minNum[0]]));
				
				for(int i=1; i<minNumLength;i++) {
					bw.append(String.valueOf(matchesToMinNum[minNum[i]]));
				}
				bw.append(" ");
				
				
				
			}else {
				for(int i=0;i<minNumLength;i++) {
					bw.append('8');	
				}
				bw.append(" ");
			}
			
			/**
			 * 최대값 구하기
			 * 성냥개비 2개로 만들 수 있는 1로 가득채워 자릿수를 늘리고,
			 * N%2==1이라면 맨 앞 숫자를 7로 변경
			 * */
			int maxNumLength = N/2;
			if(N%2==1) {
				bw.append('7');
				maxNumLength--;
			}
			for(int i=0;i<maxNumLength;i++) {
				bw.append('1');
			}
			
			
			
			
			bw.append("\n");
		}

		bw.flush();
		bw.close();

	}

}
