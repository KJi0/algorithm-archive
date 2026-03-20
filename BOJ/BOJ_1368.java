package BOJ;

/*
 * BOJ 1368: 물대기
 * MST에서 직접 연결 vs 다른 선택 구조이면 새로운 간선으로 추가
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1368 {
	static int N;
	static int[] parent;
	static List<Edge> edge;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		parent = new int[N + 1];
		edge = new ArrayList<>();
		
		for (int i = 0; i <= N; i++) parent[i] = i;
		
		for (int i = 1; i <= N; i++)
			edge.add(new Edge(0, i, Integer.parseInt(br.readLine())));
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 1; j <= N; j++) {
				int val = Integer.parseInt(st.nextToken());
				
				if (i >= j) continue;
				edge.add(new Edge(i, j, val));
			}
		}
		
		Collections.sort(edge);
		
		int ans = kruskal();
		System.out.println(ans);
	}
	
	static int kruskal() {
		int cost = 0;
		
		for (Edge cur : edge) {
			if (find(cur.from) != find(cur.to)) {
				cost += cur.val;
				union(cur);
			}
		}
		
		return cost;
	}
	
	static int find(int x) {
		if (parent[x] == x) return x;
		else return find(parent[x]);
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
