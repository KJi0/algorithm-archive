package BOJ;

/*
 * BOJ 1753: 최단경로
 * 다익스트라는 PQ + 인접리스트
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1753 {
	static int V, E, K;
	static int[] dist;
	static List<Edge>[] edge;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		
		dist = new int[V + 1];
		edge = new List[V + 1];
	
		Arrays.fill(dist, Integer.MAX_VALUE);
		for (int i = 1; i <= V; i++) edge[i] = new ArrayList<>();
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			edge[u].add(new Edge(v, w));
		}
		
		dijkstra();
		
		for (int i = 1; i <= V; i++) {
			if (i == K) dist[i] = 0;
			
			if (dist[i] == Integer.MAX_VALUE) System.out.println("INF");
			else System.out.println(dist[i]);
		}
	}
	
	static void dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		pq.add(new Edge(K, 0));
		dist[K] = 0;
		
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if (cur.weight > dist[cur.to]) continue; 
			
			for (Edge next : edge[cur.to]) {
				if (next.weight + cur.weight < dist[next.to]) {
					dist[next.to] = next.weight + cur.weight;
					pq.add(new Edge(next.to, dist[next.to]));
				}
			}
		}
	}

	static class Edge implements Comparable<Edge> {
		int to; int weight;
		
		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
}
