package SWEA;

/*
 * SWEA 5658: 보물상자 비밀번호
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA_5658 {
	static int N, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			Set<Integer> set = new HashSet<>();
			String str = br.readLine();
			int size = N / 4;
			
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < 4; j++) {
					int start = j * size;
					int end = start + size;
					
					String sub = str.substring(start, end);
					
					int num = Integer.parseInt(sub, 16);
					set.add(num);
				}
				
				str = str.charAt(N - 1) + str.substring(0, N - 1);
			}
			
			List<Integer> list = new ArrayList<>(set);
			Collections.sort(list, Collections.reverseOrder());
			
			int ans = list.get(K - 1);
			
			System.out.println("#" + t + " " + ans);
		}
	}
}