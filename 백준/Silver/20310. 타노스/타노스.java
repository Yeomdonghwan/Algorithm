import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s=sc.nextLine();

        StringBuilder sb = new StringBuilder();
        sb.append(s);

        int count_0=0;
        int count_1=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='0'){
                count_0++;
            }else{
                count_1++;
            }
        }
        count_0/=2;
        count_1/=2;

        int idx=0;
        while(count_1>0){
            if(sb.charAt(idx)=='1'){
                sb.deleteCharAt(idx);
                count_1--;
            }else{
                idx++;
            }
        }

        idx=sb.length()-1;
        while(count_0>0){
            if(sb.charAt(idx)=='0'){
                sb.deleteCharAt(idx);
                count_0--;
            }
            idx--;
        }
        bw.write(sb.toString());
        bw.flush();

    }
}
