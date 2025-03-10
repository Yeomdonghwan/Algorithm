/**
 * N개의 도시, 간선은 최소 0개 ~ 최대 N-1개
 * 주어진 간선 중에서 M개의 간선으로 스패닝트리를 만들어야함
 *
 * 간선의 개수가 N-1개 미만인 경우 => 스패닝트리가 만들어질 수 없으므로 -1
 * 간선의 개수가 N-1개 이상인 경우 => N-1개로 최소스패닝트리를 만들고, M-(N-1)개를 더 연결해서 총 M개의 간선 연결
 * */
import java.util.*;
import java.io.*;

public class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st= new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Queue<int[]> edges = new LinkedList<>();
        int[] ans = new int[N];
        parent = new int[N];
        boolean[][] map = new boolean[N][N];

        for(int i=0;i<N;i++){
            parent[i]=i; //union-find에서 사용할 parent배열 초기화
            String input = br.readLine();
            for(int j=0;j<N;j++){
                map[i][j] = (input.charAt(j) == 'Y');
                if(map[j][i]!=map[i][j]){ //도로가 양방향이므로 처음입력받는 도로만 간선에 추가함
                    edges.add(new int[]{i,j});
                    map[j][i]=map[i][j];

                }

            }
        }

        if(edges.size()<N-1 || edges.size()<M ){//N-1개보다 간선개수가 적으면 스패닝트리를 만들 수 없고, 최소 M개의 간선 필요
            System.out.println("-1");
            return;
        }

        Queue<int[]> remainEdges = new LinkedList<>(); //최소스패닝트리에 선택되지 않은 간선들
        //N-1개의 간선을 이용하여 최소 스패닝트리 만들기
        while(!edges.isEmpty()){
            int[] edge = edges.poll();
            int u = edge[0];
            int v = edge[1];

            if(find(u)==find(v)){
                //부모가 같은경우 => 싸이클
                remainEdges.add(edge);
                continue;
            }

            union(u,v);
            ans[u]++;
            ans[v]++;
        }

        //스패닝트리가 만들어졌는지 확인. 만들어지지 않는다면 -1 출력 및 종료
        boolean isSpaningTree = true;
        int root = find(0);
        for(int i=1;i<N;i++){
            if(find(i)!=root){
                isSpaningTree=false;
                break;
            }
        }

        if(!isSpaningTree){
            System.out.println(-1);
            return;
        }

        //남은 간선들 중에서 최대 M-(N-1)개 뽑아서 추가하기
        for(int i=0;i<M-(N-1) && !remainEdges.isEmpty();i++){
            int[] edge = remainEdges.poll();
            int u = edge[0];
            int v = edge[1];
            ans[u]++;
            ans[v]++;
        }

        for(int i=0;i<N;i++){
            bw.append(ans[i]+" ");
        }
        bw.flush();
        bw.close();

    }
    static int find(int node){
        if(parent[node]==node){
            return node; //자신이 루트이면 자기자신 반환
        }else{
            return parent[node]=find(parent[node]); //경로압축
        }
    }

    static void union(int node1, int node2){
        int root1 = find(node1);
        int root2 = find(node2);
        if(root1<root2){
            parent[root1] = root2;
        }else{
            parent[root2] = root1;
        }

    }
}
