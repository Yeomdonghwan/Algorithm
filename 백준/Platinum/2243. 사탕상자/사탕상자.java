import java.util.*;
import java.io.*;
public class Main {

	static final int SIZE = 1_000_001; //백만일개
	static int[] tree; //세그먼트트리
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		
		//세그먼트트리
		tree = new int[SIZE*4]; //트리 사이즈는 편하게 SIZE*4로 넉넉잡음
		
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			if(a==1){
				//사탕꺼내기
				int rank = Integer.parseInt(st.nextToken());
				
				//세그먼트트리에서 값을찾아 반환하고, 트리  업데이트 필요
				int candy = query(1,SIZE,1,rank); //1~끝까지 범위로, 1번노드부터 시작해서 리프노드로 내려가면서 rank에 해당하는 값 찾기
				bw.append(candy+"\n");
			}else if(a==2) {
				//사탕넣기
				int level = Integer.parseInt(st.nextToken());
				int amount = Integer.parseInt(st.nextToken());
				
				//트리 업데이트 필요
				update(1,SIZE,1,level,amount); //1~끝까지 범위로 1번노드(루트)부터 시작해 리프노드까지 내려가며 트리 업데이트.
			}
			
		}
		bw.flush();
		bw.close();
	}
	
	/**
	 * 트리에서 a번째 등수 사탕 반환, 트리 업데이트
	 * 루트부터 시작해서 재귀로 리프까지 이동
	 * */
	static int query(int start, int end, int node, int rank) {
		
		if(start == end) {//start==end가 되었다는것은 리프노드가 되었다는건가
			//업데이트하기
			update(1,SIZE,1,start,-1);
			//l, r중 하나 반환
			return start;
		}
		
		int mid = (start+end)/2; //
		if(tree[node * 2] >= rank) {
			//왼쪽자식이 rank보다 크거나같으면 왼쪽자식으로 내려감
			return query(start, mid, node*2, rank);
		}else {
			//왼쪽자식이 rank보다 작음 => 
			//오른쪽자식으로 이동하고
			//rank는 왼쪽자식에 달려있는만큼 제거함
			return query(mid+1, end, node*2+1, rank-tree[node*2]);
		}
		


	}
	
	/**
	 * target사탕에 amount 개수 추가함
	 * 루트부터 시작해서 재귀로 리프까지 이동하고, 
	 * 현재 노드 아래에 target이 존재하는 경우 현재노드값+amount
	 * 
	 * */
	static void update(int start, int end, int node, int target, long amount) {;
		if(target < start || end < target) {
			//이 노드 아래에 target이 존재하지 않는경우 => 업데이트 할 필요가 업슴
			return;
			
		}else {
			tree[node] += amount;
			if(start != end) {
				int mid = (start+end)/2;
				update(start,mid, node *2 , target, amount); //왼쪽자식
				update(mid+1,end,node*2+1, target,amount); //오른쪽자식
			}
		}
	}

}
