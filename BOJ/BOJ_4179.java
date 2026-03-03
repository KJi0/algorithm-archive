package BOJ;

/*
 * BOJ 4179: 불!
 * 
 * [확산 + 시간 비교 BFS]
 * 1. "확산 도착 시간 먼저" 계산
 * 2. 이동 BFS에서 시간 비교
 * 3. 확산 <= dist + 1이면 이동 불가
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_4179 {
	static int R, C;
	static char[][] map;
	static int[][] fire, dist;
	static Queue<Node> fq = new LinkedList<>();
	static Queue<Node> q = new LinkedList<>();
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		fire = new int[R][C];
		dist = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			Arrays.fill(fire[i], -1);
			Arrays.fill(dist[i], -1);
			
			map[i] = br.readLine().toCharArray();
			
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'J') { 
					q.add(new Node(i, j));
					dist[i][j] = 0;
				}
				
				if (map[i][j] == 'F') {
					fq.add(new Node(i, j));
					fire[i][j] = 0;
				}
			}
		}
		
		simul();
	}
	
	static void simul() {
		// 불 확산
		while (!fq.isEmpty()) {
			Node cur = fq.poll();
			
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
				
				if (map[nx][ny] == '#') continue;
				if (fire[nx][ny] != -1) continue;
				
				fire[nx][ny] = fire[cur.x][cur.y] + 1; 
				fq.add(new Node(nx, ny));
			}
		}
		
		// 지훈 이동
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
					System.out.println(dist[cur.x][cur.y] + 1);
					return;
				}
				
				if (map[nx][ny] == '#') continue;
				if (dist[nx][ny] != -1) continue; // 이미 방문
				
				if (fire[nx][ny] != -1 && fire[nx][ny] <= dist[cur.x][cur.y] + 1) continue;
				
				dist[nx][ny] = dist[cur.x][cur.y] + 1;
				q.add(new Node(nx, ny));
			}
		}
		
		System.out.println("IMPOSSIBLE");
	}

	static class Node {
		int x; int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
