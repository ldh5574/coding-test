package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class boj2748 {

    public static long dap = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        long arr[] = new long[n+1];
        arr[0] = 0;
        arr[1] = 1;
        for(int i=2; i<=n; i++) {
            arr[i] = arr[i-2] + arr[i-1];
            // System.out.print(arr[i] + " ");
        }
        dap = arr[n];



        bw.write(String.valueOf(dap) + "\n");
        bw.flush();
        bw.close();
        return;
    }
}




