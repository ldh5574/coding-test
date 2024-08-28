package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj11722 {

    public static long dap = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int arr[] = new int[n+1];
        for(int i=1; i<=n; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
        }



        int dp[] = new int[n+1];
        Arrays.fill(dp, 1);
        int max = 1;

        // dp[i] = "i번째 숫자를 기준으로, 왼쪽에 나보다 큰 연속된 수, 최대 몇개냐?"
        //   10 30 10 20 80 10
        //    1  1  2  2  2  3
        // 두번째인 30부터 돌면서 왼쪽에 한놈 체크해보자
        // (10) > 30 인지 체크, dp[i] 계속 유지
        // 세번째인 10 체크해볼껀데 왼쪽에 두놈 있네?
        // (10, 30) > 10, 한번은 유지했다가, 30>10 만났을때 +1되서 2되겠네
        // 네번째인 20 체크해볼껀데 왼쪽에 세놈있네?
        // (10, 30, 10) > 20, 30>20 만났을때 +1되서 2 되겠네
        // (다섯번째 스킵)
        // 여섯번째 10 체크해볼껀데, 왼쪽에 다섯놈있네?
        // (10, 30, 10, 20, 80) > 10, 30>10 만났을때 2되고, 20>10 만났을때 3되겠네
        for(int i=2;i<=n;i++) {         // 기준값
            for(int j=1;j<=i-1;j++) {     // 이전값들
                if(arr[j] > arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
                // System.out.print(dp[i] + " ");
            }
            // System.out.println();
            max = Math.max(max, dp[i]);
        }

        for(int i=1; i<=n; i++) {
            // System.out.println("dp[i] = " + i + " " + dp[i]);
        }

        dap = max;

        bw.write(String.valueOf(dap) + "\n");
        bw.flush();
        bw.close();
        return;
    }
}




