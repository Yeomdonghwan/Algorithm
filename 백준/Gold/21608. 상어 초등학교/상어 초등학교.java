import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] prefer = new int[n*n+1][4];
        int[][] map = new int[n][n];
        int[] dx = {0,0,-1,1};
        int[] dy = {1,-1,0,0};

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                map[i][j]=0; //빈칸 초기화
            }
        }
        for(int i=0;i<n*n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int student = Integer.parseInt(st.nextToken());
            prefer[student][0]=Integer.parseInt(st.nextToken());
            prefer[student][1]=Integer.parseInt(st.nextToken());
            prefer[student][2]=Integer.parseInt(st.nextToken());
            prefer[student][3]=Integer.parseInt(st.nextToken());

            int max_prefer_count=-1;
            int max_blank_count=-1;
            int r_idx=0;
            int c_idx=0;

            for(int j=0;j<n;j++){
                for(int k=0;k<n;k++){
                    if(map[j][k]!=0)    continue;
                    int r = j;
                    int c = k;
                    int prefer_count=0;
                    int blank_count=0;
                    for(int l=0;l<4;l++){
                        int next_r = r+dx[l];
                        int next_c = c+dy[l];
                        if(next_r>=n || next_r<0 || next_c <0 || next_c >=n)    continue;

                        if(map[next_r][next_c]==0)  blank_count++;
                        else if(map[next_r][next_c]==prefer[student][0] ||
                                map[next_r][next_c]==prefer[student][1] ||
                                map[next_r][next_c]==prefer[student][2] ||
                                map[next_r][next_c]==prefer[student][3])
                        {
                            prefer_count++;
                        }
                    }

                    if(prefer_count>max_prefer_count){
                        max_prefer_count = prefer_count;
                        max_blank_count= blank_count;
                        r_idx=r;
                        c_idx=c;
                    }else if(prefer_count==max_prefer_count && blank_count > max_blank_count){
                        max_blank_count= blank_count;
                        r_idx=r;
                        c_idx=c;
                    }

                }
            }
            map[r_idx][c_idx]=student;

        }

        int rs=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int count=0;
                for(int k=0;k<4;k++){
                    int next_r = i+dx[k];
                    int next_c = j+dy[k];
                    if(next_r>=n || next_r<0 || next_c <0 || next_c >=n)    continue;

                    if(map[next_r][next_c]==prefer[map[i][j]][0] ||
                            map[next_r][next_c]==prefer[map[i][j]][1] ||
                            map[next_r][next_c]==prefer[map[i][j]][2] ||
                            map[next_r][next_c]==prefer[map[i][j]][3] )
                    {
                        count++;
                    }
                }

                if(count==1)    rs+=1;
                if(count==2)    rs+=10;
                if(count==3)    rs+=100;
                if(count==4)    rs+=1000;

            }
        }
        System.out.println(rs);

    }
}
