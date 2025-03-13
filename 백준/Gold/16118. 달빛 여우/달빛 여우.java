import java.util.*;
import java.io.*;

public class Main {
    static List<int[]>[] edges;
    static int[][] wDist;
    static int[] fox_dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N, M;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        wDist = new int[2][N + 1];
        fox_dist = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            wDist[1][i] = wDist[0][i] = Integer.MAX_VALUE;
            fox_dist[i] = Integer.MAX_VALUE;
        }

        fox_dist[1] = wDist[0][1] = 0;

        edges = new List[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if (edges[u] == null) {
                edges[u] = new ArrayList<>();
            }

            if (edges[v] == null) {
                edges[v] = new ArrayList<>();
            }

            //2로 나누어도 정수가 되도록 2를 곱해두고 계산함
            edges[u].add(new int[]{v, cost * 2});
            edges[v].add(new int[]{u, cost * 2});
        }

        dfs();
//        System.out.println("dist: "+Arrays.toString(dist));
//        System.out.println("odd : "+Arrays.toString(odd_dist));
//        System.out.println("even: "+Arrays.toString(even_dist));

        dijkstra();

        int count = 0;
        for (int i = 2; i <= N; i++) {
            if (Math.min(wDist[0][i], wDist[1][i]) > fox_dist[i])
                count++;
        }

        System.out.println(count);
//        System.out.println(Arrays.toString(fox_dist));
    }

    static void dijkstra() {
//        Arrays.fill(fox_dist, Integer.MAX_VALUE);
        fox_dist[1] = 0;


        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.offer(new int[]{1, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int now = current[0];
            int nowCost = current[1];
            if (fox_dist[now] < nowCost) continue;

            for (int[] edge : edges[now]) {
                int next = edge[0];
                int cost = edge[1];
                if (fox_dist[next] > fox_dist[now] + cost) {
                    fox_dist[next] = fox_dist[now] + cost;
                    pq.offer(new int[]{next, fox_dist[next]});
                }
            }
        }
    }

    static void dfs() {

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> (o1[1] - o2[1]));
        pq.add(new int[]{1, 0, 0}); //node, cost, step

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int now = current[0];
            int nowCost = current[1];
            int step = current[2];


            if (nowCost > wDist[step % 2][now]) continue;

            for (int[] edge : edges[now]) {
                int next = edge[0];
                int cost = edge[1];

                int totalCost = 0;
                //2배 빠르게 감
                if (step % 2 == 0)
                    totalCost = nowCost + cost / 2;
                else //2배 느리게감
                    totalCost = nowCost + cost * 2;

                if (wDist[(step+1) % 2][next] > totalCost) {
                    //더 빠른 경우
                    wDist[(step+1) % 2][next] = totalCost;
                    pq.offer(new int[]{next, totalCost, step + 1});
                }


            }
        }
    }
}
