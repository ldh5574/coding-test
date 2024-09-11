package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class boj17266 {

    public static long dap = 0;
    public static long n;
    public static int m;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());    // 굴다리 길이
        m = Integer.parseInt(br.readLine());    // 가로등 개수

        StringTokenizer st = new StringTokenizer(br.readLine());
        Integer stand[] = new Integer[m];
        for(int i=0; i<m; i++){
            stand[i] = Integer.parseInt(st.nextToken());
        }

        // Arrays.sort(arr);
        int height = 1;
        long left = 0;
        long right = n;
        long mid = n/2;


        while(left <= right){
            mid = (left + right) / 2;
            boolean canLight = binary_search(stand, mid);
            // System.out.println("L, R, mid, YN : " + left + ", " + right + ", " + mid + ", " + canLight);
            if(canLight){   // 전부 비출 수 있네..전등 높이가 너무 높나? (바이너리 좌측영역으로 이동)
                dap = mid;
                right = mid-1;
            }else{  // 전부 못비춰. 높이 더 높아야해 (바이너리 우측영역으로 이동)
                left = mid+1;
            }
        }

        if(m == 1){
            dap = n;
        }

        bw.write(String.valueOf(dap) + "\n");
        bw.flush();
        bw.close();
    }

    public static boolean binary_search(Integer stand[], long h){
        // stand 위치마다 h높이 전등을 세웠을때, N길이의 모든 영역을 비출 수 있나요?
        long leftPos = 0;
        for(int i=0; i<stand.length; i++){
            long leftLightOffset = stand[i]-h;
            if(leftLightOffset <= leftPos){    // 왼쪽 비추기 불가능
                leftPos = stand[i]+h;
            }else{
                return false;
            }
        }

        // 히든 테스트 케이스
        // 마지막 전등의 우측 offSet이, 길이n까지 못비춘다면
        if(leftPos < n){    // if(stand[m-1]+h < n){ 와 동일조건
            return false;
        }
        return true;
    }
}
