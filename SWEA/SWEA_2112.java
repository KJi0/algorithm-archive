package SWEA;

/*
 * SWEA 2112: 보호 필름
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2112 {
	static int D, W, K, ans;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[D][W];
			
			// 0은 A, 1은 B
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			ans = Integer.MAX_VALUE;
			dfs(0, 0);
			System.out.println("#" + t + " " + (ans == Integer.MAX_VALUE ? 0 : ans));
		}
	}
	
	static void dfs(int depth, int cnt) {
		if (cnt >= ans) return;
		
		if (check()) {
			ans = cnt;
			return;
		}
		
		if (depth == D) return;
		
		// 약품 투입 X
		dfs(depth + 1, cnt);
		
		int[] tmpMap = map[depth].clone();
		
		// 약품 A 투입
		fill(depth, 0);
		dfs(depth + 1, cnt + 1);
		
		// 약품 B 투입
		fill(depth, 1);
		dfs(depth + 1, cnt + 1);
		
		map[depth] = tmpMap;
	}
	
	static boolean check() {
		for (int j = 0; j < W; j++) {
			int cnt = 1;
			boolean flag = false;
			
			for (int i = 1; i < D; i++) {
				if (map[i][j] == map[i - 1][j]) cnt++;
				else cnt = 1;
				
				if (cnt >= K) {
					flag = true;
					break;
				}
			}		
			if (!flag) return false;
		}
		return true;
	}
	
	static void fill(int row, int val) {
		for (int j = 0; j < W; j++)
			map[row][j] = val;
	}
}