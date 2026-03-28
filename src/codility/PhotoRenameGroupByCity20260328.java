package codility;

import java.io.*;
import java.util.*;

/*
 * [문제] 사진 이름 수정 (Photo Rename by City)
 *
 * 입력 : n개의 줄, 각 줄은 "파일명, 도시, 촬영시간" 형식
 *        예) mountain.jpg, Seoul, 2020-01-01 00:00:02
 *
 * 출력 : 원래 입력 순서를 유지하면서, 각 사진의 이름을 아래 규칙으로 변환
 *        → 도시이름 + 도시 내 시간순 번호(자릿수 맞춤) + 확장자
 *        예) Seoul1.jpg → Seoul01.jpg (도시 내 사진이 10장 이상이면 2자리)
 *
 * 전략 : 도시별로 인덱스를 그룹화(HashMap) → 도시 내 시간순 정렬 → 번호 부여 → 원래 순서로 출력
 */
public class PhotoRenameGroupByCity20260328 {

    public static void main(String[] args) throws IOException{

        int n;
        String dap = "";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 멀티라인 입력 전체 읽기
        StringBuilder sb = new StringBuilder();
        String line;

        while((line=br.readLine()) != null && !line.isEmpty()){
            sb.append(line).append("\n");
        }
        String[] lines = sb.toString().trim().split("\n");
        n = lines.length;

        // 각 사진의 파일명, 확장자, 도시, 시간을 분리해서 배열에 저장
        // 입력 예시 : mountain.jpg, Seoul, 2020-01-01 00:00:02
        String[] photos = new String[n];
        String[] exts = new String[n];
        String[] cities = new String[n];
        String[] times = new String[n];

        // cityMap : 도시명 → 해당 도시 사진들의 원본 인덱스 리스트
        // 원본 순서(인덱스)를 보존해야 하므로, 사진 자체가 아닌 인덱스를 저장한다
        Map<String, List<Integer>> cityMap = new HashMap<>();
        for(int i=0; i<n; i++){
            String[] parts = lines[i].split(", ");
            photos[i] = parts[0].toString();
            exts[i] = parts[0].toString().substring(parts[0].toString().lastIndexOf(".")+1);
            String city = parts[1].toString();
            cities[i] = city;
            times[i] = parts[2].toString();
            // System.out.println("photo: " + photo + ", ext: " + ext + ", city: " + city + ", time: " + time);

            if(!cityMap.containsKey(city)){
                cityMap.put(city, new ArrayList<Integer>());
            }
            cityMap.get(city).add(i);
        }
//        City: Warsaw
//            Index: 0, Line: photo.jpg, Warsaw, 2013-09-05 14:08:15
//            Index: 2, Line: myFriends.png, Warsaw, 2013-09-05 14:07:13
//            Index: 3, Line: sunset.jpg, Warsaw, 2013-09-05 14:10:00
//        City: Seoul
//            Index: 5, Line: lake.png, Seoul, 2020-01-01 00:00:01
//            Index: 6, Line: mountain.jpg, Seoul, 2020-01-01 00:00:02
//            Index: 7, Line: a.jpg, Seoul, 2020-01-01 00:00:02
//            Index: 8, Line: b.jpg, Seoul, 2020-01-01 00:00:02
//        City: London
//            Index: 1, Line: john.png, London, 2015-06-20 15:13:22
//            Index: 4, Line: tree.jpeg, London, 2015-06-20 15:13:20




        // 각 도시별로 사진 인덱스 리스트를 시간순으로 정렬한 다음, 번호를 붙여서 결과 배열에 저장한다. (2자릿수로 강제 번호 생성)
//        String[] result = new String[n];
//        for(String city : cityMap.keySet()){
//            List<Integer> list = cityMap.get(city);
//            Collections.sort(list, (a,b) -> times[a].compareTo(times[b])); // 같은 도시 내에서, 사진을 시간순으로 정렬
//
//            int cityPhotoCount = list.size();
//            for(int i=0; i<cityPhotoCount; i++){
//                int photoIndex = list.get(i);
//                String printIndex = String.format("%02d", i+1); // 2자리로 맞춰서 번호 생성 (01, 02, ...)
//                // 출력 포맷 : 도시이름 + 번호 + 확장자
//                result[photoIndex] = city + printIndex + "." + exts[photoIndex];
//            }
//        }

        // 각 도시별로 사진 인덱스 리스트를 시간순으로 정렬한 다음, 번호를 붙여서 결과 배열에 저장한다. (유동적인 번호 생성)
        String[] result = new String[n];
        for(String city : cityMap.keySet()){
            List<Integer> list = cityMap.get(city);
            Collections.sort(list, (a,b) -> times[a].compareTo(times[b])); // 같은 도시 내에서, 사진을 시간순으로 정렬

            int cityPhotoCount = list.size();
            int digit = String.valueOf(cityPhotoCount).length(); // 10장이면 2자릿수, 100장이면 3자릿수, ...
            for(int i=0; i<cityPhotoCount; i++){
                int photoIndex = list.get(i);
                String printIndex = String.format("%0" + digit + "d", i+1); // 2자리로 맞춰서 번호 생성 (01, 02, ...)
                // 출력 포맷 : 도시이름 + 번호 + 확장자
                result[photoIndex] = city + printIndex + "." + exts[photoIndex];
            }
        }

        dap = String.join("\n", result);

        bw.write(dap);
        bw.flush();
        bw.close();
    }
}
