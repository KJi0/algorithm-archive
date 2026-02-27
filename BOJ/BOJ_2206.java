package BOJ;

/*
 * BOJ 2206: 벽 부수고 이동하기
 * 좌표 말고 더 기억할 게 있다,
 * 같은 좌표라도 상황이 다르면 다른 방문이다,
 * 싶으면 무조건 3차원 BFS 고려하기!!
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206 {
	static int N, M, min = Integer.MAX_VALUE;
	static char[][] map;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		
		for (int i = 0; i < N; i++) map[i] = br.readLine().toCharArray();
		
		bfs();
		System.out.println((min == Integer.MAX_VALUE) ? -1 : min);
	}

	static void bfs() {
		Queue<Node> q = new LinkedList<>();
		boolean[][][] visited = new boolean[N][M][2];
		
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
				
				if (map[nx][ny] == '0' && !visited[nx][ny][cur.wall]) {
					visited[nx][ny][cur.wall] = true;
					q.add(new Node(nx, ny, cur.wall, cur.dist + 1));
				}
				
				if (map[nx][ny] == '1' && !visited[nx][ny][1] && cur.wall == 0) {
					visited[nx][ny][1] = true;
					q.add(new Node(nx, ny, 1, cur.dist + 1));
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