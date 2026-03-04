package BOJ;

/*
 * BOJ 7569: 토마토
 * dz는 처음이네...
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7569 {
	static int M, N, H, day;
	static int[][][] box;
	static Queue<Node> tomato = new LinkedList<>();
	
	static int[] dx = {-1, 1, 0, 0, 0, 0};
	static int[] dy = {0, 0, -1, 1, 0, 0};
	static int[] dz = {0, 0, 0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken()); // 열
		N = Integer.parseInt(st.nextToken()); // 행
		H = Integer.parseInt(st.nextToken()); // 높이
		
		box = new int[N][M][H];
		day = 0;
		
		for (int k = 0; k < H; k++) {
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					box[i][j][k] = Integer.parseInt(st.nextToken());
				
					if (box[i][j][k] == 1) tomato.add(new Node(i, j, k, 0));
				}
			}
		}
		
		bfs();
		isRemain();
	}
	
	static void bfs() {
		while (!tomato.isEmpty()) {
			Node cur = tomato.poll();
			day = cur.day;
			
			for (int d = 0; d < 6; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				int nz = cur.z + dz[d];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || nz < 0 || nz >= H) continue;
				
				if (box[nx][ny][nz] != 0) continue;
				
				box[nx][ny][nz] = 1;
				tomato.add(new Node(nx, ny, nz, cur.day + 1));
			}
		}
	}
	
	static void isRemain() {
		for (int k = 0; k < H; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (box[i][j][k] == 0) {
						System.out.println(-1);
						return;
					}
				}
			}
		}
		System.out.println(day);
	}
	
	static class Node {
		int x; int y; int z; int day;
		
		public Node(int x, int y, int z, int day) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.day = day;
		}
	}
}