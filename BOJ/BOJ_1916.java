/*
 * BOJ 1916: 최소비용 구하기
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1916 {
	static int N, M, S, E;
	static int[] dist;
	static List<Edge>[] edge;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);

		edge = new List[N + 1];
		for (int i = 1; i <= N; i++) edge[i] = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			
			edge[from].add(new Edge(to, val));
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		dijkstra();
		System.out.println(dist[E]);
	}
	
	static void dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		pq.add(new Edge(S, 0));
		dist[S] = 0;
		
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if (cur.val > dist[cur.to]) continue;
			
			for (Edge next : edge[cur.to]) {
				if (cur.val + next.val < dist[next.to]) {
					dist[next.to] = cur.val + next.val;
					pq.add(new Edge(next.to, dist[next.to])); // dist 누적 잊지 말기
				}
			}
		}
	}

	static class Edge implements Comparable<Edge> {
		int to; int val;
		
		public Edge(int to, int val) {
			this.to = to;
			this.val = val;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.val - o.val;
		}
	}
}