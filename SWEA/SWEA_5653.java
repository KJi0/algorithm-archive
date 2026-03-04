package SWEA;

/*
 * SWEA 5653: 줄기세포배양
 * 개어렵네 ㅁ~ㅊ;
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5653 {
	static int N, M, K;
	static int[][] map;
	static Queue<Node> q;
	static Queue<Node> pq;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[350][350];
			q = new LinkedList<>();
			pq = new PriorityQueue<>();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int val = Integer.parseInt(st.nextToken());
					
					if (val > 0) {
						map[i + 150][j + 150] = val;
						q.add(new Node(i + 150, j + 150, val, 0));
					}
				}
			}
			
			System.out.print("#" + t + " ");
			bfs();
		}
	}
	
	static void bfs() {
		while (K-- > 0) {
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				Node cur = q.poll();
				cur.t++;
				
				if (cur.t == cur.val + 1) {
					
					for (int d = 0; d < 4; d++) {
						int nx = cur.x + dx[d];
						int ny = cur.y + dy[d];
						
						if (map[nx][ny] == 0) pq.add(new Node(nx, ny, cur.val, 0));
					}
				}
				
				if (cur.t < cur.val * 2) q.add(cur); 
			}
			
			while (!pq.isEmpty()) {
				Node cur = pq.poll();
				
				if (map[cur.x][cur.y] == 0) {
					map[cur.x][cur.y] = cur.val;
					q.add(cur);
				}
			}
		}
		System.out.println(q.size());
	}

	static class Node implements Comparable<Node> {
		int x; int y; int val; int t;
		
		public Node(int x, int y, int val, int t) {
			this.x = x;
			this.y = y;
			this.val = val;
			this.t = t;
		}
		
		@Override
		public int compareTo(Node o) {
			return o.val - this.val;
		}
	}
}