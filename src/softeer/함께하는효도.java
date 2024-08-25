package softeer;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 함께하는효도 {

    public static int dap = 0;
    public static int dx[] = {0, 0, -1, 1};
    public static int dy[] = {-1, 1, 0, 0};

    public static class Point{
        int x;
        int y;

        public Point() {
        }

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));



        // StringTokenizer st = new StringTokenizer(bf.readLine());
        // String s = bf.readLine();
        // Integer n = Integer.parseInt(bf.readLine());
        // Integer n = Integer.parseInt(st.nextToken());
        // Integer k = Integer.parseInt(st.nextToken());
        // String a = bf.readLine();
        // String b = bf.readLine();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int map[][] = new int[n+1][n+1];
        int v[][] = new int[n+1][n+1];
        for(int i=1; i<n+1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<n+1; j++){
                int k = Integer.parseInt(st.nextToken());
                map[i][j] = k;
                v[i][j] = 0;
            }
        }

        ArrayList<Point> workerList = new ArrayList<>();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int inputX = Integer.parseInt(st.nextToken());
            int inputY = Integer.parseInt(st.nextToken());
            workerList.add(new Point(inputX, inputY));
        }

//        System.out.println(map[1][2]);
//        System.out.println(map[2][3]);

        int firstGold = map[workerList.get(0).x][workerList.get(0).y];
        map[workerList.get(0).x][workerList.get(0).y] = 0;
        v[workerList.get(0).x][workerList.get(0).y] = 1;
        dfs(map, v, workerList, 0, firstGold, new Point(workerList.get(0).x, workerList.get(0).y), 0);

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

    public static void dfs(int[][] map, int[][] v, ArrayList<Point> workerList, int workerId, int sum, Point cur, int depth){

        dap = Math.max(dap, sum);

//        System.out.println();
//        for(int i=1; i< map.length; i++){
//            for(int j=1; j<map[0].length; j++){
//                if(i==cur.x && j==cur.y){
//                    System.out.print(sum + " ");
//                }
//                else{
//                    System.out.print("- ");
//                }
//            }
//            System.out.print(depth);
//            System.out.println();
//        }

        if(depth == 3){
            // System.out.println();
            if(workerId+1 < workerList.size()){
                v[workerList.get(workerId+1).x][workerList.get(workerId+1).y] = 1;
                int nextGold = map[workerList.get(workerId+1).x][workerList.get(workerId+1).y];
                dfs(map, v, workerList, workerId+1, sum+nextGold, new Point(workerList.get(workerId+1).x, workerList.get(workerId+1).y), 0);
            }
            return;
        }

        for(int i=0; i<4; i++){
            int nx = cur.x + dx[i];
            int ny = cur.y + dy[i];

            if( nx < 1 || ny < 1 || nx >= map.length || ny >= map[0].length ){
                continue;   // 맵 벗어난 경우
            }
            if(v[nx][ny] != 0){
                continue;
            }

            int nextGold = map[nx][ny];
            v[nx][ny] = 1;
            dfs(map, v, workerList, workerId, sum+nextGold, new Point(nx, ny),depth+1);
            v[nx][ny] = 0;
        }
    }

}