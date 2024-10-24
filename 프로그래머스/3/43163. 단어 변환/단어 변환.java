import java.util.*;
class Solution {
    
        boolean[] visited;
        int index;
    int[][] maps;
    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length+1];
        index=-1;
        for(int i=0;i<words.length;i++){
            if(words[i].equals(target)){
                index=i;
            }
        }
        
        if(index==-1){
            return 0;
        }
        
        maps = new int[words.length+1][words.length+1];
        for(int i=0;i<words.length;i++){
            for(int j=0;j<words.length;j++){
                if(i==j)
                    maps[i][j]=0;
                else if(isChangeable(words[i],words[j])){
                    maps[i][j]=1;
                }else{
                    maps[i][j]=0;
                }
            }
        }
        
        for(int i=0;i<words.length;i++){
            if(isChangeable(begin, words[i])){
            maps[words.length][i]=maps[i][words.length]=1;
          
            }
              }
        // for(int i=0;i<words.length+1;i++){
        //     for(int j=0;j<words.length+1;j++){
        //         System.out.print(maps[i][j]+" ");
        //     }
        //     System.out.println();
        // }
        
        int answer =bfs(words, words.length);
        
        return answer;
    }
    
    public boolean isChangeable(String word1, String word2){
        int length = word1.length();
        
        int difCount=0;
        for(int i=0;i<length;i++){
            if(word1.charAt(i)!=word2.charAt(i))
                difCount++;
            if(difCount>1){
                return false;
            }
            
        }
        return true;
    }
   
    
    
    public int bfs(String[] words, int node){
        
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> count = new LinkedList<>();
        queue.add(node);
        visited[node]=true;
        count.add(0);
        while(!queue.isEmpty()){
            int now = queue.poll();
            int now_count = count.poll();
            
            for(int i=0;i<words.length+1;i++){
                if(maps[i][now]>=1 && visited[i]==false){
                    queue.add(i);
                    count.add((now_count+1));
                    
                    visited[i]=true;
                    maps[i][now]=maps[now][i]=(now_count+1);
                    if(i==index){
                        return now_count+1;
                    }
                }
            }
        }
        return 0;
    }
}