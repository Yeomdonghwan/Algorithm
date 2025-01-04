import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        
        Queue<Integer> wait = new LinkedList<>();
        for(int i=0;i<truck_weights.length;i++){
            wait.add(truck_weights[i]);
        }
        
        Queue<Integer> bridge = new LinkedList<>();
        int now_weight=0;
        while(!wait.isEmpty()){
            answer++;
            if(bridge.size()>=bridge_length){
                now_weight-=bridge.poll();
            }
            
            
            
            if(now_weight+wait.peek() <= weight){
                now_weight+=wait.peek();
                bridge.add(wait.poll());
                
            }else{
                bridge.add(0);
            }
            
            
        }
        answer+=bridge_length;
        return answer;
    }
}