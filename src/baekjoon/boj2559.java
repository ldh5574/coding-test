package baekjoon;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.io.*;
import java.util.StringTokenizer;

public class boj2559 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int dap = Integer.MIN_VALUE;

        // StringTokenizer st = new StringTokenizer(bf.readLine());
        // String s = bf.readLine();
        // Integer n = Integer.parseInt(bf.readLine());
        // Integer n = Integer.parseInt(st.nextToken());
        // Integer k = Integer.parseInt(st.nextToken());
        // String a = bf.readLine();
        // String b = bf.readLine();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int arr[] = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<n+1; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 12345 됐을때부터 dap 체크하고
        // 12345 에서 1 빼고 6 더해서 23456 합 max랑비교
        // 23456 에서 2 빼고 7 더해서 34567 합 max랑비교

        int sum = 0;
        for(int i=1; i<n+1; i++){
            int x = arr[i];
            sum += x;
            if(i==k){
                dap = sum;
            }
            if(i>=k+1){
                int leftSideNum = arr[i - k];
                sum -= leftSideNum;
                dap = Math.max(dap, sum);
            }
        }

        // Arrays.sort(arr[i], Collections.reverseOrder());
        // Arrays.sort(sortedArr);
        // int afterIndex = Arrays.asList(sortedArr).indexOf(arr[i][j]);
        // dap += Math.abs(afterIndex-j);
        // Arrays.sort(arr, (a,b) -> a[1] == b[1] ? (a[2]==b[2] ? a[3]-b[3] : a[2]-b[2]) : a[1]-b[1]);


        // bw.write(String.valueOf(arr[1][0]));
        bw.write(String.valueOf( dap ) + "\n");
        bw.flush();
        bw.close();
    }
}