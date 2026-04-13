package SWEA;

/*
 * SWEA 1953: 탈주범 검거
 * 
 * isValid에서 조건을 하나하나 따져야 하나 했는데...
 * opp 배열 하나만 두면 간단해진다는 것 잊지 말기!
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1953 {
	static int N, M, R, C, L, cnt;
	static int[][] map;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[] opp = {1, 0, 3, 2};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			cnt = 1;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			bfs();
			System.out.println("#" + t + " " + cnt);
		}
	}

	static void bfs() {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		
		q.add(new Node(R, C));
		visited[R][C] = true;
		
		int t = 1;
		
		while (!q.isEmpty()) {
			if (t == L) break;
			
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				Node cur = q.poll();
				int[] dirs = getDir(map[cur.x][cur.y]);
				
				for (int d : dirs) {
					int nx = cur.x + dx[d];
					int ny = cur.y + dy[d];
					
					if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
					if (visited[nx][ny] || map[nx][ny] == 0) continue;
					
					if (isValid(d, map[nx][ny])) {
						visited[nx][ny] = true;
						q.add(new Node(nx, ny));
						cnt++;
					}
				}
			}
			
			t++;
		}
	}
	
	static int[] getDir(int type) {
		if (type == 1) return new int[] {0, 1, 2, 3};
		if (type == 2) return new int[] {0, 1};
		if (type == 3) return new int[] {2, 3};
		if (type == 4) return new int[] {0, 3};
		if (type == 5) return new int[] {1, 3};
		if (type == 6) return new int[] {1, 2};
		else return new int[] {0, 2};
	}
	
	static boolean isValid(int curD, int next) {
		int[] dirs = getDir(next);
		
		for (int d : dirs) {
			if (d == opp[curD])
				return true;
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