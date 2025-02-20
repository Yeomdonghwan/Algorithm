import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {
    static int n,m;
    static int[] arr;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        n=sc.nextInt();
        m =sc.nextInt();
        arr = new int[m];
        dfs(0,0);
        bw.flush();
        bw.close();
    }
    static void dfs(int step,int k) throws IOException {
        if(step==m){
            for(int i=0;i<arr.length;i++){
                bw.append(arr[i]+" ");
            }
            bw.append("\n");
            return;
        }

        for(int i=k+1;i<=n;i++){
            arr[step]=i;
            dfs(step+1,i);
        }
    }
}
