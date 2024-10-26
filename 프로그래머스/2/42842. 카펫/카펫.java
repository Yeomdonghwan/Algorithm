/**
갈색 칸 수, 노란색 칸 수를 알 때 카펫의 가로,세로 길이?

가로세로는 최소3. (이때 엘로우가 1)
노랑이가 세로한줄일때 가로가 2칸늘어나야 옐로우 1증가
노랑이가 세로두줄일때 가로가 2칸늘어나야 옐로우 2증가

노랑 먼저 결정 -> 브라운 수가 정해짐
ex) y=24, b=24

(1,24) => 2* (24+2) + 2* 1 = 100
(2,12) => 2* (12+2) + 2* 2 = 50
(3,8) =>  2* (8 +2) + 2* 3 = 38
(4,6)  => 2* (6 +2) + 2* 4 = 24

옐로우를 가능한 경우의수대로 뽑으면 될듯함
*/
import java.util.*;
class Solution {
    public int[] solution(int brown, int yellow) {
        Set<int[]> yellow_c = new HashSet<>();
        
        for(int i=1;i<=yellow;i++){
            if(yellow % i ==0){
                if(yellow/i < i)
                    break;
                int[] element = new int[2];
                element[0]=i;
                element[1]=yellow/i;
                yellow_c.add(element);
            }
        }
        
        int[] answer= {};
        for(int[] com: yellow_c){
            if ( brown == (2*(com[1]+2) + 2*com[0])){
                answer = new int[2];
                answer[0] = com[1]+2;
                answer[1] = com[0]+2;
            }
        }
        return answer;
    }
}