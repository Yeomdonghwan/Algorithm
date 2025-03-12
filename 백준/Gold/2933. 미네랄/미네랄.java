import java.util.*;
import java.io.*;

public class Main {

    static char[][] map;
    static int[] dr = {0,0,-1,1};
    static int[] dc = {1,-1,0,0};
    static int R,C;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R+1][C];


        //높이와 인덱스가 일치하도록 거꾸로 입력받음
        for(int i=R;i>=1;i--) {
            String input = br.readLine();
            for(int j=0;j<C;j++) {
                map[i][j]=input.charAt(j);
            }
        }

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            int h = Integer.parseInt(st.nextToken());
            if(i%2==0) {
                //왼쪽에서 던질 차례
                for(int j=0;j<C;j++) {
                    if (miningMineral(i, h, j)) break;
                }

            }else {
                //오른쪽에서 던질 차례
                for(int j=C-1;j>=0;j--) {
                    if (miningMineral(i, h, j)) break;
                }
            }

        }


        for(int i=R;i>=1;i--) {
            for(int j=0;j<C;j++) {
//                System.out.print(map[i][j]);
                bw.append(map[i][j]);
            }
//            System.out.println();
            bw.append("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean miningMineral(int i, int h, int j) {
        if(map[h][j]=='x') {
            //미네랄 폭파
            map[h][j]='.';

            for(int k=0;k<4;k++) {
                //4방향으로 dfs 탐색 ......................!!
                int next_r = h+dr[k];
                int next_c = j+dc[k];
                if(next_r>R || next_r<1 || next_c<0 || next_c>=C)
                    continue;
                if(map[next_r][next_c]=='.')
                    continue;

                boolean[][] visited = new boolean[R+1][C];
                isFloating=true;
                minHeight = new int[C]; //공중에 얼마나 높이 떠있나 기록..
                for(int z=0;z<C;z++) {
                    minHeight[z]=Integer.MAX_VALUE;
                }

                dfs(next_r, next_c, visited);

                int maxDropHeight=Integer.MAX_VALUE;
                if(isFloating) {
                    //얼마나 내릴 수 있나 확인하기 ..;;
                    for(int z=0;z<C;z++) {
                        if(minHeight[z]==Integer.MAX_VALUE)
                            continue; //z열에는 이 도형이 없었다는 것 ..;

                        int dropHeight=0;
                        for(int row = minHeight[z]-1; row>=1; row--) {
                            if(map[row][z]=='x')
                                break;
                            dropHeight++;
                        }
                        maxDropHeight=Math.min(maxDropHeight, dropHeight);

                    }

                    char[][] block= new char[R+1][C];
                    removeBlock(next_r,next_c, block);

                    drawBlock(block,maxDropHeight);

                    break;
                }

            }
            return true;
        }
        return false;
    }

    static int[] minHeight;
    static boolean isFloating = true;
    static void dfs(int r, int c, boolean[][] visited) {
        visited[r][c]=true;

        if(r==1) {
            //땅에 닿아있다는 것 ..
            isFloating=false;
        }

        minHeight[c]=Math.min(minHeight[c], r);

        for(int k=0;k<4;k++) {
            int next_r = r+dr[k];
            int next_c = c+dc[k];
            if(next_r>R || next_r<1 || next_c<0 || next_c>=C)
                continue;
            if(map[next_r][next_c]=='.' || visited[next_r][next_c])
                continue;

            dfs(next_r, next_c, visited);
        }
    }

    static void removeBlock(int r, int c, char[][] block) {
        map[r][c]='.';
        block[r][c]='x';

        for(int k=0;k<4;k++) {
            int next_r = r+dr[k];
            int next_c = c+dc[k];
            if(next_r>R || next_r<1 || next_c<0 || next_c>=C)
                continue;
            if(map[next_r][next_c]=='.')
                continue;

            removeBlock(next_r, next_c,block);
        }
    }
    static void drawBlock(char[][] block, int dropHeight) {
        for(int i=R;i>=1;i--) {
            for(int j=0;j<C;j++) {
                if(block[i][j]=='x') {
                    map[i-dropHeight][j]='x';

                }
            }
        }
    }
}