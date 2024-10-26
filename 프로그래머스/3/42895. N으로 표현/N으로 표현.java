import java.util.*;
/**
최대8번까지이므로...
x 번 사용해서 만들수있는 숫자들을 set에 넣어둔다.
이 떄 x번 사용해서 만들 수 있는 숫자들은
- NNN...(x번)
- x-1번째 + 1번째
- x-1번째 * 1번째
- x-1번째 - 1번째
- x-1번째 / 1번째
+
+ 반대경우
+ 더해서 x번이되는 모든조합



*/
class Solution {
    public int solution(int N, int number) {
        
        List<Set<Integer>> list = new ArrayList<>();
        int n_repeat = N;
        for(int i=0;i<9;i++){
            list.add(new HashSet<>());
            
        }
        list.get(1).add(N);
        if(number==N)
            return 1;
        for(int i=2;i<9;i++){
            //박스 채우기
            // System.out.println(i);
            for(int j=1;j<=i/2;j++){
                union(list.get(i),list.get(j),list.get(i-j));
                
                union(list.get(i),list.get(i-j),list.get(j));
                
                n_repeat = n_repeat*(10)+ N;
                list.get(i).add(n_repeat);
                // System.out.println(i+"번쨰"+"\n"+list.get(i).toString());
            }
            
            //타겟 있는지 확인
            if(list.get(i).contains(number)){
                return i;
                
            }
            
        }
        
        int answer = -1;
        return answer;
    }
    public void union(Set<Integer> res , Set<Integer> o1, Set<Integer> o2){
        for(Integer num1: o1){
            for(Integer num2: o2){
                res.add(num1+num2);
                res.add(num1-num2);
                res.add(num1*num2);
                if(num2!=0)
                    res.add(num1/num2);
            }
        }
        
    }
}