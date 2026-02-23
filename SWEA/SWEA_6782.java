package SWEA;

/*
 * SWEA 6782: 현주가 좋아하는 제곱근 놀이
 * 횟수가 최소가 되려면 정수가 되는 다음 제곱근으로 점프하기
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_6782 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			long N = Long.parseLong(br.readLine());
			int cnt = 0;

			while (N > 2) {
				long n = (long)Math.sqrt(N);
				
				if (n * n == N) {
					N = n;
					cnt++;
				}
				
				else {
					cnt += (n + 1) * (n + 1) - N; // N + 1 연산들
					N = (n + 1) * (n + 1);
				}
			}
			
			System.out.println("#" + t + " " + cnt);
		}
	}
}