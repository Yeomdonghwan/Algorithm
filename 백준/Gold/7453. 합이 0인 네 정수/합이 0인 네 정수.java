
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        int[] A = new int[n];
        int[] B = new int[n];
        int[] C = new int[n];
        int[] D = new int[n];

        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i]=Integer.parseInt(st.nextToken());
            B[i]=Integer.parseInt(st.nextToken());
            C[i]=Integer.parseInt(st.nextToken());
            D[i]=Integer.parseInt(st.nextToken());
        }

        // long[] AB = new long[n*n];
        // long[] CD = new long[n*n];
        Map<Integer,Integer> CD = new HashMap<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                // AB[index] = A[i]+B[j];
                // CD[index] = C[i]+D[j];
                if(CD.containsKey(C[i]+D[j])){
                    CD.put(C[i]+D[j],CD.get(C[i]+D[j])+1);
                }else{
                    CD.put(C[i]+D[j],1);
                }

            }
        }


        long count=0;

        // Arrays.sort(AB);
        // Arrays.sort(CD);

        

        // for(int i=0;i<n*n;i++){
        
        //     long num = AB[i];
            // int idx=Arrays.binarySearch(CD, -num1);
            // if(idx>=0){
            //     //왼쪽탐색
            //     int left_idx = idx; 
            //     while(left_idx>=0 && CD[left_idx]==-num1){
            //         count++;
            //         left_idx--;
            //     }

            //     //오른쪽 탐색
            //     int right_idx = idx+1;
            //     while(right_idx<n*n && CD[right_idx]==-num1){
            //         count++;
            //         right_idx++;
            //     }
            // }
        //     if(CD.containsKey(-num)){
        //         count+=CD.get(-num);
        //     }
        // }

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(CD.containsKey(-(A[i]+B[j]))){
                    count+=CD.get(-(A[i]+B[j]));
                }
            }
        }
        System.out.println(count);

    }

    
}