import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String[] names = new String[n];
        int[] strongs = new int[n];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int strong = Integer.parseInt(st.nextToken());

            names[i]=name;
            strongs[i]=strong;

        }

        for(int i=0;i<m;i++){
            int strong = Integer.parseInt(br.readLine());

            int right = n-1;
            int left = 0;
            int middle = right/2;
            while(right>left){

                if(strongs[middle] >= strong){
                    //타겟이 중간보다 작은경우
                    right = middle;
                    middle = (right+left)/2;

                }else{
                    //타겟이 중간값보다 큰경우
                    left = middle+1;
                    middle = (right+left)/2;

                }
            }

            bw.write(names[middle]+"\n");
        }
        bw.flush();
    }
}
