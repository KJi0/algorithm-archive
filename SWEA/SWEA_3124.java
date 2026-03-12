

/*
 * SWEA 3124: 최소 스패닝 트리 [크루스칼]
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_3124 {
	static int V, E;
	static int[] parent;
	static List<Edge> edge;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			parent = new int[V + 1];
			edge = new ArrayList<>();
			
			for (int i = 1; i <= V; i++) parent[i] = i;
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken());
				
				edge.add(new Edge(A, B, C));
			}
			
			Collections.sort(edge);

			System.out.print("#" + t + " ");
			kruskal();
		}
	}
	
	static void kruskal() {
		long cost = 0;
		
		for (Edge cur : edge) {
			if (find(cur.from) != find(cur.to)) {
				cost += cur.weight;
				union(cur.from, cur.to);
			}
		}
		
		System.out.println(cost);
	}
	
	static void union(int from, int to) {
		int a = find(from);
		int b = find(to);
		
		if (a < b) parent[b] = a;
		else parent[a] = b;
	}
	
	static int find(int x) {
		if (parent[x] == x) return x;
		else return find(parent[x]);
	}

	static class Edge implements Comparable<Edge> {
		int from; int to; int weight;
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
}