package etc;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KnapsackProblem {

    public static double dap = 0;
    public static int n, k, m;
    public static Integer[][] goldList;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 0-1 KP : 0-1 Knapsack Problem (0-1 배낭 문제, 담거나 말거나라서 0-1)
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // 인원수
        k = Integer.parseInt(st.nextToken());   // 보물 개수
        m = Integer.parseInt(st.nextToken());   // 가방 크기

        Double goldList[][] = new Double[k][3];  // 보물(무게, 가치, 단가)
        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            goldList[i][0] = Double.parseDouble(st.nextToken());
            goldList[i][1] = Double.parseDouble(st.nextToken());
            goldList[i][2] = goldList[i][1]/goldList[i][0];
        }

        // 단가로 내림차순 정렬
        // Arrays.sort(goldList, (a,b) -> (int) (a[2]==b[2] ? b[0]-a[0] : b[2]-a[2])); // int로 정렬시 정밀도 손실 발생
        Arrays.sort(goldList, (a,b) -> {        // 단가 높은순 + 무게 무거운순
            return Double.compare(b[2], a[2])==0 ? Double.compare(b[0], a[0]) : Double.compare(b[2], a[2]);
        });
        /*
        for(int i=0; i<k; i++){
            for(int j=0; j<3; j++){
                System.out.print(goldList[i][j] + " ");
            }
            System.out.println();
        }
        */

        int curBagSize = 0;
        for(int i=0; i<n; i++){                 // 한명씩 보물을 담아보자
            curBagSize = 0;                     // 배낭에 담긴 무게를 일단 초기화해주고

            for(int j=0; j<k; j++){             // 보물들을 둘러보자
                double weight = goldList[j][0];
                if(weight <= 0){                // 사용완료 보물에 대한 처리
                    continue;
                }

                double value = goldList[j][1];
                if(m-curBagSize >= weight){     // 배낭에 빈 공간이 > 보물보다 크면, 담을 수 있으니
                    curBagSize += weight;       // 해당 보물 통째로 담고
                    dap += value;               // 해당 보물 통째로 가치 더해주고
                    goldList[j][0] -= weight;   // 해당 보물 통째로 사용처리
                }
                if(curBagSize == m){            // 배낭이 꽉찼으면 조기종료
                    break;
                }
            }
        }

        bw.write(String.valueOf(dap) + "\n");
        bw.flush();
        bw.close();
    }
}
