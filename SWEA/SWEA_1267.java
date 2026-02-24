package SWEA;

/*
 * SWEA 1267: 작업 순서
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1267 {
	static int V, E;
	static ArrayList<Integer>[] graph;
	static int[] indegree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t = 1; t <= 10; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList[V + 1];
			indegree = new int[V + 1];
			
			for (int i = 1; i <= V; i++) graph[i] = new ArrayList<>();

			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < E; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				graph[from].add(to);
				indegree[to]++;
			}
			
			System.out.print("#" + t + " ");
			bfs();
			System.out.println();
		}
	}

	static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		
		for (int i = 1; i <= V; i++) {
			if (indegree[i] == 0) q.add(i);
		}
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			System.out.print(cur + " ");
			
			for (int node : graph[cur]) {
				indegree[node]--;

				if (indegree[node] == 0) q.add(node);
			}
		}
	}
}