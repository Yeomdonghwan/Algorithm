import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(st.nextToken());
        int[] v = new int[N+1];
        int[] w = new int[N+1];
        int[][] dp = new int[N+1][K+1];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());

            w[i+1] = Integer.parseInt(st.nextToken());
            v[i+1] = Integer.parseInt(st.nextToken());

        }

        for(int i=0;i<=N;i++){
            for(int j=0;j<=K;j++){

                if(i==0){
                    dp[i][j]=0;
                }
                else if(j-w[i]>=0){
                    dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-w[i]]+v[i]);
                }else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        System.out.println(dp[N][K]);
    }
}
