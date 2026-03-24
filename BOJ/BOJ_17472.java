package BOJ;

/*
 * BOJ 17472: 다리 만들기 2
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17472 {
	static int N, M;
	static int[] parent;
	static int[][] map;
	static List<Edge> edge;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int idx = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					map[i][j] = idx;
					drawMap(i, j, idx);
					idx++;
				}
			}
		}
		
		edge = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0) {
					for (int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
						if (map[nx][ny] != 0) continue;
						
						makeBridge(i, j, d, map[i][j]);
					}
				}
			}
		}
		
		parent = new int[idx];
		for (int i = 2; i < idx; i++) parent[i] = i;
		
		Collections.sort(edge);
		System.out.println(kruskal(idx - 2));
	}
	
	static void drawMap(int x, int y, int idx) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		map[x][y] = idx;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				
				if (map[nx][ny] == 1) {
					map[nx][ny] = idx;
					q.add(new int[] {nx, ny});
				}
			}
		}
	}
	
	static void makeBridge(int x, int y, int d, int idx) {
		int len = 0;
		
		while (true) {
			x += dx[d];
			y += dy[d];
			
			if (x < 0 || x >= N || y < 0 || y >= M) return;
			if (map[x][y] == idx) return;
			
			if (map[x][y] == 0) { len++; continue; }
			
			if (map[x][y] > 0) {
				if (len >= 2) edge.add(new Edge(idx, map[x][y], len));
				return;
			}
		}
	}
	
	static int kruskal(int cnt) {
		int cost = 0;
		int used = 0;
		
		for (Edge cur : edge) {
			if (find(cur.from) != find(cur.to)) {
				cost += cur.val;
				union(cur);
				used++;
			}
		}
		
		return (used == cnt - 1) ? cost : -1;
	}
	
	static int find(int x) {
		if (parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	
	static void union(Edge cur) {
		int a = find(cur.from);
		int b = find(cur.to);
		
		if (a < b) parent[b] = a;
		else parent[a] = b;
	}

	static class Edge implements Comparable<Edge> {
		int from; int to; int val;
		
		public Edge(int from, int to, int val) {
			this.from = from;
			this.to = to;
			this.val = val;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.val - o.val;
		}
	}
}