import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class Main {
    static Stack<Integer> stack = new Stack<>();
    static Set<Integer[]> gunnerSet = new HashSet<>();
    static class Enemy{
        int r;
        int c;
        boolean isDead=false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        List<List<Enemy>> eList = new ArrayList<>();
        for(int i=0;i<=n;i++){
            eList.add(new ArrayList<>());
        }
        for(int i=n;i>=1;i--){
            //i는 행
            st = new StringTokenizer(br.readLine());

            for(int j=0;j<m;j++){
                int isEnemy = Integer.parseInt(st.nextToken());
                if(isEnemy==1){
                    //적이 있음
                    Enemy e = new Enemy();
                    e.r=i;
                    e.c=j;
                    eList.get(i).add(e); //적을 행별 리스트에 저장
                }
            }
        }
        int ans=0;
        //궁수 세명 뽑기. 0~m-1까지 조합을 gunnerSet에 저장함.
        findGunnerSet(m-1,0);

        for(Integer[] gunners: gunnerSet){
            //궁수 세명 조합이 gunners[0], [1], [2]에 저장되어있음

            int killCount=0;


            Set<Enemy> killListForRevert = new HashSet<>(); //죽이고 나서 다음 조합으로 돌릴 때 죽인 적 살리기 위해.. 죽은 적들을 모아둘 set
            for(int turnIdx=1; turnIdx<=n;turnIdx++){
                //한 턴씩.. ~ 마지막 턴까지 반복

                Set<Enemy> killList = new HashSet<>(); //죽일 적 리스트




                for(int gunnerIdx=0;gunnerIdx<3;gunnerIdx++){
                    //궁수 한 명씩 꺼냄
                    int minDistance=Integer.MAX_VALUE;
                    Enemy killEnemy = null;
                    //가장 가까운 적 찾기
                    for(int i=turnIdx;i<=n;i++){ //예를들어 1턴에는 1행~n행까지 탐색, 2턴에는 2행~n행까지 탐색. (2턴일 때 1행은 사라지므로)



                            for(int j=0;j<eList.get(i).size();j++){
                                Enemy e = eList.get(i).get(j);
                                if(e.isDead)    continue;

                                int distance = calDistance(gunners[gunnerIdx],i-turnIdx+1,e.c);

                                //가장 가까운 적 찾기
                                if(distance<=d && distance<=minDistance)
                                {
                                    if(killEnemy==null || distance<minDistance) {
                                        minDistance=distance;
                                        killEnemy = e;
                                    }
                                    else if(killEnemy.c>e.c){
                                        minDistance = distance;
                                        killEnemy=e;                                        
                                    }
                                }
                            }
                         }

                        if(killEnemy!=null){
                            //죽일 적이 있음
                            killList.add(killEnemy);
                        }


                }
                for(Enemy e: killList){

                    killCount++;
                    //적 죽이기
                    e.isDead=true;
                    killListForRevert.add(e); //다음 궁수 조합을 돌리려면 죽은 적들 다시 살려야함 => 죽은 적들 저장해둘 set에 추가
                }

            }
            for(Enemy e: killListForRevert){
                e.isDead=false; //부활
            }

            ans=Math.max(ans,killCount);
        }
        System.out.println(ans);

    }

    static int calDistance(int gunnerColumn, int r, int c){
        //c열에 있는 궁수와 r,c 사이의 거리를 계산함
        return (r + Math.abs(gunnerColumn - c));
    }

    //궁수 세명을 뽑는 조합을 gunnerSet에 담음
    static void findGunnerSet(int m, int step){
        if(stack.size()==3){
            //궁수세명 뽑음..
            gunnerSet.add(stack.toArray(new Integer[3]));
            return;
        }

        if(step>m)
            return;

        stack.add(step);
        findGunnerSet(m,step+1);
        stack.pop();

        findGunnerSet(m, step+1);

    }

}
