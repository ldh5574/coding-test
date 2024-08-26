package softeer;

import java.io.*;
import java.util.StringTokenizer;

public class 택배마스터광우2 {

    public static int dap = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int line[] = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            line[i] = Integer.parseInt(st.nextToken());
        }

        int order[] = new int[n];
        int v[] = new int[n];
        sol(n, m, k, line, order, v, 0);

        bw.write(String.valueOf(dap));
        bw.flush();
        bw.close();
        return;
    }

    public static void sol(int n, int m, int k, int[] line, int[] order, int[] v, int count){

        if(count == n){
            // for(int i=0; i<n; i++){
            //     System.out.print(order[i] + " ");
            // }
            dap = Math.min( dap, getSum(n, m, k, order) );
            // System.out.println( " => " + dap);
        }

        for(int i=0; i<n; i++){
            if(v[i] == 1){
                continue;
            }
            else{
                int pickLine = line[i];
                order[count] = pickLine;
                v[i] = 1;
                sol(n, m, k, line, order, v, count+1);
                v[i] = 0;
            }

        }
    }

    public static int getSum(int n, int m, int k, int w[]){

        int sum = 0;
        int i = 0;
        int bag = 0;
        int bagCnt = 1;

        while(bagCnt <= k){
            if(i>=n){
                i = 0;
            }
            // System.out.println(sum + " " + i + " " + bag + " " + bagCnt);
            if(bag + w[i] <= m){    // 더 담을 수 있다면
                bag += w[i];
                sum += w[i];
                i++;
                continue;
            }
            else{  //  if(bag + w[i] > m){    // 더 못담으면
                if(bagCnt == k){
                    return sum;
                }
                bag = w[i];
                sum += w[i];
                i++;
                bagCnt++;
                continue;
            }
        }

        return sum;
    }
}




