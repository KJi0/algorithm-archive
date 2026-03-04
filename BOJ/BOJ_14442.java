package BOJ;

/*
 * BOJ 14442: 벽 부수고 이동하기 2
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14442 {
	static int N, M, K, min;
	static char[][] map;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		min = Integer.MAX_VALUE;
		
		map = new char[N][M];
		for (int i = 0; i < N; i++) map[i] = br.readLine().toCharArray();
		
		bfs();
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	static void bfs() {
		Queue<Node> q = new LinkedList<>();
		boolean[][][] visited = new boolean[N][M][K + 1];
		
		q.add(new Node(0, 0, 0, 1));
		visited[0][0][0] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			if (cur.x == N - 1 && cur.y == M - 1) {
				min = Math.min(min, cur.dist);
				return;
			}
			
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				
				if (map[nx][ny] == '1' && cur.wall < K) {
					if (!visited[nx][ny][cur.wall + 1]) { 
						visited[nx][ny][cur.wall + 1] = true;
						q.add(new Node(nx, ny, cur.wall + 1, cur.dist + 1));
					}
				}
				
				else if (map[nx][ny] == '0' && !visited[nx][ny][cur.wall]) {
					visited[nx][ny][cur.wall] = true;
					q.add(new Node(nx, ny, cur.wall, cur.dist + 1));
				}
			}
		}
	}
	
	static class Node {
		int x; int y; int wall; int dist;
		
		public Node(int x, int y, int wall, int dist) {
			this.x = x;
			this.y = y;
			this.wall = wall;
			this.dist = dist;
		}
	}
}