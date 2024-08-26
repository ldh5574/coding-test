import java.io.*;
import java.util.*;

public class Main {

    public static long dap = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int arr[] = new int[n+2];
        for(int i=1; i<n+1; i++){
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
        }

        // 답은 맞지만, 시간초과 발생
//        for(int i=0; i<n; i++){
//            for(int j=i+1; j<n; j++){
//                int a = arr[i];
//                int b = arr[j];
//
//                if(a>b){
//                    continue;
//                }
//                // a < b
//                for(int k=j+1; k<n; k++){
//                    int c = arr[k];
//                    if(a > c){
//                        dap++;
//                        // System.out.println(a + " " + b + " " + c);
//                    }
//                }
//            }
//        }

        // 시간초과 해결 DP 구현
        // A > C 인 경우 계산해주기
        // arr[0][4] = "1 보다 오른쪽에 있는 배열 값들에 대해, 4보다 작은 수들의 개수"인 0개*를 저장
        // arr[0][3] = "3 보다 오른쪽에 있는 배열 값들에 대해, 4보다 작은 수들의 개수"인 1개를 저장
        // arr[0][2] = "5 보다 오른쪽에 있는 배열 값들에 대해, 4보다 작은 수들의 개수"인 2개를 저장
        // arr[0][1] = "2 보다 오른쪽에 있는 배열 값들에 대해, 4보다 작은 수들의 개수"인 2개를 저장
        // arr[0][0] = "4 보다 오른쪽에 있는 배열 값들에 대해, 4보다 작은 수들의 개수"인 3개를 저장
        //   4 2 5 3 1
        // 4 3 2 2 1 0개*
        // 2     <- <- <-
        // 5
        // 3
        // 1

        // DP 저장
        int dp[][] = new int[n+2][n+2];
        for(int i=1; i<=n; i++){
            for(int j=n-1; j>=1; j--){
                if(arr[i] > arr[j+1]){
                    dp[i][j] = dp[i][j+1] + 1;
                }else{
                    dp[i][j] = dp[i][j+1];
                }

            }
        }

        // DP 출력
//        for(int i=1; i<=n; i++){
//            for(int j=1; j<=n; j++){
//                if(arr[i] < arr[j]){
//                    System.out.print("+" + dp[i][j]);
//                }
//                else{
//                    System.out.print(" " + dp[i][j]);
//                }
//            }
//            System.out.println();
//        }

        // A < B 더해주기
        for(int i=1; i<=n; i++){
            for(int j=i+1; j<=n; j++){
                if(arr[i] < arr[j]){
                    dap += dp[i][j];
                    // System.out.print("\t" + arr[i] + "," + arr[j] + "=" + dp[i][j]);
                }
            }
            // System.out.println();
        }

        bw.write(String.valueOf(dap));
        bw.flush();
        bw.close();
        return;
    }

}




