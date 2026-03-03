package SWEA;

/*
 * SWEA 2805: 농작물 수확하기
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_2805 {
	static int N;
	static char[][] farm;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			farm = new char[N][N];
			
			for (int i = 0; i < N; i++) farm[i] = br.readLine().toCharArray();
			
			int ans = 0;
			int M = N / 2;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int dist = Math.abs(i - M) + Math.abs(j - M);
					
					if (dist <= M) ans += farm[i][j] - '0';
				}
			}
			
			System.out.println("#" + t + " " + ans);
		}
	}
}
