import java.util.*;
import java.io.*;

class Solution {
    private static int[] parent;
    private static int[] weight;
    private static int[] rank;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case<=T; test_case++){
            bw.append("#"+test_case+" ");

            StringTokenizer st =new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            parent = new int[n+1];
            weight = new int[n+1];
            rank = new int[n+1];
            for(int i=0;i<n+1;i++){
                parent[i]=i;
                weight[i]=0;
                rank[i]=0;
            }
            for(int i=0;i<m;i++) {
                st = new StringTokenizer(br.readLine());
                char command = st.nextToken().charAt(0);
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if(command=='!') {
                    int cost = Integer.parseInt(st.nextToken());
                    union(a,b,cost);
                }else if(command=='?') {
                    int rootA = find(a);
                    int rootB = find(b);
                    if(rootA != rootB) {
                        bw.append("UNKNOWN ");
                    }else {
                        bw.append(String.valueOf(weight[a]-weight[b])+" ");
                    }
                }
            }
            bw.append("\n");
        }
        bw.flush();
        bw.close();


    }

    static int find(int n) {
        if(n==parent[n]) return n;
        int root = find(parent[n]);
        weight[n]+=weight[parent[n]]; //원래 내 무게 + 부모 .. 조상무게
        return root;
    }

    static void union(int a, int b, int w) {
        int rootA = find(a);
        int rootB = find(b);

        if(rootA==rootB)
            return;

        if(rank[rootA] < rank[rootB]) {
            //B가 더 큰 트리
            parent[rootA] = rootB;
            //A가 B 밑으로 들어감

            //루트a는 루트b로부터 몇 차이 나는가?
            // => 원래 루트B에 달려있던 b는 루트b로부터 w[b]만큼 떨어짐
            // a는 여기다 w만큼 더 떨어짐 = w[b]+w.
            // 루트a는 a와 - w[a]만큼 떨어져있음
            // = w[b]+w - w[a]
            weight[rootA] = weight[b] - weight[a] + w;
        }else{

            if(rank[rootA]==rank[rootB]){
                rank[rootA]++;
            }

            //B트리가 A트리 밑으로 들어감
            parent[rootB] = rootA;

            //루트B는 루트A로부터 몇 떨어져있는가?
            //a -> b 가중치 w
            //a는 루트a로부터 w[a]만큼 떨어져 있음.
            //a에서 -w 만큼 가면 b임
            //b(=w[a]-w) 에서 루트b까지는 -w[b]임
            //정리하면 w[a]-w[b]-w
            weight[rootB] = weight[a] - weight[b] - w;
        }
    }

}