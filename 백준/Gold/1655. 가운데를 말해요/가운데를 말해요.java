import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n= Integer.parseInt(br.readLine());
        //PQ는 기본적으로 작은숫자가 루트로 감
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<n;i++){
            int num = Integer.parseInt(br.readLine());

            if(maxHeap.size() == minHeap.size()){
                maxHeap.add(num);
            }else{
                minHeap.add(num);
            }

            if(!minHeap.isEmpty() && !maxHeap.isEmpty() && maxHeap.peek() > minHeap.peek()){
                //swap
                minHeap.add(maxHeap.poll());
                maxHeap.add(minHeap.poll());
            }

            if(!maxHeap.isEmpty()){
                bw.write(maxHeap.peek()+"\n");
            }
        }
        bw.flush();
    }
}
