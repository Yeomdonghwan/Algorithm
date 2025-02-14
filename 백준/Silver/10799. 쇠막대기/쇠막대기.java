import java.io.*;
import java.util.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		int idx=0;
		int layerCount=0;
		int answer =0;
		while(idx<line.length()) {
			char c = line.charAt(idx);
			
			if(c=='(') {
				if(idx+1<line.length() && line.charAt(idx+1)==')') {
					//절단인 경우
					answer+=layerCount;
					idx++; //다음 문자까지 봤기 때문에 idx 1 더 증가시킴
				}else {
					//새 조각이 생기는 경우
					layerCount++;
				}
			}else {
				//)인 경우
				//조각이 끝나는 경우
				answer++;
				layerCount--;
			}
			idx++;
		}
		
		System.out.println(answer);
	}
	
}
