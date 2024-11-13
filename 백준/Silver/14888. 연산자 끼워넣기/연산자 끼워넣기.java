import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] commands = new int[4];
    static int[] numbers;
    static int n;
    static int max=Integer.MIN_VALUE;
    static int min=Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        numbers = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        st=new StringTokenizer(br.readLine());
        for(int i=0;i<4;i++){
            commands[i]=Integer.parseInt(st.nextToken());
        }


        backtracking(numbers[0],1);

        System.out.println(max);
        System.out.println(min);
    }
    static void backtracking(int value, int step){
        if(step==n){
            max=Math.max(max,value);
            min=Math.min(min,value);
            return;
        }

        for(int i=0;i<4;i++){
            if(commands[i]==0){
                continue;
            }
            commands[i]--;
            if(i==0){
                backtracking(value+numbers[step],step+1);
            }else if(i==1){
                backtracking(value-numbers[step],step+1);
            }else if(i==2){
                backtracking(value*numbers[step],step+1);
            }else if(i==3){
                backtracking(value/numbers[step],step+1);
            }

            commands[i]++;
        }
    }
}
