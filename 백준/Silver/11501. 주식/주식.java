import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++){
            int n = Integer.parseInt(br.readLine());

            int[] price = new int[n];
            int[] max_after_days = new int[n];
            long profit =0;
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<n;i++){

                price[i]=Integer.parseInt(st.nextToken());
            }

            max_after_days[n-1]=0;
            int max_price = price[n-1];
            for(int i=n-1-1;i>=0;i--){
                max_price=Math.max(max_price,price[i+1]);
                max_after_days[i]=max_price;

            }

            for(int i=0;i<n;i++){
                if(max_after_days[i]>price[i]){
                    profit+=max_after_days[i]-price[i];
                }
            }

            bw.write(profit+"\n");
        }
        bw.flush();
    }
}
