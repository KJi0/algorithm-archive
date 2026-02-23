package BOJ;

/*
 * BOJ 7576: 토마토
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576 {
	static int M, N;
	static int[][] box;
	static Queue<Node> tomato;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken()); // 열
		N = Integer.parseInt(st.nextToken()); // 행
		box = new int[N][M];
		tomato = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
			
				if (box[i][j] == 1) tomato.add(new Node(i, j, 0));
			}
		}
		
		bfs();
	}

	static void bfs() {
		int cnt = 0;
		
		while (!tomato.isEmpty()) {
			Node cur = tomato.poll();
			cnt = cur.cnt;
			
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if (box[nx][ny] != 0) continue;
				
				box[nx][ny] = 1;
				tomato.add(new Node(nx, ny, cur.cnt + 1));
			}
		}
		
		if (check()) System.out.println(cnt);
		else System.out.println(-1);
	}
	
	static boolean check() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (box[i][j] == 0)
					return false;
			}
		}
		return true;
	}
	
	static class Node {
		int x; int y; int cnt;
		
		public Node(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
}