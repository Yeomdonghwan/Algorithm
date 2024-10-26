
/**
위, 왼쪽에서 올 수 있다는것 확인.
*/
class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] maps = new int[n+1][m+1];
        for(int i=0;i<puddles.length;i++){
            maps[puddles[i][1]][puddles[i][0]]=-1;
        }
        maps[1][1]=1;
        
        // for(int i=0;i<n;i++){
        //     if(maps[i][0]==-1){
        //         break;
        //     }
        //     maps[i][0]=1;
        // }
        // for(int i=0;i<m;i++){
        //     if(maps[0][i]==-1){
        //         break;
        //     }
        //     maps[0][i]=1;
        // }
        
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(maps[i][j]==-1)
                    continue;
                
                if(maps[i-1][j]>0)
                    maps[i][j]+=maps[i-1][j];
                
                if(maps[i][j-1]>0)
                    maps[i][j]+=maps[i][j-1];
                
                maps[i][j]%=1000000007;
                    }
                
            }
        
//         for(int i=0;i<n+1;i++){
//             for(int j=0;j<m+1;j++){
//                 System.out.print(maps[i][j]+" ");
                
//             }
//             System.out.println();
//         }
        int answer = maps[n][m];
        return answer;
    }
}