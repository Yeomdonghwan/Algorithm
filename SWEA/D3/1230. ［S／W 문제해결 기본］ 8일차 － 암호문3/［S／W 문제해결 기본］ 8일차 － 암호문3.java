import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;
/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	public static void main(String args[]) throws Exception
	{BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = 10;

        for(int test_case = 1; test_case <= T; test_case++)
        {
            LinkedList<Integer> list = new LinkedList<>();
            //암호문 뭉치 속 암호문 개수
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0;i<n;i++){
                list.add(Integer.parseInt(st.nextToken()));
            }

            int command_count = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<command_count;i++){
                String command = st.nextToken();
                if("A".equals(command)){
                    int y = Integer.parseInt(st.nextToken());
                    for(int j=0;j<y;j++){
                        list.add(Integer.parseInt(st.nextToken()));
                    }
                }else if("D".equals(command)){
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    for(int j=0;j<y;j++){
                        list.remove(x);
                    }
                }else if("I".equals(command)){
                    //삽입
                    int x = Integer.parseInt(st.nextToken()); //x번째뭉치 다음에
                    int y = Integer.parseInt(st.nextToken()); //y개의 암호문
                    for(int j=0;j<y;j++){
                        list.add(x++,Integer.parseInt(st.nextToken()));
                    }
                }
            }
            bw.append("#"+test_case+" ");
            for(int i=0;i<10 && list.size()>i; i++){
                bw.append(list.get(i)+" ");
            }
            bw.append("\n");
        }

        bw.flush();
        bw.close();
    }
}