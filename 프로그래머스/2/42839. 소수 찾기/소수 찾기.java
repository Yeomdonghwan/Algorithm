import java.util.*;
class Solution {
    boolean[] visited;
    Set<Integer> nums;
    public int solution(String numbers) {
        boolean[] isPrime = new boolean[10000000];
        visited = new boolean[numbers.length()];
        for(int i=0;i<10000000;i++){
            isPrime[i]=true;
        }
        isPrime[0]=isPrime[1]=false;
        
        for(int i=2;i<10000000;i++){
            if(isPrime[i]){
                for(int j=i*2;j<10000000;j+=i){
                    isPrime[j]=false;
                }
            }
        }
        nums = new HashSet<>();
        
        for(int i=0;i<numbers.length();i++){
            nums.add(Integer.parseInt(numbers.substring(i,i+1)));
        }
        int answer = 0;
        backtracking(numbers,"",0);
        for(int num : nums){
            if(isPrime[num])
                answer++;
        }
        
        return answer;
    }
    public void backtracking(String numbers,String s, int step){
        if(step==numbers.length()){
            return;
        }
        
        for(int i=0;i<numbers.length();i++){
            if(visited[i]==false){
                visited[i]=true;
                nums.add(Integer.parseInt(s+numbers.charAt(i)));
                backtracking(numbers,s+numbers.charAt(i),step+1);
                visited[i]=false;
            }
        }
    }
}