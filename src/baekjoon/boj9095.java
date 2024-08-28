package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class boj9095 {

    public static long dap = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        // 1은 (1) 1가지
        // 2는 (2), (1+1) 2가지
        // 3은 (1+1+1), (1+2), (2+1), (3) 4가지
        // 4는 테케처럼 7가지

        // 런타임 에러 발생 코드
//        for(int i=0; i<n; i++){
//            int num = Integer.parseInt(br.readLine());
//            int dp[] = new int[num+1];
//            dp[0] = 1;
//            dp[1] = 2;
//            dp[2] = 4;
//            for(int k=3; k<=num; k++){
//                dp[k] = dp[k-3] + dp[k-2] + dp[k-1];
//            }
//            bw.write(String.valueOf(dp[num-1]) + "\n");
//        }

        // 런타임 에러 해결
        int dp[] = new int[11];
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 4;
        for(int i=0; i<n; i++){
            int num = Integer.parseInt(br.readLine());
            for(int k=3; k<=num; k++){
                dp[k] = dp[k-3] + dp[k-2] + dp[k-1];
            }
            bw.write(String.valueOf(dp[num-1]) + "\n");
        }

        // bw.write(String.valueOf(dap) + "\n");
        bw.flush();
        bw.close();
        return;
    }
}




