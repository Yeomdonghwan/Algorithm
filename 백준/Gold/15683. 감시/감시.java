import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {0,0,-1,1};
    static int[] dc = {1,-1,0,0};
    static int[][] map ;
    static int n,m, ans=Integer.MAX_VALUE;
    static List<Integer[]>[] cctvs;
    static public void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        cctvs = new ArrayList[6];
        for(int i=1;i<6;i++){
            cctvs[i]=new ArrayList<>();
        }

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                map[i][j]=Integer.parseInt(st.nextToken());

                if(map[i][j]>0 &&map[i][j]!=6){
                    //cctv
                    cctvs[map[i][j]].add(new Integer[]{i,j});
                }
            }
        }

        for(Integer[] cctv : cctvs[5]){
            //5cctv는 1가지 경우밖에 없으므로 미리 표시
            int r=cctv[0];
            int c=cctv[1];
            for(int k=0;k<4;k++){
                explore(r, c, k,0);

            }
        }

        bruteforce(1,0);
        System.out.println(ans);
    }

    /**
     * r,c위치에서 k방향으로 탐색하며 벽이나 cctv를 만나기 전까지 map에 표시.
     * 복구하는 경우 revert에 2를 넣음. 평상시 탐색은 revert= 0
     * */
    private static void explore(int r, int c, int k, int revert) {
        int distance=1;
        while(true){
            int next_r = r +dr[k]*distance;
            int next_c = c +dc[k]*distance;

            if(next_r<0||next_c<0||next_r>=n||next_c>=m || map[next_r][next_c]==6){
                break;
            }
            if(map[next_r][next_c]<=0)
                map[next_r][next_c]+=(-1+revert);
            distance++;
        }
    }

    private static void bruteforce(int level, int step){
        if(level==4 && step==cctvs[4].size()){
            //끝남. 사각지대 세기

//            for(int i=0;i<n;i++) {
//                for (int j = 0; j < m; j++) {
//                    System.out.printf("%3d ",map[i][j]);
//                }
//                System.out.println();
//            }
//            System.out.println();
            int count=0;
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    if(map[i][j]==0)
                        count++;
                }
            }
            ans=Math.min(ans,count);
            return;
        }

        if(cctvs[level].size() == step){
            //level단계의 cctv를 모두 탐색함
            bruteforce(level+1, 0);
            return;
        }

        Integer[] cctv = cctvs[level].get(step);
        int r=cctv[0];
        int c=cctv[1];

        if(level==1){
            //4방향 탐색
            for(int i=0;i<4;i++){
                explore(r,c,i,0);
                bruteforce(level,step+1);
                explore(r,c,i,2); //revert
            }

        }else if(level==2){
            //위아래
            explore(r,c,0,0);
            explore(r,c,1,0);
            bruteforce(level,step+1);
            explore(r,c,0,2);
            explore(r,c,1,2);

            explore(r,c,2,0);
            explore(r,c,3,0);
            bruteforce(level,step+1);
            explore(r,c,2,2);
            explore(r,c,3,2);
        }else if(level==3){
            //네가지 조합 ..;;
            explore(r,c,0,0);
            explore(r,c,3,0);
            bruteforce(level,step+1);
            explore(r,c,0,2);
            explore(r,c,3,2);

            explore(r,c,1,0);
            explore(r,c,3,0);
            bruteforce(level,step+1);
            explore(r,c,1,2);
            explore(r,c,3,2);

            explore(r,c,1,0);
            explore(r,c,2,0);
            bruteforce(level,step+1);
            explore(r,c,1,2);
            explore(r,c,2,2);

            explore(r,c,2,0);
            explore(r,c,0,0);
            bruteforce(level,step+1);
            explore(r,c,2,2);
            explore(r,c,0,2);
        }else if(level==4){
            for(int i=0;i<4;i++){
                explore(r,c,(0+i)%4,0);
                explore(r,c,(1+i)%4,0);
                explore(r,c,(2+i)%4,0);
                bruteforce(level,step+1);
                explore(r,c,(0+i)%4,2);
                explore(r,c,(1+i)%4,2);
                explore(r,c,(2+i)%4,2);

            }

        }
    }
}
