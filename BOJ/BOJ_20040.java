package BOJ;

/*
 * BOJ 20040: 사이클 게임
 * 같은 그룹인지 (두 정점이 이미 연결되어 있는지) 확인하는 문제 -> union-find
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20040 {
	static int N, M;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N];
		for (int i = 0; i < N; i++) parent[i] = i;
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (find(a) == find(b)) {
				System.out.println(i);
				return;
			}
			
			union(a, b);
		}
		
		System.out.println(0);
		
	}
	
	static int find(int x) {
		if (parent[x] == x) return x;
		else return find(parent[x]);
	}
	
	static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if (pa != pb) parent[pb] = pa;
	}
}