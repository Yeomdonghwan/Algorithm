import java.util.*;
class Solution {
    public int solution(int coin, int[] cards) {
        int answer = 0;
        
        Map<Integer, Integer> cardMap = new HashMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();//낼수있는 조합의 비용 저장
        
        // int initN = cards.length/3;
        int idx=0;
        int N = cards.length;
        // int sum = cards.length+1;
        for(idx=0;idx<N/3;idx++){
            //초기 N/3개 입력
            if(cardMap.containsKey(N+1-cards[idx])){
                cardMap.remove(N+1-cards[idx]);
                // pq.add(new int[]{N+1-cards[i],cards[i],0});        
                pq.add(0); //공짜
            }else{
            
            cardMap.put(cards[idx],0);
            
            }
        }
        
        
        // System.out.println(pq.size());
        int round=1;
        while(true){
            
            
            //카드뭉치에 두 장 이상 남아있는지 확인
            if(idx>cards.length-2)
                break;
            
            //한장 뽑기
            if(cardMap.containsKey(N+1-cards[idx])){
                //짝이 있음
                int cost = cardMap.remove(N+1-cards[idx]);
                pq.add(cost+1);
            }else{
                cardMap.put(cards[idx],1);
            }

            idx++;
            //한장 뽑기
            if(cardMap.containsKey(N+1-cards[idx])){
                //짝이 있음
                int cost = cardMap.remove(N+1-cards[idx]);
                pq.add(cost+1);
            }else{
                cardMap.put(cards[idx],1);
            }
            idx++;

            
            if(pq.isEmpty())
                break;
            
            int cost = pq.poll(); //조합 내기
            if(cost>coin)
                break;
            
            coin-=cost;
            
            round++;
        }
        
        return round;
    }
}