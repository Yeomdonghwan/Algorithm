import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[][] visited;
    static int n;
    static int[][] map;
    static int min=Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map=new int[n][n];
        visited=new boolean[n][n];
        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        backtracking(0,0);
        System.out.println(min);
    }
    public static void backtracking(int cost, int step){
        if(step==3){
            min=Math.min(min,cost);
            return;
        }
        int[] dx={0,0,0,-1,1};
        int[] dy={0,1,-1,0,0};
        for(int i=1;i<n-1;i++){
            for(int j=1;j<n-1;j++){
                //상하좌우 방문전인지 check
                boolean isAvailable=true;
                for(int k=0;k<5;k++){
                    if(visited[i+dx[k]][j+dy[k]]) {
                        isAvailable=false;
                        continue;
                    }
                }
                if(!isAvailable)
                    continue;

                //방문표시
                int sum=0;
                for(int k=0;k<5;k++){
                    visited[i+dx[k]][j+dy[k]]=true;
                    sum+=map[i+dx[k]][j+dy[k]];
                }
                backtracking(cost+sum, step+1);
                for(int k=0;k<5;k++){
                    visited[i+dx[k]][j+dy[k]]=false;
                }
            }
        }
    }
}
