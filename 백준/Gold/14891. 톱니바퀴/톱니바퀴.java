import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static boolean N = true;
    static boolean S = false;
    static boolean[][] gear;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        gear = new boolean[5][8];
        for(int i=1;i<5;i++){
            String gear_str = br.readLine();
            for(int j=0;j<8;j++){
                gear[i][j] = (gear_str.charAt(j)=='0')?N:S;
            }
        }


        int n = Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int gear_idx = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            Queue<int[]> rotate_waiting_queue = new LinkedList<>();
            rotate_waiting_queue.add(new int[]{gear_idx,direction});
            //현재기어 기준 왼쪽으로 회전할게 있는지 탐색
            int idx=gear_idx;
            int dir=direction;
            while(idx>1){
                if(gear[idx][6]!=gear[idx-1][2]){

                    idx--;
                    dir = (dir==1)?-1:1;
                    rotate_waiting_queue.add(new int[]{idx, dir});
                }else{
                    break;
                }

            }

            //현재기어 기준 오른쪽으로 회전할게 있는지 탐색
            idx=gear_idx;
            dir=direction;
            while(idx<4){
                if(gear[idx][2]!=gear[idx+1][6]){

                    idx++;
                    dir = (dir==1)?-1:1;
                    rotate_waiting_queue.add(new int[]{idx,dir});
                    
                }else{
                    break;
                }

            }

            while(!rotate_waiting_queue.isEmpty()){
                int[] now_rotate = rotate_waiting_queue.poll();
                rotate(now_rotate[0],now_rotate[1]);
            }

        }

        int rs =0;
        if(gear[1][0]==S)   rs+=1;
        if(gear[2][0]==S)   rs+=2;
        if(gear[3][0]==S)   rs+=4;
        if(gear[4][0]==S)   rs+=8;

        System.out.println(rs);

    }

    static void rotate(int gear_idx,int direction){
        //회전하기 전 양옆 기어 체크
        //오른쪽

        if(direction==1){
            //시계방향. 한칸씩 오른쪽으로.
            boolean temp = gear[gear_idx][7];
            for(int i=7;i>=1;i--){
                gear[gear_idx][i] = gear[gear_idx][i-1];
            }
            gear[gear_idx][0]=temp;
        }
        else{
            //반시계방향. 한칸씩 왼쪽으로.
            boolean temp = gear[gear_idx][0];
            for(int i=0;i<=6;i++){
                gear[gear_idx][i] = gear[gear_idx][i+1];
            }
            gear[gear_idx][7]=temp;

        }

    }
}
