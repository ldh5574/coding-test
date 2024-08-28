package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class boj1463 {

    public static long dap = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        // n을 1로 만드는게 아니라,
        // 1부터 바텀업해가면서 n까지 숫자를 만드는데 드는 최소 연산횟수를 dp에 저장해주자.
        long dp[] = new long[n+1];
        dp[0] = 0;
        dp[1] = 0;

        // 1을 만들려면 연산횟수는 0이다. 필요없다
        // 1에서 2를 만들려면 +1하면 된다. 최소연산횟수는 한번
        // 1에서 3을 만들려면 +1+1도 가능하지만, *3하면 된다. 최소연산횟수는 한번
        // 1에서 4를 만들려면 +1+1+1도 가능하지만, *2*2 혹은 *3+1 하면 된다. 최소연산횟수는 두번
        // ...
        // 1에서 9를 만들려면 +1+1+1...도 가능하지만,(3의 dp값)에 *3해서 +1만 해주면 된다! 최소연산횐수 두번
        // 1에서 10을 만들려면 +1+1+1...도 가능하지만, (9의 DP값)에 +1해서, 최소연산횟수는 세번

        //   0 1 2 3 4 5
        // 0 1 2 3 4 5 6
        // 0 0 2 4 6 8 10
        // 0 0 3 6 9 12 15
        for(int i=2; i<=n; i++) {
            // 직전값에서 그냥 덧셈+1해서 한번 카운트 해주는 경우 (이게 최악의 케이스니 일단 dp값 갱신해주자)
            long plusOne = dp[i-1] + 1;
            dp[i] = plusOne;

            // 현재값이 2의 배수인 경우
            if(i%2 == 0){
                // (i/2) 즉 절반의 숫자에 대해서, 곱셈*2해서 i를 만들 수 있으니, 절반숫자dp값에 +1 해주고, plusOne이랑 비교
                long two = dp[i/2] + 1;
                dp[i] = Math.min(dp[i], two);
            }

            // 현재값이 3의 배수인 경우
            if(i%3 == 0){
                // (i/3) 즉 삼등분 숫자에 대해서, 곱셈*3해서 i를 만들 수 있으니, 삼등분숫자dp값에 +1 해주고, 현재dp값이랑 비교
                long three = dp[i/3] + 1;
                dp[i] = Math.min(dp[i], three);
            }

            System.out.println(i + " " + dp[i]);
            // System.out.print(arr[i] + " ");
        }
        dap = dp[n];



        bw.write(String.valueOf(dap) + "\n");
        bw.flush();
        bw.close();
        return;
    }
}




