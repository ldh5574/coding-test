package etc;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FractionalKanpsackProblem {

    public static float dap = 0;
    public static int n, k, m;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // Knapsack(배낭) 알고리즘 - 그리디

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());    // 인원수
        k = Integer.parseInt(st.nextToken());    // 보물 개수
        m = Integer.parseInt(st.nextToken());    // 가방 크기

        Float goldList[][] = new Float[k][4];            // 보물(무게, 가치, 단가)
        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            goldList[i][0] = Float.parseFloat(st.nextToken());
            goldList[i][1] = Float.parseFloat(st.nextToken());
            goldList[i][2] = goldList[i][1]/goldList[i][0];
        }

        // 단가로 내림차순 정렬 (int로 정렬시 정밀도 손실 발생)
        // Arrays.sort(goldList, (a,b) -> (int) (a[2]==b[2] ? b[0]-a[0] : b[2]-a[2]));
        Arrays.sort(goldList, (a,b) -> {    // 단가 높은순 + 무게 무거운순
            return Float.compare(b[2], a[2])==0 ? Float.compare(b[0], a[0]) : Float.compare(b[2], a[2]);
        });
//        for(int i=0; i<k; i++){
//            System.out.println("무게,가치,단가: " + goldList[i][0] + ", " + goldList[i][1]+", " + goldList[i][2]);
//        }

        int curBagSize = 0;
        for(int i=0; i<n; i++){                 // 한명씩 보물을 담아보자
            curBagSize = 0;                     // 가방에 담긴 무게를 일단 초기화해주고

            for(int j=0; j<k; j++){             // 보물들을 둘러보자
                float weight = goldList[j][0];
                if(weight <= 0){                // 사용완료 보물에 대한 처리
                    continue;
                }

                float value = goldList[j][1];
                float perValue = goldList[j][2];
                if(m-curBagSize > 0){           // 가방에 빈 공간이 있는 경우
                    float canWeigh = Math.min( m-curBagSize, weight );
                    curBagSize += canWeigh;     // 담을 수 있는 만큼 담고
                    dap += canWeigh * perValue; // 가치 더해주고
                    goldList[j][0] -= canWeigh; // 해당 보물 사용처리
                }
//                System.out.println("인원,누적보물,누적가치: " + (i+1) + ", " +curBagSize+"/"+ m + ", "+weight+", "+dap);
                if(curBagSize == m){            // 가방이 꽉찼으면 조기종료
                    break;
                }
            }
        }

        dap = (float) (Math.round(dap * 100) / 100.0);
        bw.write(String.valueOf(dap) + "\n");
        bw.flush();
        bw.close();
    }


}
