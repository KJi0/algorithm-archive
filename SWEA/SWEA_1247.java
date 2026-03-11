package SWEA;

/*
 * SWEA 1247: 최적 경로
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1247 {
    static int N, sx, sy, dx, dy, min;
    static Node[] customers;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            customers = new Node[N];
            visited = new boolean[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            sx = Integer.parseInt(st.nextToken());
            sy = Integer.parseInt(st.nextToken());
            dx = Integer.parseInt(st.nextToken());
            dy = Integer.parseInt(st.nextToken());

            for (int i = 0; i < N; i++) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                customers[i] = new Node(x, y, 0);
            }

            min = Integer.MAX_VALUE;
            dfs(0, new Node(dx, dy, 0));
            System.out.println("#" + t + " " + min);
        }
    }

    static void dfs(int idx, Node cur) {
        if (cur.dist >= min) return;

        if (idx == N) {
            int d = calc(cur, new Node(sx, sy, 0));
            min = Math.min(d, min);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            int value = calc(cur, customers[i]);
            dfs(idx + 1, new Node(customers[i].x, customers[i].y, value));
            visited[i] = false;
        }
    }

    static int calc(Node cur, Node next) {
        return cur.dist + Math.abs(next.x - cur.x) + Math.abs(next.y - cur.y);
    }

    static class Node {
        int x; int y; int dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}