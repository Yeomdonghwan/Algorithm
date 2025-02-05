import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /**
        O(N^2) 시간복잡도로 시간초과

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n];
        int[] nums = new int[n];
        dp[0]=1;
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            nums[i]=Integer.parseInt(st.nextToken());
            for(int j=0;j<i;j++){
                if(nums[j]<nums[i]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            if(dp[i]==0)
                dp[i]=1;
        }
        int rs =0;
        for(int i=0;i<n;i++){
            rs=Math.max(rs,dp[i]);
        }
         System.out.println(rs);

         */

        int n = Integer.parseInt(br.readLine());
        int[] subArr = new int[n];
        st = new StringTokenizer(br.readLine());

        //첫번째 값은 넣어둔다
        subArr[0]=Integer.parseInt(st.nextToken());
        int arrSize=1;
        
        for(int i=1;i<n;i++){
            int num = Integer.parseInt(st.nextToken());

            //부분수열의 마지막 값보다 큰 경우 뒤에 붙인다
            if(subArr[arrSize-1]<num){
                subArr[arrSize++]=num;
            }else{
                //이진탐색으로 subArr에 num 들어갈 위치 찾고 변경한다
                int insertIdx = binarySearch(subArr,num,0,arrSize-1);
                subArr[insertIdx] = num;

            }

        }

        System.out.println(arrSize);
    }
    static int binarySearch(int[] arr, int num,int start, int end){
        int middle = (start+end)/2;
        if(arr[middle]==num || start>=end){
            //중앙값과 같음 또는 못찾음
            if(arr[middle]>=num){
                return middle;
            }else{
                return middle+1;
            }

        } else if (arr[middle]>num) {
            //중앙값보다 작은 경우
            return binarySearch(arr,num,start,middle-1);
        }else{
            //중앙값보다 큰 경우
            return binarySearch(arr,num,middle+1,end);
        }
    }
}
