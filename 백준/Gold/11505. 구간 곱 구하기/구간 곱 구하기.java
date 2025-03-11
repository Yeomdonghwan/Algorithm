import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static final int MOD = 1_000_000_007;
    static long[] tree;
    static long[] input;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N,M,K;
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int h = (int) Math.ceil(Math.log(N)/Math.log(2));
        tree = new long[4*N];
        input = new long[N+1];

        for(int i=1;i<=N;i++){
            input[i]=Integer.parseInt(br.readLine());
        }
        init(1,N,1);

        for(int i=0;i<M+K;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a==1){
                //b번째 수를 c로 업데이트
                update(1,N,1,b,c);
            }else if(a==2){
                //b부터 c까지의 구간곱 출력
                long num = query(1,N,1,b,c);
                bw.append(String.valueOf(num)+"\n");
            }
        }
        bw.flush();
        bw.close();
    }
    static long init(int start, int end, int node){
        if(start==end){
            return tree[node] = input[start];
        }

        int mid = (start+end) / 2;

        return tree[node] = (init(start,mid, node*2) * init(mid+1, end, node*2+1))%MOD;
    }

    static long update(int start, int end, int node, int targetIdx, long value){
        if(targetIdx<start || targetIdx>end){
            return tree[node];
        }

        if(start==end){
            //리프노드인 경우 업데이트
            return tree[node] = value;
        }

        int mid = (start+end)/2;

        return tree[node] = (update(start,mid,node*2, targetIdx, value) * update(mid+1,end, node*2+1, targetIdx,value)) % MOD;
    }

    static long query(int start, int end, int node, int left, int right){
        if(left>end || right<start){
            return 1;
        }

        if(left <= start && end <=right){
            return tree[node];//범위안에 쏙 들어가있는경우
        }

        int mid = (start+end)/2;
        return (query(start,mid,node*2,left,right)*query(mid+1,end,node*2+1,left,right))% MOD;
    }
}
