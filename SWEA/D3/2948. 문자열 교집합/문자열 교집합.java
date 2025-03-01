import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for(int test_case=1; test_case<=T; test_case++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int count=0;

            st = new StringTokenizer(br.readLine());
            Set<String> set = new HashSet<>();
            for(int i=0;i<n1;i++){
                set.add(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<n2;i++){
                if(set.contains(st.nextToken())){
                    count++;
                }
            }
            bw.append("#"+test_case+" "+count+"\n");
        }
        bw.flush();
        bw.close();
    }
}
