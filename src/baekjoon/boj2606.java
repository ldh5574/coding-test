package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class boj2606 {

    public static long dap = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] g = new int[n][n];
        int v[] = new int[n];
        for(int i=0; i<m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            g[a-1][b-1] = 1;
            g[b-1][a-1] = 1;
        }

        int pos = 0;
//        v[pos] = 1;
        int count = 0;
        dfs(n, m, g, v, pos);

        bw.write(String.valueOf(dap-1) + "\n");
        bw.flush();
        bw.close();
        return;
    }

    public static void dfs(int n, int m, int[][] g, int[] v,int pos) {

        v[pos] = 1;
        dap++;

        for(int i=0; i<n; i++) {   // 연결되어있고 미방문 지점 찾아서 넘겨주자
            if(g[pos][i] == 1 && v[i] == 0) {
                // System.out.println("g[pos][i] = " + pos + " " + i + " " + g[pos][i]);
                dfs(n, m, g, v, i);
            }
        }

    }
}




