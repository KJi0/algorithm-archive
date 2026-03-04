package SWEA;

/*
 * SWEA 1249: 보급로
 * 2차원 배열에서 가중치가 있는 최소 비용 문제 => 다익스트라 + PQ
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class SWEA_1249 {
	static int N;
	static int[][] dist;
	static char[][] map;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			dist = new int[N][N];
			
			for (int i = 0; i < N; i++) map[i] = br.readLine().toCharArray();
			for (int i = 0; i < N; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
			
			simul();
			System.out.println("#" + t + " " + dist[N - 1][N - 1]);
		}
	}
	
	static void simul() {
		Queue<Node> pq = new PriorityQueue<>();
		
		pq.add(new Node(0, 0, 0));
		dist[0][0] = 0;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (cur.val > dist[cur.x][cur.y]) continue; 
			
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

				int nval = cur.val + (map[nx][ny] - '0');
				
				if (dist[nx][ny] <= nval) continue;
				
				dist[nx][ny] = nval;
				pq.add(new Node(nx, ny, nval));
			}
		}
	}

	static class Node implements Comparable<Node> {
		int x; int y; int val;
		
		public Node(int x, int y, int val) {
			this.x = x;
			this.y = y;
			this.val = val;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.val - o.val;
		}
	}
}