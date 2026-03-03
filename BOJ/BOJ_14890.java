package BOJ;

/*
BOJ 14890: 경사로
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14890 {
	static int N, L, ans;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		ans = 0;
		
		// 행
		for (int i = 0; i < N; i++) if (simul(map[i])) ans++;
		
		// 열
		for (int i = 0; i < N; i++) {
			int[] col = new int[N];
			
			for (int j = 0; j < N; j++) col[j] = map[j][i];
			if (simul(col)) ans++;
		}
		
		System.out.println(ans);
	}
	
	static boolean simul(int[] arr) {
		boolean[] used = new boolean[N];
		
		for (int i = 0; i < N - 1; i++) {
			int diff = arr[i + 1] - arr[i];
			
			if (Math.abs(diff) > 1) return false;
			
			else if (diff == 1) {
				for (int j = 0; j < L; j++) {
					int idx = i - j;
					
					if (idx < 0) return false;
					if (arr[idx] != arr[i] || used[idx]) return false;

					used[idx] = true;
				}
			}
			
			else if (diff == -1) {
				for (int j = 1; j <= L; j++) {
					int idx = i + j;
					
					if (idx >= N) return false;
					if (arr[idx] != arr[i + 1] || used[idx]) return false;

					used[idx] = true;
				}
			}
		}
		
		return true;
	}
}
