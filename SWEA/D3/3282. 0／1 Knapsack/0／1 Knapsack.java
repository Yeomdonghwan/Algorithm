import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int test_case=1; test_case<=T; test_case++){
            st = new StringTokenizer(br.readLine());
            int n=Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int[][] dp = new int[n+1][k+1];
            for(int i=1;i<=n;i++){
                st = new StringTokenizer(br.readLine());
                int v=Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                for(int j=1;j<=k;j++){
                    if(v>j){ //부피커서 못넣음
                        dp[i][j]=dp[i-1][j];
                    }else{
                        dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-v]+c);
                    }

                }
            }
            System.out.println("#"+test_case+" "+dp[n][k]);
        }
    }
}
