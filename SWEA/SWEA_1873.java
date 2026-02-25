package SWEA;

/*
 * SWEA 1873: 상호의 배틀필드
 * class 새로 만드는 것에 집착하지 말자~~
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1873 {
	static int H, W;
	static char[][] map;
	static int tx, ty, dir;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken()); // 행
			W = Integer.parseInt(st.nextToken()); // 열
			
			map = new char[H][W];
			
			for (int i = 0; i < H; i++) {
				map[i] = br.readLine().toCharArray();
				
				for (int j = 0; j < W; j++) {
					if (map[i][j] == '^') { tx = i; ty = j; dir = 0; }
					if (map[i][j] == 'v') { tx = i; ty = j; dir = 1; }
					if (map[i][j] == '<') { tx = i; ty = j; dir = 2; }
					if (map[i][j] == '>') { tx = i; ty = j; dir = 3; }
				}
			}
		
			int N = Integer.parseInt(br.readLine());
			char[] order = new char[N];
			
			order = br.readLine().toCharArray();
			
			for (int i = 0; i < N; i++) {
				if (order[i] == 'S') shoot();
				else move(order[i]);
			}
			
			System.out.print("#" + t + " ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
	}
	
	static void shoot() {
		int nx = tx;
		int ny = ty;
		
		while (true) {
			nx += dx[dir];
			ny += dy[dir];
			
			if (nx < 0 || nx >= H || ny < 0 || ny >= W) break;
			
			if (map[nx][ny] == '*') {
				map[nx][ny] = '.';
				break;
			}
			
			if (map[nx][ny] == '#') break;
		}
	}

	static void move(char order) {
		char d = changeDir(order);
		map[tx][ty] = d;
		
		int nx = tx + dx[dir];
		int ny = ty + dy[dir];
		
		if (nx >= 0 && nx < H && ny >= 0 && ny < W) {
			if (map[nx][ny] == '.') {
				map[tx][ty] = '.';
				map[nx][ny] = d;
				tx = nx; ty = ny;
			}
		}
	}
	
	static char changeDir(char order) {
		if (order == 'U') { dir = 0; return '^'; }
		if (order == 'D') { dir = 1; return 'v'; }
		if (order == 'L') { dir = 2; return '<'; }
		if (order == 'R') { dir = 3; return '>'; }
		return 0;
	}
}