
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		//2^k >= n 만족하는 k?
		double treeHeight = Math.ceil(Math.log(n)/Math.log(2));
//		System.out.println(treeHeight);
        // 트리의 노드 수 계산
        long treeNodeCount = Math.round(Math.pow(2, treeHeight+1));
//        System.out.println(treeNodeCount);

        int start_idx = (int) Math.round(Math.pow(2,treeHeight));
//        System.out.println(start_idx);
        
        long[] tree = new long[(int)treeNodeCount];
        for(int i=0;i<tree.length;i++) {
        	tree[i]=0;
        }
        for(int i=0;i<n;i++) {
        	tree[start_idx+i]=Long.parseLong(br.readLine());
        }
        
        tree_init(tree);

        for(int i=0;i<m+k;i++) {
        	st = new StringTokenizer(br.readLine());
        	int a=Integer.parseInt(st.nextToken());
        	int b=Integer.parseInt(st.nextToken());
        	long c=Long.parseLong(st.nextToken());
        	if(a==1) {
        		//b번째 수를 c로 변경
        		int b_index = start_idx+b-1;
        		tree_update(tree,b_index,c);
        	}else if(a==2) {
        		//b~c번째 수 합 출력
        		long sum = tree_sum(tree,start_idx+b-1,(int) (start_idx+c-1));
        		bw.append(String.valueOf(sum)+"\n");
        	}
        }

//        System.out.println(Arrays.toString(tree));
		bw.flush();
		bw.close();
	}
	
	static void tree_init(long[] tree) {
		for(int i=tree.length-1;i>1;i--) {
			//역순으로
			tree[i/2]+=tree[i];
		}
	}
	
	static void tree_update(long[] tree, int idx, long value) {
		tree[idx]=value;
		while(idx>1) {
		
			if(idx%2==1) {
				tree[idx/2]=tree[idx]+tree[idx-1];
			}else {
				tree[idx/2]=tree[idx]+tree[idx+1];
			}
			idx=idx/2;
		
		}	
	}
	
	static long tree_sum(long[] tree, int start, int end) {
		long sum=0;
		int left=start;
		int right=end;
		while(left<=right) {

			if(left%2==1) {
				sum+=tree[left];
//				System.out.println("left:"+left);
			}
			if(right%2==0) {
				sum+=tree[right];

//				System.out.println("right"+right);
			}
			left=(left+1)/2;
			right=(right-1)/2;
			
		}	
		return sum;
	}
	
}
