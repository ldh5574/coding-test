package etc;

import java.io.*;
import java.util.StringTokenizer;

public class MultipleKanpsackProblem {

    public static int dap = 0;
    public static int n, k, m;
    public static Integer[][] goldList;
    public static boolean[] used;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 0-1 MKP : 0-1 Multiple Knapsack Problem (0-1 다중 배낭 문제, 담거나 말거나라서 0-1)
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // 인원수
        k = Integer.parseInt(st.nextToken());   // 보물 개수
        m = Integer.parseInt(st.nextToken());   // 가방 크기

        goldList = new Integer[k][2];           // 보물(무게, 가치)
        used = new boolean[k];                  // 보물 사용 여부 배열
        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            goldList[i][0] = Integer.parseInt(st.nextToken());
            goldList[i][1] = Integer.parseInt(st.nextToken());
            used[i] = false;
        }

        // DFS를 통해 모든 보물 조합 탐색
        int goldIndex = 0;
        int[] bagCapacity = new int[n];
        dfs(goldIndex, bagCapacity, 0);

        bw.write(String.valueOf(dap) + "\n");
        bw.flush();
        bw.close();
    }

    // DFS 탐색 함수
    public static void dfs(int goldIndex, int[] bagCapacity, int sum) {
        // 모든 보물에 대해 탐색 완료한 경우
        if (goldIndex==k) {
            // 모든 신입사원의 가방에 담긴 보물의 총 가치를 계산
            dap = Math.max(dap, sum);
            return;
        }

        // 현재(goldIndex번째) 보물을 사용할 경우, 각 사람마다 goldWeight를 더해줘보며 최대값을 찾는다.
        for (int person=0; person<n; person++) {
            int goldWeight = goldList[goldIndex][0];
            int goldValue = goldList[goldIndex][1];
            int tobeWeight = bagCapacity[person]+goldWeight;
            if (!used[goldIndex] && tobeWeight <= m) {                      // 미사용 && 배낭무게를 넘지않는다면
                used[goldIndex] = true;                                     // 보물 사용처리
                bagCapacity[person] += goldWeight;                          // 현재 신입사원의 가방 용량 업데이트
                dfs(goldIndex+1, bagCapacity, sum+goldValue); // 다음 보물로 이동
                bagCapacity[person] -= goldWeight;                          // 백트래킹: 현재 신입사원의 가방 용량 되돌리기
                used[goldIndex] = false;                                    // 백트래킹: 보물 사용 상태 되돌리기
            }
        }

        // 현재(goldIndex번째) 보물을 사용하지 않는 경우, 아무 처리하지 않고 그냥 다음 보물로 이동한다.
        dfs(goldIndex+1, bagCapacity, sum);
    }
}
