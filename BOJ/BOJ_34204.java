package BOJ;

/*
 * BOJ 34204: 가방
 * 어렵다~...
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_34204 {
	static int N, K, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		int[] A = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(A);
		
		long kb = 0L;
		long sh = 0L;
		
		for (int i = 0; i < Math.min(K, N); i++) kb += A[i];
		
		
		int l = 0; int r = K - 1;
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= C; i++) {
			if (sh + A[l] <= i) {
				if (r - l + 1 == K && r + 1 < N) {
					sh += A[l];
					kb -= A[l++];
					kb += A[++r];
				}
			}
			
			sb.append(kb).append('\n');
		}
		
		System.out.println(sb);
	}
}