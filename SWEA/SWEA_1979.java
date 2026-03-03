package SWEA;

/*
 * SWEA 1979: 어디에 단어가 들어갈 수 있을까
 * 
 * 정확히 K 길이 연속 구간 세기 (씨앗/활주로/집 짓기류)
 * 가로/세로 각각 검사, 연속된 값 세어 정확히 K인지 확인, K 이상이면 X
 * 
 * 공통 풀이 패턴:
 * cnt = 0;
 * for (0~N) {
 * 	if (조건) cnt++
 * 	else if (cnt == K) ans++, cnt = 0
 * 	마지막에 한 번 더 체크
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1979 {
	static int N, K, ans;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			ans = 0;
			
			// 가로
			for (int i = 0; i < N; i++) simul(map[i]);
			
			// 세로
			for (int i = 0; i < N; i++) {
				int[] col = new int[N];
				
				for (int j = 0; j < N; j++) col[j] = map[j][i];
				simul(col);
			}
			
			System.out.println("#" + t + " " + ans);
		}
	}

	static void simul(int[] arr) {
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			if (arr[i] == 1) {
				cnt++;
			}
			
			else {
				if (cnt == K) ans++;
				cnt = 0;
			}
		}
		
		if (cnt == K) ans++;
	}
}
