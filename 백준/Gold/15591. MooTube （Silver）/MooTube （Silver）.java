import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        int id;
        int usado;

        public Node(int q, int r) {
            this.id = q;
            this.usado = r;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

//        int [][] arr = new int[N+1][N+1];
        List<List<Node>> arr = new ArrayList<>();
        for(int i=0;i<N+1;i++){
            arr.add(new ArrayList<>());
        }


        for(int i=0;i<N-1;i++){
            st= new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r= Integer.parseInt(st.nextToken());

//            arr[p][q] = arr[q][p]=r;
            arr.get(p).add(new Node(q,r));
            arr.get(q).add(new Node(p,r));
        }


        for(int i=0;i<Q;i++){
            st = new StringTokenizer(br.readLine());
            int k=Integer.parseInt(st.nextToken());
            int v=Integer.parseInt(st.nextToken());

            Queue<Integer> queue = new LinkedList<>();
            boolean[] visited = new boolean[N+1];

            int count=0;

            queue.add(v);
            visited[v]=true;
            while(!queue.isEmpty()){
                int now = queue.poll();

                    count++;


//                for(int j=1;j<N+1;j++){
//                    if(arr[now][j] >= k && !visited[j]){
//                        queue.add(j);
//                        visited[j] = true;
//                    }
//                }

                for(int j=0;j<arr.get(now).size();j++){
                    Node next = arr.get(now).get(j);
                    if(next.usado >= k  && !visited[next.id]){
                        queue.add(next.id);
                        visited[next.id]=true;
                    }
                }
            }
            bw.write(count-1+"\n");
        }

        bw.flush();
    }
        }
