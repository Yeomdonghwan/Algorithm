import java.util.*;
import java.io.*;

public class Main {

	static boolean[] visited;
	static List<Integer>[] childEdges;
	static int[] parentCount;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		visited = new boolean[N+1];
		childEdges = new ArrayList[N+1]; 
		parentCount = new int[N+1];
		
		for(int i=1;i<=N;i++) {
			childEdges[i]=new ArrayList<>();
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			childEdges[parent].add(child);
			parentCount[child]++;
		}
		
		for(int i=1;i<=N;i++) {
			if(parentCount[i]==0 && !visited[i]) {
				dfs(i);
			}
		}
		
		bw.flush();
		bw.close();
	}
	
	static void dfs(int node) throws IOException {
		visited[node]=true;
		bw.append(node+" ");
		
		for(int next: childEdges[node]) {
			
			parentCount[next]--;
			if(parentCount[next]==0 && !visited[next]) {
				dfs(next);
			}
		}
		
	}

}
