pacakge BOJ;

/*
 * BOJ 3109: 빵집
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3109 {
	static int R, C, cnt;
	static char[][] map;
	static boolean[][] visited;

	static int[] dx = {-1, 0, 1};
	static int[] dy = {1, 1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visited = new boolean[R][C];
		
		for (int i = 0; i < R; i++) map[i] = br.readLine().toCharArray();
		
		cnt = 0;
		
		for (int i = 0; i < R; i++) if(dfs(i, 0)) cnt++;
		System.out.println(cnt);
	}
	
	static boolean dfs(int r, int c) {
		if (c == C - 1) return true;
		
		for (int d = 0; d < 3; d++) {
			int nx = r + dx[d];
			int ny = c + dy[d];
			
			if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
			if (map[nx][ny] == 'x' || visited[nx][ny]) continue;
			
			visited[nx][ny] = true;
			
			if (dfs(nx, ny)) return true; // 이거 해 줘야 됨
		}
		
		return false;
	}

	static class Node {
		int x; int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}