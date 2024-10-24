import java.util.*;

class Solution {
    public boolean [] visited;
    public int solution(int n, int[][] computers) {
        int count=0;
         visited = new boolean[n];
        for(int i=0;i<n;i++){
        visited[i]=false;
        }
        
        for(int i=0;i<n;i++){
            if(visited[i]==false){
                dfs(computers, i);
                count++;
            }
        }
        int answer = count;
        return answer;
    }
    public void dfs(int[][] computers, int node){
        if(visited[node]==true){
            return;
        }
        
        visited[node]=true;
        for(int i=0;i<computers[node].length;i++){
            if(visited[i]==false && computers[node][i] == 1){
                dfs(computers,i);
            }
        }
    }
}