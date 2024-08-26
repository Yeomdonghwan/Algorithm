import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Set<String> keyword = new HashSet<>();
        for (int i = 0; i < n; i++) {
            keyword.add(br.readLine());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), ",");
            while (st.hasMoreTokens()) {

                keyword.remove(st.nextToken());
            }
            bw.write(keyword.size() + "\n");
        }
        bw.flush();
    }
}
