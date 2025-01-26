import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int[][] map;
    //북,동,남,서
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};

    static public void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m =Integer.parseInt(st.nextToken());
        map = new int[n][m];

        st= new StringTokenizer(br.readLine());
        int start_r = Integer.parseInt(st.nextToken());
        int start_c = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());


        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        cleaning(start_r,start_c,direction);

        int count=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(map[i][j]==-1) count++;
            }
        }

        System.out.println(count);
    }
    static void cleaning(int r,int c, int direction){
        map[r][c]=-1; //방문표시

        int cleanable =0;
        for(int i=0;i<4;i++){
            int next_r = r+dr[i];
            int next_c = c+dc[i];
            if(map[next_r][next_c]==0)
                cleanable++;
        }

        if(cleanable==0){
            //후진
            int next_r=0, next_c=0;
            next_r = r+dr[(direction+2)%4];
            next_c = c+dc[(direction+2)%4];


            if(map[next_r][next_c]==1){
                //벽인 경우
                return;
            }else{
                cleaning(next_r,next_c,direction); //원래 방향 그대로 후진
                return;
            }


        }else{
            //청소할곳이 있는경우

            int next_direction=direction;
            for(int i=1;i<=4;i++){
                //90도 회전
                if(next_direction==0)
                    next_direction=3;
                else{
                    next_direction=next_direction-1;
                }
                int next_r = r+dr[next_direction];
                int next_c = c+dc[next_direction];

                if(map[next_r][next_c]==0){
                    //청소안된곳인 경우
                    cleaning(next_r,next_c,next_direction);
                    break;
                }
            }
        }
    }
}