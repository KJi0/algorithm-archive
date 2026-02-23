package SWEA;

/*
 * SWEA 7733: 치즈 도둑
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_7733 {
	static int N, max;
	static int[][] map;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			int maxTaste = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					maxTaste = Math.max(maxTaste, map[i][j]);
				}
			}
			
			max = Integer.MIN_VALUE;
			
			while (maxTaste-- > 0)
				max = Math.max(max, bfs(maxTaste));
			
			System.out.println("#" + t + " " + max);
		}
	}

	
	static int bfs(int day) {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];

		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j] || map[i][j] <= day) continue;
				
				cnt++;
				
				q.add(new Node(i, j));
				visited[i][j] = true;
			
				while (!q.isEmpty()) {
					Node cur = q.poll();
					
					for (int d = 0; d < 4; d++) {
						int nx = cur.x + dx[d];
						int ny = cur.y + dy[d];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
						if (visited[nx][ny] || map[nx][ny] <= day) continue;
						
						q.add(new Node(nx, ny));
						visited[nx][ny] = true;
					}
				}
			}
		}
		
		return cnt;
	}
	
	static class Node {
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
