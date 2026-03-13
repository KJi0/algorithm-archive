package SWEA;

/*
 * SWEA 7793: 오! 나의 여신님
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_7793 {
	static int N, M, time;
	static char[][] map;
	static Queue<Node> devil;
	static Queue<Node> sy;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			
			devil = new LinkedList<>();
			sy = new LinkedList<>();
			
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
				
				for (int j = 0; j < M; j++) {
					if (map[i][j] == '*') devil.add(new Node(i, j));
					if (map[i][j] == 'S') sy.add(new Node(i, j));
				}
			}
			
			time = 1;
			
			System.out.print("#" + t + " ");
			System.out.println(simul() == -1 ? "GAME OVER" : time);
		}
	}
	
	static int simul() {
		while (!sy.isEmpty()) {
			int size = devil.size();
			
			for (int i = 0; i < size; i++) {
				Node cur = devil.poll();
				
				for (int d = 0; d < 4; d++) {
					int nx = cur.x + dx[d];
					int ny = cur.y + dy[d];
					
					if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				
					if (map[nx][ny] == '.') {
						map[nx][ny] = '*';
						devil.add(new Node(nx, ny));
					}
				}
			}
			
			size = sy.size();
			
			for (int i = 0; i < size; i++) {
				Node cur = sy.poll();
				
				for (int d = 0; d < 4; d++) {
					int nx = cur.x + dx[d];
					int ny = cur.y + dy[d];
					
					if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
					if (map[nx][ny] == 'D') return time + 1;
					
					if (map[nx][ny] == '.') {
						map[nx][ny] = 'S';
						sy.add(new Node(nx, ny));
					}
				}
			}
			
			time++;
		}
		return -1;
	}
	
	static class Node {
		int x; int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
