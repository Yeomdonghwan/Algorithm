import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int test_case=1; test_case<=T; test_case++){
            st = new StringTokenizer(br.readLine());
            int n=Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());

            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            maxHeap.add(a);
            int ans=0;
            for(int i=0;i<n;i++){
                st = new StringTokenizer(br.readLine());
                int num1 = Integer.parseInt(st.nextToken());
                int num2 = Integer.parseInt(st.nextToken());

                minHeap.add(num1);
                maxHeap.add(num2);

                int pop1 = minHeap.poll();
                int pop2 = maxHeap.poll();
                if(pop1>pop2){
                    maxHeap.add(pop2);
                    minHeap.add(pop1);
                }else{
                    maxHeap.add(pop1);
                    minHeap.add(pop2);
                }

                int mid = maxHeap.peek();
                ans= (ans+mid)%20171109;
            }
            System.out.println("#"+test_case+" "+ans);
        }
    }
}
