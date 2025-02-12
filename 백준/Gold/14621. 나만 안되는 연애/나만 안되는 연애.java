

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); //학교 수
		int m = Integer.parseInt(st.nextToken()); //도로개수
		parent = new int[n+1];
		for(int i=1;i<n+1;i++) {
			parent[i]=i; //부모노드 초기화
		}
		
		char[] gender = new char[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=n;i++) {
			gender[i]=st.nextToken().charAt(0);
		}
		
		List<Integer[]> edges = new ArrayList<>();
		
		for(int i=0;i<m;i++) {
			//간선 입력
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			//성별이 같은경우 간선 추가 하지 않음
			if(gender[u]==gender[v])
				continue;
			
			edges.add(new Integer[] {u,v,cost});
		}
		
		//cost로 정렬
		edges.sort((o1,o2)->{
			return o1[2]-o2[2];
		});
		
		int count=0;
		int totalCost=0;
		for(int i=0;i<edges.size();i++) {
			//가장 싼 간선부터 확인
			
			//사이클이 발생하는지 확인
			if(find(edges.get(i)[0])==find(edges.get(i)[1]))
				continue;
			
			//해당 간선 추가
			union(edges.get(i)[0],edges.get(i)[1]);
			count++;
			totalCost+=edges.get(i)[2];
			
		}
		
		if(count<n-1)
			System.out.println(-1);
		else {
			System.out.println(totalCost);
		}
		
	}
	
	static int find(int node) {
		if(node==parent[node])
			return node;
		else {
			return find(parent[node]);
		}
	}
	
	static void union(int node1, int node2) {
		int root1 = find(node1);
		int root2 = find(node2);
		
		if(root1>root2) {
			parent[root2]=root1;
		}else {
			parent[root1]=root2;
		}
	}

}
