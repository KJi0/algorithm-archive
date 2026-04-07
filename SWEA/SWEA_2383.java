package SWEA;

/*
 * SWEA 2383: 점심 식사시간
 * 어렵다 ;
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_2383 {
	static int N, ans;
	static int[][] map;
	static int[] isSelected;
	
	static List<Node> people;
	static Node[] stair;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			stair = new Node[2];
			people = new ArrayList<>();
			
			int stairCnt = 0;
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					if (map[i][j] == 1) people.add(new Node(i, j));
					else if (map[i][j] > 1) stair[stairCnt++] = new Node(i, j);
				}
			}
			
			isSelected = new int[people.size()];
			ans = Integer.MAX_VALUE;
			
			dfs(0);
			System.out.println("#" + t + " " + ans);
		}
	}
	
	static void dfs(int n) {
		if (n == people.size()) {
			ans = Math.min(ans, simul());
			return;
		}
		
		isSelected[n] = 0;
		dfs(n + 1);
		
		isSelected[n] = 1;
		dfs(n + 1);
	}
	
	static int simul() {
		List<Integer>[] arrivalTime = new List[2];
		for (int i = 0; i < 2; i++) arrivalTime[i] = new ArrayList<>();
		
		// 계단별 거리 계산
		for (int i = 0; i < people.size(); i++) {
			int s = isSelected[i];
			Node cur = people.get(i);
			
			int dist = Math.abs(cur.x - stair[s].x) + Math.abs(cur.y - stair[s].y);
			arrivalTime[s].add(dist);
		}
		
		int total = 0;
		
		for (int i = 0; i < 2; i++) {
			List<Integer> list = arrivalTime[i];
			Collections.sort(list);
			
			int len = map[stair[i].x][stair[i].y];
			int[] finish = new int[list.size()];
			
			for (int j = 0; j < list.size(); j++) {
				int arrive = list.get(j) + 1;
				
				if (j < 3)
					finish[j] = arrive + len;
				else
					finish[j] = Math.max(arrive, finish[j - 3]) + len;
			}
			
			if (list.size() > 0)
				total = Math.max(total, finish[list.size() - 1]);
		}
		
		return total;
	}

	static class Node {
		int x; int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
