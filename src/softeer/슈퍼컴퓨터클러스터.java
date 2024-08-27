package softeer;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 슈퍼컴퓨터클러스터 {

    public static long dap = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int arr[] = new int[n];
        for(int i=0; i<n; i++){
            int a = Integer.parseInt(st.nextToken());
            arr[i] = a;
        }
        // Arrays.sort(arr, Collections.reverseOrder());
        Arrays.sort(arr);
//        for(int i=0; i<n; i++){
//            System.out.print(arr[i] + " ");
//        }
//        System.out.println();

        // 이진탐색 시작
        long left = arr[0]; // 최소값으로 초기화
        long right = arr[n - 1] + (long)Math.sqrt(b); // 최대값 + B원으로 업그레이 할 수 있는 최대 성능
        while(left <= right){
            long mid = (left + right) / 2;
            // System.out.println("L M R : " + left+ " " + mid + " " + right);

            long cost = 0;
            boolean upgradeYn = true;

            // 대충 중간쯤 숫자 정해서
            // 해당 숫자를 기준으로 버전 업그레이드 해보자~
            for(int i : arr){
                if(i < mid){    // 업그레이드 기준 버전보다 작으면
                    cost += ((mid-i)*(mid-i));  // 업그레이드해주고
                    if(cost > b){
                        upgradeYn = false;  // 해당버전까지 업그레이드 불가능
                        break;
                    }
                }
            }

            if(upgradeYn){  // 업그레이드 가능했다면, 비용이 남는거고, 더 높은 버전을 찾기위해, 반 짤라서 우측영역을 체크해보자
                left = mid+1;
                dap = mid; // mid까지는 업그레이드가 가능했음
            }else{          // 업그레이드 불가능했다면, 비용 부족하니, 낮은 버전을 찾기위해, 반 짤라서 좌측영역을 체크해보자
                right = mid-1;
            }
        }

        bw.write(String.valueOf(dap) + "\n");
        bw.flush();
        bw.close();
        return;
    }

}




