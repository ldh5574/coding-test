package codility;

import java.io.*;
import java.util.StringTokenizer;

public class RoundRobinTotalWaitTime20260328 {

    public static void main(String[] args) throws IOException{

        // 라운드로빈 대기시간 합 문제
        // RoundRobin : 공평하게 정해진 시간만큼 번갈아가며 처리하는 알고리즘

        // 3, 1, 2 => 정답은 13
//        시간 1: 0번 (3→2)
//        시간 2: 1번 (1→0) → 완료 (2)
//        시간 3: 2번 (2→1)
//        시간 4: 0번 (2→1)
//        시간 5: 2번 (1→0) → 완료 (5)
//        시간 6: 0번 (1→0) → 완료 (6)

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[] task = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            task[i] = Integer.parseInt(st.nextToken());
        }

        int dap = 0;
        // i번째 위치한 작업이, 언제 종료되는지를 계산
        for(int i=0; i<n; i++){
            // 본인 포함하여, 완료 직전까지, 한바퀴씩 돌면서, 방해받는 최소 기준 시간
            int disturbTime = task[i]-1;

            // i번째 작업이, 모든 Task들로부터 방해받는 시간들 계산
            int commonDisturbTime = 0;
            for(int j=0; j<n; j++){
                commonDisturbTime+= Math.min(task[j], disturbTime);
            }

            // i번째 작업이, 본인보다 앞에 있고 & 크거나 같은 작업들로부터, 방해받는 시간들 계산
            int frontDisturbTime = 0;
            for(int j=0; j<i; j++){
                if(task[j] >= task[i]){
                    frontDisturbTime++;
                }
            }

            // i번째 작업이, 방해받아왔던 시간들 + 본인 작업 마무리 1 더해주기
            int totalDisturbTime = commonDisturbTime + frontDisturbTime + 1;
            dap += totalDisturbTime;
        }

        // Test Case Example : 3,1,2
        // 3,1,2에서 첫번째 작업 3은, (하나작은 2기준으로 min했을때 2,1,2 총 다섯번 방해받음) + (3이 제일 앞에 위치해서 앞쪽 방해 없음) + 본인마무리한번 = 5+0+1 = 6걸림.
        // 3,1,2에서 두번째 작업 1은, (하나작은 0기준으로 min했을때 0,0,0 방해 없이 바로 끝남) + (1보다 앞에 큰건 3 한개임) + 본인마무리한번 = 0+1+1 = 2걸림.
        // 3,1,2에서 세번째 작업 2는, (하나작은 1기준으로 min했을때 1,1,1 총 세번 방해받음) + (2보다 앞에 큰건 3 한개임) + 본인마무리한번 = 3+1+1 = 5걸림.
        // 총 걸리는 시간은 6+2+5 = 13

        bw.write(String.valueOf(dap));
        bw.flush();
        bw.close();
    }
}
