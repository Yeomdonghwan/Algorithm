
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


class Solution
{
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
       
        int T;

        T = Integer.parseInt(br.readLine());
        
        
        for(int test_case = 1; test_case <= T; test_case++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int check = (1<<n)-1;
            m = m & check;
            if(m==check){
                bw.append("#"+test_case+" "+"ON\n");
            }else{
                bw.append("#"+test_case+" "+"OFF\n");

            }

        }
        bw.flush();
        bw.close();
    }
}