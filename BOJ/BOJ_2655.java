package BOJ;

/*
 * BOJ 2655: 가장높은탑쌓기
 * 역추적 좀 더 공부하기
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2655 {
	static int N;
	static List<Node> brick;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N];
		int[] ans = new int[N];
		
		brick = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int b = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			brick.add(new Node(b, h, w, i + 1));
		}
		
		Collections.sort(brick);
		
		for (int i = 0; i < N; i++) {
			dp[i] = brick.get(i).h;
			ans[i] = -1;
			
			for (int j = 0; j < i; j++) {
				if (brick.get(i).w < brick.get(j).w) {
					if (dp[i] < dp[j] + brick.get(i).h) {
						dp[i] = dp[j] + brick.get(i).h;
						ans[i] = j;
					}
				}
			}
		}
		
		int max = 0, idx = 0;
		for (int i = 0; i < N; i++) {
			if (dp[i] > max) {
				max = dp[i];
				idx = i;
			}
		}
		
		List<Integer> result = new ArrayList<>();
		
		while (idx != -1) {
			result.add(idx);
			idx = ans[idx];
		}
		
		System.out.println(result.size());
		
		for (int i = 0; i < result.size(); i++) {
			int cur = result.get(i);
			System.out.println(brick.get(cur).idx);
		}
	}

	static class Node implements Comparable<Node> {
		int b; int h; int w; int idx;
		
		public Node(int b, int h, int w, int idx) {
			this.b = b;
			this.h = h;
			this.w = w;
			this.idx = idx;
		}
		
		@Override
		public int compareTo(Node o) {
			return o.b - this.b;
		}
	}
}