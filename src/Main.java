import java.io.*;
import java.util.*;

public class Main {

    public static int n, m, k;
    public static int line[];
    public static int v[];
    public static int dap = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        line = new int[n];
        v = new int[n];
        for(int i=0; i<n; i++){
            int kg = Integer.parseInt(st.nextToken());
            line[i] = kg;
        }

        dfs(new int[n], 0);

        // bw.newLine();
        bw.write(String.valueOf(dap) + "\n");
        bw.flush();
        bw.close();
    }

    // 첫번째 미션 : 레일 순서 정하기
    static void dfs(int[] railOrder, int count) {

//        for(int i=0; i<railOrder.length; i++){
//            System.out.println(i + " " + railOrder[i] + " " + count);
//        }
//        System.out.println();

        if(count == n) {    // railOrder 가 꽉 채워져서 n개의 레일의 순서가 모두 정해졌다면
            int w = calWeight(railOrder);   // 바구니제한m과 k횟수로 최소무게 구하러 가자
            dap = Math.min(w, dap);
            return;
        }

        for(int i = 0; i < n; i++) {
            if(v[i] == 0) {
                v[i] = 1;
                railOrder[count] = line[i];
                dfs(railOrder, count+1);
                v[i] = 0;
            }
        }
    }

    // 두번째 미션 : 정해진 레일 순서에 대해서, 바구니제한m과 k횟수로 최소무게 합 구하기
    static int calWeight(int[] railOrder) {
        int sum = 0;
        int railNum = 0;
        for(int i = 0; i < k; i++) {    // k번 시행횟수 안에서
            int checkWeight = 0;        // 일회당 옮겨질 무게 변수 초기화해주고
            while(checkWeight + railOrder[railNum] <= m) {  // 무게제한m보다 작으면
                checkWeight += railOrder[railNum];          // 더해주고
                // railNum = (railNum + 1 == n) ? 0 : railNum+1;
                if(railNum+1 == n){     // 현재 레일이 마지막이었다면 (=다음레일이 없다면)
                    railNum = 0;        // 첫번째 레일로 다시 돌아가기 위해 변수 초기화
                }else{                  // 현재 레일이 중간 레일이라면
                    railNum++;          // 다음 레일로 가기 위한 변수 +1
                }
            }
            // 1회 안에 바구니에 담을 수 있는 무게 전부 담았다면 sum에 더해주자
            sum += checkWeight;
        }
        return sum;
    }
}




