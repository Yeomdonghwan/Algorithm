import java.util.*;
import java.io.*;
public class Main {

	static long[] input;
	static long[] tree, lazy;
	
	//Lazy
	private static void propagate(int start, int end, int node) {
		if(lazy[node]==0)
			return;
		
		tree[node]+= (end - start + 1) * lazy[node]; //범위에 있는 원소개수만큼 lazy[node]수 감소 시키는것같음
		if(start != end) {
			lazy[node*2] += lazy[node];
			lazy[node*2 +1] += lazy[node]; //자식으로 전파
		}
		lazy[node] = 0; //여기있던값은 트리에 넣었으니까 사용처리 하는것같음
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N,M,K;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		input= new long[N+1];
		int treeHeight = (int) Math.ceil(Math.log(N)/Math.log(2));
		tree = new long[N*4];
		lazy = new long[N*4];
		
		for(int i=1;i<=N;i++) {
			input[i] = Long.parseLong(br.readLine());
		}
		
		init(1,N,1);
		for(int i=0;i<M+K;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(a==1) {
				long d = Long.parseLong(st.nextToken());
				update(1,N,1,b,c,d);
//				System.out.println(Arrays.toString(tree));
//				System.out.println(Arrays.toString(lazy));
//				System.out.println();
			}else if(a==2) {
				long n = query(1,N,1,b,c);

//				System.out.println(n);
//				System.out.println(Arrays.toString(tree));
//				System.out.println(Arrays.toString(lazy));
				bw.append(String.valueOf(n)+"\n");
			}
		}
		bw.flush();
		bw.close();
	}
	
	static long init(int start, int end, int node) {
		if(start==end) {
			//리프
			return tree[node] = input[start];
		}
		
		int mid = (start+end)/2;
		return tree[node]=init(start,mid,node*2)+init(mid+1,end,node*2+1);
	}
	
//	static long update(int start, int end, int node, int left, int right, long value) {
//		propagate(node,start,end); //lazy값 업데이트??
//		
//		
//		//범위 벗어나는 경우
//		if(start> right || end < left) {
//			return tree[node];
//		}
//		if(start==end) {
//			return tree[node]=tree[node]+value;
//		}
//		
//		int mid = (start+end)/2;
//		
//		return tree[node] = update(start,mid,node*2, left, right, value)+update(mid+1,end,node*2+1,left,right,value);
//	}
	static void update(int start, int end, int node, int left, int right, long value) {
		propagate(start,end,node); //lazy값 업데이트??
		
		
		//범위 벗어나는 경우
		if(start> right || end < left) {
			return;
		}
		if(left<=start && right>=end) {
			//범위안에 쏙 들어오는 경우
			tree[node] += (end-start+1)*value;
			if(start!=end) {
				//리프가 아니면
				lazy[2*node] += value;
				lazy[2*node+1] += value;
			}
			return;
		}
		
		int mid = (start+end)/2;
		update(start,mid,2*node,left,right,value);
		update(mid+1,end,2*node+1,left,right,value);
		tree[node] = tree[2*node] + tree[2*node+1];
		}	
	static long query(int start, int end, int node, int left, int right) {

		propagate(start, end, node);
		
		//범위 벗어나는 경우
		if(start> right || end < left) {
			return 0;
		}
		//범위안에 쏙 들어감
		if(start>=left && end <= right) {
			return tree[node];
		}
		
		int mid = (start+end)/2;
		
		return query(start,mid,node*2, left, right)+query(mid+1,end,node*2+1,left,right);
	}

}
