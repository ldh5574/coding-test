import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

public class Main {

    public static long dap = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

//         n=1, 1 = 1개
//         n=2, 10 = 1개
//         n=3, 100 + 101 = 2개
//         n=4, (100 0 + 101 0) + (10 01) = 3개 = (n=3인경우+0) + (n=2인경우+01)
//         n=5, (1000 0 + 1010 0 + 1001 0) + (100 01 + 101 01) = 5개 = (n=4인경우+0) + (n=3인경우+01)
//         ... ...

        long[] dp = new long[n+2];  // 누적합
        dp[1] = 1;
        dp[2] = 1;
        for(int i=3; i<=n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        dap = dp[n];
        bw.write(String.valueOf(dap) + "\n");

        // bw.write(String.valueOf(dap-1) + "\n");
        bw.flush();
        bw.close();
        return;
    }
}




