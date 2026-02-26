package SWEA;

/*
 * SWEA 2382: 미생물 격리
 * 객체 2차원 배열은 처음 써 보는데 기억해 두자
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_2382 {
	static int N, M, K;
	static Queue<Cluster> q;
	
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
			
			q = new LinkedList<>();
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1;
				
				q.add(new Cluster(x, y, cnt, dir, cnt));
			}
			
			System.out.print("#" + t + " ");
			simul();
		}
	}
	
	static void simul() {
		while (M-- > 0) {
			Cluster[][] map = new Cluster[N][N];
			int size = q.size();
			
			for (int k = 0; k < size; k++) {
				Cluster cur = q.poll();
				
				int nx = cur.x + dx[cur.dir];
				int ny = cur.y + dy[cur.dir];
				int ncnt = cur.cnt;
				int ndir = cur.dir;
				
				if (nx == 0 || nx == N - 1 || ny == 0 || ny == N - 1) {
					ncnt /= 2;
					ndir = (cur.dir % 2 == 0) ? cur.dir + 1 : cur.dir - 1;
					
					if (ncnt == 0) continue;
				}
				
				if (map[nx][ny] == null) map[nx][ny] = new Cluster(nx, ny, ncnt, ndir, ncnt);
				
				else {
					map[nx][ny].cnt += ncnt;
					
					if (ncnt > map[nx][ny].max) {
						map[nx][ny].max = ncnt;
						map[nx][ny].dir = ndir;
					}
				}
			}
			
			// 동시간대만 확인해야 하므로 매 턴마다 초기화
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] != null)
						q.add(map[i][j]);
				}
			}
		}
		
		int ans = getSum();
		System.out.println(ans);
	}
	
	static int getSum() {
		int sum = 0;
		
		while (!q.isEmpty()) {
			Cluster cur = q.poll();
			
			sum += cur.cnt;
		}
		
		return sum;
	}
	
	static class Cluster {
		int x; int y; int cnt; int dir; int max;
		
		public Cluster(int x, int y, int cnt, int dir, int max) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.dir = dir;
			this.max = max;
		}
	}
}
