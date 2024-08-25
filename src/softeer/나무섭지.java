package softeer;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 나무섭지 {

    public static int dx[] = {-1, 0, 1, 0};
    public static int dy[] = {0, 1, 0, -1};

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

        int dap = 0;

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

        char map[][] = new char[n][m];
        Point nam = new Point();
        Point dest = new Point();
        ArrayList<Point> ghost = new ArrayList<>();

        for(int i=0; i<n; i++){
            String line = br.readLine();
            for(int j=0; j<m; j++){
                char c = line.charAt(j);
                // System.out.print(c);
                map[i][j] = c;

                if(c == 'N'){
                    nam.x = i; nam.y = j;
                }
                if(c == 'D'){
                    dest.x = i; dest.y = j;
                }
                if(c == 'G'){
                    ghost.add(new Point(i, j));
                }
            }
            // System.out.println();
        }

        int namCnt = bfs(map, nam, dest, false);
        // System.out.println("namCnt : " + namCnt);



        int ghostCnt = Integer.MAX_VALUE;
        // 유령 전체 다 bfs 돌리니 시간초과 발생 (이슈3)
//        for(int i=0; i<ghost.size(); i++){
//            int calCnt = bfs(map, ghost.get(i), dest, true);
//            // System.out.println("calCnt[" + i + "] : " + calCnt);
//            ghostCnt = Math.min(ghostCnt, calCnt);
//        }
        // x,y 좌표 계산해서 출구랑 가장 가까운 거리의 유령을 찾고 그녀석만 비교해주자
        int selectGhost = 0;
        int disGhostAndExit = Integer.MAX_VALUE;
        for(int i=0; i<ghost.size(); i++){
            int a = Math.abs(dest.x-ghost.get(i).x);
            int b = Math.abs(dest.y-ghost.get(i).y);
            if( disGhostAndExit > (a*a)+(b*b) ){
                disGhostAndExit = (a*a)+(b*b);
                selectGhost = i;
            }
        }
        // 이슈4 유령 없을때 예외처리 추가
        if(!ghost.isEmpty())
            ghostCnt = bfs(map, ghost.get(selectGhost), dest, true);
        else
            ghostCnt = Integer.MAX_VALUE;
        // System.out.println("ghostCnt[" + selectGhost + "] : " + ghostCnt);



        // 출력
        if(namCnt < ghostCnt && namCnt != 0){
            bw.write("Yes");
        }else{
            bw.write("No");
        }


        // Arrays.sort(arr[i], Collections.reverseOrder());
        // Arrays.sort(sortedArr);
        // int afterIndex = Arrays.asList(sortedArr).indexOf(arr[i][j]);
        // dap += Math.abs(afterIndex-j);
        // Arrays.sort(arr, (a,b) -> a[1] == b[1] ? (a[2]==b[2] ? a[3]-b[3] : a[2]-b[2]) : a[1]-b[1]);


        // bw.write(String.valueOf(arr[1][0]));
        // bw.write(String.valueOf( dap ) + "\n");
        bw.flush();
        bw.close();
    }

    public static int bfs(char map[][], Point mop, Point dest, boolean ghostFlag){

        boolean v[][] = new boolean[map.length][map[0].length];
        int dis[][] = new int[map.length][map[0].length];
        for(int i=0; i<map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                v[i][j] = false;
                dis[i][j] = 0;
            }
        }

        // 시작위치 방문표시하고 큐에 추가
        v[mop.x][mop.y] = true;
        Queue<Point> q = new LinkedList<>();
        q.add(mop);

        // 큐에서 하나씩 꺼내면서 너비탐색 시작
        while(!q.isEmpty()){
            Point cur = q.poll();

//            System.out.println();
//            for(int i=0; i<map.length; i++){
//                for(int j=0; j<map[0].length; j++){
//                    System.out.print(dis[i][j] + " ");
//                }
//                System.out.println();
//            }

            // 상하좌우로 이동해볼건데
            for(int i=0; i<4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= map.length || ny >= map[0].length){  // 맵에서 벗어나지 않고
                    continue;
                }
                // System.out.println("(" + nx + ", " + ny + ")" + " : " + dis[nx][ny]);
                if(v[nx][ny] == true){ // 기방문은 이동불가
                    continue;
                }
                if(map[nx][ny] == '#' && !ghostFlag){ // 사람은 벽 이동불가, 유령은 통과가능 (이슈1)
                    continue;
                }

                v[nx][ny] = true;
                dis[nx][ny] = dis[cur.x][cur.y]+1;
                if(dis[dest.x][dest.y] != 0){   // 목적지 발견시 바로 종료 (이슈2)
                    return dis[dest.x][dest.y];
                }
                q.add(new Point(nx, ny));
            }
        }
        return dis[dest.x][dest.y];
    }
}