package softeer;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 업무처리 {

    public static long dap = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());   // 조직도높이
        int k = Integer.parseInt(st.nextToken());   // 말단대기업무개수
        int r = Integer.parseInt(st.nextToken());   // 업무진행날짜

        int totalNodeCount = (1<<h+1) - 1;  // 전체노드개수 (h=2일때 2^3-1 = 7개)
        int leafNodeCount = (1<<h);         // 말단노드개수 (h=2일때 2^2 = 4개)
        // System.out.println(totalNodeCount + " " + leafNodeCount);

        // 전체노드개수만큼 큐 생성(좌측 우측 나눠서)
        Queue<Integer>[][] q = new Queue[totalNodeCount][2];
        for(int i=0; i<q.length; i++){
            for(int j=0; j<q[0].length; j++){
                q[i][j] = new LinkedList<Integer>();
            }
        }

        // 제일 왼쪽 말단노드부터 ~제일 오른쪽 말단노드까지 돌면서 (h=2일때 인덱스는 3,4,5,6 돌면서)
        for(int i=totalNodeCount-leafNodeCount; i<totalNodeCount; i++){
            // System.out.println("i : " + i);
            // 말단대기업무개수 전부 넣어주기
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<k; j++){
                q[i][0].add(Integer.parseInt(st.nextToken()));  // 말단직원은 무조건 왼쪽에 넣어주기..!
            }
        }
        // 세팅 끝


        // ##### 일 시작 #####
        dap = 0;
        // 1일~r일간, 부서장/팀원/막내 분기처리해서 일 시킬건데
        for(int day=1; day<=r; day++){

            // 전체 노드를 돌면서 전직원 일시킬건데
            for(int i=0; i<totalNodeCount; i++){

                // System.out.println("["+day+"]"+" i: " + i + ", left/right = " + q[i][0].peek() + " " + q[i][1].peek());

                // 부서장이면, 홀수일엔 왼쪽 일을, 짝수일에는 오른쪽 일을, 처리해서 완료한다.
                if(i == 0){
                    if(day%2 == 1){     // 홀수일이니 왼쪽 부하 직원이 올린 업무만 처리해주자
                        if(!q[i][0].isEmpty()) {     // 왼쪽 부하 직원이 올린 일감이 있으면
                            int leftWork = q[i][0].remove();
                            dap += leftWork;         // 완료처리
                            // System.out.println("dap += " + leftWork);
                        }
                    }else{              // 짝수일이니 오른쪽 부하 직원이 올린 업무만 처리해주자
                        if(!q[i][1].isEmpty()) {
                            int rightWork = q[i][1].remove();
                            dap += rightWork;
                            // System.out.println("dap += " + rightWork);
                        }
                    }
                }
                // 중간 팀원이면
                else if( 0 < i && i < totalNodeCount-leafNodeCount){
                    int parent = (i-1)/2;           // 부모인덱스 찾아놓고
                    if(day%2 == 1) {                // 홀수일이니 왼쪽 부하 직원이 올린 업무만 처리해주자
                        if(!q[i][0].isEmpty()) {    // 왼쪽 부하 직원이 올린 일감이 있으면
                            int leftChildWork = q[i][0].remove();
                            if(i%2 == 1){               // 저는 왼쪽 자식입니다 홀수일에 처리하실 업무 드려요~ 하고 [0]에 넣어주기
                                q[parent][0].add(leftChildWork);
                            }else{                      // 저는 오른쪽 자식입니다 짝수일에 처리하실 업무 드려요~ 하고 [1]에 넣어주기
                                q[parent][1].add(leftChildWork);
                            }
                        }
                    }
                    else{                           // 짝수일이니 오른쪽 부하 직원이 올린 업무만 처리해주자
                        if(!q[i][1].isEmpty()) {
                            int rightChildWork = q[i][1].remove();
                            if(i%2 == 1){               // 저는 왼쪽 자식입니다 홀수일에 처리하실 업무 드려요~ 하고 [0]에 넣어주기
                                q[parent][0].add(rightChildWork);
                            }else{                      // 저는 오른쪽 자식입니다 짝수일에 처리하실 업무 드려요~ 하고 [1]에 넣어주기
                                q[parent][1].add(rightChildWork);
                            }
                        }
                    }
                }
                // 막내면
                else{
                    int parent = (i-1)/2;           // 부모인덱스 찾아놓고

                    // 꺼내오는건 날짜 상관없이 [i][0]이 비어있지 않으면 상사한테 넣어주기
                    if(!q[i][0].isEmpty()) {        // 말단은 무조건 왼쪽 [i][0] 에서 꺼내옴
                        int leafWork = q[i][0].remove();
                        if(i%2 == 1){               // 저는 왼쪽 자식입니다 홀수일에 처리하실 업무 드려요~ 하고 [0]에 넣어주기
                            q[parent][0].add(leafWork);
                        }else{                      // 저는 오른쪽 자식입니다 짝수일에 처리하실 업무 드려요~ 하고 [1]에 넣어주기
                            q[parent][1].add(leafWork);
                        }
                    }
                }
            }
        }

        bw.write(String.valueOf(dap));
        bw.flush();
        bw.close();
        return;
    }

}




