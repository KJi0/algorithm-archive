

/*
 * SWEA 3124: 최소 스패닝 트리 [프림]
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_3124_p {
	static int V, E;
	static List<Edge>[] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			graph = new List[V + 1];
			for (int i = 1; i <= V; i++) graph[i] = new ArrayList<>();
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken());
				
				graph[A].add(new Edge(B, C));
				graph[B].add(new Edge(A, C));
			}

			System.out.println("#" + t + " " + prim());
		}
	}
	
	static long prim() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[V + 1];
		
		pq.add(new Edge(1, 0));
		
		long cost = 0;
		int cnt = 0;
		
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if (visited[cur.to]) continue;
			
			visited[cur.to] = true;
			cost += cur.weight;
			cnt++;
			
			if (cnt == V) break;
			
			for (Edge next : graph[cur.to]) {
				if (!visited[next.to]) {
					pq.add(next);
				}
			}
		}
		return cost;
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