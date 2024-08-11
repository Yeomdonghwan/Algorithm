import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] light_x = new int[M];
        int max_space = 0;
        int side_max_space =0;
        for(int i=0;i<M;i++){
            light_x[i] = Integer.parseInt(st.nextToken());

            if(i!=0){
                max_space = Integer.max(max_space,light_x[i]-light_x[i-1]);
            }
        }
        int ans = (int) Math.ceil(max_space / 2.0);
        
        side_max_space = Math.max(light_x[0], N-light_x[M-1]);
        ans = Math.max(ans, side_max_space);

        System.out.println(ans);

    }
}
