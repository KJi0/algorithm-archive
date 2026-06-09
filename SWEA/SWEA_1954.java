package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_1954 {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];

            int idx = 1;
            int x = 0; int y = 0; int d = 0;

            while (idx <= N * N) {
                map[x][y] = idx++;
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] != 0) {
                    d = (d + 1) % 4;
                    nx = x + dx[d];
                    ny = y + dy[d];
                }

                x = nx; y = ny;
            }

            System.out.println("#" + t);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++)
                    System.out.print(map[i][j] + " ");
                System.out.println();
            }
        }
    }
}