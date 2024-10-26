/**
최소피로도가 있고 소모피로도가 있다..
소모피로도가 적고 최소피로도가 높은걸 우선으로 하ㅡ는게 좋겠구나
완전탐색 말고는 풀수없나?
*/
class Solution {
    boolean[] visited;
    int ans=0;
    public int solution(int k, int[][] dungeons) {
        ans= -1;
        visited = new boolean[dungeons.length];
        backtracking(dungeons, k, 0);
        
        return ans;
    }
    public void backtracking(int[][] dungeons, int pirodo, int step){
        ans=Math.max(ans,step);
        
        for(int i=0;i<dungeons.length;i++){
            if(visited[i]==false && pirodo >= dungeons[i][0]){
                visited[i]=true;
                backtracking(dungeons, pirodo-dungeons[i][1], step+1);
                visited[i]=false;
                
            }
        }
    }
}