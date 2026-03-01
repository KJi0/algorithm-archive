package SWEA;

/*
 * SWEA 4014: 활주로 건설
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4014 {
	static int N, X;
	static int[][] map;
	static boolean[] used;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}

			int cnt = 0;

			// 행 검사
			for (int i = 0; i < N; i++) {
				if (isOK(map[i])) cnt++;
			}

			// 열 검사
			for (int i = 0; i < N; i++) {
				int[] col = new int[N];
				for (int j = 0; j < N; j++) col[j] = map[j][i];

				if (isOK(col)) cnt++;
			}

			System.out.println("#" + t + " " + cnt);
		}
	}

	static boolean isOK(int[] arr) {
		used = new boolean[N];

		for (int i = 0; i < N - 1; i++) {
			int diff = arr[i + 1] - arr[i];

			if (diff == 0) continue;

			else if (diff == 1) {
				for (int j = 1; j < X; j++) {
					int idx = i - j;

					if (idx < 0) return false;
					if (arr[idx] != arr[i] || used[idx]) return false;

					used[idx] = true;
				}
			}

			else if (diff == -1) {
				for (int j = 1; j <= X; j++) {
					int idx = i + j;

					if (idx >= N) return false;
					if (arr[idx] != arr[i + 1] || used[idx]) return false;

					used[idx] = true;
				}
			}
			else return false;
		}
		return true;
	}
}
