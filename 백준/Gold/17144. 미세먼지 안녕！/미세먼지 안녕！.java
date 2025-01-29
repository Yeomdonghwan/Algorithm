import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R,C;
    static int [][] map;
    static int[] dr={0,0,-1,1};
    static int[] dc={1,-1,0,0};
    static int circulator_R,circulator_C;
    static final int CIRCULATOR = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        //T초

        map = new int[R][C];
        for(int i=0;i<R;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<C;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==-1)    circulator_R=i;

            }
        }

        for(int i=0;i<T;i++){
            spread();
//            System.out.println(i+"회");
//            for(int j=0;j<R;j++){
//                for(int k=0;k<C;k++){
//                    System.out.printf("%3d ",map[j][k]);
//                }
//                System.out.println();
//            }
            circulate();
//            System.out.println(i+"회+써큘");
//            for(int j=0;j<R;j++){
//                for(int k=0;k<C;k++){
//                    System.out.printf("%3d ",map[j][k]);
//                }
//                System.out.println();
//            }
        }

        int ans =0;
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j]==-1)   continue;
                ans+=map[i][j];
            }
        }

        System.out.println(ans);
    }

    public static void circulate(){
        //노가다..?
        //윗쪽 순환
        int top_circulator_r = circulator_R-1;
//        System.out.println(top_circulator_r);
        for(int i=top_circulator_r-1;i>=1;i--){
            map[i][0]=map[i-1][0];
        }
        for(int i=0;i<C-1;i++){
            map[0][i]=map[0][i+1];
        }
        for(int i=0;i<top_circulator_r;i++){
            map[i][C-1]=map[i+1][C-1];
        }
        for(int i=C-1;i>=2;i--){
            map[top_circulator_r][i]=map[top_circulator_r][i-1];
        }
        map[top_circulator_r][1]=0;

        //아랫쪽 순환
        for(int i=circulator_R+1;i<R-1;i++){
            map[i][0]=map[i+1][0];
        }

        for(int i=0;i<C-1;i++){
            map[R-1][i]=map[R-1][i+1];
        }
        for(int i=R-1;i>=circulator_R+1;i--){
            map[i][C-1]=map[i-1][C-1];
        }

        //오른쪽으로 진행할 때, 역순으로 오른쪽부터 채워넣음
        for(int i=C-1;i>=2;i--){
            map[circulator_R][i]=map[circulator_R][i-1];
        }
        map[circulator_R][1]=0;
    }

    public static void spread(){
        int[][] spread_map = new int[R][C];


        for(int i=0;i<R;i++)
        {
            for(int j=0;j<C;j++){

                //퍼뜨릴게 없거나, 순환기거나 pass
                if(map[i][j]<=0)   continue;

                int spread_count=0;
                for(int k=0;k<4;k++){
                    int next_r = i+dr[k];
                    int next_c = j+dc[k];

                    if(next_r<0||next_c<0||next_r>=R||next_c>=C)    continue;
                    if(map[next_r][next_c]==CIRCULATOR) continue;

                    spread_count++;
                }

                if(spread_count==0) continue;

                int spread_amount = map[i][j]/5;

                map[i][j]-=(spread_count * spread_amount);
                if(spread_amount==0)    continue;

                for(int k=0;k<4;k++){
                    int next_r = i+dr[k];
                    int next_c = j+dc[k];

                    if(next_r<0||next_c<0||next_r>=R||next_c>=C)    continue;
                    if(map[next_r][next_c]==CIRCULATOR) continue;

                    spread_map[next_r][next_c]+=spread_amount; //음수-1로 퍼뜨릴 양 업데이트
                }
            }
        }

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(spread_map[i][j]>0){
                    map[i][j]+=spread_map[i][j];
                }
            }
        }
    }
}
