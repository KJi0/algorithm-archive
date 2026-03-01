package SWEA;

/*
 * SWEA 1251: 하나로
 * "모든 노드를 최소 비용으로 연결하라" == MST
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1251 {
    static int N;
    static long[] x, y;
    static double E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());

            x = new long[N];
            y = new long[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) x[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) y[i] = Integer.parseInt(st.nextToken());

            E = Double.parseDouble(br.readLine());

            long ans = prim();
            System.out.println("#" + t + " " + ans);
        }
    }

    static long prim() {
        boolean[] visited = new boolean[N];
        long[] minEdge = new long[N];

        Arrays.fill(minEdge, Long.MAX_VALUE);
        minEdge[0] = 0;

        long total = 0;

        for (int i = 0; i < N; i++) {
            long min = Long.MAX_VALUE;
            int minVertex = -1;

            for (int j = 0; j < N; j++) {
                if (!visited[j] && minEdge[j] < min) {
                    min = minEdge[j];
                    minVertex = j;
                }
            }

            visited[minVertex] = true;
            total += min;

            for (int j = 0; j < N; j++) {
                if (!visited[j]) {
                    long dx = x[minVertex] - x[j];
                    long dy = y[minVertex] - y[j];
                    long cost = dx * dx + dy * dy;

                    if (cost < minEdge[j]) minEdge[j] = cost;
                }
            }
        }
        return Math.round(total * E);
    }
}
