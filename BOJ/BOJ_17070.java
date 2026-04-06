package BOJ;

/*
 * BOJ 17070: 파이프 옮기기 1
 * DFS인 줄 알았는데...
 * 같은 상태를 여러 번 방문하면 "DP"!
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17070 {
	static int N;
	static int[][] map;
	static int[][][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		dp = new int[N][N][3];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		dp[0][1][0] = 1;
		
		for (int i = 0; i < N; i++) {
			for (int j = 2; j < N; j++) {
				if (j - 1 >= 0 && map[i][j] == 0)
					dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][1];
				if (i - 1 >= 0 && map[i][j] == 0)
					dp[i][j][2] = dp[i - 1][j][1] + dp[i - 1][j][2];
				if (j - 1 >= 0 && i - 1 >= 0 &&
						map[i][j] == 0 && map[i - 1][j] == 0 && map[i][j - 1] == 0)
					dp[i][j][1] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
			}
		}
		
		int ans = dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2];
		System.out.println(ans);
	}
}