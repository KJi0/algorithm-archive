

/*
 * SWEA 1251: 하나로 [크루스칼]
 * prim은 정점 중심, kruskal은 간선 중심
 * 간선이 많을 때는 prim이 유리
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1251_k {
	static int N;
	static int[] x, y, parent;
	static double E;
	static List<Edge> edge;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			x = new int[N];
			y = new int[N];
			parent = new int[N];
			
			for (int i = 0; i < N; i++) parent[i] = i;

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) x[i] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) y[i] = Integer.parseInt(st.nextToken());
			
			E = Double.parseDouble(br.readLine());
			edge = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					long dx = Math.abs(x[i] - x[j]);
					long dy = Math.abs(y[i] - y[j]);
					edge.add(new Edge(i, j, dx * dx + dy * dy));
				}
			}
			
			Collections.sort(edge);
			
			long ans = kruskal();
			System.out.println("#" + t + " " + ans);
		}
	}
	
	static long kruskal() {
		long cost = 0;
		
		for (Edge cur : edge) {
			if (find(cur.from) != find(cur.to)) {
				cost += cur.val;
				union(cur);
			}
		}
		
		return Math.round(E * cost);
	}
	
	static void union(Edge cur) {
		int a = find(cur.from);
		int b = find(cur.to);
		
		if (a < b) parent[b] = a;
		else parent[a] = b;
	}
	
	static int find(int x) {
		if (parent[x] == x) return x;
		else return(find(parent[x]));
	}
	
	static class Edge implements Comparable<Edge> {
		int from; int to; long val;
		
		public Edge(int from, int to, long val) {
			this.from = from;
			this.to = to;
			this.val = val;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.val, o.val);
		}
	}
}
