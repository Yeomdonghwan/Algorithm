import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] combination;
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        combination = new int[n][n];
        visited=new boolean[n];
        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            for(int j=0;j<n;j++){
                combination[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        //백트래킹 on
        backtracking(0,0);

        System.out.println(min);
    }

    static void backtracking(int step, int count){
        if(count==n/2){
            //양팀 점수 계산

            int team1_score=0;
            int team2_score=0;
            for(int i=0;i<n-1;i++){
                for(int j=i+1;j<n;j++){
                    if(visited[i] && visited[j]){
                        team1_score+=(combination[i][j]+combination[j][i]);
                    }else if(!visited[i] && !visited[j]){
                        team2_score+=(combination[i][j]+combination[j][i]);
                    }
                }
            }
            int val =  Math.abs(team1_score-team2_score);
            min=Math.min(min,val);
            if(min==0){
                System.out.println(0);
                System.exit(0);
            }
            return;
        }

        for(int i=step;i<n;i++){
            if(!visited[i])   
            {
            visited[i]=true;


            backtracking(i+1,count+1);

            visited[i]=false;
            }
        }
    }
}
