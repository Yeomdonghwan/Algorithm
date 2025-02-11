import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
class Main{
    
    private static class Shark{
        int r;
        int c;
        int s;
        int d;
        int z;
        char name;
        public Shark(int r,int c,int s,int d,int z){
            this.r =r;
            this.c=c;
            this.s=s;
            this.d=d;
            this.z=z;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());


        List<Shark> sharkList = new ArrayList<>();
        Map<Integer,Map<Integer,Shark>> sharkMap = new HashMap<>(); //상어의 위치를 저장할 해시맵. [r,c]:shark
        Shark catchShark= null;
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int shark_r = Integer.parseInt(st.nextToken());
            int shark_c = Integer.parseInt(st.nextToken());
            int shark_s = Integer.parseInt(st.nextToken());
            int shark_d = Integer.parseInt(st.nextToken());
            int shark_z = Integer.parseInt(st.nextToken());

            Shark shark = new Shark(shark_r, shark_c, shark_s, shark_d, shark_z);
            shark.name=(char) ('A'+i);
            sharkList.add(shark);
            
            if(shark_c==1) {
            	if(catchShark==null || catchShark.r>shark_r) {
            		catchShark=shark;
            	}
            }

        }

        int answer=0;
        for(int i=1;i<=c;i++){
            //1초씩 증가
            sharkMap.clear();

            //상어잡기
            if(catchShark!=null) {
            	answer+=catchShark.z;
                sharkList.remove(catchShark);
            }
            catchShark=null;
            


            //상어 이동
            List<Shark> deadSharks = new ArrayList<>();
            for(Shark shark:sharkList){
                //상어를 한마리씩 꺼냄
                
                //1초후 상어의 목적지
                int goalR=0;
                int goalC=0;

                //상어 이동
                //방향이 오른쪽인 경우
                switch (shark.d) {
                    case 3:
                        {
                            int distance = shark.s; //1초동안 상어가 이동할 거리
                            int directionChangeCount=0;
                            //오른쪽 방향이고 벽을 넘는 경우
                            if( shark.c+distance>=c){
                                
                                //오른쪽 끝에 붙어있는 상태로 만듦
                                directionChangeCount++;
                                distance-=(c-shark.c);
                                
                                //이후 몇번의 방향전환이 발생하는지 계산
                                directionChangeCount+=(distance/(c-1));
                                distance=distance%(c-1);
                                
                                if(directionChangeCount%2==0){
                                    //짝수번 방향전환 == 원래방향
                                    goalR = shark.r;
                                    goalC = distance+1;
                                }else{
                                    //홀수번 방향전환
                                    shark.d=4; //방향변경
                                    goalR = shark.r;
                                    goalC = c-distance;
                                }
                                
                            }else{
                                //오른쪽 방향이고 벽을 못넘는 경우
                                goalR=shark.r;
                                goalC=shark.c+shark.s;
                            }       break;
                        }
                    case 4:
                        {
                            //왼쪽 진행
                            int distance = shark.s; //1초동안 상어가 이동할 거리
                            int directionChangeCount=0;
                            //왼쪽 방향이고 벽을 넘는 경우
                            if(shark.c-distance<=1){
                                
                                //왼쪽 끝에 붙어있는 상태로 만듦
                                directionChangeCount++;
                                distance-=(shark.c-1);
                                
                                //이후 몇번의 방향전환이 발생하는지 계산
                                directionChangeCount+=(distance/(c-1));
                                distance=distance%(c-1);
                                
                                if(directionChangeCount%2==0){
                                    //짝수번 방향전환 == 원래방향(왼쪽)
                                    goalR = shark.r;
                                    goalC = c-distance;
                                }else{
                                    //홀수번 방향전환 == 반대방향(오른쪽)
                                    shark.d=3; //방향변경
                                    goalR = shark.r;
                                    goalC = distance+1;
                                }
                                
                            }else{
                                //왼쪽 방향이고 벽을 못넘는 경우
                                goalR=shark.r;
                                goalC=shark.c-shark.s;
                            }       break;
                        }
                    case 1:
                        {
                            //위쪽 진행
                            int distance = shark.s; //1초동안 상어가 이동할 거리
                            int directionChangeCount=0;
                            //위쪽 방향이고 벽을 넘는 경우
                            if(shark.r-distance<=1){
                                
                                //위쪽 끝에 붙어있는 상태로 만듦
                                directionChangeCount++;
                                distance-=(shark.r-1);
                                
                                //이후 몇번의 방향전환이 발생하는지 계산
                                directionChangeCount+=(distance/(r-1));
                                distance=distance%(r-1);
                                
                                if(directionChangeCount%2==0){
                                    //짝수번 방향전환 == 원래방향(위쪽)
                                    goalR = r-distance;
                                    goalC = shark.c;
                                }else{
                                    //홀수번 방향전환 == 반대방향(아래쪽)
                                    shark.d=2; //방향변경
                                    goalR = distance+1;
                                    goalC = shark.c;
                                }
                                
                            }else{
                                //위쪽 방향이고 벽을 못넘는 경우
                                goalR=shark.r-distance;
                                goalC=shark.c;
                            }       break;
                        }
                    case 2:
                        {
                            //아래쪽 진행
                            int distance = shark.s; //1초동안 상어가 이동할 거리
                            int directionChangeCount=0;
                            //아래쪽 방향이고 벽을 넘는 경우
                            if(shark.r+distance>=r){
                                
                                //아래쪽 끝에 붙어있는 상태로 만듦
                                directionChangeCount++;
                                distance-=(r-shark.r);
                                
                                //이후 몇번의 방향전환이 발생하는지 계산
                                directionChangeCount+=(distance/(r-1));
                                distance=distance%(r-1);
                                
                                if(directionChangeCount%2==0){
                                    //짝수번 방향전환 == 원래방향(아래쪽)
                                    goalR = distance+1;
                                    goalC = shark.c;
                                }else{
                                    //홀수번 방향전환 == 반대방향(위쪽)
                                    shark.d=1; //방향변경
                                    goalR = r-distance;
                                    goalC = shark.c;
                                }
                                
                            }else{
                                //아래 방향이고 벽을 못넘는 경우
                                goalR=shark.r+distance;
                                goalC=shark.c;
                            }       break;
                        }
                    default:
                        break;
                }

                
                //이동완료
                shark.r=goalR;
                shark.c=goalC;
                
                if(sharkMap.containsKey(goalR)){
                    if(sharkMap.get(goalR).containsKey(goalC)){
                        //비교하고 잡아먹기
                        Shark otherShark = sharkMap.get(goalR).get(goalC);
                        if(otherShark.z>=shark.z){
                            //기존 상어가 더 큼

                            // sharkList.remove(shark);//죽은 상어 삭제
                            deadSharks.add(shark);
                        }else{

                            sharkMap.get(goalR).put(goalC,shark);
                            // sharkList.remove(otherShark);
                            deadSharks.add(otherShark);
                        }
                    }else {
                    	sharkMap.get(goalR).put(goalC, shark);
                    }
                }else{
                    sharkMap.put(goalR, new HashMap<>());
                    sharkMap.get(goalR).put(goalC, shark);
                }

                
                if(goalC==i+1){
                    //잡을 수 있는 상어임
                    if(catchShark==null || catchShark.r>=goalR){
                        
                        catchShark = sharkMap.get(goalR).get(goalC); 
                    }
                }
            }
            
            //죽은 상어 삭제
            for(Shark deadShark: deadSharks){
                sharkList.remove(deadShark);
            }

        }
        System.out.println(answer);
    }
}