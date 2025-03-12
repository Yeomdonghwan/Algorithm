import java.util.*;
import java.io.*;

public class Main {
    static int[] odd_tree, even_tree;
    static int[] inputs;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        odd_tree = new int[4*N];
        even_tree= new int[4*N];
        inputs = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            inputs[i]=Integer.parseInt(st.nextToken());
        }

        init(odd_tree,1,N,1,false);
        init(even_tree,1,N,1,true);

//        System.out.println(Arrays.toString(odd_tree));
//        System.out.println(Arrays.toString(even_tree));
        int M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a= Integer.parseInt(st.nextToken());
            int b= Integer.parseInt(st.nextToken());
            int c= Integer.parseInt(st.nextToken());

            if(a==1){
                //b번째 수 c로 업데이트
                update(odd_tree,1,N,1,b,c,false);
                update(even_tree,1,N,1,b,c,true);
//                System.out.println(Arrays.toString(odd_tree));
//                System.out.println(Arrays.toString(even_tree));
            }else if(a==2){
                //b-c구간 짝수 출력
                int n = query(even_tree,1,N,1,b,c,true);
                bw.append(n+"\n");
            }else if(a==3){
                //b-c구간 홀수 출력
                int n = query(odd_tree,1,N,1,b,c,false);
//                System.out.println("ghf"+n);
                bw.append(n+"\n");
            }
        }
        bw.flush();
        bw.close();
    }

    static int init(int[] tree, int start, int end, int node, boolean isEvenTree){
        if(start==end){
            //리프노드
            tree[node]=inputs[start];
            if(isEvenTree){
                //짝수트리임
                return inputs[start]%2==0?1:0;
            }else{
                return inputs[start]%2==0?0:1;

            }
        }
        int mid = (start+end)/2;
        return tree[node]=init(tree,start,mid,node*2,isEvenTree)+init(tree,mid+1,end,node*2+1,isEvenTree);
    }

    static int update(int[] tree, int start, int end, int node, int target, int value, boolean isEvenTree){

        if(start>target || target>end){
            //범위 벗어남
            if(start==end){
                if(isEvenTree){
                    return tree[node]%2==0?1:0;
                }else{
                    return tree[node]%2==0?0:1;
                }
            }
            return tree[node];
        }


        if(start==end){
            //리프임
            //리프노드
            tree[node]=value;
            if(isEvenTree){
                //짝수트리임
                return value%2==0?1:0;
            }else{
                return value%2==0?0:1;

            }
        }

        int mid = (start+end)/2;
        return tree[node]=update(tree,start,mid,node*2,target,value,isEvenTree)
                + update(tree,mid+1,end,node*2+1,target,value,isEvenTree);
    }

    static int query(int[] tree, int start, int end, int node, int left, int right, boolean isEvenTree){

        if(end<left || right<start){
            //범위 벗어남
            return 0;
        }

        if(start==end){
                //리프인 경우
            if(isEvenTree){
                return tree[node]%2==0?1:0;
            }else{
                return tree[node]%2==0?0:1;
            }

        }

        //범위안에 쏙들어옴
        if(left<=start && end<=right){
            return tree[node];
        }

        int mid = (start+end)/2;
        return query(tree,start,mid,node*2,left,right,isEvenTree)
                +query(tree,mid+1,end,node*2+1,left,right,isEvenTree);
    }
}
