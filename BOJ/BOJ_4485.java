package BOJ;

/*
 * BOJ 4485: 녹색 옷 입은 애가 젤다지?
 * 
 * "모든 이동 비용이 동일한가?"
 * 동일 -> BFS, 다름 -> 다익스트라
 * 
 * 2차원 배열에서 가중치가 있는 최소 비용 문제 => 다익스트라 + PQ
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_4485 {
	static int N;
	static int[][] map, dist;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int idx = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());
			
			if (N == 0) break;
			
			map = new int[N][N];
			dist = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0; i < N; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
			
			move();
			System.out.println("Problem " + idx++ + ": " + dist[N - 1][N - 1]);
		}
	}
	
	static void move() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		pq.add(new Node(0, 0, map[0][0]));
		dist[0][0] = map[0][0];
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (cur.rupee > dist[cur.x][cur.y]) continue;
			
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				
				int cost = cur.rupee + map[nx][ny];
				
				if (dist[nx][ny] > cost) {
					dist[nx][ny] = cost;
					pq.add(new Node(nx, ny, cost));
				}
			}
		}
	}
	
	static class Node implements Comparable<Node> {
		int x; int y; int rupee;
		
		public Node(int x, int y, int rupee) {
			this.x = x;
			this.y = y;
			this.rupee = rupee;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.rupee - o.rupee;
		}
	}
}