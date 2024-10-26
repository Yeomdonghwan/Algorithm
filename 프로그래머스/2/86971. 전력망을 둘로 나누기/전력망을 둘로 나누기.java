/**
노드가 몇개인지?
어디서 끊어야 몇개 몇개로 나뉘는지..?

임의로 한 전선을 끊은 다음 dfs돌리기?..

*/
class Solution {
    boolean[] visited;
    int count=0;
    public int solution(int n, int[][] wires) {
        visited = new boolean[n+1];
        
        int answer = 100;
        for(int i=0;i<wires.length;i++){
            //i번째 전선을 끊음
            dfs(1,wires,i);
            // System.out.println(count);
            
            answer = Math.min(Math.abs((n-count)-count), answer);
            
            count=0;
            
            for(int j=1;j<n+1;j++){
                visited[j]=false;
            }
        }
        
        
        return answer;
    }
    public void dfs(int now, int[][] wires, int cut){
        visited[now]=true;
        count++;
        for(int i=0;i<wires.length;i++){
            if(i==cut)
                continue;
            if(wires[i][0]==now && visited[wires[i][1]]==false){
                // System.out.println(wires[i][1]);
                dfs(wires[i][1], wires, cut);
            }
            else if(wires[i][1] == now && visited[wires[i][0]] ==false){
                dfs(wires[i][0],wires,cut);
            }
        }
    }
}