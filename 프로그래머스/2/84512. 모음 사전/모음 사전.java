/**
word 앞에 몇개 만들수있나 봐야하는건가..

AAAAE의 경우
앞에오는건
A로 시작하는 1,2,3,4 자리수 단어들 + AAAA[A,E,I,O,U]
1 
1*1
1*1*1
1*1*1*1*
1*1*1*1*1
= 5개 => 5+1 = 6

1. 단어의 자릿수 계산
2. 자릿수가 더 짧은것들 경우의수를 계산
3. 같은단어일경우 경우의수 게싼
4. 합치고 1더하기
*/
import java.util.*;

class Solution {
    public int solution(String word) {
        int answer = 0;
        
        char[] alpha = {'A','E','I','O','U'};
//         int len = word.length();
//         int[] word_index = new int[word.length()];
        
//         for(int i=0;i<len;i++){
//             if(word.charAt(i)=='A'){
//                 word_index[i]=1;
//             }else if(word.charAt(i)=='E'){
//                 word_index[i]=2;
//             }else if(word.charAt(i)=='I'){
//                 word_index[i]=3;
//             }else if(word.charAt(i)=='O'){
//                 word_index[i]=4;
//             }else if(word.charAt(i)=='U'){
//                 word_index[i]=5;
//             }
//         }
        
        
//         for(int i=1;i<len+1;i++){
//             //i자릿수이고 word보다 앞에오는 단어개수
//             // int count=word_index[0];
//             int cal = word_index[0];
//             for(int j=1;j<i;j++){
//                 cal=cal*word_index[j];
//                 // count+= cal;
                
//             }
            
//             System.out.println(cal);
//             answer+=cal;
//         }
        
//         // len=4. A A A E 
//         //내 문자보다 더 긴경우..
//         for(int j=len+1;j<=5;j++){
//             //하나 채우기
//             int cal = word_index[len-1]-1;
//             for(int i=0;i<len-1;i++){
//                 cal*=word_index[i];
//             }
//             for(int i=len+1;i<=j;i++){
//                 cal*=5;
//             }
//             answer+=cal;
//         }
        
//         //같은 자릿수에서 앞에오는것 개수..
        wordbook = new ArrayList<>();
      backtracking(alpha,0,"");  
        
        
        
        
        return wordbook.indexOf(word)+1;
    }
    List<String> wordbook;
    public void backtracking(char[] alpha, int step, String s){
        if(step==6)
            return;
        
        if(step!=0){
            wordbook.add(s);
            // System.out.println(s);
        }
        for(int i=0;i<alpha.length;i++){
            backtracking(alpha, step+1, s+alpha[i]);
        }
    }
}