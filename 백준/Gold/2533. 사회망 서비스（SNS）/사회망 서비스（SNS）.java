import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

//    static List<Integer>[] edges;
//    static int[] friends;
//    static boolean[] isEarlyAdapter;
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//        int n = Integer.parseInt(br.readLine());
//        friends = new int[n+1]; //얼리어답터가 아닌 친구 수
//        isEarlyAdapter = new boolean[n+1]; //얼리어답터 여부
//        edges = new List[n+1];
//        for(int i=1;i<=n;i++){
//            edges[i]=new ArrayList<>();
//            friends[i]=0;
//        }
//        for(int i=0;i<n-1;i++){
//            st = new StringTokenizer(br.readLine());
//            int u = Integer.parseInt(st.nextToken());
//            int v = Integer.parseInt(st.nextToken());
//
//            edges[u].add(v);
//            edges[v].add(u);
//            friends[u]++;
//            friends[v]++;
//        }
//        int result=0;
//        for(result=0;result<n;result++){
//            //친구가 가장 많은 사람으로 얼리어답터 선정
//            int earlyAdapter = getMaxIdx(friends);
//            if(earlyAdapter==-1){
//                //모든사람이 얼리어답터
//                break;
//            }
//            becomeEarlyAdapter(earlyAdapter);
//
//        }
//
//        System.out.println(result);
//    }
//
//    /**
//     * 얼리어답터가 되어 주변 친구에게 영향을 준다.(주변친구의 친구 수 1씩 감소)
//     * */
//    static void becomeEarlyAdapter(int earlyAdapter){
//        friends[earlyAdapter]=-1;
//        isEarlyAdapter[earlyAdapter]=true;
//
//
//        for(int j=0;j<edges[earlyAdapter].size();j++){
//            int friend = edges[earlyAdapter].get(j);
//            friends[friend]--;
//
//            //주변 친구가 얼리어답터가 되는 경우
//            if(friends[friend]<=0 && !isEarlyAdapter[friend]){
//                becomeEarlyAdapter(friend);
//            }
//
//        }
//    }
//
//
//    //얼리어답터가 아닌 친구를 가장 많이 보유한 사람의 인덱스 반환
//    //만약 모든 사람이 얼리어답터라면 -1 반환
//    private static int getMaxIdx(int[] friends) {
//
//        int max=-1;
//        int idx=-1;
//        for(int i=1;i< friends.length;i++){
//            if(friends[i]>0 && max<friends[i]){
//                max=friends[i];
//                idx=i;
//            }
//        }
//        return idx;
//    }
    static int[][] dp;
    static boolean[] visited;
    static List<Integer>[] edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        dp = new int[n+1][2]; //[i][0]=i가 얼리어답터가 아닐 때 선택된 얼리어답터 수 , [i][1]=i가 얼리어답터일 때 선택된 얼리어답터 수
        visited = new boolean[n+1];

        edges = new List[n+1];
        for(int i=1;i<=n;i++){
            edges[i]=new ArrayList<>();
        }
        for(int i=0;i<n-1;i++){
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            edges[u].add(v);
            edges[v].add(u);
        }
        int root = 1; //루트노드로 어떤 노드를 선택하든 상관 없음. 
        dfs(root);
        System.out.println(Math.min(dp[root][0],dp[root][1]));
    }
    static void dfs(int index){
        visited[index]=true;

        //index번 노드는 얼리어답터로 선택하지 않는 경우, 0으로 초기화.
        dp[index][0]=0;
        //index번 노드를 얼리어답터로 선택하는 경우, 1로 초기화.
        dp[index][1]=1;
        for(int i=0;i<edges[index].size();i++){
            int child = edges[index].get(i);
            if(!visited[child]){
                //방문하지 않은 자식노드가 있다면 끝까지 내려감
                dfs(child);

                //index번 노드를 얼리어답터로 선택하지 않는 경우, 모든 child는 얼리어답터여야함
                dp[index][0]+=dp[child][1];
                //index번 노드를 얼리어답터로 선택하는 경우, child가 얼리어답터인지 아닌지는 상관 없으므로 [child][0], [child][1] 중 작은 값을 더함
                dp[index][1]+=Math.min(dp[child][0],dp[child][1]);


            }
        }
    }
}