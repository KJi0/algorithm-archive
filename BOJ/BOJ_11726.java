package BOJ;

/*
 * BOJ 11726: 2xn 타일링
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11726 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] dp = new int[3][1001];
		
		dp[2][1] = dp[1][2] = 1;
		dp[2][2] = 2;
				
		for (int i = 3; i <= n; i++)
			dp[2][i] = (dp[2][i - 2] + dp[2][i - 1]) % 10_007;
		
		System.out.println(dp[2][n]);
	}
}