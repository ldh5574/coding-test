package softeer;

import java.io.*;
import java.util.ArrayList;

public class 플레이페어암호 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String mStr = br.readLine();
        String kStr = br.readLine();

//        System.out.println((int)'A');   // 65
//        System.out.println((int)'a');   // 97

        // ##########################################
        // 1. 키 처리 시작
        // 문자열부터 앞에 넣기
        ArrayList<Character> keyList = new ArrayList<>();
        for(int i=0; i<kStr.length(); i++){
            char c = kStr.charAt(i);
            if(keyList.contains(c) == false){
                keyList.add(c);
            }
        }

        // 나머지 문자들 넣기 (J빼고)
        for(int i=((int)'A'); i<((int)'Z')+1; i++){
            char c = (char)i;
            if(keyList.contains(c) == false && c != 'J'){
                keyList.add(c);
            }
        }

        // 5개씩 짤라서 다시 저장
        char[][] keyMap = new char[5][5];
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++) {
                keyMap[i][j] = keyList.get( (5*i) + j );
            }
        }

        // 암호화 키 전체 출력
//        for(int i=0; i<5; i++){
//            for(int j=0; j<5; j++) {
//                System.out.print(keyMap[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println("키 출력 완료\n");


        // ##########################################
        // 2. 메세지 처리 시작
        // LL => LX + L?
        // XX => XQ
        // 한글자 남으면 ?X
        // String mStr = br.readLine();
        ArrayList<Character> mList = new ArrayList<>();
        for(int i=0; i<mStr.length(); i++){
            char c = mStr.charAt(i);
            mList.add(c);
        }

        // 두글자씩 나누기 (시간초과 발생)
//        for(int i=0; i<mList.size(); i++){
//            if(i % 2 == 0){
//                if(i == mList.size()-1){    // 끝에 하나만 남은 경우
//                    mList.add(i+1, 'X');
//                }
//                else{
//                    continue;
//                }
//            }
//            else{   // 짝수번째 체크 (i=1, 3, 5, ...)
//                char a = mList.get(i-1);
//                char b = mList.get(i);
//                if(a==b){
//                    if(a != 'X')
//                        mList.add(i, 'X');
//                    else{
//                        mList.add(i, 'Q');
//                    }
//                }
//            }
//        }

        // 두글자씩 나누기 (시간초과 해결)
        for(int i=0; i<mList.size()/2; i++){
            char a = mList.get(2*i);
            char b = mList.get(2*i + 1);

            // 두번째 문자 체크
            if(a==b){
                if(a != 'X')
                    mList.add(2*i+1, 'X');
                else{
                    mList.add(2*i+1, 'Q');
                }
            }
        }
        if(mList.size() % 2 == 1){  // 끝에 비면 강제추가
            mList.add('X');
        }


        // 암호화 메세지 전체 출력
//        for(int i=0; i<mList.size(); i++){
//            System.out.print(mList.get(i) );
//            if(i % 2 == 1){
//                System.out.print(" ");
//            }
//        }
//        System.out.println();


        // ##########################################
        // 3. 키와 메세지로 마지막 암호화 시작
        for(int i=0; i<mList.size()/2; i++){
            char c1 = mList.get((2*i));
            char c2 = mList.get((2*i) +1);

            // 두점 찾기
            int c1x = 0, c1y = 0, c2x = 0, c2y = 0;
            for(int x=0; x<5; x++){
                for(int y=0; y<5; y++) {
                    if(c1 == keyMap[x][y]){
                        c1x = x; c1y = y;
                    }
                    if(c2 == keyMap[x][y]){
                        c2x = x; c2y = y;
                    }
                }
            }

            // 3-1 같은행(x동일)이면 우측1칸이동(y+1),
            if(c1x == c2x){
                // System.out.println("\n11 : [" + c1x + "," + c1y + "] = " + keyMap[c1x][c1y] + keyMap[c2x][c2y]);
                c1y++; c2y++;
                if(c1y >= 5){ c1y -= 5;}
                if(c2y >= 5){ c2y -= 5;}
                mList.set(2*i, keyMap[c1x][c1y]);
                mList.set(2*i+1, keyMap[c2x][c2y]);
                // System.out.println("12 : [" + c1x + "," + c1y + "] = " + keyMap[c1x][c1y] + keyMap[c2x][c2y]);
            }
            else if(c1y == c2y){    // 3-2 같은열(y동일)이면 하단1칸이동(x+1)
                // System.out.println("21 : [" + c1x + "," + c1y + "] = " + keyMap[c1x][c1y] + keyMap[c2x][c2y]);
                c1x++; c2x++;
                if(c1x >= 5){ c1x -= 5;}
                if(c2x >= 5){ c2x -= 5;}
                mList.set(2*i, keyMap[c1x][c1y]);
                mList.set(2*i+1, keyMap[c2x][c2y]);
                // System.out.println("22 : [" + c1x + "," + c1y + "] = " + keyMap[c1x][c1y] + keyMap[c2x][c2y]);
            }
            else{   // 3-3 행 열 교환
                mList.set(2*i, keyMap[c1x][c2y]);
                mList.set(2*i+1, keyMap[c2x][c1y]);
            }

        }

        // 암호화 메세지 전체 최종 출력
        // System.out.println();
        for(int i=0; i<mList.size(); i++){
            // System.out.print(mList.get(i) );
            bw.write(String.valueOf(mList.get(i)));
        }
        // bw.newLine();
        // bw.write(String.valueOf(mStr) + "\n");
        bw.flush();
        bw.close();
    }
}




