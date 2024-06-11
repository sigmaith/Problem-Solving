// #23349 졸업 사진
import java.io.*;
import java.util.*;

class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static class PlaceTime implements Comparable<PlaceTime>{
        String place;
        int start;
        
        PlaceTime(String place, int start){
            this.place = place;
            this.start = start;
        }

        @Override
        public int compareTo(PlaceTime o){
            if (!this.place.equals(o.place)){
                return this.place.compareTo(o.place);
            }
            return Integer.compare(this.start, o.start);
        }
    }

    public static void main(String[] args) throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        Map<String, int[]> map = new HashMap<>();  // 각 장소마다 사용시간별로 인원 기록
        Set<String> nameSet = new HashSet<>();  // 중복 없는 이름 데이터셋
        int max = -1; // 최대 사용인원 기록

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String place = st.nextToken();
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if (nameSet.contains(name)) continue;
            nameSet.add(name);

            int[] counts = map.computeIfAbsent(place, k -> new int[50001]);  // key(place)로 시간배열 찾기, 없으면 초기화(람다식)
            for(int idx=start; idx<end; idx++){
                counts[idx]++; // 시작시간부터 끝나는 시간까지 이용인원 업데이트 (+1)
                max = Math.max(max, counts[idx]); // max 이용인원 업데이트
            }             
        }

        List<PlaceTime> haveMax_List = new ArrayList<>(); // map에서 max값을 가지는 place 털어내기
        for (String place : map.keySet()){ // place 묶음으로 반복문
            int[] counts = map.get(place); // place에 해당하는 시간별 인원 배열
            for (int i=0; i < counts.length; i++){
                if(counts[i] == max){ // max면 haveMax_List에 추가
                    haveMax_List.add(new PlaceTime(place, i)); // 생성자 이용
                    break;
                }
            }
        }

        Collections.sort(haveMax_List); // 위에서 오버라이딩한 comparable, compareTo가 적용되어 정렬됨
        PlaceTime now_Place = haveMax_List.get(0);
        int end = findEnd(map.get(now_Place.place), now_Place.start, max);
        bw.write(now_Place.place + " " + now_Place.start + " " + end + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    private static int findEnd(int[] counts, int start, int max){
        int end = start;
        while (end < counts.length && counts[end] == max){
            end++;
        }
        return end;
    }
}
