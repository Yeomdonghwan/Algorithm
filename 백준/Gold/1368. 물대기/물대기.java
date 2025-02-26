import java.util.*;
import java.io.*;
public class Main {

	static private class Edge{
		int start;
		int end;
		int cost;
		Edge(int start, int end, int cost){
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
	}
	
	static boolean[] visited;
	static int[] parent;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] edgesArr = new int[N][N];
		PriorityQueue<Edge> edgesQueue = new PriorityQueue<>((o1,o2)->o1.cost-o2.cost);
		
		visited=new boolean[N];
		
		for(int i=0;i<N;i++) {
			int cost = Integer.parseInt(br.readLine());
			edgesQueue.add(new Edge(i,i,cost));
		}
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				//edges[i][j];
				int cost = Integer.parseInt(st.nextToken());
				edgesArr[i][j]=cost;	
			}
		}
		
		int visitedCount=0;
		int totalCost = 0;
		while(true) {
			//edges에서 방문한 적 없는 가장 싼 간선또는 우물 선택
			Edge edge = edgesQueue.poll();
			
			while(visited[edge.end]) {
				edge = edgesQueue.poll();
			}
			
			int node = edge.end;
			
			visited[node]=true;
			visitedCount++;
			totalCost+=edge.cost;
			
			if(visitedCount==N) {
				//모든 노드를 방문한 경우 종료
				break;
			}
			
			//해당 위치에서 갈 수 있는것들 엣지스에 추가
			for(int i=0;i<N;i++) {
				if(!visited[i]) {
					edgesQueue.add(new Edge(node, i, edgesArr[node][i]));
				}
			}
			
			
			//모든 노드 방문할때까지 반복
		}
		
		System.out.println(totalCost);
	}

}
