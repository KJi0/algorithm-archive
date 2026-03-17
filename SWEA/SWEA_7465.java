package SWEA;

/*
 * SWEA 7465: 창용 마을 무리의 개수
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_7465 {
	static int N, M;
	static List<Integer>[] person;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			person = new List[N + 1];
			visited = new boolean[N + 1];
			
			for (int i = 1; i <= N; i++) person[i] = new ArrayList<>();
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				person[from].add(to);
				person[to].add(from);
			}
			
			int cnt = 0;
			
			for (int i = 1; i <= N; i++) {
				if (!visited[i]) {
					cnt += simul(i);
				}
			}
			
			System.out.println("#" + t + " " + cnt);
		}
	}
	
	static int simul(int x) {
		Queue<Integer> q = new LinkedList<>();
		
		q.add(x);
		visited[x] = true;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int next : person[cur]) {
				if (!visited[next]) {
					visited[next] = true;
					q.add(next);
				}
			}
		}
		
		return 1;
	}
}