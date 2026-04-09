package SWEA;

/*
 * SWEA 3282: 0/1 Knapsack
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3282 {
	static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			int[] v = new int[N];
			int[] c = new int[N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				v[i] = Integer.parseInt(st.nextToken());
				c[i] = Integer.parseInt(st.nextToken());
			}
			
			int[] dp = new int[K + 1]; // 부피 k로 얻을 수 있는 최대 가치
			
			for (int i = 0; i < N; i++) {
				for (int j = K; j >= v[i]; j--) {
					dp[j] = Math.max(dp[j], dp[j - v[i]] + c[i]);
				}
			}
			
			System.out.println("#" + t + " " + dp[K]);
		}
	}
}