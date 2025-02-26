import java.util.*;
import java.io.*;
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		List<int[]> meetings = new ArrayList<>();
		List<int[]> answer = new ArrayList<>();
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			meetings.add(new int[] {start,end});
		}
		
		
		meetings.sort((o1,o2)->o1[1]==o2[1]?o1[0]-o2[0]:o1[1]-o2[1]); //끝나는 시간 순으로 정렬
		
		answer.add(meetings.get(0));
		
		for(int i=1;i<N;i++) {
			if(answer.get(answer.size()-1)[1]<=meetings.get(i)[0]) {
				answer.add(meetings.get(i));
			}
		}
		
		System.out.println(answer.size());
		
	}

}
