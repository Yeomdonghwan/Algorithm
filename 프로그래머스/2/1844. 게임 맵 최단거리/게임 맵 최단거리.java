import java.util.*; 
class Solution {
            int[] dx = {0,0,-1,1};
        int[] dy = {1,-1,0,0};
    public int solution(int[][] maps) {
        

        boolean[][] visited = new boolean[maps.length][maps[0].length];
        
        bfs(maps,visited, 0,0);
        
        int answer = visited[maps.length-1][maps[0].length-1]==false?-1:maps[maps.length-1][maps[0].length-1];
        return answer;
    }
    
    public void bfs(int[][] maps, boolean[][] visited, int r, int c){
        
        Queue<Integer> q_x = new LinkedList<>();
        Queue<Integer> q_y = new LinkedList<>();
        
        q_x.add(r);
        q_y.add(c);
        while(!q_x.isEmpty()){
            r = q_x.poll();
            c = q_y.poll();
            
            visited[r][c]=true;
            for(int i=0;i<4;i++){
                int next_r = r+dx[i];
                int next_c = c+dy[i];
                if(next_r<0||next_c<0||next_r>=maps.length||next_c>=maps[0].length)
                    continue;
                if(visited[next_r][next_c] || maps[next_r][next_c]!=1)
                    continue;
                q_x.add(next_r);
                q_y.add(next_c);
                
            maps[next_r][next_c] = maps[r][c]+1;
            
            
        }
            
        }
    }
}