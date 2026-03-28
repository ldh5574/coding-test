package codility;

import java.io.*;
import java.util.StringTokenizer;

public class SmallestNumberWithGivenDigitSum20260328 {

    public static String dap;
    public static int n;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // 각 자릿수의 합이 되는 수

        //  그리디 알고리즘 (Greedy Algorithm) : 매 순간마다 가장 최선의 선택을 하는 방법
        // 자릿수 합을 빠르게 채우기 위해 9를 최대한 사용하고,
        // 숫자를 최소로 만들기 위해 나머지를 가장 앞자리에 배치하는 Greedy 알고리즘으로 해결한다.

        if(n == 0){ // 0이면 답은 0
            dap = "0";
        }else if(n < 10){ // 10 미만의 숫자면 무조건 자기 자신이 답
            dap = String.valueOf(n);
        }else{
            int mok = n / 9;    // 몫
            int rest = n % 9;

            StringBuilder sb = new StringBuilder();
            if(rest != 0){  // 히든테케 : 나머지가 0이 아닐때만 맨 앞에 추가하기
                sb.append(rest);
            }
            if(mok != 0){
                for(int i=0; i<mok; i++){
                    sb.append(9);
                }
            }
            dap = sb.toString();
        }

        bw.write(String.valueOf(dap) + "\n");
        bw.flush();
        bw.close();
    }
}
