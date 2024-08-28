package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class boj11726 {

    public static long dap = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        // 런타임 에러 해결
        int dp[] = new int[n+1];
        dp[0] = 1;
        dp[1] = 2;
        for(int i=2; i<n; i++){
            dp[i] = (dp[i-2] + dp[i-1]) % 10007;
        }

        dap = dp[n-1] % 10007;

        bw.write(String.valueOf(dap) + "\n");
        bw.flush();
        bw.close();
        return;
    }
}




