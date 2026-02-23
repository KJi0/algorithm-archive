package SWEA;

/*
SWEA 2105: 디저트 카페
방향을 유지해야 하면 보통 DFS를 쓴다
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2105 {
    static int N, max;
    static int[][] map;
    static boolean[] dessert;

    static int[] dx = {1, 1, -1, -1};
    static int[] dy = {1, -1, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            max = -1;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++)
                    map[i][j] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dessert = new boolean[101];
                    dfs(i, j, i, j, 0, 0);
                }
            }

            System.out.println("#" + t + " " + max);
        }
    }

    static void dfs(int sx, int sy, int x, int y, int dir, int cnt) {

        dessert[map[x][y]] = true;

        for (int d = dir; d < 4; d++) {

            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

            if (nx == sx && ny == sy && cnt >= 3) {
                max = Math.max(max, cnt + 1);
                continue;
            }

            if (!dessert[map[nx][ny]]) {
                dfs(sx, sy, nx, ny, d, cnt + 1);
            }
        }

        dessert[map[x][y]] = false;
    }
}