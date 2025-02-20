import java.util.Scanner;

public class Main {
    static public void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        //1~k 숫자 중에 b[k]값을 찾는다.
        int low = 1;
        int high = k;
        int mid = (low+high)/2;

        while(low<high){
            mid = (low+high)/2;
            //mid숫자보다 작은 숫자들 개수 세기
            int count=0;
            for(int i=1;i<=n;i++){
                //각 행 별로 mid보다 작은 숫자 개수를 셈
                //i행에서 mid보다 작은 숫자 개수는 mid/i개이며 한 행에서 숫자개수는 n개를 넘어갈 수 없음
                count+=Math.min(mid/i, n);
                if(i>mid)
                    break; //i가 mid보다 크면, i행에 mid보다 작은수는 존재하지 않음(ex: 5행에 3보다 작은 수는 없음)
            }

            if(count <k){
                //mid보다 작은숫자가 k개보다 작음 => 더 큰 숫자 범위에서 탐색
                low=mid+1;
            }else if(count==k){
                //운좋게 딱 k번쨰 숫자를 찾았지만 이 숫자가 표에 존재하는 수인지 모름
                //재탐색
                high=mid;
            }else{
                //mid보다 작은 숫자가 k개 이상임 => mid가 답일수도 아닐수도 있음
                //범위를 좁혀 재탐색
                high=mid;
            }


        }

        System.out.println(high);

    }
}
