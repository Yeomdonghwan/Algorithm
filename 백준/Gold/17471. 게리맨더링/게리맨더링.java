
import java.io.*;
import java.util.*;
import java.util.*;

public class Main {

	static private Stack<Integer> stack = new Stack<>();
	static private Set<boolean[]> combinations = new HashSet<>();
	static private List<Integer>[] edges;
	static private int n;
	static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		int[] population = new int[n+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int sum_population=0;
		
		edges = new ArrayList[n+1];
		for(int i=1;i<=n;i++) {
			population[i] = Integer.parseInt(st.nextToken());
			sum_population+=population[i];
			edges[i]=new ArrayList<>();
		}
		
		for(int i=1;i<=n;i++) {
			st = new StringTokenizer(br.readLine());
			int edgeCount= Integer.parseInt(st.nextToken());
			for(int j=0;j<edgeCount;j++) {
				edges[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		int ans=Integer.MAX_VALUE;
		for(int i=1;i<=n/2;i++) {
			//n개의 지역 중에서 i개 지역을 선택함
			combination(i,1);
			
			for(boolean[] combi: combinations) {
				//이 i개 지역이 연결돼있는지 확인
				
				int first_true=-1;
				int first_false=-1;
				for(int j=1;j<combi.length;j++) {
					if(first_true==-1&&combi[j]==true) {
						first_true=j;
					}else if(first_false==-1&&combi[j]==false) {
						first_false=j;
					}
					
					if(first_true!=-1 && first_false!=-1)
						break;
				}
				
				//true팀 연결되어있는지 확인
				visited= new boolean[n+1];
				dfs(combi, first_true); //조합의 한 노드부터 출발해서 dfs
				boolean connect = true;
				for(int j=1;j<n+1;j++) {
					if(combi[j]==true && visited[j]==false) {
						connect = false;
						break;
					}
				}
				
				if(!connect) continue; //연결이 안됨
				//나머지 지역 연결돼있는지 확인
				visited=new boolean[n+1];
				dfs(combi,first_false);
				connect = true;
				for(int j=1;j<n+1;j++) {
					if(combi[j]==false && visited[j]==false) {
						connect = false;
						break;
					}
				}
	
				if(!connect) continue; //연결이 안됨
	
				//인구수 계산
				int sum_A=0;
				int sum_B=0;
				for(int j=1;j<=n;j++) {
					if(combi[j]==true) {
						sum_A+=population[j];
					}else {
						sum_B+=population[j];
					}
				}
				//최대값 계산
				ans=Math.min(Math.abs(sum_A-sum_B), ans);
			}
		}
		
		if(ans==Integer.MAX_VALUE)
			ans=-1;
		System.out.println(ans);
	}
	
	static void combination(int maxSize, int step) {
		if(maxSize==stack.size()) {
			boolean[] combi = new boolean[n+1];
			for(int i: stack) {
				combi[i]=true;
			}
			
			combinations.add(combi);
			
			return;
		}
		
		if(step>n)
			return;
		
		stack.add(step);
		combination(maxSize, step+1);
		stack.pop();
		
		combination(maxSize,step+1);
	}
	
	static void dfs(boolean[] nodes, int start) {
		visited[start]=true;
		
		for(int next: edges[start]) {
			if(visited[next]==false && nodes[next]==nodes[start]) {
				//방문한 적 없고 같은팀인 경우
				dfs(nodes,next);
			}
		}
	}
	
}
