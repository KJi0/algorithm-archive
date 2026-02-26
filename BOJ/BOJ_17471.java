package BOJ;

/*
 * BOJ 17471: 게리맨더링
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17471 {
	static int N, min = Integer.MAX_VALUE;
	static int[] people;
	static List<Integer>[] graph;
	static boolean[] isAgroup;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[N + 1];
		people = new int[N + 1];
		isAgroup = new boolean[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
		for (int i = 1; i <= N; i++) people[i] = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < n; j++)
				graph[i].add(Integer.parseInt(st.nextToken()));
		}
		
		dfs(1);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}
	
	static void dfs(int idx) {
		if (idx == N + 1) {
			if (!isValid()) return;
			if (!isConnected()) return;
			
			int sumA = 0, sumB = 0;
			
			for (int i = 1; i <= N; i++) {
				if (isAgroup[i]) sumA += people[i];
				else sumB += people[i];
			}
			
			min = Math.min(min, Math.abs(sumA - sumB));
			return;
		}
		
		isAgroup[idx] = true;
		dfs(idx + 1);
		isAgroup[idx] = false;
		dfs(idx + 1);
	}
	
	// 각 구역은 두 선거구 중 하나에 포함되어야 한다
	static boolean isValid() {
		boolean hasA = false;
		boolean hasB = false;
		
		for (int i = 1; i <= N; i++) {
			if (isAgroup[i]) hasA = true;
			else hasB = true;
		}
		
		return hasA && hasB;
	}

	// 한 선거구에 포함되어 있는 구역은 모두 연결되어 있어야 한다
	static boolean isConnected() {
		boolean[] visited = new boolean[N + 1];
		
		// A 그룹
		int startA = -1;
		int cntA = 0;
		
		for (int i = 1; i <= N; i++) {
			if (isAgroup[i]) {
				cntA++;
				startA = i;
			}
		}
		
		int connectedA = bfs(startA, true, visited);
		if (connectedA != cntA) return false;
		
		// B 그룹
		int startB = -1;
		int cntB = 0;
		
		for (int i = 1; i <= N; i++) {
			if (!isAgroup[i]) {
				cntB++;
				startB = i;
			}
		}
		
		int connectedB = bfs(startB, false, visited);
		return connectedB == cntB;
				
	}

	static int bfs(int start, boolean group, boolean[] visited) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		visited[start] = true;
		
		int cnt = 1;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int next : graph[cur]) {
				if (!visited[next] && isAgroup[next] == group) {
					cnt++;
					visited[next] = true;
					q.add(next);
				}
			}
		}
		
		return cnt;
	}

}
