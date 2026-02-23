import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_1227 {
	static char[][] map;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t = 1; t <= 10; t++) {
			br.readLine();
			
			map = new char[100][100];
			int startX = 0, startY = 0;
			
			for (int i = 0; i < 100; i++) {
				map[i] = br.readLine().toCharArray();
				
				for (int j = 0; j < 100; j++) {
					if (map[i][j] == '2') {
						startX = i;
						startY = j;
					}
				}
			}
			
			System.out.println("#" + t + " " + bfs(startX, startY));
		}
	}
	
	static int bfs(int x, int y) {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[100][100];
		
		q.add(new Node(x, y));
		visited[x][y] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			if (map[cur.x][cur.y] == '3') return 1;
			
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				if (nx < 0 || nx >= 100 || ny < 0 || ny >= 100) continue;
				if (map[nx][ny] == '1' || visited[nx][ny]) continue;
				
				visited[nx][ny] = true;
				q.add(new Node(nx, ny));
			}
		}
		
		return 0;
	}
	
	static class Node {
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
