package BOJ;

/*
 * BOJ 7579: 앱
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_7579 {
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] m = new int[N + 1];
		int[] c = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) m[i] = Integer.parseInt(st.nextToken());
		
		int sum = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			c[i] = Integer.parseInt(st.nextToken());
			sum += c[i];
		}
		
		int[] dp = new int[sum + 1]; // 비용 n으로 얻을 수 있는 메모리
		
		for (int i = 1; i <= N; i++) {
			for (int j = sum; j >= c[i]; j--) {
				dp[j] = Math.max(dp[j], dp[j - c[i]] + m[i]);
			}
		}
		
		for (int i = 0; i <= sum; i++) {
			if (dp[i] >= M) {
				System.out.println(i);
				break;
			}
		}
	}
}