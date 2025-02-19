import java.util.*;
import java.io.*;
public class Main {

	static int N,M;
	static boolean[] visited;
	static int arr[];
	static BufferedWriter bw;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		arr = new int[N+1];
		visited = new boolean[N+1];
		dfs(1);
		bw.flush();
		bw.close();
	}
	
	static void dfs(int step) throws IOException {
		if(step==M+1) {
			for(int i=1;i<M+1;i++) {
				bw.append(arr[i]+" ");
			}
			bw.append("\n");
			return;
		}

		for(int i=1;i<=N;i++) {
			if(visited[i])	continue;
			
			arr[step]=i;
			visited[i]=true;
			dfs(step+1);
			visited[i]=false;
		}
	}

}
