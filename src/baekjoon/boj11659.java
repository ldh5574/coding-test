package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class boj11659 {

    public static long dap = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] dp = new int[n+1];  // 누적합
        for(int i=1; i<=n; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(i==1){
                dp[i] = num;
                continue;
            }
            dp[i] = dp[i-1] + num;
        }

        for(int i=1; i<=m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            bw.write(String.valueOf(dp[b]-dp[a-1]) + "\n");
        }

        // bw.write(String.valueOf(dap-1) + "\n");
        bw.flush();
        bw.close();
        return;
    }
}




