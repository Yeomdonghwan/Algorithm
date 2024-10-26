import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        double pi = Math.PI;  // Math 클래스의 상수 PI 사용

    
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int food: scoville){
            minHeap.add(food);
        }
        
        while(minHeap.size() >=2 && minHeap.peek()<K){
            int first = minHeap.poll();
            int second = minHeap.poll();
            
            int nFood = first + 2*second;
            answer++;
            
            minHeap.add(nFood);
        }

        if(!minHeap.isEmpty() && minHeap.peek()>=K)
            return answer;
        else{
            return -1;
        }
    }
}