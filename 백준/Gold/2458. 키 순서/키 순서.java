import java.util.*;
import java.io.*;

public class Main {

    private static boolean[] visited;
    private static int visitedCount=0;
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
             StringTokenizer st = new StringTokenizer(br.readLine());
             int n = Integer.parseInt(st.nextToken());
             int m = Integer.parseInt(st.nextToken());
             int ans=0;
             
             //노드가 n개
             List<Integer>[] parentList = new ArrayList[n+1];
             List<Integer>[] childList = new ArrayList[n+1];
             //각각 [i]번 노드가 가지는 부모 리스트, 자식 리스트임
             
             for(int i=1;i<=n;i++) {
                 parentList[i]= new ArrayList<>();
                 childList[i] = new ArrayList<>();
             }
             
             for(int i=0;i<m;i++) {
                 st = new StringTokenizer(br.readLine());
                 int child = Integer.parseInt(st.nextToken());
                 int parent = Integer.parseInt(st.nextToken());
                 
                 parentList[child].add(parent);
                 childList[parent].add(child);
             }
             
             
             //1부터 n번 노드까지 반복
             for(int i=1;i<=n;i++) {
                 
                 visited = new boolean[n+1];
                 visitedCount=0;
                 //i번 노드부터 반복하면서 부모들 거슬러감
                 dfs(i, parentList);
                 
                 //i번 노드부터 자식들 거슬러감
                 dfs(i, childList);
                 
                 if(visitedCount==n+1) {
                     //모두 방문한 경우
                     ans++;
                 }
                 
                 
             }
             
             
             bw.append(ans+"\n");
             
        
        bw.flush();
        bw.close();
          
    }
    static void dfs(int node, List<Integer>[] list) {
        visited[node]=true;
        visitedCount++;
        for(Integer next: list[node]) {
             if(!visited[next]) {
                 dfs(next,list);
             }
         }
    }

}