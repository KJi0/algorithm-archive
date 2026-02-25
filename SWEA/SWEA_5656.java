package SWEA;

/*
 * SWEA 5656: 벽돌 깨기
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5656 {
	static int N, W, H, min;
	static int[][] map;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 구슬 개수
			W = Integer.parseInt(st.nextToken()); // 열
			H = Integer.parseInt(st.nextToken()); // 행
			
			map = new int[H][W];
			min = Integer.MAX_VALUE;
			
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0, map);
			System.out.println("#" + t + " " + min);
		}
	}

	/*
	 * dfs로 map 돌면서 하나씩 구슬 맞추기 시작
	 * bfs로 폭탄 크기만큼 터지기
	 * 만약 폭탄 거리에 1 이상의 폭탄이 있다면 q에 넣기
	 * 다 터지면 depth + 1
	 * j = 0, i = N - 1부터 돌면서 하나씩 내리기
	 */
	
	static void dfs(int depth, int[][] map) {
		int remain = countBrick(map);
		
		if (remain == 0) { min = 0; return; }
		
		if (depth == N) {
			min = Math.min(min, remain);
			return;
		}
		
		for (int j = 0; j < W; j++) {
			int i = findFirstBrick(j, map);
			if (i == -1) continue;
			
			int[][] newMap = copy(map);
			
			boom(i, j, newMap);
			downBrick(newMap);
			
			dfs(depth + 1, newMap);
		}
	}
	
	static int findFirstBrick(int j, int[][] map) {
		for (int i = 0; i < H; i++) {
			if (map[i][j] > 0) return i;
		}
		return -1;
	}
	
	static void boom(int i, int j, int[][] map) {
		Queue<int[]> q = new LinkedList<>();
		
		if (map[i][j] > 1) q.add(new int[] {i, j, map[i][j]});
		
		map[i][j] = 0;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int power = cur[2];
			
			for (int d = 0; d < 4; d++) {
				for (int p = 1; p < power; p++) {
					int nx = cur[0] + dx[d] * p;
					int ny = cur[1] + dy[d] * p;
					
					if (nx < 0 || nx >= H || ny < 0 || ny >= W) break;
					if (map[nx][ny] == 0) continue;
					
					if (map[nx][ny] > 1) q.add(new int[] {nx, ny, map[nx][ny]});
					
					map[nx][ny] = 0;	
				}
			}
		}
	}
	
	static void downBrick(int[][] map) {
		for (int j = 0; j < W; j++) {
			int bottom = H - 1;
			
			for (int i = H - 1; i >= 0; i--) {
				if (map[i][j] > 0) {
					map[bottom][j] = map[i][j];
					if (bottom != i)
						map[i][j] = 0;
					bottom--;
				}
			}
		}
	}
	
	static int countBrick(int[][] map) {
		int cnt = 0;
		
		for (int j = 0; j < W; j++) {
			for (int i = 0; i < H; i++) {
				if (map[i][j] > 0) cnt++;
			}
		}
		return cnt;
	}
	
	static int[][] copy(int[][] map) {
		int[][] newMap = new int[H][W];
		
		for (int i = 0; i < H; i++)
			System.arraycopy(map[i], 0, newMap[i], 0, W);
		
		return newMap;
	}
}