import java.util.*;
/**
tip: pq는 remove(element)가 가능하다.

*/
class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<operations.length;i++){
            String[] temp=operations[i].split(" ");
            if(temp[0].equals("I")){
                minHeap.add(Integer.parseInt(temp[1]));
                maxHeap.add(Integer.parseInt(temp[1]));
            }else if(temp[0].equals("D")){
                if(minHeap.isEmpty())
                    continue;
                if(temp[1].equals("1")){
                    //최댓값 삭제
                    int e = maxHeap.poll();
                    minHeap.remove(e);
                }else if(temp[1].equals("-1")){
                    int e = minHeap.poll();
                    maxHeap.remove(e);
                }
            }
        }
        if(minHeap.isEmpty()){
            answer[0]=answer[1]=0;
        }else{
            answer[0]=maxHeap.poll();
            answer[1]=minHeap.poll();
        }
        return answer;
    }
}