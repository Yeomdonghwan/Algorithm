import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int sum=0;
        int[] arr=new int[9];
        for(int i=0;i<9;i++){
            arr[i]=sc.nextInt();
            sum+=arr[i];
        }
        boolean[] isNotHuman = new boolean[9];
        esc:
        for(int i=0;i<8;i++){
            sum-=arr[i];
            for(int j=i+1;j<9;j++){
                sum-=arr[j];
                if(sum==100){
                    isNotHuman[i]=true;
                    isNotHuman[j]=true;
                    break esc;
                }
                sum+=arr[j];
            }
            sum+=arr[i];
        }

        for(int i=0;i<9;i++){
            if(isNotHuman[i])   continue;
            bw.append(arr[i]+"\n");
        }
        bw.flush();
        bw.close();
    }
}
