package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class boj2579 {

    public static long dap = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int arr[] = new int[n+1];
        int dp[] = new int[n+1];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // Arrays.sort(arr);
//        for(int i=0; i<=n; i++){
//            System.out.println("arr[i] = " + arr[i]);
//        }

        // 1번째는 무조건 삽입
        dp[1] = arr[1];

        // 총계단개수 2개 이상이면 2번째 dp배열 초기화
        if(n>=2){
            dp[2] = arr[1] + arr[2];
        }

        // 총계단개수 3개 이상이면
        for(int i=3; i<=n; i++) {
            // 총 계단 개수가 2개 이하면, 반복문 자체도 들어오지 않고, 초기화해둔 값으로 답 출력

            int jump101 = dp[i-2] + 0 + arr[i];
            int jump1011 = dp[i-3] + 0 + arr[i-1] + arr[i];
            dp[i] = Math.max(jump101, jump1011);
            // System.out.println("dp[i] = " + dp[i]);
        }

        dap = dp[n];

        bw.write(String.valueOf(dap) + "\n");
        bw.flush();
        bw.close();
        return;
    }
}




