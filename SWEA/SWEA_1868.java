package SWEA;

/*
 * SWEA 1868: 파핑파핑 지뢰찾기
 * 난 지뢰찾기 안 해 봐서 룰을 모르는데...
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_1868 {
	static int N, ans;
	static char[][] map;
	static int[][] cnt;
	static boolean[][] visited;

	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			map = new char[N][N];
			cnt = new int[N][N];
			visited = new boolean[N][N];
			ans = 0;
			
			for (int i = 0; i < N; i++) map[i] = br.readLine().toCharArray();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '*') continue;
					
					int n = 0;
					
					for (int d = 0; d < 8; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
						
						if (map[nx][ny] == '*') n++;
					}
					
					cnt[i][j] = n;
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '.' && cnt[i][j] == 0 && !visited[i][j]) {
						bfs(i, j);
						ans++;
					}
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '.' && !visited[i][j]) ans++;
				}
			}
			
			System.out.println("#" + t + " " + ans);
		}
	}

	static void bfs(int x, int y) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(x, y));
		visited[x][y] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			for (int d = 0; d < 8; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				if (visited[nx][ny]) continue;
				if (map[nx][ny] == '*') continue;
				
				visited[nx][ny] = true;
				
				if (cnt[nx][ny] == 0) q.add(new Node(nx, ny));
			}
		}
	}
	
	static class Node {
		int x; int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
